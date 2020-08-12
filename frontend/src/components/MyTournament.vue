<template>
    <div class="list">
        <!-- <h1>My Tournaments</h1> -->
        <!-- <div class="team" v-for="team in teams" v-bind:key="team.teamname">
            <h2>Name: {{team.teamName}}</h2>
            <h3>Seed: {{team.seed}}</h3>
        </div> -->
        <div class="tournaments" v-for="tournament in tournaments" v-bind:key="tournament.tournamentName">
            <h2>{{tournament.tournamentName}}</h2>
            <h3>Game/Sport: {{tournament.gameName}}</h3>
            <h3>Start Date: {{tournament.startDate}}</h3>
            <h3>End Date: {{tournament.endDate}}</h3>
            <h3>Number of Teams:{{tournament.numberOfTeams}}</h3>
            <button v-bind:currentTournament="tournament" v-on:click="viewTournamentDetails(tournament)">Tournament Details</button><br>
        </div>
    </div>
</template>

<script>
import TournamentService from '../services/TournamentService';

export default {
    data() {
        return {
            teams: [],
            tournaments: []
        };
    },
    created() {
        TournamentService.getUsersTournaments(this.$store.state.user.id).then(response => {
            this.tournaments = response.data;
        })
    },
    methods: {
        viewTournamentDetails(tournament) {
            this.$store.commit('SET_CURRENT_TOURNAMENT', {
            saveMe: tournament
            })
            this.$router.push(`/tournaments/${tournament.id}`);
        }
    }
}
</script>

<style scoped>
    .list{
        margin-left: 5%;
        display: grid;
        grid-template-columns: 1fr 1fr;
        grid-gap: 20px;
        margin-top: 25px;
    }
    .tournaments{
        background-color: rgba(28, 143, 158, 0.9);
        border-radius: 6px;
        font-family: 'Arial Narrow', Arial, sans-serif;
        width: 300px;
    }
    button{
        background-color: rgb(8, 69, 97);
    }
    .tournaments>h2{
        padding-top: 10px;
        text-align:center;
    }
    .tournaments>h3{
        padding-left: 20px;
    }

</style>