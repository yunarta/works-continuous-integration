package com.mobilesolutionworks.ci.core.model

enum class AutomationPlatform {
    Jenkins
    // VisualStudioTeamService,
    // Travis
}

sealed class AutomationPlatformService {

}

class Jenkins : AutomationPlatformService() {

}