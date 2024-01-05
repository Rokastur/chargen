export default function authHeader() {
  const account = localStorage.getItem("authToken");
  console.log("account: ", account);
  if (account && account.token) {
    return {
      Authorization: "Bearer " + account.token,
    };
  } else {
    return {};
  }
}
