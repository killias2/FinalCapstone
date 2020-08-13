<template>
    <div class="home">
        <!-- <div class="banner">
            <h1>Welcome to TournaMint!</h1>
        </div> -->
        <div class="body">
            <h2 id="intro"> Brackets[] can help with all of your tournament-related needs!</h2>
            <h3> News and Notes </h3>
            <h4 id="news-text"> 
                The recent Ping Pong tournament ended in a resounding victory for team Java!
            </h4>
            <!-- <img alt="Soccer Ball" src="../assets/soccerball.webp"> -->
            <!-- <p>You are an authenticated user!</p> -->
        </div>
    </div>
</template>

<script>

export default {
  name: "home",
 
}

</script>

<style scoped>
.body {
    margin-left: 5%;
    background-color: rgba(28, 143, 158, 0.8);
    border-radius: 6px;
    font-family: 'Arial Narrow', Arial, sans-serif;
    margin-right: 200px;
    padding: 20px;

}
.banner {
    margin-left: 100px;
    margin-right: 100px;
    font-family: 'Arial Black', Arial, sans-serif;

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