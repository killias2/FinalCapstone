<template>
  <div>
    <div class="profile">
       <h2 class="title">Welcome back, {{currentUser.username}}!</h2>
        <h3>Your Email: {{currentUser.emailAddress}}</h3>
      <router-link 
        tag="button"
        :to="{ name: '', params: {} }"
        class="btn changePassword">Change Password</router-link>
    </div>
  </div>
</template>

<script>
// import AuthService from '../services/AuthService';
export default {
    data() {
        return {
            currentUser: this.$store.state.user
        };
            
    },
    // created() {
    //     AuthService.getUser(this.$store.state.user.id).then(response => {
    //        this.user = response.data;
    //     })
    // }
}
</script>