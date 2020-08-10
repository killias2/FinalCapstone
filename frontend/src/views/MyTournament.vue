<template>
    <div>
        <my-tournament />
        <user-nav />
        
    </div>
</template>

<script>
import MyTournament from "../components/MyTournament"
import UserNav from "../components/UserNav"

export default {
    components: {
        MyTournament,
        UserNav
    }
}
</script>