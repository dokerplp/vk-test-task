<template>
  <Login v-if="!register" @toggleRegister="toggleRegister"/>
  <Register v-else @toggleRegister="toggleRegister"/>
</template>

<script>

import Login from "@/components/Login";
import Register from "@/components/Register";

export default {
  name: 'Auth',
  data () {
    return {
      register: false
    }
  },
  components: {
    Register,
    Login
  },
  methods: {
    toggleRegister() {
      this.register = !this.register
    }
  }
}
</script>

<style>
</style>
