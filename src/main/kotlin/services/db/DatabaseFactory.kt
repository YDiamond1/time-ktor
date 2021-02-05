package services.db

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import io.ktor.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.logging.Logger
import javax.sql.DataSource


const val HIKARI_CONFIG_KEY = "ktor.hikariconfig"


object DatabaseFactory{
    fun initDB(): DataSource {
        val config = HoconApplicationConfig(ConfigFactory.load()).propertyOrNull(HIKARI_CONFIG_KEY)!!.getString()
        val dataSourceConfig = HikariConfig(config)
        val dataSource = HikariDataSource(dataSourceConfig)
        Database.connect(dataSource)
        Logger.getLogger(Application::class.simpleName).info("Init a database")
        return dataSource
    }

    suspend fun <T> dbQuery(block: suspend () -> T) : T = newSuspendedTransaction { block() }
}
