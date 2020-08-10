<template>
  <div>
    <!-- <h1>User Profile</h1> -->
    <div class="profile">
       <h2 class="title">Welcome back, {{currentUser.username}}!</h2>
        <h3>Your Email: {{currentUser.emailAddress}}</h3>
      <router-link 
        tag="button"
        v-bind:to="{ name: 'password-change', params: {id: this.$store.state.user.id}}"
        class="btn changePassword">Change Password</router-link>
    </div>
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