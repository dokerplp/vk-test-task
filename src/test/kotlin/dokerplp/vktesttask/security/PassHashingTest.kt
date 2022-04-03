package dokerplp.vktesttask.security

import dokerplp.vktesttask.model.entity.User
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import java.util.*

@SpringBootTest
@Component
class PassHashingTest(
    @Autowired private val passHashing: PassHashing
)
{

    @Test
    fun salt() {
        val password = "aboba"
        val salt = passHashing.salt()

        val hash1 = passHashing.hash(salt, password.toCharArray())
        val hash2 = passHashing.hash(salt, password.toCharArray())

        assert(hash1.contentEquals(hash2))
    }

    @Test
    fun hash() {
    }
}