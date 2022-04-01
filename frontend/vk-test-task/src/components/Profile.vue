<template>
  <div id="my_profile">
    <div id="data">
      <div>
        <input type="text" v-model="name"/>
      </div>
      <div>
        <input type="text" v-model="surname"/>
      </div>
      <div>
        <input type="date" v-model="birthday"/>
      </div>
    </div>
    <div id="labels">
      <div>Name</div>
      <div>Surname</div>
      <div>Birthday</div>
      <div id="save_changes" v-on:click="save_changes">Save</div>
    </div>
  </div>
</template>

<script>

import { postData } from '@/store'

export default {
  name: "Profile",
  created () {
    postData('/api/user', { login: this.$store.getters.LOGIN })
      .then(response => response.json())
      .then(data => {
        if (data) {
          this.name = data.name
          this.surname = data.surname
          this.birthday = new Date(data.birthday).toISOString().slice(0, 10)
        }
        console.log(data)
      })
  },
  data: function () {
    return {
      name: '',
      surname: '',
      birthday: ''
    }
  },
  methods: {
    save_changes () {
      postData('/api/user/change', {
        auth: {
          login: this.$store.getters.LOGIN,
          pass: this.$store.getters.PASS
        },
        name: this.name,
        surname: this.surname,
        birthday: this.birthday
      })
    }
  }
}
</script>

<style scoped>

#labels {
  float: right;
  width: 20%;
}

#data {
  float: right;
  width: 40%;
  margin-right: 150px;
}

#my_profile div:nth-child(2) div {
  margin-left: 20px;
  background: rgba(78,78,78,0.96);
  font-size: 20px;
  border-radius: 8px 8px 8px 8px;
  margin-top: 20px;
  font-weight: bold;
}

input[type="text"], input[type="date"] {
  background: white;
  color: black;
  border-radius: 8px 8px 8px 8px;
  border-width: 2px;
  border-color: black;
  font-size: 15px;
  margin-top: 20px;
}

#labels #save_changes {
  background: rgba(255,159,208,0.96);
  color: rgba(78,78,78,0.96);
}

</style>
