<template>
  <div id="app">
    <div id="nav">
      <router-link class="nav" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link class="nav" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
    <router-view />
  </div>
</template>
<style scoped>
  #nav {
    text-align: right;
  }
</style>
<style scoped>
  #nav {
    color: blue;
  }
  .nav {
    color: blue;
  }
  .nav:hover {
    color: blueviolet;
  }
</style>