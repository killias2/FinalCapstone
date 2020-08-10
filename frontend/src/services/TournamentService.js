import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  newTournament(tournament) {
    return http.post('/tournaments', tournament);
  },

  viewTournaments() {
    return http.get('/tournaments');
  },

  getAllMatches(tournamentId) {
    return http.get(`/tournaments/${tournamentId}/matches`);
  },
  
  getAllTeams(tournamentId){
    return http.get(`/tournaments/${tournamentId}/teams`);
  },

  getTournament(tournamentID) {
    return http.get(`/tournaments/${tournamentID}`, tournamentID);
  },

  getAllGames() {
    return http.get('/games');
  }, 

  getUsersTournaments(organizerID) {
    return http.get(`/tournament/my-tournaments/${organizerID}`);
  },

  getCurrentTournaments() {
    return http.get('/tournaments/current');
  },
  
  getPastTournaments() {
    return http.get('/tournaments/past');
  }


}