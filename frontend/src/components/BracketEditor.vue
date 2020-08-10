<template>
    <div>
        <bracket-display v-bind:edit-mode="true"/>
        <div>
            <p> click on a game to enter the winner </p>
            <label for="winnerInput"> Enter winner of game: </label>
            <select name="winnerInput" v-model="winningTeam">
                <option v-for="team in teamsInMatch" :key="team.id">{{ team.teamName }} </option>
            </select>
        </div>
    </div>
</template>
<script>
import TournamentService from '../services/TournamentService';
import BracketDisplay from "../components/BracketDisplay.vue"
export default {
    name: 'BracketEditor',
    components: {
        'BracketDisplay': BracketDisplay
    },
    watch: {
        selectedMatch: function(val) {
            this.fetchMatch(val);
        }
    },
    methods: {
        fetchMatch(matchId){
            TournamentService.getMatchFromId(matchId)
                .then(response => {
                this.selectedMatchTeams = response.data;
                })
        }
    },
    data() {
        return {
            selectedMatchTeams: [],
            winningTeam: {},
            selectedMatch: this.$store.state.selectedMatchId
        }
    }
    
}
</script>
