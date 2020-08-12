<template>
    <div class="home">
        <!-- <div class="banner">
            <h1>Welcome to TournaMint!</h1>
        </div> -->
        <div class="body">
            <h2 id="intro"> Brackets[] can help with all of your tournament-related needs!</h2>
            <h3> News and Notes </h3>
            <h4 id="news-text"> Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
                sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, 
                quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. 
                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. 
                Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
            </h4>
            <!-- <img alt="Soccer Ball" src="../assets/soccerball.webp"> -->
            <!-- <p>You are an authenticated user!</p> -->
        </div>
    </div>
</template>

<script>


export default {
  name: "home"
}

</script>

<style scoped>
.body {
    /* margin-left: 200px; */
    background-color: rgba(28, 143, 158, 0.8);
    border-radius: 6px;
    font-family: 'Arial Narrow', Arial, sans-serif;
    margin-right: 200px;

}
.banner {
    margin-left: 200px;
    margin-right: 200px;
    font-family: 'Arial Black', Arial, sans-serif
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