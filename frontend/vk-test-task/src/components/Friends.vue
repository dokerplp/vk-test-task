<template>
  <div>
    <table id="mode">
      <tr>
        <td v-on:click="mode = 1">Search</td>
        <td v-on:click="mode = 2">Friends</td>
        <td v-on:click="mode = 3">Requests</td>
      </tr>
    </table>
    <div v-if="mode === 1" id="findFriends">
      <input type="text" v-model="search"/>
      <button v-on:click="findUser" class="button">Search</button>
      <div id="search" v-if="found">
        <div>{{user.login}}</div>
        <div>{{user.name}}</div>
        <div>{{user.surname}}</div>
        <div>{{new Date(user.birthday).toISOString().slice(0, 10)}}</div>
        <div v-on:click="addFriend(user.login)">Add Friend</div>
      </div>
    </div>
    <div v-if="mode === 2" id="showFriends">
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
    <div v-if="mode === 3" id="requests">
      <table id="req">
        <tr v-for="request in requests" :key="request.login">
          <td>
            {{request.login}}
          </td>
          <td>
            {{request.name}}
          </td>
          <td>
            {{request.surname}}
          </td>
          <td>
            {{new Date(request.birthday).toISOString().slice(0, 10)}}
          </td>
          <td id="accept_button" v-on:click="addFriend(request.login)">
            Accept
          </td>
          <td id="decline_button">
            Decline
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
import { postData } from '@/store'

const State = Object.freeze({ Search: 1, Friends: 2, Requests: 3 })

export default {
  name: "Friends",
  created () {
    this.initFriends()
    this.initRequests()
  },
  data () {
    return {
      friends: [],
      requests: [],
      mode: State.Search,
      search: '',
      user: {},
      found: false
    }
  },
  methods: {
    addFriend (user) {
      postData('/api/add-friend', {
        auth: {
          login: this.$store.getters.LOGIN,
          pass: this.$store.getters.PASS
        },
        friend: user
      })
      this.initFriends()
      this.initRequests()
    },
    initRequests () {
      postData('/api/request-list', {
        login: this.$store.getters.LOGIN,
        pass: this.$store.getters.PASS
      })
        .then(response => response.json())
        .then(data => {
          this.requests = data
          console.log(data)
        })
    },
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
    },
    findUser () {
      postData('/api/find-friend', {
        login: this.search
      }).then(this.found = false)
        .then(response => response.json())
        .then(data => {
          if (data === null || data.login === this.$store.getters.LOGIN) this.found = false
          else {
            this.user = data
            this.found = true
          }
        })
    }
  }
}
</script>

<style scoped>

#search div {
  float: left;
  background: white;
  width: 15%;
  color: rgba(78,78,78,0.96);
  border-radius: 8px 8px 8px 8px;
  margin-left: 20px;
  margin-top: 20px;
}

#search div:last-child {
  background: rgba(255,159,208,0.96);
  color: rgba(78,78,78,0.96);
}


#mode {
  border-spacing: 20px 20px;
  width: 100%;
}

#mode tr td {
  border-radius: 8px 8px 8px 8px;
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

.button {
  margin-top: 20px;
}

#req {
  border-spacing: 20px 20px;
  width: 100%;
}

#req tr td {
  border-radius: 8px 8px 8px 8px;
  font-size: 20px;
  background: white;
  color: rgba(78,78,78,0.96);
}

</style>
