package dokerplp.vktesttask

import dokerplp.vktesttask.controller.AuthControllerTest
import dokerplp.vktesttask.controller.FriendsControllerTest
import dokerplp.vktesttask.controller.MessageControllerTest
import dokerplp.vktesttask.controller.UserControllerTest
import dokerplp.vktesttask.model.service.MessageServiceTest
import dokerplp.vktesttask.model.service.UserServiceTest
import dokerplp.vktesttask.security.PassHashingTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
class VkTestTaskApplicationTests(
) {

	@Test
	fun contextLoads() {

	}

}
