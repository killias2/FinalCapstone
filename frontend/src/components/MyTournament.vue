<template>
    <div class="teamList">
        <h1>My Tournaments</h1>
        <div class="team" v-for="team in teams" v-bind:key="team.teamname">
            <h2>Name: {{team.teamName}}</h2>
            <h3>Seed: {{team.seed}}</h3>
        </div>
    </div>
</template>

<script>
import TeamService from '../services/TeamService';

export default {
    data() {
        return {
            teams: []
        };
    },
    created() {
        TeamService.viewTeams(1).then(response => {
            this.teams = response.data;
        })
    }
}
</script>

<style scoped>

</style>