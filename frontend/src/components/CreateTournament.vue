<template>
    <form v-on:submit.prevent="addNewTournament">
        <div class="form-element">
            <div class="tournamentName">
                <label class="title" for="tournament-name">Tournament Name:</label>
            </div>
                <input id="name" v-model="newTournament.name" type="text"  />
            
        </div>
        <div class="form-element">
            <label class="title" for="dropdown">Select Game</label>
            <div>
                <select required v-model="newTournament.gameid" name="game" class="dropdown">
                    <option value="1">Billiards</option>
                    <option value="2">Soccer</option>
                </select>
            </div>
        </div>
        <div class="form-element">
            <label class="title" for="dropdown">Select Tournament Type</label>
            <div>
                <select required  v-model="newTournament.bracketid" name="bracket" class="dropdown">
                    <option value="1">Single Elimination Bracket</option>
                    <option value="2">Double Elimination Bracket</option>
                    <option value="3">Round Robin</option>
                </select>
            </div>
        </div>
        <div class="form-element">
            <label class="title" for="dropdown">Type of seeding</label>
            <input type="radio" v-model="newTournament.isSeeded" id="radio" name="type-of-seeding" value="true">
            <label for="Seeded" id="radio">Seeded</label>
            <input type="radio" v-model="newTournament.isSeeded" id="radio" name="type-of-seeding" value="false">
            <label for="Random" id="radio">Random</label>
        </div>
        <div class="form-element">
            <label class="title" for="dropdown">How many Teams?</label>
            <input type="number" v-model="newTournament.teams" class="number"/>
        </div>
        <div class="form-element">
            <label class="title" for="start-date">Start Date:</label>
            <input id="start" v-model="newTournament.startDate" type="date"  />
        </div>
        <div class="form-element">
            <label class="title" for="end-date">End Date:</label>
            <input id="end" v-model="newTournament.endDate" type="date"  />
        </div>
        <div class="actions">
            <button v-on:click.prevent="resetForm" type="cancel">Cancel</button>
            <button type="submit">Submit</button>
        </div>
    </form>
</template>

<script>
import tournamentService from "../services/TournamentService";

export default {
    name: "create-tournament",
    data() {
        return {
            newTournament: {
                id: 0,
                name: '',
                isOpen: false, //default is closed tournament
                gameid: 0, //what sport/game is this tournament for?
                bracketid: 0, //single elim / double elim / round robin
                startDate: '',
                endDate: '',
                organizerId: 0,
                isSeeded: false,
                teams: 0
            }
        }
    },
    methods: {
        resetForm() {
            this.newTournament = {};
        },
        addNewTournament() {
            tournamentService.addNewTournament(this.newTournament)
            .then(response => {
                if (response.status < 299) {
                 console.log('success');
                }
            });

        }
    }
}
</script>

<style scoped>
    .number {
        width: auto;
        margin-top: 10px;
    }
    .title {
        margin-left: 25px;
        font-weight: bold;

    }
    .dropdown {
        margin-left: 25px;
        margin-bottom: 10px;
        width: auto;
        font-weight: 100;
    }
    #name {
        margin-top: 10px;
    }
    form {
        margin-top: 25px;
    }
    .tournamentName {
        padding-top: 25px;
    }
    #start, #end {
        margin-top: 10px;
    }
    #radio {
        margin-left: 25px;
    }

</style>