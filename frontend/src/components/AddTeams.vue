<template>
    <div class="add-teams">
        <div v-show="(this.teams.length < this.currentTournament.numberOfTeams) && $store.state.token != '' && this.user.id === this.currentTournament.tournamentOrganizerId">
            <form v-on:submit.prevent="addNewTeam" >
                <h2>Add Team to Tournament</h2>
                <div class="form-fields">
                    <label class="text-field" for="teamName" required >Team Name:</label>
                    <input v-model="newTeam.teamName" type="text" />
                    <div class="form-element">
                        <label class=".text-field" for="dropdown">Does this team have a registered General Manager?</label>
                        <div>
                            <select v-model="dropDownChoice" required name="drowpdown-choice" class="dropdown">
                                <option value="No" selected="selected">No</option>
                                <option value="Yes">Yes</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-element" v-if="this.dropDownChoice == 'No'">
                        <label class="text-field" for="email" required >Email Address:</label>
                        <input v-model="newTeam.email" type="email" />
                    </div>
                    <!-- <div class="form-element" v-if="this.dropDownChoice == 'Yes'">
                        <label class="text-field" for="general_manager" required >General Manager ID#:</label>
                        <input v-model="newTeam.generalManagerId" type="number" min="1" />
                    </div> -->
                    <div class="form-element" v-if="this.dropDownChoice == 'Yes'">
                        <label class="text-field" for="general_manager" required >General Manager Username:</label>
                        <input v-model="generalManagerName" type="text" />
                    </div>
                    <div class="form-element" v-if="this.currentTournament.isSeeded == true">
                    <label class="text-field" for="seed" required >Seed:</label>
                    <input v-model="newTeam.seed" type="number" min="1" required/>
                    </div>
                </div>
                <div class="actions">
                    <button v-on:click.prevent="resetForm" type="cancel">Cancel</button>
                    <button type="submit" >Submit</button>
                </div>
            </form>
        </div>

        <div v-show="(this.teams.length < this.currentTournament.numberOfTeams) && $store.state.token != '' && 
            this.user.id != this.currentTournament.tournamentOrganizerId && !this.generalManagerArray.includes(this.user.id)">
            <form v-on:submit.prevent="addSelfToTeam">
                <div class="form-fields">
                    <label class="text-field" >Give a Team Name and Add Your Team to this Tournament!</label>
                    <br>
                    <br>
                    <label class="text-field" for="teamName">Team Name:</label>
                    <input v-model="newTeam.teamName" type="text" required />
                        <div class="actions">
                            <button v-on:click.prevent="resetForm" type="cancel">Cancel</button>
                            <button type="submit" >Submit</button>
                        </div>
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
        <table v-if="this.currentTournament.isSeeded == true">
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
        <table v-if="this.currentTournament.isSeeded == false">
            <thead>
                <tr>
                    <th class="teamName">Team Name</th>
                </tr>
            </thead>
            <tbody v-for="team in teams" v-bind:key="team.id">
                <td>{{team.teamName}}</td>
            </tbody>
        </table>
    </div>
</template>

<script>
import TeamService from '../services/TeamService'
import TournamentService from '../services/TournamentService'
import AuthService from '../services/AuthService'

