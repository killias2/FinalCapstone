<template>
    <div>
        <bracket-display />
        <bracket-editor />

    
    </div>
</template>

<script>
import BracketDisplay from '../components/BracketDisplay.vue';
import BracketEditor from '../components/BracketEditor.vue';

export default {
    name: 'TournamentHQ',
    components:{
        'BracketDisplay': BracketDisplay,
        'BracketEditor': BracketEditor
    }
    
}
</script>

