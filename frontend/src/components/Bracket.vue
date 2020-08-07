<template>

    <bracket :rounds="fixedRounds">
        <template #player="{ player }"> 
            {{ player.name }}
        </template>
        <template #player-extension-bottom="{ match }">
            game info: {{ match.title }}
        </template>
    </bracket>

</template>
 
<script>
    import Bracket from "vue-tournament-bracket";
    import TournamentService from '../services/TournamentService';

    export default {
        components: {
            Bracket
        },
        created() {
            TournamentService.getAllMatches(this.tournamentId).then(response => {
                this.matches = response.data;
            })
            TournamentService.getAllTeams(this.tournamentId)
            .then(response => {
                this.teams = response.data;
                })
            .then(() => {
                this.fixRounds()
            })
            .then(() => {
                this.autoFill()
            });
        },
        methods: {
            fixRounds() {
                this.fixedRounds = this.rounds;
                if(this.teams.length >= 16){
                    this.fixedRounds = this.rounds;
                } 
                else if(this.teams.length >= 8) {
                    this.fixedRounds.shift();
                    
                }
                else {
                    this.fixedRounds.splice(-2, 2);
                }
            },
            autoFill() {
                for (let i = 0; i < this.fixedRounds[0].games.length; i++){
                    //this.fixedRounds[0].games[i].player1.name = this.matches[i].teamList[0];
                    this.fixedRounds[0].games[i].player2.name = "the other team";
                    // if win, mark em.
                    
                }
            }
        },
        data() {
            return {
                matches: [],
                teams: [],
                tournamentId: this.$store.state.currentTournament.id,
                fixedRounds: [],
                rounds: [
                    //sweet 16
                    {
                        games: [
                            {
                                player1: { id: "", name: "", winner: null },
                                player2: { id: "", name: "", winner: null },
                            },
                            {
            
                                player1: { id: "", name: "", winner: null },
                                player2: { id: "", name: "", winner: null  },
                            },
                            {
            
                                player1: { id: "", name: "", winner: null },
                                player2: { id: "", name: "", winner: null},
                            },
                            {
            
                                player1: { id: "", name: "", winner: null },
                                player2: { id: "", name: "", winner: null },
                            },
                                                        {
                                player1: { id: "", name: "", winner: null},
                                player2: { id: "", name: "", winner: null},
                            },
                            {
            
                                player1: { id: "", name: "", winner: null },
                                player2: { id: "", name: "", winner: null},
                            },
                            {
            
                                player1: { id: "", name: "", winner: null },
                                player2: { id: "", name: "", winner: null},
                            },
                            {
            
                                player1: { id: "", name: "", winner: null},
                                player2: { id: "", name: "", winner: null},
                            }
                        ]
                    },
                //elite 8 
                    {
                        games: [
                            {
            
                                player1: { id: "", name: "", winner: null},
                                player2: { id: "", name: "", winner: null},
                            },
                            {
            
                                player1: { id: "", name: "", winner: null},
                                player2: { id: "", name: "", winner: null},
                            },
                            {
            
                                player1: { id: "", name: "", winner: null},
                                player2: { id: "", name: "", winner: null},
                            },
                            {
            
                                player1: { id: "", name: "", winner: null},
                                player2: { id: "", name: "", winner: null},
                            }
                        ]
                    },
                    //final 4
                    {
                        games: [
                            {
            
                                player1: { id: "", name: "", winner: null},
                                player2: { id: "", name: "", winner: null},
                            },
                            {
            
                                player1: { id: "", name: "", winner: null},
                                player2: { id: "", name: "", winner: null},
                            }
                        ]
                    },
                    //Final
                    {
                        games: [
                            {
            
                                player1: {id: "", name: "", winner: null},
                                player2: {id: "", name: "", winner: null },
                            }
                        ]
                    }
                ]
            }
        }
    }
</script> 