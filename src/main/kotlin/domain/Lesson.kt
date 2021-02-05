package domain

import org.jetbrains.exposed.sql.Table

object Lessons: Table(){
    val lesson_id = integer ("lesson_id").autoIncrement()
    val from_time = text("from_time")
    val to_time = text("to_time")
    val teacher = text("teacher")
    val day_of_week = text("day_of_week")
    val even = bool("even")
    val comment = text("comment")
    val creator_id = text("creator_id") references Users.username
    val subject_id = integer("subject_id") references Subjects.subject_id

    override val primaryKey = PrimaryKey(lesson_id)
}



data class Lesson (val lesson_id: Int,
                   val from_time: String,
                   val to_time: String,
                   val teacher: String,
                   val day_of_week: String,
                   val even: Boolean,
                   val comment: String,
                   val creator_id: String,
                   val subject_id: Int
)
data class NewLesson(
    val from_time: String,
    val to_time: String,
    val teacher: String,
    val day_of_week: String,
    val even: Boolean,
    val comment: String,
    val creator_id: String?,
    var subject_id: Int?
)
