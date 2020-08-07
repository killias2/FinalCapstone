<template>

    <bracket :rounds="fixedRounds">
        <template #player="{ player }"> 
            {{ player.name }} {{ player.id }} <!-- only show if seeded -->
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
        name: 'BracketDisplay',
        components: {
            Bracket
        },
        mounted() {
         //Promise.all(
            TournamentService.getAllMatches(this.tournamentId)
                .then(response => {
                this.matches = response.data;
                })
            // .then(() => {
            // TournamentService.getAllTeams(this.tournamentId)
            //     .then(response => {
            //         this.teams = response.data;
            //     })
            // })
            .then(() => {
                this.fixRounds()
            })
         //)
            .then(() => {
                this.autoFill()
            });
            
        },
        methods: {
            fixRounds() {
                this.fixedRounds = this.rounds;
                if(this.matches.length >= 15){
                    this.fixedRounds = this.rounds;
                } 
                else if(this.matches.length >= 7) {
                    this.fixedRounds.shift();
                    
                }
                else {
                    this.fixedRounds.splice(-2, 2);
                }
            },
            autoFill() { 
                for (let i = 0; i < this.fixedRounds[0].games.length; i++){
                    this.fixedRounds[0].games[i].player1.name = this.matches[i].teamList[0].teamName;
                    this.fixedRounds[0].games[i].player1.id = toString(this.matches[i].teamsList[0].seed);
                    this.fixedRounds[0].games[i].player2.name = this.matches[i].teamList[1].teamName;
                    this.fixedRounds[0].games[i].player2.id = toString(this.matches[i].teamsList[1].seed);
                    // if win, mark em.
                    // if(this team wins make them winner and the other team loser then do opposite){
                    // this.fixedRounds[0].games[i].player1.winner = this.matches[i].;
                    // this.fixedRounds[0].games[i].player1.winner = this.matches[i].teamList[1].teamName;
                    // }
                    //still need seed
                }
            }
        },
        data() {
            return {
                matches: [],
                teams: [],
                currentTournament: this.$store.state.currentTournament,
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