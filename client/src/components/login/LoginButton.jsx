import styled from "styled-components";
import logo_github from "../../assets/logo_github.svg";
import logo_google from "../../assets/logo_google.svg";

const LoginButton = ({ type, onClick }) => {
  if (type === "google") {
    return (
      <GoogleLogin onClick={onClick}>
        <img src={logo_google} alt="logo_google" />
        Log in with Google
      </GoogleLogin>
    );
  }
  if (type === "github") {
    return (
      <GitLogin onClick={onClick}>
        <img src={logo_github} alt="logo_github" />
        Log in with GitHub
      </GitLogin>
    );
  }

  if (type === "login") {
    return <LoginBtn>로그인</LoginBtn>;
  }

  if (type === "signup") {
    return <LoginBtn>회원가입</LoginBtn>;
  }
};

const SocialLoginButton = styled.button`
  img {
    margin-right: 10px;
  }

  display: inline-block;
  width: 100%;
  font-size: 20px;
  line-height: 15px;
  text-align: center;
  letter-spacing: center;

  padding: 10px;
  margin: 6px 0;

  box-shadow: rgba(255, 255, 255, 0.4) 0px 1px 0px 0px inset;
  border-radius: 3px;
  outline: 1px solid gray;
  margin: 5px 0px;
  height: 50px;
`;

const GitLogin = styled(SocialLoginButton)`
  background-color: #232629;
  color: white;
  outline: none;
`;

const GoogleLogin = styled(SocialLoginButton)``;

const LoginBtn = styled.button`
  display: inline-block;
  width: 100%;
  font-size: 20px;
  line-height: 15px;
  text-align: center;
  letter-spacing: center;

  background-color: #ffffff;
  padding: 10px;
  margin: 6px 0;
  color: #c26d53;
  box-shadow: rgba(255, 255, 255, 0.4) 0px 1px 0px 0px inset;
  border-radius: 3px;
  outline: 1px solid #c26d53;
  margin: 5px 0px;
  height: 50px;
`;

export default LoginButton;
