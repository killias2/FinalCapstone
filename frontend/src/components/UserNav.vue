<template>
    <div id="nav">
      <router-link class="nav" tag="button" v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link class="nav" tag="button" v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link class="nav" tag="button" v-bind:to="{name: 'login'}"  v-if="$store.state.token == ''">Login</router-link>
      <router-link class="nav" tag="button" v-bind:to="{name: 'register'}"  v-if="$store.state.token == ''">Register</router-link>
      <router-link class="nav" tag="button" v-bind:to="{name: 'user-profile', params: {id: this.$store.state.user.id}}" v-if="$store.state.token != ''">My Profile</router-link> 
      <!-- NEED TO ADD V-BIND ONCE MY PROFILE PAGE ADDED -->
      <router-link class="nav" tag="button" v-bind:to="{name: 'my-tournament', params: {id: this.$store.state.user.id}}" v-if="$store.state.token != ''">My Tournaments</router-link>
    </div>
</template>

<script>
export default {
  methods: {
    myProfile() {
      this.$router.push(`/users/${this.$store.state.user.id}`);
    }
  }
}
</script>

<style scoped>

  .nav {
    display: flex;
    padding: 20px;
    justify-content: space-evenly;
    color: white;
    border-radius: 25px;
    height: auto;
  }
  #nav {
      display: flex;
      justify-content: space-evenly;

  }

  .nav:hover {
    background: blueviolet;
  }
</style>