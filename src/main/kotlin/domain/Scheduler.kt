package domain

import org.jetbrains.exposed.sql.Table

object Schedulers : Table(){
    val scheduler_id = integer("scheduler_id").autoIncrement()
    val name = text("name")
    val group_id = integer("group_id") references Groups.group_id

    override val primaryKey = PrimaryKey(scheduler_id)
}



data class Scheduler (
    val scheduler_id: Int,
    val name: String,
    val group_id: Int
)
data class NewScheduler(
    val name: String,
    val group_id: Int
)
