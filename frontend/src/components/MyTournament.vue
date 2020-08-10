<template>
    <div class="teamList">
        <!-- <h1>My Tournaments</h1> -->
        <!-- <div class="team" v-for="team in teams" v-bind:key="team.teamname">
            <h2>Name: {{team.teamName}}</h2>
            <h3>Seed: {{team.seed}}</h3>
        </div> -->
        <div class="tournaments" v-for="tournament in tournaments" v-bind:key="tournament.tournamentName">
            <h2>Tournament Name: {{tournament.tournamentName}}</h2>
            <h3>Game/Sport: {{tournament.gameName}}</h3>
            <h3>Start Date: {{tournament.startDate}}</h3>
            <h3>End Date: {{tournament.endDate}}</h3>
            <h3>Number of Teams:{{tournament.numberOfTeams}}</h3>
            <button v-bind:currentTournament="tournament" v-on:click="viewTournamentDetails(tournament)">Tournament Details</button><br>
        </div>
    </div>
</template>

<script>
// import TeamService from '../services/TeamService';
import TournamentService from '../services/TournamentService';

export default {
    data() {
        return {
            teams: [],
            tournaments: []
        };
    },
    created() {
        // TeamService.viewTeams(1).then(response => {
        //     this.teams = response.data;
        // }),
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

</style>