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
        maven("https://jitpack.io")
        google()
        mavenCentral()
    }
}
rootProject.name = "BookManager2.0-compose"
include(":app")
