plugins {
    // trick: for the same plugin versions in all sub-modules
    kotlin("jvm").version(Versions.kotlin).apply(false)
    kotlin("multiplatform").version(Versions.kotlin).apply(false)
    kotlin("android").version(Versions.kotlin).apply(false)

    id("com.android.application").version(Versions.gradlePlugin).apply(false)
    id("com.android.library").version(Versions.gradlePlugin).apply(false)

    id("org.jetbrains.compose").version(Versions.compose_multiplatform).apply(false)
}

tasks.register<GenerateScreen>("generate")
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
