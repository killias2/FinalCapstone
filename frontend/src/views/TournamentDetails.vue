<template>
    <div>
        <h1>Tournament Details</h1>
        <tournament-details/>
        <add-teams/>
        <bracket/>
        <user-nav />
    </div>
</template>

<script>
import TournamentDetails from "../components/TournamentDetails"
import bracket from '../components/Bracket';
import UserNav from '../components/UserNav'
import AddTeams from '../components/AddTeams'

export default {
    components: {
        TournamentDetails,
        bracket,
        UserNav,
        AddTeams
    }
};
</script>