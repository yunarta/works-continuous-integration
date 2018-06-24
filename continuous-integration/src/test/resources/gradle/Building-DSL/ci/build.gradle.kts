plugins {
    id("com.mobilesolutionworks.ci.core")
}

worksSchematic {

    jenkins {
        agent = "android"
    }
}