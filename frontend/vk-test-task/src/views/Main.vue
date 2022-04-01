<template>
  <div id="main">
    <div id="left">
      <Panel/>
    </div>
    <div id="right">
      <router-view/>
    </div>
  </div>
</template>

<script>
import Profile from "@/components/Profile";
import Panel from "@/components/Panel";

export default {
  name: "Main",
  components: {Panel, Profile}
}
</script>

<style scoped>

#left {
  float: left;
  width: 35%;
}

#right {
  float: left;
  width: 65%;
}

</style>
