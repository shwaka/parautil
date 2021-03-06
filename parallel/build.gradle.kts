plugins {
    kotlin("multiplatform") version "1.4.31"
    `maven-publish`
    id("com.adarshr.test-logger") version "3.0.0"
}

group = "com.github.shwaka.parautil"
version = "0.2"

repositories {
    jcenter()
    // maven(url = "https://shwaka.github.io/maven/")
}

kotlin {
    explicitApiWarning()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(BOTH) {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Use the Kotlin JDK 8 standard library.
                implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                // Use the Kotlin test library.
                // implementation("org.jetbrains.kotlin:kotlin-test")

                // Use the Kotlin JUnit integration.
                // implementation("org.jetbrains.kotlin:kotlin-test-junit")
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                // implementation(kotlin("test-junit"))
                // kotest
                val version = "4.3.2"
                implementation("io.kotest:kotest-runner-junit5:$version")
                implementation("io.kotest:kotest-assertions-core:$version")
                implementation("io.kotest:kotest-property:$version")
                implementation("io.kotest:kotest-assertions-compiler:$version")
            }
        }
        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val nativeMain by getting
        val nativeTest by getting
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("skipped", "passed", "failed") // "started" ????????????
    }
    System.getProperty("kotest.tags")?.let {
        // null ??? set ???????????????????????????????????????????????? ?.let ????????????
        systemProperties["kotest.tags"] = it
    }
}

testlogger {
    theme = com.adarshr.gradle.testlogger.theme.ThemeType.MOCHA
    showCauses = true
    showStandardStreams = true
    showFullStackTraces = true
}

publishing {
    repositories {
        maven {
            url = uri("../../maven/repository")
            name = "MyMaven"
        }
    }
}
