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
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "MarvelApp"
include(":app")
include(":feature-characters")
include(":feature-characters:feature-characters-data")
include(":feature-characters:feature-characters-domain")
include(":feature-characters:feature-characters-presentation")
include(":api")
include(":shared")
include(":shared:shared-domain")
include(":shared:shared-presentation")
include(":feature-character-details")
include(":feature-character-details:feature-character-details-data")
include(":feature-character-details:feature-character-details-domain")
include(":feature-character-details:feature-character-details-presentation")
include(":shared:shared-data")
