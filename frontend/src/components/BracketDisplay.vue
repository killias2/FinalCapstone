<template>

    <bracket :rounds="fixedRounds">
        <template #player="{ player }"> 
           {{ player.seed }} {{ player.teamName }}
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
            .then(() => {
                this.fixRounds();
            })
            .then(() => {
                this.autoFill();
            })
        },
        methods: {
            fixRounds() {
                // this.fixedRounds = this.rounds;
                // if(this.matches.length >= 15){
                //     this.fixedRounds = this.rounds;
                // } 
                // else if(this.matches.length >= 7) {
                //     this.fixedRounds.shift();
                // }
                // else {
                //     this.fixedRounds.splice(-2, 2);
                // }
                for (let i = 0; i < this.numRounds; i++){
                        let tempArray = {games: []};
                        let thisRound = this.matches.filter((match) => {
                            return match.round == i;
                        })
                        thisRound.forEach(() => {
                            tempArray.games.push(this.matchObj)
                        })
                        this.fixedRounds.push(tempArray)
                    }
            },
            autoFill() { 
                    //fill out each match of the first round based on the sorted matches objects
                for (let i = 0; i < this.fixedRounds[0].games.length; i++){
                    this.fixedRounds[0].games[i].player1 = this.matches[i].teamList[0];
                    this.fixedRounds[0].games[i].player2 = this.matches[i].teamList[1];
                    if (this.matches[i].winnerTeamId){
                        //filter through this game's teamList to match that team. then, mark that team as winner in fixedRounds based on seed.
                        let winnerId = this.matches[i].teamList.filter((team) => {
                            return team.teamId == this.matches[i].winnerTeamId;
                        });
                        let winnerSeed = winnerId[0].seed;
                        // for (let team = 0; team < this.fixedRounds[0].games[i].length; team++){
                            if(this.fixedRounds[0].games[i].player1.seed == winnerSeed){
                                this.fixedRounds[0].games[i].player1.winner = true;
                                this.fixedRounds[0].games[i].player2.winner = false;
                            }
                            if(this.fixedRounds[0].games[i].player2.seed == winnerSeed){
                                this.fixedRounds[0].games[i].player2.winner = true;
                                this.fixedRounds[0].games[i].player1.winner = false;
                            }
                        }
                }
                this.currentRound++;
                //start grabbing the next round
                // i is the round index we are on, j is the game index within that round
                for(let i = 1; i < this.numRounds; i++){
                    for(let j = 0; j < this.fixedRounds[i].games.length; j++){
                    if(this.currentRoundMatches[j].teamList.length > 0){
                        this.fixedRounds[i].games[j].player1 = this.currentRoundMatches[j].teamList[0];
                    } 
                    if (this.currentRoundMatches[j].teamList.length > 1){
                        this.fixedRounds[i].games[j].player2 = this.currentRoundMatches[j].teamList[1];
                    }
                    if (this.currentRoundMatches[j].winnerTeamId){
                        //filter through this game's teamList to match that team. then, mark that team as winner in fixedRounds based on seed.
                        let winnerId = this.currentRoundMatches[j].teamList.filter((team) => {
                            return team.teamId == this.currentRoundMatches[j].winnerTeamId;
                        });
                        let winnerSeed = winnerId[0].seed;
                        // for (let team = 0; team < this.fixedRounds[0].games[i].length; team++){
                            if(this.fixedRounds[i].games[j].player1.seed == winnerSeed){
                                this.fixedRounds[i].games[j].player1.winner = true;
                                this.fixedRounds[i].games[j].player2.winner = false;
                            }
                            if(this.fixedRounds[i].games[j].player2.seed == winnerSeed){
                                this.fixedRounds[i].games[j].player2.winner = true;
                                this.fixedRounds[i].games[j].player1.winner = false;
                            }
                        }
                    }
                    this.currentRound++;
                }
            }
        },
        computed: {
            numRounds() {
                return this.matches[this.matches.length - 1].round +1;
            },
            currentRoundMatches() {
                return this.matches.filter((match) => {
                    return match.round == this.currentRound;
                })
            }
        },
        data() {
            return {
                currentRound: 0,
                sortedMatches: [],
                matches: [],
                currentTournament: this.$store.state.currentTournament,
                tournamentId: this.$store.state.currentTournament.id,
                fixedRounds: [],
                matchObj: {
                                player1: { teamId: "", teamName: "", winner: null, seed: ""},
                                player2: { teamId: "", teamName: "", winner: null, seed: ""},
                            },
            }
        }
    }
</script> 