export default {
    data() {
        return {
            currentTournament: this.$store.state.currentTournament,
            teams: [],
            user: this.$store.state.user,
            newTeam: {
                tournamentId: this.$route.params.id,
                teamName: '',
                seed: null,
                email: '', 
                generalManagerId: null
            },
            generalManagerName: "",
            dropDownChoice: "No",
            showGenButton: true,
            generalManager: {},
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
    generalManagerArray: function() {
        let gms = []
        this.teams.forEach((team) => {
            gms.push(team.generalManagerId)
        })
        return gms;
    },
    seedIsValid: 
        function(){
        if(this.seedsArray.includes(parseInt(this.newTeam.seed)) && this.currentTournament.isSeeded == true) {
            return false 
        } else {
            return true;
        }
    },
    // generalManagerNameIsValid:
    //     function(){
    //         if(this.generalManager.id < 1) {
    //             return false
    //         } else {
    //             return true;
    //         }
    //     },
    generalManagerIsValid:
        function() {
            if(this.generalManagerArray.includes(parseInt(this.newTeam.generalManagerId)) && this.dropDownChoice=="Yes"){
                return false
            }
            else if (this.newTeam.generalManagerId == null && this.dropDownChoice=="Yes"){
                return false
            }
            else {
                return true;
            }
        },
    emailIsValid:
        function() {
            if(this.dropDownChoice == "Yes"){
                return true
            }
            else if(this.emailArray.includes(this.newTeam.email)) {
                return false
            }
            else if (this.newTeam.email == ""){
                this.$alert("Please add an email or a General Manager")
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
            if(this.teamNameIsValid && this.emailIsValid && this.seedIsValid && this.generalManagerIsValid) {
                return true;
            }
            else {
                return false;
            }
        }
    },
    methods: {

        resetForm() {
            this.newTeam = {
                tournamentId: this.$route.params.id,
                teamName: '',
                seed: 1,
                email: '',
                generalManagerId: null
            }
            this.generalManager = {},
            this.generalManagerName = "";
        },
        getGeneralManagerID(){
            if(AuthService.getUserByName(this.generalManagerName).id < 1){
                return false
            } else {
                return true;
            }
        },
        getTeams() {
            TeamService.viewTeams(this.$route.params.id).then(response => {
            this.teams = response.data;
        })
        },
        addSelfToTeam(){
            if(this.teamNameIsValid){
                this.newTeam.generalManagerId = this.user.id;
                this.newTeam.seed = null;
                // if(this.teams.length < 1){
                //     this.newTeam.seed = 1;
                // }
                // else {
                //     this.newTeam.seed = this.teams.length + 1;
                // }
            }
            if(this.generalManagerIsValid){
                TeamService.addTeams(this.newTeam).then(response => {
                    if (response.status < 299) {
                        console.log('success');
                    }
                }).then(() => {
                    this.getTeams();
                })
                this.resetForm();
            }
        },
        addNewTeam() {
            if(this.currentTournament.isSeeded == false){
                this.newTeam.seed = null;
                // if(this.teams.length < 1){
                //     this.newTeam.seed = 1;
                // }
                // else {
                //     this.newTeam.seed = this.teams.length + 1;
                // }
            }
            if(this.dropDownChoice == "Yes"){
                AuthService.getUserByName(this.generalManagerName).then(response =>{
                    this.generalManager = response.data
                    this.newTeam.generalManagerId = this.generalManager.id
                    if(this.formIsValid){
                        TeamService.addTeams(this.newTeam).then(response => {
                            if (response.status < 299) {
                            console.log('success');
                            }
                        }).then(() => {
                            this.getTeams();
                        })
                        this.resetForm();
                    }
                    
                    else {
                        if(!this.emailIsValid) {
                            this.$alert("Please check team email to make sure it exists and is unique")
                        }
                        else if(!this.teamNameIsValid) {
                            this.$alert("Unable to submit due to duplicate team names")
                        }
                        else if(!this.generalManagerIsValid){
                            this.$alert("Unable to submit due to duplicate or non-existent general manager")
                        }
                        else if(!this.seedIsValid){
                            this.$alert("Unable to submit due to duplicate seeds")
                        }
                        else {
                            this.$alert("Please doublecheck the general manager username")
                        }
                        this.resetForm();
                    }
                })
            }
            else if(this.formIsValid) {
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
                    this.$alert("Please check team email to make sure that it exists and is unique")
                }
                else if(!this.teamNameIsValid) {
                    this.$alert("Unable to submit due to duplicate team names")
                }
                else if(!this.generalManagerIsValid){
                    this.$alert("Unable to submit due to duplicate or non-existent general manager")
                }
                else if(!this.seedIsValid){
                    this.$alert("Unable to submit due to duplicate seeds")
                }
                else {
                    this.$alert("Please doublecheck the general manager username")
                }
                this.resetForm();
            }
        },
        generateBrackets() {
            TournamentService.generateBrackets(this.currentTournament).then(response => {
                if (response.status < 299) {
                    this.$alert("Brackets generated successfully")
                    console.log('success');
                    this.$router.go(0)
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
    background-color: rgba(28, 143, 158, 0.9);
    border-radius: 6px;
    font-family: 'Arial Narrow', Arial, sans-serif;
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
        background-color: rgba(28, 143, 158, 0.9);
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