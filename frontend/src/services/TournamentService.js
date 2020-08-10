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

  getTournament(tournamentId) {
    return http.get(`/tournaments/${tournamentId}`, tournamentId);
  },

  getCurrentTournaments() {
    return http.get('/tournaments/current');
  },
  getPastTournaments() {
    return http.get('/tournaments/past');
  },

  getMatchFromId(matchId){
    return http.get(`/matches/${matchId}/teams`);
  }





}