<template>
    <form class="password-recovery" @submit.prevent="sendEmail">
        <h6 id="pw_text">If you cannot remember your password, please enter your username and email address and click the link
        below to have a temporary password sent to your email address</h6>
        <div
        class="alert alert-danger"
        role="alert"
        v-if="wasError"
        >{{errorMessage}}</div>
        <div class="alert alert-success" role="alert" v-if="recoverySubmitted">A new password has been generated and sent to your email!</div>
        <label>Email Address:</label>
        <input type="email" name="user_email" class="form-control" v-model="password_request.emailAddress" required/>
        <label>Username:</label>
        <input type="text" class="form-control" v-model="password_request.userName" required/>
        <button type="submit" value="Send">Submit Request</button>
    </form>
</template>

<script>
import emailService from "../services/EmailService";

export default {
    name: "passwordrecovery",
    data() {
        return {
            password_request: {
                userName: "",
                emailAddress: ""
            },
            wasError: false,
            errorMessage: "",
            recoverySubmitted: false
        }
    },
    methods: {
        sendEmail() {
            emailService
            .generatePassword(this.password_request)
            .then(response => {
                if (response.status == 200){
                    this.recoverySubmitted = true;
                }
            })
            .catch(error => {
                const response = error.response
                if (response.status === 401) {
                    this.errorMessage = "Your User Doesn't Exist";
                    this.wasError = true;
                }
                else if (response.status === 500) {
                    this.errorMessage = "Your User and Email Do Not Match";
                    this.wasError = true;
                }
            });
        }
    }
}
</script>

