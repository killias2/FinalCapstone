<template>
  <div id="app" >
    <div class="header">
      <div class="banner">
              <h2>Brackets [⚾⚽🕹️⛳🃏🎱🏓]!</h2>
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
    background-image: url('~@/assets/MT5rFQ.png');
    background-position: center center;
    background-repeat: no-repeat;
    background-attachment: fixed;
    background-size: cover;
    background-color: #464646;
    padding: 25px;
    padding-bottom: 200px;
    margin: -20px;

  }
  #main {
    display: flex;
  }

  .sidebar {
    flex-basis: auto;
    min-width: 200px;
  }

  #mainbody{
    margin-left: 20px;
    flex-grow: 1;
    color: white;
    /* background-color: rgb(28, 143, 158);
    margin-right: 40px;
    border-radius: 10px; */
  }
  .banner {
    margin-left: 200px;
    margin-right: 200px;
    font-family: 'Arial Black', Arial, sans-serif;
    border-radius: 6px;
    background-color:  rgba(28, 143, 158, 0.9);
    text-align: center;
    font-size: 30px;
    color: white;
    width: auto;
  }



</style>