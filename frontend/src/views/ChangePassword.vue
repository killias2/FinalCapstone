<template>
    <div>
        <h1>Update Password</h1>
        
        <change-password />
        <user-nav />
    </div>
</template>

<script>
import ChangePassword from "../components/ChangePassword"
import UserNav from "../components/UserNav"

export default {
    components: {
        ChangePassword,
        UserNav
    }
}
</script>