package routes

import services.schedule.ScheduleService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import payload.request.scheduler.SchedulerRequest

@ExperimentalCoroutinesApi
fun Route.schedule(schedulerService: ScheduleService){
    route("/schedule"){
        get{
            call.respond(schedulerService.getAllSchedulers())
        }
        get("/{id}"){
            val id = call.parameters["id"]?.toInt() ?: throw IllegalStateException("Must provide id")
            val schedule = schedulerService.getScheduler(id)
            if( schedule == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(schedule)
        }

        post("/add"){
            val payload = call.receive<SchedulerRequest>()
            println(payload)
            call.respond(schedulerService.addScheduler(payload))
        }
    }
}
