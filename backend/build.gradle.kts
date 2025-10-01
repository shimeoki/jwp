plugins {
    application
}

group = "io.github.shimeoki"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    // TODO: db driver
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "" // TODO: main class
}
