import React, { useState } from "react";
import AuthService from "../services/auth.service";

const required = (value) => {
  if (!value) {
    return "This field is required!";
  }
};

const validUsername = (value) => {
  if (value.length < 3 || value.length > 20) {
    return "The username must be between 3 and 20 characters";
  }
};

const validPassword = (value) => {
  if (value.length < 6 || value.length > 40) {
    return "The password must be between 6 and 40 characters";
  }
};

const Register = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [successful, setSuccessful] = useState(false);
  const [message, setMessage] = useState("");
  const [errors, setErrors] = useState({});

  const onChangeUsername = (e) => {
    setUsername(e.target.value);
    setErrors({
      ...errors,
      username: validUsername(e.target.value) || required(e.target.value),
    });
  };

  const onChangePassword = (e) => {
    setPassword(e.target.value);
    setErrors({
      ...errors,
      password: validPassword(e.target.value) || required(e.target.value),
    });
  };

  const handleRegister = (e) => {
    e.preventDefault();
    setMessage("");
    setSuccessful(false);

    const usernameError = validUsername(username) || required(username);
    const passwordError = validPassword(password) || required(password);
    if (!usernameError && !passwordError) {
      AuthService.register(username, password).then(
        (response) => {
          setMessage(response.data.message);
          setSuccessful(true);
        },
        (error) => {
          const resMessage =
            (error.response &&
              error.response.data &&
              error.response.data.message) ||
            error.message ||
            error.toString();
          setMessage(resMessage);
          setSuccessful(false);
        }
      );
    } else {
      setErrors({ username: usernameError, password: passwordError });
    }
  };

  return (
    <div>
      <form onSubmit={handleRegister}>
        {!successful && (
          <div>
            <div>
              <label htmlFor="username">Username</label>
              <input
                type="text"
                name="username"
                value={username}
                onChange={onChangeUsername}
              />
              {errors.username && <div role="alert">{errors.username}</div>}
            </div>
            <div>
              <label htmlFor="password">Password</label>
              <input
                type="password"
                name="password"
                value={password}
                onChange={onChangePassword}
              />
              {errors.password && <div role="alert">{errors.password}</div>}
            </div>
            <div>
              <button>Sign Up</button>
            </div>
          </div>
        )}
        {message && (
          <div>
            <div role="alert">{message}</div>
          </div>
        )}
      </form>
    </div>
  );
};

export default Register;
