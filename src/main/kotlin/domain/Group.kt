package domain

import org.jetbrains.exposed.sql.Table

object Groups: Table(){
       val group_id = integer ("group_id").autoIncrement()
       val name = text("name")
       val creator_id = text("creator_id") references Users.username

       override val primaryKey = PrimaryKey(group_id)
}




data class Group(
       val group_id: Long?,
       val name: String,
       val creator_id: String
){

}
