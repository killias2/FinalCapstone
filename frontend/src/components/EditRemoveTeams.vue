<template>
    <div>
        <select v-model="selectedTeam.teamId" name="all-teams" class="dropdown">
            <option v-for="team in teams"  v-bind:key="team.teamId" :value="team.teamId" >{{team.teamName}}</option>
        </select>
        <button type="submit" v-on:click.prevent="removeTeamFromTournament" >Remove Team</button>
    </div>
</template>

<script>
import TeamService from '../services/TeamService'

export default {
    props: {
        tournament: Object,
        teams: Array
    },
    data () {
        return {
            selectedTeam: {}
        }
    },
    methods: {
        removeTeamFromTournament() {
            this.$confirm("Are you sure?").then(() => {
                TeamService.removeTeams(this.selectedTeam).then(response => {
                    if (response.status < 299) {
                        this.$alert("Team successfully removed")
                        console.log('success')
                        this.$router.go(0)
                // this.teams.filter((team) => {
                //         return team.teamName != this.selectedTeam.teamName;
                //     })
                //     location.reload();
                    } 
                })
            
            })
        }
    }
}
</script>