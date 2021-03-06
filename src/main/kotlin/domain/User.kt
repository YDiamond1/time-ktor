package domain

import org.jetbrains.exposed.sql.Table

object Users : Table(){
        val username = text("username")
        val password = text("password")

        override val primaryKey = PrimaryKey(username)
}


data class User(
        val username: String,
        val password: String
);
