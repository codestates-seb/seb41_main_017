import EmailForm from "./IdForm";
import PasswordForm from "./PasswordForm";
import LoginButton from "./LoginButton";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import SignForm from "../signup/SignupForm";
import BasicButton from "../BasicButton";
import BasicInput from "../BasicInput";
import BASE_URL from "../../constants/BASE_URL";
import axios from "axios";

function LoginInputForm() {
  const [toggle, setToggle] = useState(1);
  const [loginId, setLoginId] = useState("");
  const [loginPassword, setLoginPassword] = useState("");
  const navigate = useNavigate();

  const handleLoginBtn = (e) => {
    e.preventDefault();

    const header = {
      headers: {
        "Content-Type": `application/json`,
      },
    };

    const reqbody = {
      username: loginId,
      password: loginPassword,
    };

    axios
      .post(`${BASE_URL}/users/signin`, reqbody, header)
      .then((res) => {
        localStorage.setItem("token", JSON.stringify(res.headers));
        navigate("/");
        window.location.reload();
      })
      .catch((err) => {
        window.alert(
          "로그인 정보가 일치하지 않습니다! 계정정보를 확인해주세요!!"
        );
      });
  };

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
          <form onSubmit={handleLoginBtn}>
            <EmailForm setLoginId={setLoginId} />

            <PasswordForm setLoginPassword={setLoginPassword} />

            <CheckboxContent>
              <div className="autoContent">
                <input type="checkbox" />
                <span className="CheckboxText">자동로그인</span>
              </div>
            </CheckboxContent>
            <ButtonWrapper>
              <BasicButton
                children={"로그인"}
                font={"20"}
                radius={"5"}
                p_height={"12"}
                p_width={"159"}
              />
            </ButtonWrapper>
          </form>
          <LoginButton type={"github"} />
          <LoginButton type={"google"} />
        </div>
        <div className={toggle === 2 ? "activeContent" : "content"}>
          <SignForm></SignForm>
        </div>
      </section>
    </LoginContainer>
  );
}

const ButtonWrapper = styled.button`
  display: flex;
  justify-content: center;
  gap: 8px;
  border-top: 1px solid rgb(244, 244, 244);
  text-align: center;
  padding-top: 20px;
  margin-top: 20px;

  a {
    background-color: #ffffff;
    color: #c26d53;
    border-radius: 3px;
    outline: 1px solid #c26d53;
  }
`;

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
