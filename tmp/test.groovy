buildCount = env.DEFAULT_HISTORY_COUNT ?: "5"

pipeline {
    agent {
        node {
            label 'java'
        }
    }

    options {
        buildDiscarder(logRotator(numToKeepStr: buildCount))
        disableConcurrentBuilds()
    }
    stages {
        stage('Select') {
            parallel {
                stage('Checkout') {
                    when { expression { notIntegration() } } steps { checkout scm; seedReset() }
                } stage('Integrate') {
                    when { expression { isIntegration() } } steps {
                        echo "Execute integration"; stopUnless(isStartedBy("upstream"))
                    }
                }
            }
        }

        stage("Coverage, Analyze and Test") {
            when {
                expression {
                    notIntegration() && notRelease()
                }
            }

            options {
                retry(2)
            }

            steps {
                seedGrow("test")

                echo "Build for test and analyze"
                sh """echo "Execute test"
./gradlew cleanTest test worksGatherReport -PignoreFailures=${
                    seedEval("test", [1: "true", "else": "false"])
                }"""
            }
        }

        stage("Publish CAT") {
            when {
                expression {
                    notIntegration() && notRelease()
                }
            }

            steps {
                echo "Publishing test and analyze result"

                jacoco execPattern: 'build/reports/jacoco/exec/root/*.exec', classPattern: 'plugin/build/classes/kotlin/main', sourcePattern: ''
                junit allowEmptyResults: true, testResults: 'build/reports/junit/xml/**/*.xml'
//                checkstyle canComputeNew: false, defaultEncoding: '', healthy: '', pattern: 'plugin/build/reports/detekt/detekt-report.xml', unHealthy: ''

//                codeCoverage()
            }
        }
    }
}
