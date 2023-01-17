import React from "react";
import styled from "styled-components";
import LoginInputForm from "../../components/login/LoginInputForm";

const Page = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;
const LoginWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 420px; // 나중에 컴포넌트 조립 때 수정해야한다!
`;

const ButtonWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 16px;
`;
const Logowrapper = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 24px;
  cursor: pointer;
`;
const FormWrapper = styled.div`
  width: 100%;
  margin-bottom: 24px;
  padding: 24px;
  border-radius: 7px;
  background-color: white;
  // box-shadow: rgba(0, 0, 0, 0.05) 0px 10px 24px 0px,
  //   rgba(0, 0, 0, 0.05) 0px 20px 48px 0px, rgba(0, 0, 0, 0.1) 0px 1px 4px 0px;
`;

function Login() {
  return (
    <Page>
      <LoginWrapper>
        <Logowrapper></Logowrapper>
        <ButtonWrapper>
          {/* <LoginButton type={"google"} />
          <LoginButton type={"github"} /> */}
        </ButtonWrapper>
        <FormWrapper>
          <LoginInputForm></LoginInputForm>
        </FormWrapper>
      </LoginWrapper>
    </Page>
  );
}

export default Login;
