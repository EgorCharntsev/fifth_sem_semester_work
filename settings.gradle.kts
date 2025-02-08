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
include(":core:common")
include(":core:db")
include(":core:designsystem")
include(":core:network")
include(":core:utils")


include(":feature")
include(":feature:home")
include(":feature:home:api")
include(":feature:home:impl")
include(":feature:search")
include(":feature:search:api")
include(":feature:search:impl")
include(":feature:authorization")
include(":feature:authorization:api")
include(":feature:authorization:impl")
include(":feature:favorites")
include(":feature:favorites:api")
include(":feature:favorites:impl")