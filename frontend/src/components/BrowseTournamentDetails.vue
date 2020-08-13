<template>
  <div>
    <div class="tournament" >
       <h2 class="title">{{currentTournament.tournamentName}}</h2>
        <h3 class="date">{{currentTournament.startDate}} to {{currentTournament.endDate}}</h3>
        <div class="winner" v-if="currentTournament.isComplete == true">
          <h3>This Tournament is complete!</h3>
          <h3>The winning team is: 👑 {{winningTeam.teamName}} 👑 !</h3>
          <h3>Congratulations!</h3>
        </div>
      <div v-if="user.id == tournament.tournamentOrganizerId">
        <button
          v-on:click="goToEditor"
          :to="{ name: 'tournamentHQ', params: {id: $route.params.tournamentID} }"
          class="btn editTournament">Set Match Winners</button>
        <button class="btn deleteTournament" v-on:click="deleteTournament"
        v-if="this.matches.length == 0">Delete Tournament</button>
        <button v-on:click="goToEditTeams"
          :to="{ name: 'edit-teams', params: {id: $route.params.tournamentID} }"
          class="btn editTournament"
          v-if="this.matches.length == 0">Edit Teams</button>
      </div>
    </div>
  </div>
</template>

<script>
import TournamentService from '../services/TournamentService';


export default {
  data() {
    return {
      currentTournament: this.tournament,
      user: JSON.parse(localStorage.getItem('user'))
    };
  },
  computed: {
        winningTeam:
            function() {
                if(this.currentTournament.isComplete == true) {
                  return this.teams.find((team) => {
                    return this.currentTournament.winnerTeamId == team.teamId
                  })
                }
                else {
                  return null
                }
            }
  },
  props: {
    tournament: Object,
    teams: Array,
    matches: Array
  },
  created() {
    TournamentService.getTournament(this.$route.params.id).then(response => {
      this.currentTournament = response.data;
    })
  },
  methods: {
      goToEditor() {
          this.$store.commit('SET_CURRENT_TOURNAMENT', {
            saveMe: this.currentTournament
          })
          this.$router.push(`/tournamentHQ/${this.currentTournament.id}`);
        },
      deleteTournament() {
        this.$confirm("Are you sure?").then(() => {
          TournamentService.deleteTournament(this.currentTournament).then(response => {
          if (response.status < 299) {
            this.$alert("Tournament successfully deleted")
            console.log('success')
          }
          this.$router.push({name: 'my-tournament'})
          })
        })
      },
      goToEditTeams() {
        this.$router.push(`/tournamentteams/${this.tournament.id}`);
      }
    },
  }
</script>

<style>

.btn.editTournament {
  color: #fff;
  background-color: #508ca8;
  border-color: #508ca8;
  margin-bottom: 10px;
}
.btn.deleteTournament {
  color: #fff;
  background-color: #ef031a;
  border-color: #ef031a;
  margin-bottom: 10px;
}
.title {
  font-size: 50px; 
}
.date {
  font-size: 20px;
}

.tournament {
  text-align: center;
  display: block;
}

</style>
