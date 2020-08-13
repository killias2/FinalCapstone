<template>
    <div>
        <!-- <h1>Upcoming Tournaments</h1> -->
        <h3 class="header">Upcoming Tournaments:</h3>
        <section class="tournament-card">
            <div class='tournament' v-for="tournament in tournaments" v-bind:key="tournament.id" 
                v-bind:currentTournament="tournament"
                v-bind:class="{ open: (tournament.openToJoin && !tournament.full)}">
            <h2>{{tournament.tournamentName}}</h2>
            <h3>{{tournament.startDate}} to {{tournament.endDate}}</h3>
            <h3>{{tournament.gameName}}</h3>
            <h3>{{tournament.numberOfTeams}} teams</h3>
            <h4 v-show="!tournament.openToJoin">Private Tournament</h4>
            <h4 v-show="tournament.openToJoin && !tournament.full"> Join this tournament!</h4>
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
        TournamentService.getFutureTournaments().then(response => {
           this.tournaments = response.data;
        })
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
    .tournament {
        background-color: rgba(28, 143, 158, 0.9);
        border-radius: 6px;
        font-family: 'Arial Narrow', Arial, sans-serif;
        width: 300px;
    }
    .tournament-card {
        margin-left: 5%;
        display: grid;
        grid-template-columns: 1fr 1fr 1fr;
        grid-gap: 20px;
        margin-top: 25px;
    }
    button{
        background-color: rgba(8, 69, 97, .7);
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
    .open {
        background-color: red;
    }

    @media (max-width: 1250px) {
        .tournament-card {
            grid-template-columns: 1fr 1fr;
        }
    }
   
</style>