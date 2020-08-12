<template>
    <div>
        <!-- <add-teams v-bind:tournament="tournament" v-bind:teams="teams" v-bind:user="user"/> -->
        <button v-on:click.prevent="returnToTournamentDetails">Click Here to Return to Tournament</button>
        <edit-remove-teams v-if="this.user.id === this.tournament.tournamentOrganizerId"
            v-bind:teams="teams" v-bind:tournament="tournament"/>
        <edit-add-teams v-show="(this.teams.length < this.tournament.numberOfTeams) && $store.state.token != '' 
        && this.user.id === this.tournament.tournamentOrganizerId" v-bind:teams="teams" v-bind:tournament="tournament"/>

        <seeded-teams-list v-bind:tournament="tournament" v-bind:teams="teams"  v-if="this.tournament.isSeeded == true && this.teams.length > 0"/>
        <teams-list v-bind:tournament="tournament" v-bind:teams="teams"  v-if="this.tournament.isSeeded == false && this.teams.length > 0"/>
    </div> 
</template>

<script>
// import AddTeams from '../components/AddTeams'
import EditRemoveTeams from '../components/EditRemoveTeams'
import EditAddTeams from '../components/EditAddTeams'
import TeamsList from '../components/TeamsList'
import SeededTeamsList from '../components/SeededTeamsList'
import TeamService from '../services/TeamService'
import TournamentService from '../services/TournamentService'
// import AuthService from '../services/AuthService'

export default {
    components: {
        EditAddTeams,
        EditRemoveTeams,
        // AddTeams,
        TeamsList,
        SeededTeamsList
    },
    data(){
        return{
            tournament: {},
            teams: [],
            user: JSON.parse(localStorage.getItem('user'))
        }
    },
    created() {
        TournamentService.getTournament(this.$route.params.id).then(response => {
            this.tournament = response.data;
        })
        TeamService.viewTeams(this.$route.params.id).then(response => {
            this.teams = response.data;    
        })
    },
    methods: {
        returnToTournamentDetails(){
            this.$router.push(`/tournaments/${this.tournament.id}`);
        }
    }
}
</script>