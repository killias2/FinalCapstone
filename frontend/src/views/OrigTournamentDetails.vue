<template>
    <div>
        <!-- <h1>Tournament Details</h1> -->
        <tournament-details />
        <add-teams/>
        <bracket-display v-bind:edit-mode="false"/>
    </div>
</template>

<script>
import TournamentDetails from "../components/TournamentDetails"
import AddTeams from '../components/AddTeams'
import BracketDisplay from '../components/BracketDisplay';
// import TournamentService from '../services/TournamentService';
// import store from "../store/index"

export default {
    components: {
        TournamentDetails,
        AddTeams,
        BracketDisplay,
    },
    data() {
        return {
            tournament: {}
        }
    },
    // created() {
    //     // if(this.$store.state.currentTournament == {}){
    //         TournamentService.getTournament(this.$route.params.id).then(response => {
    //             this.tournament = response.data;
    //         })
    //         this.$store.commit('SET_CURRENT_TOURNAMENT', {
    //         saveMe: this.tournament
    //         })
    //     // }
    // }
}
</script>