package xyz.leequix.msceschedulevkbot.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.leequix.msceschedulevkbot.model.Group
import xyz.leequix.msceschedulevkbot.repository.GroupRepositroy

@Service
class GroupService {
    @Autowired
    private lateinit var groupRepository: GroupRepositroy

    fun updateOrCreateAll(groups: List<Group>) {
        for (group in groups) {
            if (!groupRepository.existsByNumber(group.number)) {
                groupRepository.save(group)
            }
        }
    }
}