<template>
  <div>
    <div class="tournament">
       <h2 class="title">{{currentTournament.tournamentName}}</h2>
        <h3 class="date">{{currentTournament.startDate}} to {{currentTournament.endDate}}</h3>
      <router-link
        tag="button"
        :to="{ name: 'EditTournament', params: {tournamentID: $route.params.tournamentID} }"
        class="btn editTournament">Edit Tournament</router-link>
      <button class="btn deleteTournament" v-on:click="deleteTournament">Delete Tournament</button>
    </div>
  </div>
</template>

<script>
// import TournamentService from "../services/TournamentService";


export default {
  data() {
    return {
      currentTournament: this.$store.state.currentTournament
    };
    
  },
  methods: {
    // retrieveTournament() {
    //     TournamentService
    //     .getTournament(this.$route.params.tournament.id)
    //     .catch(error => {
    //       if (error.response && error.response.status === 404) {
    //         alert(
    //           "Tournament not available."
    //         );
    //         this.$router.push("/");
    //       }
    //     });
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
  font-size: 30px; 
}
.date {
  font-size: 16px;
}

.tournament {
  text-align: center;
  display: block;
}
</style>
