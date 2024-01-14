import React, { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";
import "./App.css";
import AuthService from "./services/auth.service";
import Login from "./components/Login";
import Register from "./components/Register";
import Home from "./components/Home";
import Campaign from "./components/Campaign";
import CampaignDetails from "./components/CampaignDetails";
import Character from "./components/character/Character";

const App = () => {
  const [currentAccount, setCurrentAccount] = useState(undefined);

  useEffect(() => {
    const user = AuthService.getCurrentAccount();
    if (user) {
      setCurrentAccount(user);
    }
  }, []);

  const handleLoginSuccess = () => {
    setCurrentAccount(AuthService.getCurrentAccount());
  };

  const logout = () => {
    AuthService.logout();
    setCurrentAccount(undefined); // Update state to reflect that the user has logged out
  };

  return (
    <div>
      <nav role="navigation">
        <ul>
          <li>
            <Link to="/campaigns">campaigns</Link>
          </li>
          <li>
            <Link to="/characters">characters</Link>
          </li>
          <li>
            <Link to="/">chargen</Link>
          </li>
          <li>
            <Link to="/home">Home</Link>
          </li>
          {currentAccount ? (
            <>
              <li>
                <Link to="/account">Account</Link>
              </li>
              <li>
                <Link
                  to="/login"
                  onClick={(e) => {
                    e.preventDefault();
                    logout();
                  }}
                >
                  LogOut
                </Link>
              </li>
            </>
          ) : (
            <>
              <li>
                <Link to="/login">Login</Link>
              </li>
              <li>
                <Link to="/register">Sign Up</Link>
              </li>
            </>
          )}
        </ul>
      </nav>
      <Routes>
        <Route path="/home" element={<Home />} />
        <Route
          path="/login"
          element={<Login onLoginSuccess={handleLoginSuccess} />}
        />
        <Route path="/register" element={<Register />} />
        <Route path="/campaigns" element={<Campaign />} />
        <Route path="/campaign/:id" element={<CampaignDetails />} />
        <Route path="/characters" element={<Character />} />
      </Routes>
    </div>
  );
};

export default App;
