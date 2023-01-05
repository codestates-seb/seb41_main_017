import styled from "styled-components";
import EmailForm from "./IdForm";
import PasswordForm from "./PasswordForm";
import LoginButton from "./LoginButton";

function LoginInputForm() {
  return (
    <LoginForm>
      <LoginTitle>로그인</LoginTitle>
      <EmailForm />
      <PasswordForm />
      <LoginButton type={"login"} />
      <LoginButton type={"signup"} />
    </LoginForm>
  );
}

const LoginTitle = styled.div`
  display: flex;
  font-size: 25px;
  margin-bottom: 10px;
`;

const LoginForm = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  background-color: white;
`;
export default LoginInputForm;
