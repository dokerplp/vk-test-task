package dokerplp.vktesttask.security

import org.springframework.stereotype.Component
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import java.security.spec.KeySpec
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

@Component
class PassHashing {

    private val pepper = System.getenv("PEPPER").toCharArray()

    fun salt(): ByteArray {
        val random = SecureRandom()
        val salt = ByteArray(16)
        random.nextBytes(salt)
        return salt
    }

    fun hash(salt: ByteArray, pass: CharArray): ByteArray {
        val spec: KeySpec = PBEKeySpec(StringBuilder().append(pass).append(pepper).toString().toCharArray(), salt, 65536, 128)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
        return factory.generateSecret(spec).encoded
    }

}