package dokerplp.vktesttask.model.entity

import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.Entity


class Friend {
    var userid: Long = 0
    var friendid: Long = 0
    var accept = false
}