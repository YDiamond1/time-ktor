package domain

import org.jetbrains.exposed.sql.Table

object UserToGroups: Table(){
    val user_id = text("user_id") references Users.username
    val group_id = integer("group_id") references Groups.group_id

    override val primaryKey = PrimaryKey(user_id, group_id)
}



data class UserToGroup(val user_id : String, val group_id: Long)

