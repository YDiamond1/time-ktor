package db.migration

import domain.*
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class V1__create_tables: BaseJavaMigration() {
    override fun migrate(context: Context?) {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                Schedulers,
                Users,
                Subjects,
                Groups,
                Lessons,
                UserToGroups
            )

            Users.insert {
                it[username] = "test"
                it[password] = "test"
                it[token] = null
            }

            Groups.insert {
                it[name] = "testGroup"
                it[creator_id] = "test"
            }

            Schedulers.insert {
                it[name] = "testScheduler"
                it[group_id] = 1
            }
            Subjects.insert {
                it[name] = "test"
                it[creator_id] = "test"
                it[scheduler_id] = 1
            }

            Lessons.insert {
                it[Lessons.creator_id] = "test"
                it[Lessons.from_time] = "11:40"
                it[Lessons.to_time] = "13:10"
                it[Lessons.comment] = "privet"
                it[Lessons.subject_id] = 1
                it[Lessons.day_of_week] = "dayoff"
                it[Lessons.teacher] = "math teacher"
                it[Lessons.even] = true
            }

        }
    }
}
