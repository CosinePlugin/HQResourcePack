plugins {
    kotlin("jvm") version "1.7.21"
}

group = "kr.cosine.resourcepack"
version = "1.0.0"

repositories {
    maven("https://maven.hqservice.kr/repository/maven-public")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("org.spigotmc", "spigot-api", "1.17.1-R0.1-SNAPSHOT")
    compileOnly("org.zeroturnaround", "zt-zip", "1.15")
    compileOnly("io.vertx", "vertx-web", "4.4.4")

    compileOnly("kr.hqservice", "hqframework-bukkit-core", "1.0.2-SNAPSHOT")
    compileOnly("kr.hqservice", "hqframework-bukkit-command", "1.0.2-SNAPSHOT")

    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()
    }
    jar {
        archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
        destinationDirectory.set(file("D:\\서버\\1.20.1 - 개발\\plugins"))
    }
}