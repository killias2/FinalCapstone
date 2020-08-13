<template>
    <div>
        <browse-tournament-details id="details" v-bind:tournament="tournament" v-bind:teams="teams" v-bind:matches="matches"/>
        <!-- <add-teams v-bind:tournament="tournament"/> -->
        <div v-if="(this.teams.length === this.tournament.numberOfTeams) && this.user.id === 
          this.tournament.tournamentOrganizerId && this.matches.length == 0">
          <button v-on:click.prevent="generateBrackets">Generate Brackets</button>
        </div>
        <add-self v-bind:tournament="tournament" v-bind:teams="teams" v-bind:user="user"/>
        <seeded-teams-list v-bind:tournament="tournament" v-bind:teams="teams"  v-if="this.tournament.isSeeded == true && this.teams.length > 0"/>
        <teams-list id="teamlist" v-bind:tournament="tournament" v-bind:teams="teams"  v-if="this.tournament.isSeeded == false && this.teams.length > 0"/>
        <bracket-display id="teamlist" v-bind:tournament="tournament" v-bind:edit-mode="editMode"/>
    </div>
</template>

<script>
import BrowseTournamentDetails from "../components/BrowseTournamentDetails"
import TeamService from '../services/TeamService'
// import AddTeams from '../components/AddTeams'
import BracketDisplay from '../components/BracketDisplay';
import TournamentService from '../services/TournamentService';
import TeamsList from '../components/TeamsList'
import SeededTeamsList from '../components/SeededTeamsList'
import AddSelf from '../components/AddSelf'

export default {
    components: {
        BrowseTournamentDetails,
        // AddTeams,
        BracketDisplay,
        TeamsList,
        SeededTeamsList,
        AddSelf
    },
  name: "tourament-details",
  data() {
    return {
      // tournament: {
      user: JSON.parse(localStorage.getItem('user')),
      matches: [],
      teams: [],
      tournament: {},
      editMode: false
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
    }),
    TeamService.viewTeams(this.$route.params.id).then(response => {
      this.teams = response.data;    
    }),
    TournamentService.getAllMatches(this.$route.params.id).then(response =>{
        this.matches = response.data;
    })
  },
  methods: {
    generateBrackets() {
      TournamentService.generateBrackets(this.tournament).then(response => {
        if (response.status < 299) {
          this.$alert("Brackets generated successfully")
          console.log('success');
        }
      })
      if(this.tournament.isFull == false){
        this.tournament.isFull = true;
        TournamentService.setTournamentFull(this.tournament).then(() =>{
          this.$router.go(0)
      })
      }
      else {
        this.$router.go(0)
      }

    }
  }
}
</script>

<style scoped>
    button {
        width: 80%;
        display: block;
        text-align: center;
    }

    #details {
      background-color: rgba(8, 69, 97, 0.9);
    }

    /* #teamlist {
      background-color: rgba(8, 69, 97, 0.9);
    } */
</style>