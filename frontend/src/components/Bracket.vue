<template>

    <bracket :rounds="rounds">
        <template #player="{ player }"> 
            {{ player.name }}
        </template>
        <template #player-extension-bottom="{ match }">
            Game time {{ match.title }}
        </template>
    </bracket>

</template>
 
<script>
    import Bracket from "vue-tournament-bracket";
    import TournamentService from '../services/TournamentService';
    const rounds = [
        //quarter finals 
        {
            games: [
                {
 
                    player1: { id: "1", name: "Competitor 1" },
                    player2: { id: "8", name: "Competitor 8"},
                },
                {
 
                    player1: { id: "4", name: "Competitor 4", winner: false },
                    player2: { id: "5", name: "Competitor 5", winner: true },
                },
                {
 
                    player1: { id: "3", name: "Competitor 3" },
                    player2: { id: "6", name: "Competitor 6"},
                },
                {
 
                    player1: { id: "2", name: "Competitor 2", winner: false },
                    player2: { id: "7", name: "Competitor 7", winner: true },
                }
            ]
        },
        //Semi finals
        {
            games: [
                {
 
                    player1: { id: "1", name: "Competitor 1" },
                    player2: { id: "4", name: "Competitor 4"},
                },
                {
 
                    player1: { id: "5", name: "Competitor 5"},
                    player2: { id: "8", name: "Competitor 8"},
                }
            ]
        },
        //Final
        {
            games: [
                {
 
                    player1: { },
                    player2: { },
                }
            ]
        }
    ];
 
    export default {
        components: {
            Bracket
        },
        created() {
        TournamentService.getAllMatches().then(response => {
            this.matches = response.data;
        })
        TournamentService.getAllTeams().then(response => {
            this.teams = response.data;
        })
        },
        data() {
            return {
                rounds: rounds,
                matches: [],
                teams: []

            }
        }
    }
</script> 