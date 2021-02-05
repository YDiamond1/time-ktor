package payload.response

import domain.Lesson
import domain.Subject


data class SubjectWithLessons (
    val subject: Subject,
    val lessons : List<Lesson>)
