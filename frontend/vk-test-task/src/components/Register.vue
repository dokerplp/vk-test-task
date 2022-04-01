<template>
  <div>
    <div id="container">
      <div>
        <div id="auth">
          <div>
            Registration
          </div>
          <div>
            <table id="form">
              <tr>
                <td>Name</td>
                <td>
                  <input type="text" v-model="name"/>
                </td>
              </tr>
              <tr>
                <td>Surname</td>
                <td>
                  <input type="text" v-model="surname"/>
                </td>
              </tr>
              <tr>
                <td>Birthday</td>
                <td>
                  <input type="date" v-model="birthday"/>
                </td>
              </tr>
              <tr>
                <td>Login</td>
                <td>
                  <input type="text" v-model="login"/>
                </td>
              </tr>
              <tr>
                <td>Password</td>
                <td>
                  <input type="password" v-model="pass"/>
                </td>
              </tr>
              <tr>
                <td>Confirm password</td>
                <td>
                  <input type="password" v-model="confpass"/>
                </td>
              </tr>
              <tr>
                <td colspan="2">
                  <span style="white-space: pre;" id="error">{{ error }}</span>
                </td>
              </tr>
            </table>
          </div>
          <div>
            <button class="button" v-on:click="register" style="margin-right: 20px">Register</button>
            <button v-on:click="toggle" class="button">Cancel</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Register',
  data () {
    return {
      name: '',
      surname: '',
      birthday: '',
      login: '',
      pass: '',
      confpass: '',
      error: ''
    }
  },
  methods: {
    validate () {
      let correct = true
      let e = ''
      if (this.name.length < 5) {
        e += 'Name is too short\n'
        correct = false
      }
      if (this.surname.length < 5) {
        e += 'Surname is too short\n'
        correct = false
      }
      if (this.birthday === '') {
        e += 'Incorrect birthday\n'
        correct = false
      }
      if (this.pass !== this.confpass) {
        e += 'Passwords are different\n'
        correct = false
      } else if (this.pass.length < 5) {
        e += 'Password is too short\n'
        correct = false
      }
      this.error = e
      return correct
    },
    register () {
      this.validate()
      this.$store.dispatch('SIGN_UP', {
        auth: {
          login: this.login,
          pass: this.pass
        },
        name: this.name,
        surname: this.surname,
        birthday: this.birthday
      })
    },
    toggle () {
      this.$emit('toggleRegister')
    }
  }
}
</script>

<style>

#container {
  width: 600px;
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

input[type="text"], input[type="password"], input[type="date"] {
  background: white;
  color: black;
  width: 90%;
  border-radius: 8px 8px 8px 8px;
  border-width: 2px;
  border-color: black;
  font-size: 15px;
}

#error {
  color: #E51C22;
  background-color: white;
}

</style>
