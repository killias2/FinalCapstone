<template>
    <div>
        <tournament-details/>
        <bracket-display/>
    </div>
</template>

<script>
import TournamentDetails from "../components/TournamentDetails"
import BracketDisplay from '../components/BracketDisplay';

export default {
    components: {
        TournamentDetails,
        BracketDisplay
    }
};
</script>