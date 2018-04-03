package xyz.leequix.msceschedulevkbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class MSCEScheduleVkBotApplication

fun main(args: Array<String>) {
    runApplication<MSCEScheduleVkBotApplication>(*args)
}
