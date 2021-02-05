package domain

import org.jetbrains.exposed.sql.Table

object Subjects: Table() {
        val subject_id = integer("subject_id").autoIncrement()
        val name = text("name")
        val creator_id = text("creator_id") references Users.username
        val scheduler_id = integer("scheduler_id") references Schedulers.scheduler_id

        override val primaryKey = PrimaryKey(subject_id)
}



data class Subject (
        val subject_id: Int,
        val name: String,
        val creator_id: String,
        val scheduler_id: Int
)
data class NewSubject (
        val name: String,
        val creator_id: String?,
        var scheduler_id: Int?
)
