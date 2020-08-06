<template>
    <div>
        <b-container fluid>
            <b-row>
                <b-col >

                    <matchup />

                
                    <matchup />
                
            </b-col>
            <b-col>
                <matchup />
            </b-col>
            </b-row>
        </b-container>
    </div>
</template>

<script>
import Matchup from '../components/Matchup.vue';
export default {
  
    name: 'rybracket',
    components: {
        Matchup
    },
    data(){
        return {
            teams: ['team1', 'team2', 'team3', 'team4']
        }
    }
    
}
</script>

<style>

</style>
