<template>
    <div>
        <add-teams />
        <my-tournament />
        
    </div>
</template>

<script>
import AddTeams from "../components/AddTeams"
import MyTournament from "../components/MyTournament"

export default {
    components: {
        AddTeams,
        MyTournament
    }
}
</script>