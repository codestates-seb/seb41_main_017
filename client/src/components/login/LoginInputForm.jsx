import styled from "styled-components";
import EmailForm from "./IdForm";
import PasswordForm from "./PasswordForm";
import LoginButton from "./LoginButton";
import { useState } from "react";
import SignForm from "../signup/SignupForm";

function LoginInputForm() {
  const [toggle, setToggle] = useState(1);

  return (
    <LoginContainer>
      <LoginTitle>환영합니다</LoginTitle>
      <Underline>
        <button
          className={toggle === 1 ? "tabs tabsActive" : "tabs"}
          onClick={() => setToggle(1)}
        >
          로그인
        </button>
        <button
          className={toggle === 2 ? "tabs tabsActive" : "tabs"}
          onClick={() => setToggle(2)}
        >
          회원가입
        </button>
      </Underline>
      <section className="contentTabs">
        <div className={toggle === 1 ? "activeContent" : "content"}>
          <form>
            <EmailForm />
            <PasswordForm />
          </form>
          <CheckboxContent>
            <div className="autoContent">
              <input type="checkbox" />
              <span className="CheckboxText">자동로그인</span>
            </div>
          </CheckboxContent>
          {/* <CheckboxContent>
            <div className="autoContent">
              <input type="checkbox" />
              <span className="CheckboxText">이용약관 동의</span>
            </div>
          </CheckboxContent> */}
          <LoginButton type={"login"} />
          <LoginButton type={"github"} />
          <LoginButton type={"google"} />
        </div>
        <div className={toggle === 2 ? "activeContent" : "content"}>
          <SignForm></SignForm>
          {/* <div>hello</div> */}
        </div>
      </section>
    </LoginContainer>
  );
}

const CheckboxContent = styled.div`
  display: flex;
  align-items: flex-start;
  width: 100%;

  .autoContent {
    align-items: left;
  }

  .CheckboxText {
    margin-left: 10px;
    text-align: center;
  }
`;

const Underline = styled.div`
  border-bottom: 1px solid gray;
  width: 100%;
  margin-bottom: 25px;
  button {
    font-size: 20px;
    margin-right: 10px;
  }
`;

const LoginTitle = styled.div`
  display: flex;
  font-size: 32px;
  margin-bottom: 36px;
`;

const LoginContainer = styled.section`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: white;
  section {
    width: 100%;
  }
  .tabsActive {
    background: white;
    border-bottom: 1px solid transparent;
    color: #c26d53;
  }

  .tabsActive::before {
    content: "";
    display: block;
    position: absolute;
    top: -5px;
    left: 50%;
    transform: translateX(-50%);
    width: calc(100% + 2px);
    height: 5px;
  }
  .tabs {
    text-align: center;

    cursor: pointer;
    box-sizing: content-box;
    position: relative;
    outline: none;
  }

  .content {
    background: white;
    padding: 20px;
    width: 100%;
    height: 100%;
    display: none;
  }

  .activeContent {
    display: block;
  }
`;
export default LoginInputForm;
