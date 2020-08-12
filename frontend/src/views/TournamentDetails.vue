<template>
    <div>
        <browse-tournament-details v-bind:tournament="tournament"/>
        <add-teams v-bind:tournament="tournament"/>
        <browse-bracket-display v-bind:tournament="tournament" v-bind:edit-mode="editMode"/>
    </div>
</template>

<script>
import BrowseTournamentDetails from "../components/BrowseTournamentDetails"
import AddTeams from '../components/AddTeams'
import BrowseBracketDisplay from '../components/BrowseBracketDisplay';
import TournamentService from '../services/TournamentService';

export default {
    components: {
        BrowseTournamentDetails,
        AddTeams,
        BrowseBracketDisplay
    },
  name: "browse-details",
  data() {
    return {
      // tournament: {

      // },
      propsObj: {
          tournament: {
          },
          editMode: false
      }
    };
    
  },
  // mounted() {
  //     this.$store.commit('SET_CURRENT_TOURNAMENT', {
  //     saveMe: this.tournament
  //     })
  // },
  created() {
      TournamentService.getTournament(this.$route.params.id).then(response => {
        this.tournament = response.data;
    })
  }
}
</script>