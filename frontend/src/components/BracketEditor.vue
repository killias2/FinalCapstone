<template>
    <div>
        <browse-bracket-display v-bind:tournament="tournament" v-bind:edit-mode="true"/>
        <div>
            <form v-on:submit.prevent="sendWinner">
                <label for="gameSelection"> Choose a game to edit: </label>
                <select name="gameSelection" v-model="selectedMatch">
                    <option v-for="game in eligibleGames" :key="game.matchid" :value="game">
                        {{ game.matchid }} 
                    </option>
                </select><br/>
                <label for="teamSelection"> Choose a winning team: </label>
                <select name="teamSelection" v-model="winningTeam">
                    <option v-for="team in selectedMatch.teamList" :key="team.teamId" :value="team.teamId">
                        {{ team.teamName }} 
                    </option>
                </select>
                <input type="submit" value="Submit">
            </form>
        </div>
        <bracket-display v-bind:edit-mode="true"/>
    </div>
</template>
<script>
import TournamentService from '../services/TournamentService';
import BrowseBracketDisplay from "../components/BrowseBracketDisplay.vue";

export default {
    name: 'BracketEditor',
    components: {
        'BrowseBracketDisplay': BrowseBracketDisplay
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
    created() {
        TournamentService.getTournament(this.$route.params.id).then(response => {
            this.tournament = response.data;
    })
    },
    watch: {
        // selectedMatch: function(val) {
        //     let match = val;
        //     let matchId =  match.matchid;
        //     this.fetchMatch(matchId);
        // }
    },
    methods: {
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
            storeMatches: this.$store.state.currentMatches,
            selectedMatch: {},
            tournament: {}
        }
    }
}
</script>
