<template>
  <div id="app">
    <div id="nav">
      <router-link class="nav" tag="button" v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link class="nav" tag="button" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
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
