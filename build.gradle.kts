import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


val ktor_version = "1.5.0"
val exposedVersion = "0.28.1"
val h2Version = "1.4.200"
val hikariCpVersion = "3.4.5"
val flywayVersion = "7.5.0"
val ktorFlywayVersion = "1.2.2"
val logbackVersion = "1.2.3"
val assertjVersion = "3.18.1"
val restAssuredVersion = "4.3.3"
val junitVersion = "5.7.0"

plugins{
    kotlin("jvm") version "1.4.21-2"
    application
}


repositories {
    mavenLocal()
    jcenter()
}

dependencies {
    implementation (kotlin("stdlib-jdk8"))

    implementation ("io.ktor:ktor-server-netty:$ktor_version")
    implementation ("ch.qos.logback:logback-classic:$logbackVersion")
    implementation ("io.ktor:ktor-server-core:$ktor_version")
    implementation ("io.ktor:ktor-jackson:$ktor_version")

    //Auth with JWT
    implementation ("io.ktor:ktor-auth:$ktor_version")
    implementation ("io.ktor:ktor-auth-jwt:$ktor_version")

    implementation("io.ktor:ktor-locations:$ktor_version")
    //Exposed
    implementation ("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation ("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation ("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    //Flyway
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    implementation("com.viartemev:ktor-flyway-feature:$ktorFlywayVersion")

    implementation ("com.zaxxer:HikariCP:3.4.5") // JDBC Connection Pool
    implementation ("org.postgresql:postgresql:42.2.1") // JDBC Connector for PostgreSQL


    testImplementation ("io.ktor:ktor-server-tests:$ktor_version")
}



tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}
application{
    mainClassName = "io.ktor.server.netty.EngineMain"
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}
