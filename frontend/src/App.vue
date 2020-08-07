<template>
  <div id="app">
    <router-view />
    <div id="nav">
      <router-link class="nav" tag="button" v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link class="nav" tag="button" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link class="nav" tag="button" v-bind:to="{ name: 'my-tournament'}" v-if="$store.state.token != ''">My Tournaments</router-link>
    </div>
    
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