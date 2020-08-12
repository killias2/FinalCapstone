<template>
  <div class="profile">
    <h2 class="title">Welcome back, {{currentUser.username}}!</h2>
    <h3 class="email">Your Email: {{currentUser.emailAddress}}</h3>
    <router-link 
      tag="button"
      v-bind:to="{ name: 'password-change', params: {id: this.$store.state.user.id}}"
      class="btn-changePassword">Change Password</router-link>
  </div>
</template>

<script>

export default {
    data() {
        return {
            currentUser: this.$store.state.user
        };
            
    }
}
</script>

<style>
  .profile {
    background-color: rgba(28, 143, 158, 0.9);
    border-radius: 6px;
    font-family: 'Arial Narrow', Arial, sans-serif;
    width: 500px;
  }
  .title {
    margin-left: 20px;
  }
  .email {
    margin-left: 20px;
  }
  .btn-changePassword {
    background-color:  rgba(8, 69, 97, .7);
    margin-left: 20px;
  }
</style>