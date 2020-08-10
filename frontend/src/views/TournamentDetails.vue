<template>
    <div>
        <!-- <h1>Tournament Details</h1> -->
        <tournament-details/>
        <add-teams/>
        <!-- <bracket/> -->
        <bracket-display/>
        <!-- <user-nav /> -->
    </div>
</template>

<script>
import TournamentDetails from "../components/TournamentDetails"
// import bracket from '../components/Bracket';
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