<template>
    <div>
        <!-- <h1>Tournament Details</h1> -->
        <tournament-details/>
        <bracket-display v-bind:edit-mode="false"/>
        <add-teams/>
        <bracket-display/>
        <!-- <user-nav /> -->
    </div>
</template>

<script>
import TournamentDetails from "../components/TournamentDetails"
// import UserNav from '../components/UserNav'
import AddTeams from '../components/AddTeams'
import BracketDisplay from '../components/BracketDisplay';

export default {
    components: {
        TournamentDetails,
        // UserNav,
        AddTeams,
        BracketDisplay
    }
};
</script>