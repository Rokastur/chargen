import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/auth.service";

const Login = ({ onLoginSuccess }) => {
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const onChangeUsername = (e) => {
    setUsername(e.target.value);
  };

  const onChangePassword = (e) => {
    setPassword(e.target.value);
  };

  const validateForm = () => {
    if (!username || !password) {
      setMessage("Username and password are required.");
      return false;
    }
    return true;
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    setMessage("");
    setLoading(true);

    if (validateForm()) {
      try {
        await AuthService.login(username, password);
        setLoading(false);
        onLoginSuccess(); // Callback function to update parent state
        navigate("/campaigns");
      } catch (error) {
        console.error("Login Error:", error);
        const resMessage =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();
        setMessage(resMessage);
        setLoading(false);
      }
    } else {
      setLoading(false);
    }
  };

  return (
    <div>
      <form onSubmit={handleLogin}>
        <div>
          <label htmlFor="username">Username</label>
          <input
            type="text"
            name="username"
            id="username"
            value={username}
            onChange={onChangeUsername}
            required
          />
        </div>
        <div>
          <label htmlFor="password">Password</label>
          <input
            type="password"
            name="password"
            id="password"
            value={password}
            onChange={onChangePassword}
            required
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? "Loading..." : "Login"}
        </button>
      </form>
      {message && <div role="alert">{message}</div>}
    </div>
  );
};
export default Login;
