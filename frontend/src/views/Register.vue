<template>
  <div id="register" class="text-center">
    
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <label for="email" class="sr-only">Email Address</label>
      <input 
        type="email" 
        class="form-control"
        placeholder="Email Address" 
        v-model="user.emailAddress"
        required 
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <!-- <div id="dropdown">
        <label class="dropdown" for="dropdown">Select Role</label>
        <div>
          <select required  v-model="user.role" name="role" id="role">
            <option value="User">User</option>
            <option value="Organizer">Organizer</option>
          </select>
        </div>
      </div> -->
      <div class="submit-buttons">
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Create Account
        </button>
        <router-link tag="button" class="login" :to="{ name: 'login' }">Have an account?</router-link>
      </div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';


export default {
  name: 'register',

  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        emailAddress: ''
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
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
  .form-control {
    margin-left: 20px;
  }
  form {
    box-sizing: border-box;
    width: 450px;
    margin-left: 15%;
    margin-bottom: 20px;
    box-shadow: 2px 2px 5px 1px rgba(0,0,0,0.2);
    padding-bottom: 20px;
    border-radius: 3px;
    background: rgba(28, 143, 158, .9);
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
  #dropdown {
    margin: 25px;
  }
  select {
    width: 25%;
    margin-top: 15px;
  }
  .submit-buttons {
    width: 79%;
    display: flex;
    color: white;
  }
  button {
    border: none;
    background: rgba(8, 69, 97, .7);
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
  .dropdown {
    font-weight: bold;

  }
  .login {
    margin-left: 25px;
    color: white;
  }
  .login:hover {
    color: blue;
  }
</style>
