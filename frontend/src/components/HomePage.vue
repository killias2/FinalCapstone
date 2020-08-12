<template>
    <div class="home">
        <!-- <div class="banner">
            <h1>Welcome to TournaMint!</h1>
        </div> -->
        <div class="body">
            <h3 id="intro"> Brackets[] can help with all of your tournament-related needs!</h3>
            <img alt="Soccer Ball" src="../assets/soccerball.webp">
            <!-- <p>You are an authenticated user!</p> -->
        </div>
    </div>
</template>

<script>


export default {
  name: "home"
}

</script>

<style>
.body {
    /* margin-left: 200px; */
}
.banner {
    margin-left: 0px;
    margin-right: 200px;
}
img {
    display: block;
    margin-left: auto;
    margin-right: auto;
    padding-bottom: 20px;
    width: 20%;
    border-radius: 50px;
}
p {
    text-align: center;
    color: red;
    font-weight: bolder;
    text-shadow: 2px;
    font-size: 50px;
}

#intro {
    text-align: center;
}
    
</style>