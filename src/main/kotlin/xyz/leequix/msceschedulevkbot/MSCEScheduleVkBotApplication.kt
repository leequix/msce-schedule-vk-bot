package xyz.leequix.msceschedulevkbot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import xyz.leequix.msceschedulevkbot.service.ScheduleDistributionService
import xyz.leequix.msceschedulevkbot.service.VkontakteAPIService

@SpringBootApplication
@EnableScheduling
class MSCEScheduleVkBotApplication

fun main(args: Array<String>) {
    runApplication<MSCEScheduleVkBotApplication>(*args)
}
