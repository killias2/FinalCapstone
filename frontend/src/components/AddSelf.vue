<template>
    <div v-show="(this.teams.length < this.tournament.numberOfTeams) && $store.state.token != '' && 
            this.user.id != this.tournament.tournamentOrganizerId && !this.generalManagerArray.includes(this.user.id)
            && this.tournament.openToJoin">
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
</template>

<script>
import TeamService from '../services/TeamService'
import TournamentService from '../services/TournamentService'

export default {
    data() {
        return {
            newTeam: {
                tournamentId: this.$route.params.id,
                teamName: '',
                seed: null,
                email: '', 
                generalManagerId: null
            },
            generalManagerName: "",
            dropDownChoice: "No",
            generalManager: {},
            selectedTeam: {}
        }
    },
    props: {
        teams: Array,
        tournament: Object,
        user: Object
    },
    methods: {
        addSelfToTeam(){
            if(this.teamNameIsValid){
                this.newTeam.generalManagerId = this.user.id;
                this.newTeam.seed = null;
            }
            if(this.generalManagerIsValid){
                TeamService.addTeams(this.newTeam).then(response => {
                    if (response.status < 299) {
                        console.log('success');
                    }
                    if (this.tournament.full == false && this.teams.length == (this.tournament.numberOfTeams - 1)){
                        this.tournament.full = true;
                        TournamentService.setTournamentFull(this.tournament).then(() =>{
                            this.$router.go(0);
                        });
                    }
                    this.$router.go(0);
                })
            }
            else {
                this.$alert("Your team name may not be available. Please try again.")
                this.resetForm();
            }
        },
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
    },
    computed: {
        teamNameIsValid:
            function() {
                if(this.teamNameArray.includes(this.newTeam.teamName)) {
                    return false
                } 
                else {
                    return true;
                }
            },
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
        generalManagerArray: function() {
            let gms = []
            this.teams.forEach((team) => {
                gms.push(team.generalManagerId)
            })
            return gms;
        },
        teamNameArray: function() {
            let names = []
            this.teams.forEach((team) => {
                names.push(team.teamName);
            })
            return names;
        }
    }
}
</script>