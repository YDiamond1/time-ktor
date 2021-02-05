package payload.request.scheduler

import domain.NewLesson
import domain.NewSubject

data class NewSubjectWithLessons(
    val subject: NewSubject,
    val lessons: List<NewLesson>
)
