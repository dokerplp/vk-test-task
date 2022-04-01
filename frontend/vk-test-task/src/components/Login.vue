<template>
  <div id="container">
    <div>
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
          <button v-on:click="toggle" class="button" style="width: 90px">Sign up</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Login',
  components: {
  },
  data () {
    return {
      login: '',
      pass: ''
    }
  },
  methods: {
    sign_in () {
      this.$store.dispatch('SIGN_IN', { login: this.login, pass: this.pass })
      this.$router.push({path: '/home/'})
    },
    toggle () {
      this.$emit('toggleRegister')
    }
  }
}
</script>

<style>

#container {
  width: 400px;
  margin: 20px auto;
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
