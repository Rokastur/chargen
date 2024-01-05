import axios from "axios";
const API_URL = "http://localhost:8080/api/campaigns/";

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

const createNewCampaign = (campaignData) => {
  return axiosInstance.post("new", campaignData);
};

const viewOneCampaign = (campaignId) => {
  return axiosInstance.get(`campaign/${campaignId}`);
};

const viewAllCampaigns = () => {
  return axiosInstance.get("all");
};

const CampaignService = {
  createNewCampaign,
  viewOneCampaign,
  viewAllCampaigns,
};

export default CampaignService;
