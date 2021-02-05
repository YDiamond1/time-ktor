package services.schedule

import domain.*
import services.db.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import payload.request.scheduler.SchedulerRequest
import payload.response.SchedulerWithLessonsAndSubjects
import payload.response.SubjectWithLessons


class ScheduleService {




    suspend fun getAllSchedulers() : List<Scheduler> = dbQuery{
        Schedulers.selectAll().map { toScheduler(it) }
    }

    suspend fun getScheduler(id: Int) : SchedulerWithLessonsAndSubjects = dbQuery {
        val scheduler = Schedulers.select { Schedulers.scheduler_id eq id }
            .mapNotNull { toScheduler(it) }
            .singleOrNull()
        val subjects = Subjects.select{Subjects.scheduler_id eq scheduler!!.scheduler_id}
            .mapNotNull { toSubject(it) }
        val lessons: MutableList<SubjectWithLessons> = mutableListOf()
        for(subject in subjects){
            val list = Lessons.select { Lessons.subject_id eq subject.subject_id }
                .mapNotNull { toLesson(it) }
            lessons.add(SubjectWithLessons(subject,list))
        }
        SchedulerWithLessonsAndSubjects(scheduler,lessons)
    }

    suspend fun addScheduler(payload: SchedulerRequest): SchedulerWithLessonsAndSubjects {
        val schedulerId = insertScheduler(payload.scheduler)
        payload.lessons.forEach{
            val subjectId = insertSubject(it.subject.apply {
                scheduler_id = schedulerId
            })
            it.lessons.forEach { newLesson ->
            insertLesson(newLesson.apply { subject_id = subjectId })
            }
        }
        return getScheduler(schedulerId)
    }

    private suspend fun insertScheduler(scheduler: NewScheduler) = dbQuery {
        return@dbQuery Schedulers.insert{
            it[Schedulers.name] = scheduler.name
            it[Schedulers.group_id] = scheduler.group_id
        } get Schedulers.scheduler_id
    }

    private suspend fun insertSubject(subject: NewSubject) = dbQuery {
       return@dbQuery Subjects.insert {
            it[Subjects.name] = subject.name
            it[Subjects.creator_id] = subject.creator_id!!
            it[Subjects.scheduler_id] = subject.scheduler_id!!
        } get Subjects.subject_id
    }

    private suspend fun insertLesson(newLesson: NewLesson) = dbQuery {
        Lessons.insert {
            it[Lessons.from_time] = newLesson.from_time
            it[Lessons.to_time] = newLesson.to_time
            it[Lessons.teacher] = newLesson.teacher
            it[Lessons.day_of_week] = newLesson.day_of_week
            it[Lessons.even] = newLesson.even
            it[Lessons.comment] = newLesson.comment
            it[Lessons.creator_id] = newLesson.creator_id!!
            it[Lessons.subject_id] = newLesson.subject_id!!
        }
    }

    private fun toScheduler(it: ResultRow) : Scheduler =
        Scheduler(
            it[Schedulers.scheduler_id],
            it[Schedulers.name],
            it[Schedulers.group_id]
        )
    private fun toLesson(it: ResultRow): Lesson =
        Lesson(
            it[Lessons.lesson_id],
            it[Lessons.from_time],
            it[Lessons.to_time],
            it[Lessons.teacher],
            it[Lessons.day_of_week],
            it[Lessons.even],
            it[Lessons.comment],
            it[Lessons.creator_id],
            it[Lessons.subject_id]
        )
    private fun toSubject(it: ResultRow) : Subject =
        Subject(
            it[Subjects.subject_id],
            it[Subjects.name],
            it[Subjects.creator_id],
            it[Subjects.scheduler_id]
        )
}
