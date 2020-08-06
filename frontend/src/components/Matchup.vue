<template>
    <div id="matchup">
        <bracket-team />
        <bracket-team />
    </div>
</template>

<script>
import BracketTeam from '../components/BracketTeam.vue';
export default {
  
    name: 'matchup',
    components: {
        BracketTeam
    },
    data(){
        return {
        }
    }
}

</script>
<style>

#matchup{
    display: flex;
    flex-direction: column;
    align-items: flex-start;
}



</style>