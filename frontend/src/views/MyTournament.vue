<template>
    <div>
        <my-tournaments />
        <add-teams />
    </div>
</template>

<script>
import AddTeams from "../components/AddTeams"
import MyTournaments from "../components/MyTournaments"

export default {
    components: {
        AddTeams,
        MyTournaments
    }
}
</script>