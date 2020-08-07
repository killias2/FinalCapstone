<template>
    <div>
        
        <add-teams />
        <my-tournament />
        <user-nav />
        
    </div>
</template>

<script>
import AddTeams from "../components/AddTeams"
import MyTournament from "../components/MyTournament"
import UserNav from "../components/UserNav"

export default {
    components: {
        AddTeams,
        MyTournament,
        UserNav
    }
}
</script>