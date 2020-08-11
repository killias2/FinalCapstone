<template>
  <div>
    <div class="tournament">
      <h2 class="title">{{currentTournament.tournamentName}}</h2>
      <h3 class="date">{{currentTournament.startDate}} to {{currentTournament.endDate}}</h3>
      <div v-show="this.user.id === this.currentTournament.tournamentOrganizerId">
        <button
          v-on:click="goToEditor"
          :to="{ name: 'tournamentHQ', params: {id: $route.params.tournamentID} }"
          class="btn editTournament"
          >Edit Tournament</button>
        <button class="btn deleteTournament" v-on:click="deleteTournament">Delete Tournament</button>
      </div>
    </div>
  </div>
</template>

<script>
import TournamentService from '../services/TournamentService';

export default {

  data() {
    return {
      currentTournament: this.$store.state.currentTournament,
      user: this.$store.state.user
    };
    
  },
  methods: {
      goToEditor() {
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

      }
  
  }
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
