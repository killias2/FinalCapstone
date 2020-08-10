import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:8080"
});

export default {

  generatePassword(passwordRequest) {
    return http.post('/passwordrecovery', passwordRequest)
  }

}