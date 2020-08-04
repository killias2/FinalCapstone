<template>
    <div class="sidebar">
        <aside>
            <h3>Current Tournaments</h3>
            <p>Soccer</p>
            <p>Football</p>
            <p>Ping Pong</p>
            <p>Madden</p>
            <p>Golf</p>
            <p>Tennis</p>
        </aside>
    </div>
</template>

<style scoped>
    aside p {
        color: white;
        font-size: 25px;
        text-align: left;
    }
    .sidebar {
        height: 100%;
        width: 200px;
        position: fixed;
        z-index: 1;
        top: 0;
        left: 0;
        background-color: rgb(8, 69, 97);
        overflow-x: hidden;
        padding-top: 20px;
    }
    aside {
        padding: 6px 8px 6px 16px;
        text-decoration: none;
        font-size: 25px;
        color: #818181;
        display: block;
    }
    aside h3 {
        word-wrap: break-word;
        color: red;
    }
</style>