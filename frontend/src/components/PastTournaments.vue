<template>
    <div>
        <!-- <h1>Past Tournaments</h1> -->
        <h3>Past Tournaments:</h3>
                    <div class="tournament" v-for="tournament in tournaments" v-bind:key="tournament.id" 
        v-bind:currentTournament="tournament" v-on:click="viewTournamentDetails(tournament)">
            <h2>{{tournament.tournamentName}}</h2>
            <h3>{{tournament.startDate}} to {{tournament.endDate}}</h3>
            <h3>{{tournament.gameName}}</h3>
            <h3>{{tournament.numberOfTeams}} teams</h3>
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
    .tournament {
        
        
        display: block;
        text-align: center;
        color: white;
        font-weight: bolder;
        text-shadow: 2px;
        font-size: 20px;
        margin-left: 300px;
        margin-right: 300px;
        background-color: rgb(8, 69, 97);
    }
    h2 {
        font-size: 30px;
    }
    h3 {
        font-size: 18px;
    }
    .tournament:hover {
        background: blueviolet;
    }
</style>