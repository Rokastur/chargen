import React from "react";
import AuthService from "../services/auth.service";
const Profile = () => {
  const currentAccount = AuthService.getCurrentAccount();
  return (
    <div>
      <header>
        <h3>
          <strong>{currentAccount.username}</strong> Profile
        </h3>
      </header>
      <p>
        <strong>Token:</strong> {currentAccount.authToken.substring(0, 20)} ...{" "}
        {currentAccount.authToken.substr(currentAccount.authToken.length - 20)}
      </p>
      <strong>Authorities:</strong>
      <ul>
        {currentAccount.roles &&
          currentAccount.roles.map((role, index) => (
            <li key={index}>{role}</li>
          ))}
      </ul>
    </div>
  );
};
export default Profile;
