package payload.response

import domain.Scheduler


data class SchedulerWithLessonsAndSubjects(
    val scheduler: Scheduler?,
    val lessons: MutableList<SubjectWithLessons>);
