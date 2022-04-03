package dokerplp.vktesttask.model.entity

import lombok.NoArgsConstructor
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "users")
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long = 0

    @Column(name = "login", nullable = false)
    lateinit var login: String

    @Column(name = "password", nullable = false)
    lateinit var password: ByteArray

    @Column(name = "salt", nullable = false)
    lateinit var salt: ByteArray

    @Column(name = "name", nullable = false)
    lateinit var name: String

    @Column(name = "surname", nullable = false)
    lateinit var surname: String

    @Column(name = "birthday", nullable = false)
    lateinit var birthday: Date

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "friends",
        joinColumns = [
            JoinColumn(name = "userid")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "friendid")
        ],
    )
    lateinit var friends: MutableList<User>


    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
    @JoinTable(
        name = "requests",
        joinColumns = [
            JoinColumn(name = "userid")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "friendid")
        ],
    )
    lateinit var requests: MutableList<User>

    constructor(
        login: String,
        password: ByteArray,
        salt: ByteArray,
        name: String,
        surname: String,
        birthday: Date
    ) : this() {
        this.login = login
        this.password = password
        this.salt = salt
        this.name = name
        this.surname = surname
        this.birthday = birthday
    }
}
