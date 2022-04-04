package dokerplp.vktesttask

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
class VkTestTaskApplication

fun main(args: Array<String>) {
    runApplication<VkTestTaskApplication>(*args)
}
