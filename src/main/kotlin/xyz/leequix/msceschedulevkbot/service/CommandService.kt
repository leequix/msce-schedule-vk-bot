package xyz.leequix.msceschedulevkbot.service

import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.leequix.msceschedulevkbot.command.Command

@Service
class CommandService {
    @Autowired
    private lateinit var beanFactory: BeanFactory

    fun getCommandByName(name: String) = beanFactory.getBean(Command::class.java, name)
}