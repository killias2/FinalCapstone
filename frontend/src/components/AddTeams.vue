<template>
    <div>
        
        <form v-on:submit.prevent="addNewTeam">
            <h2>Add Team to Tournament</h2>
            <div class="form-fields">
                <label class="text-field" for="teamName" required >Team Name:</label>
                <input v-model="newTeam.teamName" type="text" />
                <label class="text-field" for="email" required >Email Address:</label>
                <input v-model="newTeam.email" type="email" />
                <label class="text-field" for="seed" required>Seed:</label>
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
            tournament: [],
            newTeam: {
                tournamentId: this.$route.params.id,
                teamName: '',
                seed: 0,
                email: ''
            }
        }
    },
    created() {

    },
    methods: {
        resetForm() {
            this.newTeam = {
                tournamentId: this.$route.params.id,
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
        width: 300px;
        /* position: fixed; */
        right: 0;
        margin-right: 20px;
        margin-top: 50px;
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
        margin-left: 10%;
    }
    button {
        width: 80%;
        display: block;
        text-align: center;
    }
    .actions {
        display: flex;
        width: 90%;
        margin-left: 5%;
        margin-top: 20px;
    }
    .text-field {
        size: large;
        padding-top: 10px;
    }


</style>