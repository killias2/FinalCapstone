<template>
    <div>
        <bracket-display />
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
import BracketDisplay from "../components/BracketDisplay.vue"
export default {
    name: 'BracketEditor',
    components: {
        'BracketDisplay': BracketDisplay
    },
    data() {
        return {
            winningTeam: {}
        }
    }
    
}
</script>
