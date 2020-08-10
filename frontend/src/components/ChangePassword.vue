<template>
    <form class="change-password" v-on:submit.prevent="changePassword">
        <div
        class="alert alert-danger"
        role="alert"
        v-if="wasError"
        >{{errorMessage}}</div>
        
        <label for="oldpassword" class="sr-only">Original Password</label>
        <input
            type="password"
            id="oldpassword"
            class="form-control"
            placeholder="Original Password"
            v-model="passwordChange.oldPassword"
        required
        />
        <label for="newpassword" class="sr-only">New Password</label>
        <input
            type="password"
            id="newpassword"
            class="form-control"
            placeholder="New Password"
            v-model="passwordChange.newPassword"
        required
        />
        <input
            type="password"
            id="confirmPassword"
            class="form-control"
            placeholder="Confirm Password"
            v-model="passwordChange.confirmPassword"
            required
        />

        <button type="submit" value="Send">Submit Request</button>
    </form>
</template>

<script>
import passwordService from "../services/PasswordService";

export default {
    name: 'password-change',
    data() {
        return {
            currentUser: this.$store.state.user,
            passwordChange: {
                oldPassword: "",
                newPassword: "",
                confirmPassword: ""
            },
            wasError: false,
            errorMessage: "There was an error",
        };
    },
    methods: {
        changePassword(){
            if (this.passwordChange.newPassword != this.passwordChange.confirmPassword) {
                this.wasError = true;
                this.registrationErrorMsg = 'Password & Confirm Password do not match.';
            }
            else {
                passwordService.changePassword(this.passwordChange, this.currentUser.id)
                .then((response) => {
                    if (response.status == 200) {
                        this.$router.push({
                        path: '/',
                        });
                    }
                })
                .catch((error) => {
                    const response = error.response;
                    this.wasError = true;
                    if (response === 401){
                        this.errorMessage = "There was an issue with the request";
                    }
                })
            }
        }
    }
}
</script>