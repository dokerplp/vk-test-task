<template>
  <div>
    <div id="friendslabel">
      Friends
    </div>
    <table id="data">
      <thead>
      <tr>
        <td id="login">Login</td>
        <td id="name">Name</td>
        <td id="surname">Surname</td>
        <td id="birthday">Birthday</td>
        <td id="message"></td>
        <td id="delete"></td>
      </tr>
      </thead>
      <tbody id="rows">
      <tr v-for="friend in friends" :key="friend.login">
        <td>
          {{friend.login}}
        </td>
        <td>
          {{friend.name}}
        </td>
        <td>
          {{friend.surname}}
        </td>
        <td>
          {{new Date(friend.birthday).toISOString().slice(0, 10)}}
        </td>
        <td id="message_button">
          Message
        </td>
        <td id="delete_button" v-on:click="deleteUser(friend.login)">
          Delete
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import { postData } from '@/store'

export default {
  name: "Friends",
  created () {
    this.initFriends()
  },
  data () {
    return {
      friends: []
    }
  },
  methods: {
    initFriends () {
      postData('/api/friends-list', {
        login: this.$store.getters.LOGIN,
        pass: this.$store.getters.PASS
      })
        .then(response => response.json())
        .then(data => {
          this.friends = data
          console.log(data)
        })
    },
    deleteUser (user) {
      postData('/api/delete-friend', {
        auth: {
          login: this.$store.getters.LOGIN,
          pass: this.$store.getters.PASS
        },
        friend: user
      }).then(response => response.json())
        .then(data => {
          this.initFriends()
        })
    }
  }
}
</script>

<style scoped>

#friendslabel {
  margin: 20px auto 0;
  border-radius: 8px 8px 8px 8px;
  width: 30%;
  font-size: 20px;
  font-weight: bold;
  background: rgba(255,159,208,0.96);
  color: rgba(78,78,78,0.96);
}

#data {;
  border-spacing: 20px 20px;
  width: 100%;
}

#data thead * {
  margin-left: 20px;
  border-radius: 8px 8px 8px 8px;
  font-size: 20px;
  background: rgba(78,78,78,0.96);
  color: rgba(255,159,208,0.96);
}

#data thead #message {
  background: rgb(164, 164, 164);
}

#data thead #delete {
  background: rgb(164, 164, 164);
}

#data tbody tr td {
  border-radius: 8px 8px 8px 8px;
  font-size: 20px;
  background: white;
  color: rgba(78,78,78,0.96);
}

#data tbody tr #message_button {
  font-weight: bold;
  background: rgba(255,159,208,0.96);
  color: rgba(78,78,78,0.96);
}

#data tbody tr #delete_button {
  font-weight: bold;
  background: rgba(255,159,208,0.96);
  color: rgba(78,78,78,0.96);
}

#login {
  width: 10%;
}

#name {
  width: 20%;
}

#surname {
  width: 20%;
}

#birthday {
  width: 20%;
}

#message {
  width: 10%;
}

#delete {
  width: 10%;
}

</style>
