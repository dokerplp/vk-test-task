<template>
  <div id="app">
    <header>{{auth}}</header>
    <div id="content">
      <Main v-if="auth"/>
      <Auth v-else />
    </div>
    <footer><a href="https://github.com/dokerplp/vk-test-task">Link to GitHub project</a></footer>
  </div>
</template>

<script>

import Auth from '@/views/Auth'

export default {
  components: { Auth },
  computed: {
    auth () {
      return this.$store.getters.AUTH
    }
  }
}
</script>

<style>
#content {
  position: relative
}

header, footer {
  background-color: rgba(78,78,78,0.96);
  color: rgba(255,159,208,0.96);
}

header {
  font-weight: bold;
  font-size: 200%;
}

footer {
  font-size: 120%;
  position: absolute;
  bottom: 0;
  width: 100%;
}
footer * {
  font-weight: bold;
  background-color: rgba(78,78,78,0.96);
  color: rgba(255,159,208,0.96);
}

body {
  margin: 0; padding: 0;
}

* {
  background-color: rgb(164, 164, 164);
  font-family: "ALS Schlange sans", monospace;
  color: aliceblue;
  font-weight: normal;
  text-align: center;
}

</style>
