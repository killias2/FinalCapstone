<template>
    <div>
        <h1>Add Team to Tournament</h1>
        <form v-on:submit.prevent="addNewTeam">
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
        }
    }
    
}
</script>

<style scoped>
    form {
        width: auto;
    }
    .form-fields {
        display: flex;
        width: auto;
    }

</style>