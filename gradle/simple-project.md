---
title: Simple Multi-Project Gradle Project

---

# Simple Multi-Project Gradle Project

Build layout
```groovy
project/
  settings.gradle
  app/
    build.gradle
  component/
    build.gradle
```

settings.gradle
```groovy
include ':app', ':component'
```

app/build.gradle
```groovy
dependencies {
    implementation project(':component')
}
```