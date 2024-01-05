import axios from "axios";
const API_URL = "http://localhost:8080/api/auth/";
const register = (username, password) => {
  return axios.post(API_URL + "register", {
    username,
    password,
  });
};

const login = (username, password) => {
  return axios
    .post(API_URL + "login", {
      username,
      password,
    })
    .then((response) => {
      if (response.data.token) {
        localStorage.setItem("authToken", response.data.token);
      }
      console.log("response: ", response);
      console.log("token: ", response.data.token);
      return response.data;
    });
};

const logout = () => {
  localStorage.removeItem("account");
};

const getCurrentAccount = () => {
  console.log("get current account is called");
  return localStorage.getItem("authToken");
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentAccount,
};

export default AuthService;
