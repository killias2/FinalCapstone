<template>
  <div>
    <div>
       <h2>{{currentTournament.tournamentName}}</h2>
        <h3>Start Date: {{currentTournament.startDate}}</h3>
        <h3>End Date: {{currentTournament.endDate}}</h3>
      <router-link
        tag="button"
        :to="{ name: 'EditTournament', params: {tournamentID: $route.params.tournamentID} }"
        class="btn editTournament"
      >Edit Tournament</router-link>
      <button class="btn deleteTournament" v-on:click="deleteTournament">Delete Tournament</button>
    </div>
  </div>
</template>

<script>



export default {
  data() {
    return {
      currentTournament: this.$store.state.currentTournament
    };
    
  },
  methods: {
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
</style>
