package payload.request.scheduler

import domain.NewScheduler

data class SchedulerRequest(
    val scheduler: NewScheduler,
    val lessons: List<NewSubjectWithLessons>
)
