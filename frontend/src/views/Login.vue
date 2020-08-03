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

      <button type="submit">Sign in</button>
      <div >
        <router-link class="register" :to="{ name: 'register' }">Need an account?</router-link>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
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

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
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
    background: rgb(28, 143, 158);
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
    margin: auto;
    box-shadow: 2px 2px 5px 1px rgba(0,0,0,0.2);
    padding-bottom: 40px;
    border-radius: 3px;
  }
  input {
    margin: 25px 25px;
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
    color: rgb(28, 143, 158);
  }
  button {
    border: none;
    background: rgb(28, 143, 158);
    cursor: pointer;
    border-radius: 3px;
    padding: 6px;
    width: 200px;
    color: white;
    margin-left: 25px;
    margin-right: 25px;
    margin-bottom: 25px;
    box-shadow: 0 3px 6px 0 rgba(0,0,0,0.2);
  }
  button:hover {
    transform: translateY(-3px);
    box-shadow: 0 6px 6px 0 rgba(0,0,0,0.2);
  }
  .sr-only {
    margin-left: 25px;
    font-weight: bold;
  }
  .register {
    margin-left: 25px;
    color: blue;
  }
  .register:hover {
    color: blueviolet;
  }

</style>