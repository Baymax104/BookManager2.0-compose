pluginManagement {
    repositories {
        maven("https://maven.aliyun.com/repository/central/")
        maven("https://maven.aliyun.com/repository/google/")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://maven.aliyun.com/repository/central/")
        maven("https://maven.aliyun.com/repository/google/")
        google()
        mavenCentral()
    }
}
rootProject.name = "BookManager2.0-compose"
include(":app")
