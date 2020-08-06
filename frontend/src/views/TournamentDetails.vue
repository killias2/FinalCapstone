<template>
    <div>
        <tournament-details/>
        <bracket/>
    </div>
</template>

<script>
import TournamentDetails from "../components/TournamentDetails"
import bracket from '../components/Bracket';

export default {
    components: {
        TournamentDetails,
        bracket
    }
};
</script>