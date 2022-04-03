<template>
  <Login v-if="!register" @toggleRegister="toggleRegister"/>
  <Register v-else @toggleRegister="toggleRegister"/>
</template>

<script>

import Login from "@/components/Login";
import Register from "@/components/Register";

export default {
  name: 'Auth',
  computed: {
    auth() {
      return this.$store.getters.AUTH
    }
  },
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
    },
    goHome() {
      this.$router.push({path: '/home/'})
    }
  },
  watch: {
    auth: function(val) {
      this.goHome()
    }
  }
}
</script>

<style>
</style>
