import axios from "axios";
const API_URL = "http://localhost:8080/api/alignments/";

const axiosInstance = axios.create({
  baseURL: API_URL,
});

// Set the interceptor on axiosInstance, not on the global axios
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("authToken");
    if (token) {
      config.headers["Authorization"] = "Bearer " + token;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

const listAlignmentsByRuleset = (rulesetName) => {
  return axiosInstance.get(`ruleset/${rulesetName}`);
};

const AlignmentService = {
  listAlignmentsByRuleset,
};

export default AlignmentService;
