<template>
  <div id="app">
    <div class="header">
      <div class="banner">
              <h1>Welcome to Brackets[]!</h1>
      </div>
      <user-nav />
    </div>
    <div id="main">
      <sidebar class="sidebar" />
      <router-view id="mainbody"/>
    </div>
  </div>
</template>

<script>
import Sidebar from '@/components/Sidebar'
import UserNav from "@/components/UserNav"

export default {
  name: 'App',

  components: {
    Sidebar,
    UserNav
    
  },

  data: () => ({
    //
  }),
};
</script>



<style scoped>
  #app {
    display: flex-row;
  }
  #main {
    display: flex;
  }

  .sidebar {
    flex-basis: auto;
    min-width: 200px;
  }

  #mainbody{
    margin-left: 10px;
    flex-grow: 1;
  }


</style>