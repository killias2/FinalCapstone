<template>
    <div>
        <div v-show="(this.teams.length < this.currentTournament.numberOfTeams) && $store.state.token != '' && this.user.id === this.currentTournament.tournamentOrganizerId">
            <form v-on:submit.prevent="addNewTeam" >
                <h2>Add Team to Tournament</h2>
                <div class="form-fields">
                    <label class="text-field" for="teamName" required >Team Name:</label>
                    <input v-model="newTeam.teamName" type="text" />
                    <label class="text-field" for="email" required >Email Address:</label>
                    <input v-model="newTeam.email" type="email" />
                    <label v-show="this.currentTournament.isSeeded" class="text-field" for="seed" required >Seed:</label>
                    <input v-show="this.currentTournament.isSeeded" v-model="newTeam.seed" type="number" min="1" :max="this.currentTournament.numberOfTeams"/>
                    <!-- <label v-show="!this.currentTournament.isSeeded" class="text-field" for="seed" required >Temporary Seed:</label>
                    <input v-show="!this.currentTournament.isSeeded" v-model="newTeam.seed" type="number" min="1" :max="this.currentTournament.numberOfTeams"/> -->
                </div>
                <div class="actions">
                    <button v-on:click.prevent="resetForm" type="cancel">Cancel</button>
                    <button type="submit" >Submit</button>
                </div>
            </form>
        </div>
        <div v-if="(this.teams.length === this.currentTournament.numberOfTeams) && this.user.id === this.currentTournament.tournamentOrganizerId">
            <button v-if="(showGenButton)" v-on:click.prevent="generateBrackets">Generate Brackets</button>
        </div>
        <div id="remove" v-if="this.user.id === this.currentTournament.tournamentOrganizerId">
            <select v-model="selectedTeam.teamId" name="all-teams" class="dropdown">
                <option v-for="team in teams"  v-bind:key="team.teamId" :value="team.teamId" >{{team.teamName}}</option>
            </select>
            <button type="submit" v-on:click.prevent="removeTeamFromTournament" >Remove Team</button>
        </div>
        <table>
            <thead>
                <tr>
                    <th class="teamName">Team Name</th>
                    <th  class="seed">Seed</th>
                </tr>
            </thead>
            <tbody v-for="team in teams" v-bind:key="team.teamId">
                <td>{{team.teamName}}</td>
                <td  class="seed">{{team.seed}}</td>
            </tbody>
        </table>
    </div>
</template>

<script>
import TeamService from '../services/TeamService'
import TournamentService from '../services/TournamentService'

export default {
    data() {
        return {
            currentTournament: this.$store.state.currentTournament,
            teams: [],
            user: this.$store.state.user,
            newTeam: {
                tournamentId: this.$route.params.id,
                teamName: '',
                // seed: 1,
                email: '', 
                generalManagerId: this.$store.state.user.id
            },
            showGenButton: true,
            selectedTeam: {}
        }
    },
    created() {
       
        TeamService.viewTeams(this.$route.params.id).then(response => {
            this.teams = response.data;
        })
 
    },
    computed: {
    seedsArray: function(){
         let seeds = []
         this.teams.forEach((team) => {
            seeds.push(team.seed);  
         })
         return seeds;
    },
    emailArray: function() {
        let emails = []
        this.teams.forEach((team) => {
            emails.push(team.email);
        })
        return emails;
    },
    teamNameArray: function() {
        let names = []
        this.teams.forEach((team) => {
            names.push(team.teamName);
        })
        return names;
    },
    seedIsValid: 
        function(){
        if(this.seedsArray.includes(parseInt(this.newTeam.seed))) {
            return false 
        } else {
            return true;
        }
    },
    emailIsValid:
        function() {
            if(this.emailArray.includes(this.newTeam.email)) {
                return false
            }   
            else {
                return true;
                }
        },

    teamNameIsValid:
        function() {
            if(this.teamNameArray.includes(this.newTeam.teamName)) {
                return false
            } 
            else {
                return true;
            }
        },
    formIsValid:
        function() {
            if(this.teamNameIsValid && this.emailIsValid && this.seedIsValid) {
                return true;
            }
            else {
                return false;
            }
        }
    },
    methods: {

        resetForm() {
            if(this.currentTournament.isSeeded) {
                this.newTeam = {
                tournamentId: this.$route.params.id,
                teamName: '',
                seed: 1,
                email: ''
                }
            }
            else {
                this.newTeam = {
                tournamentId: this.$route.params.id,
                teamName: '',
                email: ''
                }
            }
        },
        getTeams() {
            TeamService.viewTeams(this.$route.params.id).then(response => {
            this.teams = response.data;
        })
        },
        addNewTeam() {
            if(this.formIsValid) {
                TeamService.addTeams(this.newTeam).then(response => {
                if (response.status < 299) {
                console.log('success');
                }
                
                }).then(() => {
                    this.getTeams();
            });
            this.resetForm();
            }
            else {
                if(!this.emailIsValid) {
                    this.$alert("Unable to submit due to duplicate email addresses")
                }
                else if(!this.teamNameIsValid) {
                    this.$alert("Unable to submit due to duplicate team names")
                }
                else {
                    this.$alert("Unable to submit due to duplicate seeds")
                }
            }
        },
        generateBrackets() {
            TournamentService.generateBrackets(this.currentTournament).then(response => {
                if (response.status < 299) {
                    this.$alert("Brackets generated successfully")
                    console.log('success');
                }
            })
        },
        removeTeamFromTournament() {
            this.$confirm("Are you sure?").then(() => {
                TeamService.removeTeams(this.selectedTeam).then(response => {
                    if (response.status < 299) {
                    this.$alert("Team successfully removed")
                    console.log('success')
                    this.teams.filter((team) => {
                        return team.teamName != this.selectedTeam.teamName;
                    })
                    location.reload();
                    } 
                })
            
            })
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

    .team-list {
        text-align: left;
        margin-left: 10px;
    }

    table {
        font-family: 'Open Sans', sans-serif;
        width: 300px;
        border-collapse: collapse;
        border: 3px solid #44475C;
        margin: 10px 10px 0 10px;
    }

    table th {
        text-transform: uppercase;
        text-align: left;
        background: #44475C;
        color: #FFF;
        cursor: pointer;
        border: 3px solid #44475C;
        padding: 8px;
        min-width: 30px;
    }
    table td {
        text-align: left;
        padding: 8px;
        border: 1px solid #44475C;
        border-right: 2px solid #7D82A8;
    }
    .seed {
        text-align: center;
    }
    .teamName {
        text-align: center;
    }

</style>