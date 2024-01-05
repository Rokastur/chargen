import React, { useEffect, useState } from "react";
import AuthService from "../services/auth.service";
import CampaignService from "../services/campaign.service";
import { Link } from "react-router-dom";

const Campaign = () => {
  const [campaigns, setCampaigns] = useState([]);
  const [campaignName, setCampaignName] = useState("");
  const [campaignDescription, setCampaignDescription] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const campaignData = {
        name: campaignName,
        description: campaignDescription,
      };
      const response = await CampaignService.createNewCampaign(campaignData);
      console.log("Campaign created: ", response.data);
      // Add the new campaign to the campaigns state
      setCampaigns((prevCampaigns) => [...prevCampaigns, response.data]);
      // Optionally reset form fields
      setCampaignName("");
      setCampaignDescription("");
    } catch (error) {
      console.error("Error creating campaign: ", error);
    }
  };

  const onCampaignNameChange = (e) => setCampaignName(e.target.value);
  const onCampaignDescriptionChange = (e) =>
    setCampaignDescription(e.target.value);

  useEffect(() => {
    CampaignService.viewAllCampaigns()
      .then((response) => {
        setCampaigns(response.data);
      })
      .catch((error) => {
        console.error("Error fetching campaigns: ", error.response || error);
      });
  }, []);

  return (
    <div>
      {campaigns.map((campaign) => (
        <div key={campaign.id}>
          <Link to={`/campaign/${campaign.id}`}>{campaign.name}</Link>
        </div>
      ))}

      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="campaignName">Campaign Name</label>
          <input
            value={campaignName}
            onChange={onCampaignNameChange}
            required
            type="text"
            name="campaignName"
            id="campaignName"
          />
        </div>
        <div>
          <label htmlFor="campaignDescription">Description</label>
          <textarea
            value={campaignDescription}
            onChange={onCampaignDescriptionChange}
            required
            name="campaignDescription"
            id="campaignDescription"
          />
        </div>
        <button type="submit">Create New Campaign</button>
      </form>
    </div>
  );
};

export default Campaign;
