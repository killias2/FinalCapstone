import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  viewTeams() {
    return http.get('/teams');
  },

  addTeams(team) {
    return http.post('/teams', team);
  }


}