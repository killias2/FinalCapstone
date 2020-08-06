<template>
    <div>
        <h1>All Tournaments</h1>
        <div class="tournament" v-for="tournament in tournaments" v-bind:key="tournament.id">
            <h2>{{tournament.tournamentName}}</h2>
            <h3>Start Date: {{tournament.startDate}}</h3>
            <h3>End Date: {{tournament.endDate}}</h3>
        </div>
    </div>
</template>

<script>
import TournamentService from '../services/TournamentService';

export default {
    data() {
        return {
            tournaments: []
        };
    },
    methods: {
        viewTournamentDetails(tournamentID) {
            this.$router.push(`/tournaments/${tournamentID}`);
        }
    },
    created() {
        TournamentService.viewTournaments().then(response => {
            this.tournaments = response.data;
        })
    }
}
</script>

<style scoped>
    .tournament {
        display: block;
        text-align: center;
        color: black;
        font-weight: bolder;
        text-shadow: 2px;
        font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
        font-size: 20px;
    }
</style>