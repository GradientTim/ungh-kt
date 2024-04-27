import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("jvm") version "1.9.23"
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("com.vanniktech.maven.publish") version "0.28.0"
    signing
}

group = property("project.group")!!
version = property("project.version")!!

repositories {
    mavenCentral()
}

dependencies {
    ksp("com.squareup.moshi", "moshi-kotlin-codegen", "1.15.1")

    implementation("com.squareup.moshi", "moshi-kotlin", "1.15.1")
    implementation("com.squareup.okhttp3", "okhttp", "4.12.0")

    testImplementation("io.kotest", "kotest-runner-junit5", "5.8.1")
    testImplementation("io.kotest", "kotest-assertions-core", "5.8.1")
}

kotlin {
    jvmToolchain(17)
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()

    val projectName = property("project.name").toString()
    coordinates(project.group.toString(), projectName, project.version.toString())

    pom {
        name.set(projectName)
        description.set("Unofficial Kotlin driver for the ungh api")
        inceptionYear.set("2024")
        url.set("https://github.com/GradientTim/ungh-kt/")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/license/MIT")
            }
        }

        developers {
            developer {
                id.set("gradienttim")
                name.set("GradientTim")
                url.set("https://github.com/GradientTim/")
            }
        }

        issueManagement {
            system.set("GitHub")
            url.set("https://github.com/GradientTim/ungh-kt/issues")
        }

        scm {
            url.set("https://github.com/GradientTim/ungh-kt/")
            connection.set("scm:git:git://github.com/GradientTim/ungh-kt.git")
            developerConnection.set("scm:git:ssh://git@github.com/GradientTim/ungh-kt.git")
        }
    }
}

signing {
    sign(configurations.archives.get())
}

tasks.test {
    useJUnitPlatform()
}
