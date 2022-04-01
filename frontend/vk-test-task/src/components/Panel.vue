<template>
  <div id="panels">
    <div id="my_profile">
      My profile
    </div>
    <div id="my_friends">
      Friends
    </div>
    <div id="my_messages">
      Messager
    </div>
  </div>
</template>

<script>
export default {
  name: "Panel"
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
}


</style>