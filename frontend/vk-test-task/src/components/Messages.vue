<template>
  <input id="text" type="text" v-model="text"/>
  <button class="btn" v-on:click="sendMessage">SEND</button>
  <table id="messages" v-for="m in messages" :key="m.id">
    <tr v-if="m.my">
      <td style="width: 50%"></td>
      <td>
        <div class="my message">{{m.text}}</div>
      </td>
    </tr>
    <tr v-else >
      <td>
        <div class="notmy message">{{m.text}}</div>
      </td>
      <td style="width: 50%"></td>
    </tr>
  </table>
</template>
<script>

import { postData } from '@/store'

export default {
  name: 'Messages',
  created () {
    this.user = this.$route.params.login
  },
  data () {
    return {
      messages: [],
      user: '',
      text: ''
    }
  },
  mounted: function () {
    window.setInterval(() => {
      this.initMessages()
    }, 1000)
  },
  methods: {
    initMessages () {
      postData('/api/get-messages', {
        auth: {
          login: this.$store.getters.LOGIN,
          pass: this.$store.getters.PASS
        },
        friend: this.user
      }).then(response => response.json())
        .then(data => {
          this.messages = data
          console.log(data)
        })
    },
    sendMessage () {
      postData('/api/send-message', {
        auth: {
          login: this.$store.getters.LOGIN,
          pass: this.$store.getters.PASS
        },
        friend: this.user,
        time: new Date(),
        text: this.text
      }).then(response => response.json())
        .then(data => {
          this.messages = data
          console.log(data)
        })
    }
  }
}
</script>
<style>

.btn {
  margin-left: 20px;
  background-color: rgba(68,68,68,0.96);
  border-radius: 8px 8px 8px 8px;
  border-width: 1px;
  border-color: rgba(68,68,68,0.96);
  font-size: 15px;
  font-weight: bold;
}

#text {
  margin-top: 20px;
  width: 80%;
  background: white;
  color: black;
  border-radius: 8px 8px 8px 8px;
  border-width: 2px;
  border-color: black;
  font-size: 15px;
}

#messages {
  width: 100%;
}

.message {
  margin-top: 20px;
  border-radius: 8px 8px 8px 8px;
  font-size: 20px;
  font-weight: bold;
}

.my {
  margin-left: 20px;
  margin-right: 150px;
  background: rgba(255,159,208,0.96);
  color: rgba(78,78,78,0.96);
}

.notmy {
  margin-right: 20px;
  margin-left: 150px;
  background: rgba(78,78,78,0.96);
  color: rgba(255,159,208,0.96);
}

</style>
