<template>
    <div>
        <div>
            <form class="form" v-on:submit.prevent="sendWinner">
                <label class="form-text" for="gameSelection"> Choose a game to edit: </label>
                <select name="gameSelection" v-model="selectedMatch">
                    <option v-for="game in eligibleGames" :key="game.matchid" :value="game">
                        {{ betterGameNumbers(game.matchid) }} 
                    </option>
                </select><br/>
                <label class="form-text" for="teamSelection"> Choose a winning team: </label>
                <select name="teamSelection" v-model="winningTeam">
                    <option v-for="team in selectedMatch.teamList" :key="team.teamId" :value="team.teamId">
                        {{ team.teamName }} 
                    </option>
                </select>
                <input type="submit" value="Submit">
            </form>
        </div>
    </div>
</template>
<script>
import TournamentService from '../services/TournamentService';
// import BracketDisplay from "../components/BracketDisplay.vue";

export default {
    name: 'BracketEditor',
    // components: {
    //     'BracketDisplay': BracketDisplay
    // },
    props: {
        tournament: Object,
        storeMatches: Array
    },
    computed: {
        // selectedMatch: function() {
        //     return this.$store.state.selectedMatch;
        // },
        eligibleGames: function(){
            return this.storeMatches.filter((match) => {
                if(!match.complete && match.teamList.length >= 2){
                    return match;
                }
            })
        }
    },
    methods: {
        betterGameNumbers(m){
                return (m + 1) - this.storeMatches[0].matchid;
        },
        fetchMatch(matchId){
            TournamentService.getMatchFromId(matchId)
                .then(response => {
                this.selectedMatchTeams = response.data;
                })
        },
        sendWinner() {
            this.selectedMatch.winnerTeamId = this.winningTeam
            TournamentService.completeMatch(this.selectedMatch).then(() => {
                // this.$forceUpdate();
                location.reload();
            })
                // .then(() => {
                //     this.$router.push('/');
                // })
        }
    },
    data() {
        return {
            selectedMatchTeams: [],
            winningTeam: 0,
            // storeMatches: this.$store.state.currentMatches,
            selectedMatch: {},
            // tournament: {}
        }
    }
}
</script>

<style scoped>

    .form{
    background-color: rgba(28, 143, 158, 0.9);
    border-radius: 6px;
    font-family: 'Arial Narrow', Arial, sans-serif;
    width: 300px;
    margin-right: 20px;
    margin-top: 50px;
}
    .form-text{
        text-align: center;
        padding-left: 20px;
    }


</style>
