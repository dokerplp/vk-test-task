package dokerplp.vktesttask.model.entity

import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "messages")
class Message() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long = 0

    @Column(name = "userid", nullable = false)
    var userid: Long = 0

    @Column(name = "friendid", nullable = false)
    var friendid: Long = 0

    @Column(name = "text", nullable = false)
    lateinit var text: String

    @Column(name = "time", nullable = false)
    lateinit var time: Date

    constructor(
        userId: Long,
        friendId: Long,
        text: String,
        time: Date
    ) : this() {
        this.userid = userId
        this.friendid = friendId
        this.text = text
        this.time = time
    }

}