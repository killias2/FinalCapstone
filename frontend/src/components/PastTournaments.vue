<template>
    <div>
        <h1 class="header">Past Tournaments:</h1>
        <section class="tournament-card">
            <div class="tournament" v-for="tournament in tournaments" v-bind:key="tournament.id" >
                <h2>{{tournament.tournamentName}}</h2>
                <h3>{{tournament.startDate}} to {{tournament.endDate}}</h3>
                <h3>{{tournament.numberOfTeams}} teams</h3>
                <button v-bind:currentTournament="tournament" v-on:click="viewTournamentDetails(tournament)">Tournament Details</button>
            </div>
        </section>
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
        viewTournamentDetails(tournament) {
            this.$store.commit('SET_CURRENT_TOURNAMENT', {
            saveMe: tournament
            })
            this.$router.push(`/tournaments/${tournament.id}`);
        }
    },
    created() {
        TournamentService.getPastTournaments().then(response => {
           this.tournaments = response.data;
        })
    }
}
</script>

<style scoped>
    .header {
        font-size: large;
    }
    .tournament {
        background-color: rgba(28, 143, 158, 0.9);
        border-radius: 6px;
        font-family: 'Arial Narrow', Arial, sans-serif;
        width: 300px;
    }
    .tournament-card {
        margin-left: 5%;
        display: grid;
        grid-template-columns: 1fr 1fr ;
        grid-gap: 20px;
        margin-top: 25px;
    }
    button{
        background-color: rgb(8, 69, 97);
    }
    .tournament>h2 {
        padding-top: 10px;
        text-align: center;
    }
    .tournament>h3 {
        text-align: left;
        padding-left: 20px;
    }
    button:hover {
        background: blueviolet;
    }
</style>