<template>
    <div>
        <bracket-display v-bind:edit-mode="true"/>
        <div>
            <form v-on:submit.prevent="sendWinner">
                <label for="winnerInput"> Enter winner of game: </label>
                    <select name="winnerInput" v-model="winningTeam">
                        <option v-for="team in selectedMatch.teamList" :key="team.id" :value="team.teamId">{{ team.teamName }} </option>
                    </select>
                <input type="submit" value="Submit">
            </form>
        </div>
    </div>
</template>
<script>
import TournamentService from '../services/TournamentService';
import BracketDisplay from "../components/BracketDisplay.vue";

export default {
    name: 'BracketEditor',
    components: {
        'BracketDisplay': BracketDisplay
    },
    computed: {
        selectedMatch: function() {
            return this.$store.state.selectedMatch;
        },
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
            selectedGame: {},
            tournament: {}
        }
    }
}
</script>
