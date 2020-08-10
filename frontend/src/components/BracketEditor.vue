<template>
    <div>
        <bracket-display v-bind:edit-mode="true"/>
        <div>
            <form v-on:submit="sendWinner">
                <label for="winnerInput"> Enter winner of game: </label>
                    <select name="winnerInput" v-model="winningTeam">
                        <option v-for="team in selectedMatchTeams" :key="team.id">{{ team.teamName }} </option>
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
        selectedMatchId: function() {
            return this.$store.state.selectedMatchId;
        }
    },
    watch: {
        selectedMatchId: function(val) {
            this.fetchMatch(val);
        }
    },
    methods: {
        fetchMatch(matchId){
            TournamentService.getMatchFromId(matchId)
                .then(response => {
                this.selectedMatchTeams = response.data;
                })
        },
        sendWinner() {
            
        }
    },
    data() {
        return {
            selectedMatchTeams: [],
            winningTeam: {},
        }
    }
}
</script>
