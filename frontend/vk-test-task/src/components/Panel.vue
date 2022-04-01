<template>
  <div id="panels">
    <div id="my_profile" v-on:click="redirect('/home/')">
      My profile
    </div>
    <div id="my_friends" v-on:click="redirect('/home/friends')">
      Friends
    </div>
    <div id="my_messages" v-on:click="redirect('/home/messages')">
      Messager
    </div>
    <div id="log_out" v-on:click="log_out">
      Log out
    </div>
  </div>
</template>

<script>
export default {
  name: "Panel",
  methods: {
    log_out () {
      this.$store.dispatch('LOG_OUT')
      this.$router.push({name: 'login'})
    },
    redirect (path) {
      this.$router.push({path: path})
    }
  }
}
</script>

<style scoped>

#panels {
  margin-left: 150px;
}

#panels div {
  background: rgba(78,78,78,0.96);
  font-size: 20px;
  border-radius: 8px 8px 8px 8px;
  margin-top: 20px;
  font-weight: bold;
}

#panels #log_out {
  background: rgba(255,159,208,0.96);
  color: rgba(78,78,78,0.96);
}

</style>
