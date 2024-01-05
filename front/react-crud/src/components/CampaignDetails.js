import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import CampaignService from "../services/campaign.service";

const CampaignDetails = () => {
  const { id } = useParams();
  const [campaign, setCampaign] = useState(null);

  useEffect(() => {
    console.log(campaign);
    CampaignService.viewOneCampaign(id)
      .then((response) => {
        setCampaign(response.data);
      })
      .catch((error) => {
        console.error(
          "Error fetching campaign details: ",
          error.response || error
        );
      });
  }, [id]);

  if (!campaign) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h3>Name: {campaign.name}</h3>
      <h4>Description: {campaign.description}</h4>
      <div>
        <h3>Accounts:</h3>
        <ul>
          {campaign.campaignAccounts.map((account, index) => (
            <li key={index}>
              ID: {account.id}, Username: {account.username}
            </li>
          ))}
        </ul>
      </div>
      {/* Render other campaign details */}
    </div>
  );
};

export default CampaignDetails;
