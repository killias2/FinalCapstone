<template>
    <div>
        
        <form v-on:submit.prevent="addNewTeam">
            <h2>Add Team to Tournament</h2>
            <div class="form-fields">
                <label class="title" for="teamName" required >Team Name:</label>
                <input v-model="newTeam.teamName" type="text" />
                <label class="title" for="email" required >Email Address:</label>
                <input v-model="newTeam.email" type="email" />
                <label class="title" for="seed" required>Seed:</label>
                <input v-model="newTeam.seed" type="number" min="1"/>
            </div>
            <div class="actions">
                <button v-on:click.prevent="resetForm" type="cancel">Cancel</button>
                <button type="submit">Submit</button>
            </div>
        </form>
    </div>
</template>

<script>
import TeamService from '../services/TeamService'

export default {
    data() {
        return {
            newTeam: {
                tournamentId: 1,
                teamName: '',
                seed: 0,
                email: ''
            }
        }
    },
    methods: {
        resetForm() {
            this.newTeam = {
                tournamentId: 1,
                teamName: '',
                seed: 0,
                email: ''
            };
        },
        addNewTeam() {
            TeamService.addTeams(this.newTeam).then(response => {
                if (response.status < 299) {
                 console.log('success');
                }
            });
            this.resetForm();
            this.$router.go;
        }
    }
    
}
</script>

<style scoped>
    form {
        width: 25%;
        position: fixed;
        right: 0;
        margin-right: 20px;
        margin-top: 150px;
    }
    .form-fields {
        text-align: center;
        width: auto;
    }
    h2 {
        text-align: center;
    }
    input {
        display: block;
        text-align: center;
        width: 80%;
    }
    button {
        width: 80%;
    }

</style>