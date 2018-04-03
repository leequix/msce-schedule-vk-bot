package xyz.leequix.msceschedulevkbot.service

import com.coxautodev.graphql.client.GraphQLClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import xyz.leequix.msceschedulevkbot.model.Group

@Service
class GroupFetchingService {
    private data class GroupsFetchingResult(var groups: List<Group>)

    @Autowired
    private lateinit var graphQLClient: GraphQLClient

    @Autowired
    private lateinit var groupService: GroupService

    @Scheduled(cron = "0 0 */2 * * *")
    fun fetchGroups() {
        val fetchingResult = graphQLClient
                .query(GET_GROUPS_QUERY, GroupsFetchingResult::class.java)
        groupService.updateOrCreateAll(fetchingResult.groups)
    }

    companion object {
        private const val GET_GROUPS_QUERY = "{groups: findAllGroups{number}}"
    }
}