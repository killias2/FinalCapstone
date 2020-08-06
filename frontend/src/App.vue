<template>
<<<<<<< HEAD
  <v-app>
    <router-view> </router-view>
  </v-app>
</template>

<script>


export default {
  name: 'App',

  components: {
    
  },

  data: () => ({
    //
  }),
};
</script>
=======
  <div id="app">
    <div id="nav">
      <router-link class="nav" tag="button" v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link class="nav" tag="button" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>

<style scoped>

  #nav {
    color: blue;
    text-align: right;
  }
  .nav {
    color: white;

  }
  .nav:hover {
    background: blueviolet;
  }
</style>
>>>>>>> f2fc68e0d488e00f84b538b4190b662ba3cb2ecd
