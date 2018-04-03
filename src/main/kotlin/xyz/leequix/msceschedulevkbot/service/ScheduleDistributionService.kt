package xyz.leequix.msceschedulevkbot.service

import com.coxautodev.graphql.client.GraphQLClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import redis.clients.jedis.Jedis
import xyz.leequix.msceschedulevkbot.model.Group
import xyz.leequix.msceschedulevkbot.model.User
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashSet

@Service
class ScheduleDistributionService {
    data class SubgroupPair(var title: String, var audience: String)
    data class Pair(var number: String, var subgroupPairs: List<SubgroupPair>)
    data class DayPairs(var group: Group, var pairs: List<Pair>)
    data class Day(var date: String, var dayPairs: List<DayPairs>)

    private data class ScheduleFetchingResult(var days: List<Day>)

    private val apiDateFormat: DateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)

    @Autowired
    private lateinit var graphQLClient: GraphQLClient

    @Autowired
    private lateinit var jedis: Jedis

    @Autowired
    private lateinit var groupService: GroupService

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var vkontakteAPIService: VkontakteAPIService

    private val sentList: MutableSet<String> = HashSet()

    @Scheduled(cron = "0 0 */1 * * *")
    fun checkSchedule() {
        val result = graphQLClient.query(GET_SCHEDULE_QUERY, ScheduleFetchingResult::class.java)
        if (result.days.isNotEmpty()) {
            val lastDay = result.days.last()
            val date = apiDateFormat.parse(lastDay.date)

            val lastSentDateString = jedis.get("last_date")
            var lastSentDate = Date()
            if (lastSentDateString !== null) {
                lastSentDate = apiDateFormat.parse(lastSentDateString)
            }

            if(date > lastSentDate) {
                startSendingSchedule(lastDay.dayPairs, date)
                jedis.set("last_date", apiDateFormat.format(date))
            }
        }
    }

    fun startSendingSchedule(dayPairs: List<DayPairs>, date: Date) {
        for(schedule in dayPairs) {
            if(!sentList.contains(schedule.group.number)) {
                sentList.add(schedule.group.number)
                val group = groupService.findByNumber(schedule.group.number)
                if (group === null) {
                    continue
                }
                val users = userService.findAllByGroup(group)

                sendScheduleToUsers(schedule.pairs, users, date)
            }
        }
        sentList.clear()
    }

    fun sendScheduleToUsers(pairs: List<Pair>, users: List<User>, date: Date) = if (!users.isEmpty()) {
        val messageText = generateScheduleMessage(pairs, users.first().group!!.number, date)
        vkontakteAPIService.sendMessage(users.map { it.vkontakteId }, messageText)
    } else {
    }

    fun generateScheduleMessage(pairs: List<Pair>, groupNumber: String, date: Date): String {
        var messageText = "Расписание для группы $groupNumber на ${SimpleDateFormat("dd.MM.yyyy").format(date)}\n"
        for (pair in pairs) {
            var pairText = "${pair.number})\n"
            for (subgroupPair in pair.subgroupPairs) {
                pairText += "${subgroupPair.title} [${subgroupPair.audience}]\n"
            }
            messageText += "$pairText\n"
        }

        return messageText
    }

    companion object {
        private const val GET_SCHEDULE_QUERY =
            "{\n" +
                "days: findAllDays {\n" +
                    "date\n" +
                    "dayPairs {\n" +
                        "group {\n" +
                            "number\n" +
                        "}\n" +
                        "pairs {\n" +
                            "number\n" +
                            "subgroupPairs {\n" +
                                "title\n" +
                                "audience\n" +
                            "}\n" +
                        "}\n" +
                    "}\n" +
                "}\n" +
            "}\n"
    }
}