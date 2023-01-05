import styled from "styled-components";

const LoginButton = ({ type, onClick }) => {
  if (type === "google") {
    return <GoogleLogin onClick={onClick}>Log in with Google</GoogleLogin>;
  }
  if (type === "github") {
    return <GitLogin onClick={onClick}>Log in with GitHub</GitLogin>;
  }

  if (type === "login") {
    return <BlueButton>로그인</BlueButton>;
  }

  if (type === "signup") {
    return <BlueButton>회원가입</BlueButton>;
  }
};

const SocialLoginButton = styled.button`
  display: inline-block;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 13px;
  word-spacing: normal;
  border: 1px solid #d7d9dd;
  padding: 10px;
  margin: 4px 0;
  border-radius: 5px;
  letter-spacing: normal;
  text-align: center;
  font-weight: norbal;

  & > img {
    display: inline-block;
    width: 18px;
    height: 18px;
    text-align: center;
    letter-spacing: normal;
    margin-right: 4px;
  }

  &:hover {
    opacity: 0.8;
  }
`;

const GitLogin = styled(SocialLoginButton)`
  background-color: #232629;
  color: white;
  outline: none;
`;

const GoogleLogin = styled(SocialLoginButton)`
  outline: none;
`;

const BlueButton = styled.button`
  display: inline-block;
  width: 100%;
  font-size: 13px;
  line-height: 15px;
  text-align: center;
  letter-spacing: center;
  font-family: -apple-system, "system-ui", "Segoe UI Adjusted", "Segoe UI",
    "Liberation Sans", sans-serif;
  background-color: #0a95ff;
  padding: 10px;
  margin: 6px 0;
  color: white;
  box-shadow: rgba(255, 255, 255, 0.4) 0px 1px 0px 0px inset;
  border-radius: 3px;
  outline: none;
  margin: 10px 0px;

  &:hover {
    opacity: 0.8;
  }
`;

export default LoginButton;
