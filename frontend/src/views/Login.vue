<template>
  <div id="login" class="text-center">
    
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <div class="button">
        <button type="submit">Sign in</button>
        <router-link tag="button" class="register" :to="{ name: 'register' }">Need an account?</router-link>
      </div>
      
    </form>
    <div class="recover">
      <button type="button" v-on:click="flipRecovery()"><span v-if="showRecovery == false">
            Click Here to Recover Password</span><span v-if="showRecovery == true">Cancel Password Recovery</span></button>
      <passwordrecovery class="test" v-if="showRecovery"/>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";
import passwordrecovery from "../components/PasswordRecovery";

export default {
  name: "login",
  components: {
    passwordrecovery
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false,
      showRecovery: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status >= 400) {
            this.invalidCredentials = true;
          }
        });
    },
    flipRecovery() {
      this.showRecovery = !this.showRecovery;
    }
  }
};
</script>

<style scoped>

  h1 {
    height: 100px;
    width: 100%;
    font-size: 45px;
    text-align: center;
    background: rgba(8, 69, 97, .7);
    color: white;
    line-height: 150%;
    border-radius: 3px 3px 0 0;
    box-shadow: 0 2px 5px 1px rgba(0,0,0,0.2);
    box-sizing: border-box;
    padding: 20px;
  }
  form {
    box-sizing: border-box;
    width: 500px;
    margin-left: 15%;
    margin-bottom: 20px;
    box-shadow: 2px 2px 5px 1px rgba(0,0,0,0.2);
    padding-bottom: 40px;
    border-radius: 3px;
    background: rgba(28, 143, 158, .9);
  }
  input {
    margin: 20px 20px;
    width: 70%;
    display: block;
    border: none;
    padding: 10px 0;
    border-bottom: solid 1px;
    transition: all 0.3s cubic-bezier(.64,.09,.08,1);
    background: linear-gradient(to bottom, rgba(255,255,255,0) 96%, color 4%);
    background-position: -200px 0;
    background-size: 200px 100%;
    background-repeat: no-repeat;
    color: rgba(28, 143, 158, .9);
  }
  button {
    border: none;
    background:  rgba(8, 69, 97, .7);
    cursor: pointer;
    border-radius: 3px;
    padding: 6px;
    width: 200px;
    color: white;
    margin-left: 20px;
    margin-right: 25px;
    margin-bottom: 25px;
    box-shadow: 0 3px 6px 0 rgba(0,0,0,0.2);
  }
  button:hover {
    transform: translateY(-3px);
    box-shadow: 0 6px 6px 0 rgba(0,0,0,0.2);
    color: blue;
  }
  .sr-only {
    margin-left: 20px;
    font-weight: bold;
  }
  .register {
    margin-left: 20px;
    color: white;
  }
  .register:hover {
    color: blue;
  }
  .recover {
    margin-left: 15%;
  }
  .test {
    margin-left: 0px;
  }

</style>