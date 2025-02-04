pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FifthSemWork"
include(":app")

include(":core")
include(":core:db")
include(":core:network")
include(":core:utils")
include(":core:viewmodel")
include(":core:designsystem")

include(":feature")
include(":feature:feature1")
include(":feature:feature1:api")
include(":feature:feature1:impl")
include(":feature:feature2")
include(":feature:feature2:api")
include(":feature:feature2:impl")
include(":feature:feature3")
include(":feature:feature3:api")
include(":feature:feature3:impl")
include(":feature:feature4")
include(":feature:feature4:api")
include(":feature:feature4:impl")
