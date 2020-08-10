<template>
    <div>
        <h1>Tournament Details</h1>
        <tournament-details/>
<<<<<<< HEAD
        <add-teams/>
        <bracket/>
        <user-nav />
=======
        <bracket-display/>
>>>>>>> 8bcdc55788b3d48045796a9ef7684be15f2183f1
    </div>
</template>

<script>
import TournamentDetails from "../components/TournamentDetails"
<<<<<<< HEAD
import bracket from '../components/Bracket';
import UserNav from '../components/UserNav'
import AddTeams from '../components/AddTeams'
=======
import BracketDisplay from '../components/BracketDisplay';
>>>>>>> 8bcdc55788b3d48045796a9ef7684be15f2183f1

export default {
    components: {
        TournamentDetails,
<<<<<<< HEAD
        bracket,
        UserNav,
        AddTeams
=======
        BracketDisplay
>>>>>>> 8bcdc55788b3d48045796a9ef7684be15f2183f1
    }
};
</script>