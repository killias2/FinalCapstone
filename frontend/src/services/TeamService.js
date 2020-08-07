import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  viewTeams(id) {
    return http.get(`/tournaments/${id}/teams`);
  },

  addTeams(id) {
    return http.post(`/tournaments/${id}`);
  }


}