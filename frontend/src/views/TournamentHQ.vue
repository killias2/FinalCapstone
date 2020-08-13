<template>
    <div>
        <button v-on:click.prevent="returnToTournamentDetails">Click Here to Return to Tournament</button>
        <bracket-editor v-bind:storeMatches="storeMatches" v-bind:tournament="tournament" />
        <bracket-display v-bind:edit-mode="true" v-bind:tournament="tournament"/>
    
    </div>
</template>

<script>
import TournamentService from '../services/TournamentService';
import BracketEditor from '../components/BracketEditor.vue';
import BracketDisplay from '../components/BracketDisplay';

export default {
    name: 'TournamentHQ',
    components:{
        BracketEditor,
        BracketDisplay
    },
    data() {
        return {
            tournament: {},
            storeMatches: []
        }
    },
    created() {
        TournamentService.getTournament(this.$route.params.id)
            .then(response => {
                this.tournament = response.data;
            }),
        TournamentService.getAllMatches(this.$route.params.id)
            .then(response => {
                this.storeMatches = response.data;
            })
    },
    methods: {
        returnToTournamentDetails(){
            this.$router.push(`/tournaments/${this.tournament.id}`);
        }
    }
}
</script>

