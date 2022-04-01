<template>
  <div id="container">
    <div id="right">
      <div id="auth">
        <div>
          Authentication
        </div>
        <div>
          <table id="form">
            <tr>
              <td class="login">Login</td>
              <td class="login">
                <input type="text" v-model="login"/>
              </td>
            </tr>
            <tr>
              <td id="label">Password</td>
              <td id="pass">
                <input type="password" v-model="pass"/>
              </td>
            </tr>
            <tr>
              <td colSpan="2">
                <button class="button" v-on:click="sign_in">Sign in</button>
              </td>
            </tr>
          </table>
        </div>
        <div>
          Not registered yet?
          <button v-on:click="sign_up" class="button" style="width: 90px">Sign up</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import store from "@/store";

async function postData(url, data) {
  return await fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    },
    body: JSON.stringify(data)
  })
}

export default {
  name: 'Auth',
  components: {
  },
  data () {
    return {
      login: '',
      pass: ''
    }
  },
  methods: {
    sign_in() {
      postData('/login', {
        login: this.login,
        pass: this.pass
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
          });
    },
    sign_up() {
      postData('/login', {
        login: this.login,
        pass: this.pass
      })
          .then(response => response.json())
          .then(data => {
            console.log(data)
          });
    }
  }
}
</script>

<style>

#container {
  width: 400px;
  margin: 15% auto;
}

#container {
  color: rgba(255,159,208,0.96);
}

#auth div:first-child {
  background-color: rgba(78,78,78,0.96);
  font-size: 30px;
  font-weight: bold;
  border-radius: 8px 8px 0 0;
}


#auth div:last-child {
  font-size: 20px;
  background-color: rgba(107,107,107,0.96);
  border-radius: 0 0 8px 8px;
}

#auth div:last-child a {
  background-color: rgba(68,68,68,0.96);
  font-weight: bold;
}

#form {
  background: white;
  border-spacing: 20px 20px;
  width: 100%;
  /*border-radius: 0 0 8px 8px;*/
}


#form tr td:first-child {
  width: 40%;
  background: rgba(111,111,111,0.96);
  font-size: 20px;
  border-radius: 8px 8px 8px 8px;
}

#form tr td:last-child {
  background: white;
}

.button {
  background-color: rgba(68,68,68,0.96);
  border-radius: 8px 8px 8px 8px;
  border-width: 1px;
  border-color: rgba(68,68,68,0.96);
  font-size: 20px;
  font-weight: bold;
  width: 30%;
}

input[type="text"], input[type="password"] {
  background: white;
  color: black;
  width: 90%;
  border-radius: 8px 8px 8px 8px;
  border-width: 2px;
  border-color: black;
  font-size: 15px;
}

</style>
