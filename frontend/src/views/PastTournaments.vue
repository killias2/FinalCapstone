<template>
    <div>
        <sidebar />
        <past-tournaments />
        <user-nav />
    </div>
</template>

<script>
import PastTournaments from "../components/PastTournaments"
import Sidebar from '../components/Sidebar'
import UserNav from '../components/UserNav'

export default {
    components: {
        PastTournaments,
        Sidebar,
        UserNav
    }
};
</script>