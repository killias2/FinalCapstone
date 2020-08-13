<template>
    <div >
        <h3 class="header">Tournaments as Organizer:</h3>
        <div class="list">
            <div class="tournaments" v-for="tournament in tournaments" v-bind:key="tournament.tournamentId">
                <h2>{{tournament.tournamentName}}</h2>
                <h3>Game/Sport: {{tournament.gameName}}</h3>
                <h3>Start Date: {{tournament.startDate}}</h3>
                <h3>End Date: {{tournament.endDate}}</h3>
                <h3>Number of Teams:{{tournament.numberOfTeams}}</h3>
                <button v-bind:currentTournament="tournament" v-on:click="viewTournamentDetails(tournament)">Tournament Details</button><br>
            </div>
        </div>
        <h3 class="header">Tournaments as Participant:</h3>
        <div class="list">
            <div class="tournaments" v-for="team in teams" v-bind:key="team.teamId">
                <h2>{{team.tournamentName}}</h2>
                <h3>{{team.sport}}</h3>
                <h3>Team Name: {{team.teamName}}</h3>
                <h3 v-show="team.seed">Seed: {{team.seed}}</h3>
                <h3 v-show="!team.seed">Tournament Unseeded</h3>
                <button v-bind:currentTeam="team" v-on:click="viewTourneyDetails(team)">Tournament Details</button><br>
            </div>
        </div>
    </div>
</template>

<script>
import TournamentService from '../services/TournamentService';
import TeamService from '../services/TeamService';

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
        TeamService.getTeamsByGmId(this.$store.state.user.id).then(response => {
            this.teams = response.data;
        })
    },
    methods: {
        viewTournamentDetails(tournament) {
            this.$store.commit('SET_CURRENT_TOURNAMENT', {
            saveMe: tournament
            })
            this.$router.push(`/tournaments/${tournament.id}`);
        },
        viewTourneyDetails(team) {
            this.$store.commit('SET_CURRENT_TOURNAMENT', {
                saveMe: team
            })
            this.$router.push(`/tournaments/${team.tournamentId}`)
        }
    }
}
</script>

<style scoped>
    .header {
        margin-left: 5%;
        margin-right: 5%;
        background-color: rgba(8, 69, 97, .7);
        font-size: xx-large;
        text-align: center;
        padding-top: 5px;
        padding-bottom: 5px;
        border-radius: 6px;
    }

    .list{
        margin-left: 5%;
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
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

    @media (max-width: 1250px) {
        .list {
            grid-template-columns: 1fr 1fr;
        }
    }

</style>