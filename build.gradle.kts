subprojects {
    buildscript {
        repositories {
            mavenLocal()
            jcenter()
            maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }
}

tasks {
    /*"buildClient" {
        dependsOn("cleanClient", "client:buildFromNode")
        group = "application"
        doLast {
            // TODO: Move dist folder to build
        }
    }*/

    /*"buildServer" {
        dependsOn("cleanServer", "server:jar")
        group = "application"
    }

    "cleanClient" {
        typeOf<Delete>()
        delete("$buildDir/client")
    }

    "cleanServer" {
        typeOf<Delete>()
        delete("$buildDir/server")
    }

    "bootRun" {
        group = "other"
    }*/
}
