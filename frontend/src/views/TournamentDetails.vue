<template>
    <div>
        <tournament-details/>
        <bracket/>
        <user-nav />
    </div>
</template>

<script>
import TournamentDetails from "../components/TournamentDetails"
import bracket from '../components/Bracket';
import UserNav from '../components/UserNav'

export default {
    components: {
        TournamentDetails,
        bracket,
        UserNav
    }
};
</script>