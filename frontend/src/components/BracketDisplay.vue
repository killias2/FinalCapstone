<template >
    <div class="bracket-container">
        <bracket class="bracket" :rounds="fixedRounds">
            <template #player="{ player }"> 
               <span v-if="tournament.isSeeded" class="seed"> {{ player.seed }} 
               </span> {{ player.teamName }}
            </template>
            <template class="bottom-text" #player-extension-bottom="{ match }">
                <span v-if="match.match.matchid" class="seed">Game Number: {{ betterGameNumbers(match.match.matchid) }}
                    </span>
                <span v-else class="seed"> {{ addOne(match.title) }}
                </span>
                </template>
        </bracket>
    </div>
</template>
 
<script>
    import Bracket from "vue-tournament-bracket";
    import TournamentService from '../services/TournamentService';
    export default {
        props: {
            tournament: Object,
            matches: Array,
            editMode: Boolean
        },
        name: 'BracketDisplay',
        components: {
            Bracket
        },
        mounted() {
         //Promise.all(
            TournamentService.getAllMatches(this.$route.params.id)
                .then(response => {
                this.matches = response.data;
                this.$store.commit('SET_MATCHES', {
                    saveThis: this.matches
                })
                this.fixRounds()
                this.autoFill()
                })
            //     })
            //     })
            // .then(() => {
            // this.fixRounds()
            // this.autoFill()
            // })
        },
        methods: {
            betterGameNumbers(m){
                return (m + 1) - this.matches[0].matchid;
            },
            addOne(a){
                let lastChar = a.substr(a.indexOf(' ')+1); 
                let newRound = parseInt(lastChar) + 1;
                return `Round ${newRound}`;
            },
            selectMatch(match){
                this.test = match;
                this.$store.commit('SET_CURRENT_MATCH', {
                saveMe: match
            })
            },
            fixRounds() {
                for (let i = 0; i < this.numRounds; i++){
                    let tempArray = {games: []};
                    let thisRound = this.matches.filter((match) => {
                       return match.round == i;
                    })
                    thisRound.forEach(() => {
                        tempArray.games.push({
                            match: {},
                            player1: {id:"", teamId: "", teamName: "", winner: null, seed: ""},
                            player2: {id:"", teamId: "", teamName: "", winner: null, seed: ""},
                        })
                    })
                        this.fixedRounds.push(tempArray)
                    }
            },
            autoFill() { 
                    //fill out each match of the first round based on the sorted matches objects
                for (let i = 0; i < this.fixedRounds[0].games.length; i++){
                    this.fixedRounds[0].games[i].match = this.matches[i];
                    this.fixedRounds[0].games[i].player1 = this.matches[i].teamList[0];
                    this.fixedRounds[0].games[i].player2 = this.matches[i].teamList[1];
                    this.fixedRounds[0].games[i].player1.id = this.fixedRounds[0].games[i].player1.teamId;
                    this.fixedRounds[0].games[i].player2.id = this.fixedRounds[0].games[i].player2.teamId;
                    if (this.matches[i].winnerTeamId){
                        //filter through this game's teamList to match that team. then, mark that team 
                        //as winner in fixedRounds based on seed.
                        let winnerId = this.matches[i].winnerTeamId;

                        // for(let x = 0; x < this.matches[i].teamList.length; x++){
                        //     if(this.matches[i].teamList[x].teamId == this.matches[i].winnerTeamId){
                        //         winnerSeed = this.matches[i].teamList[x].seed;
                        //     }
                        // }
                            if(this.fixedRounds[0].games[i].player1.id == winnerId){
                                this.fixedRounds[0].games[i].player1.winner = true;
                                this.fixedRounds[0].games[i].player2.winner = false;
                            }
                            if(this.fixedRounds[0].games[i].player2.id == winnerId){
                                this.fixedRounds[0].games[i].player2.winner = true;
                                this.fixedRounds[0].games[i].player1.winner = false;
                            }
                        }
                }
                this.currentRound++;
                //start grabbing the next round
                // i is the round index we are on, j is the game index within that round, 
                // k is the overall index of the matches array
                let k = this.fixedRounds[0].games.length;
                for(let i = 1; i < this.numRounds; i++){
                    for(let j = 0; j < this.fixedRounds[i].games.length; j++){
                        if(this.currentRoundMatches[j].teamList.length > 0){
                            this.fixedRounds[i].games[j].match = this.matches[k];
                            this.fixedRounds[i].games[j].player1 = this.currentRoundMatches[j].teamList[0];
                            this.fixedRounds[i].games[j].player1.id = this.fixedRounds[i].games[j].player1.teamId
                    } 
                    if (this.currentRoundMatches[j].teamList.length > 1){
                        this.fixedRounds[i].games[j].player2 = this.currentRoundMatches[j].teamList[1];
                        this.fixedRounds[i].games[j].player2.id = this.fixedRounds[i].games[j].player2.teamId
                    }
                    if (this.currentRoundMatches[j].winnerTeamId){
                        //filter through this game's teamList to match that team. then, mark that team as winner in fixedRounds based on seed.
                        // let winnerId = this.currentRoundMatches[j].teamList.filter((team) => {
                        //     return team.teamId == this.currentRoundMatches[j].winnerTeamId;
                        // });
                        let winnerId = this.currentRoundMatches[j].winnerTeamId;
                        // for (let team = 0; team < this.fixedRounds[0].games[i].length; team++){
                            if(this.fixedRounds[i].games[j].player1.id == winnerId){
                                this.fixedRounds[i].games[j].player1.winner = true;
                                this.fixedRounds[i].games[j].player2.winner = false;
                            }
                            if(this.fixedRounds[i].games[j].player2.id == winnerId){
                                this.fixedRounds[i].games[j].player2.winner = true;
                                this.fixedRounds[i].games[j].player1.winner = false;
                            }
                        }
                        k++;
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
                test: 0,
                currentRound: 0,
                sortedMatches: [],
                // matches: [],
                // currentTournament: this.$store.state.currentTournament,
                // tournamentId: this.tournament.id,
                fixedRounds: []
            }
        }
    }
</script> 
<style scoped>
    .seed{
        font-size: 12px;
        padding-left: 15px;
        padding-right: 5px;
    }

    .bracket-container{
        font-family:'Arial Black', 'Arial Narrow', Arial, sans-serif;
        background-color: rgba(8, 69, 97, 0.9);
    }
    .bottom-text{
        padding-left: 5px;
        padding-right: 5px;
    }
    .bracket{
        padding-left: 20px;
    }
</style>
