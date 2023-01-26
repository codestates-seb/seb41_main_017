import React from "react";
import styled from "styled-components";

const Page = styled.div`
  .modal {
    display: none;
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 99;
    background-color: rgba(0, 0, 0, 0.6);
  }
  .modal button {
    outline: none;
    cursor: pointer;
    border: 0;
  }

  .modal.openModal {
    display: flex;
    align-items: center;
    justify-content: center;
    animation: modal-bg-show 0.3s;
  }

  @keyframes modal-show {
    from {
      opacity: 0;
      margin-top: -50px;
    }
    to {
      opacity: 1;
      margin-top: 0;
    }
  }
  @keyframes modal-bg-show {
    from {
      opacity: 0;
    }
    to {
      opacity: 1;
    }
  }
`;

const Section = styled.section`
  width: 90%;
  max-width: 361px;
  //   max-height: 120px;
  height: 110px;
  margin: 0 auto;
  border-radius: 0.3rem;
  background-color: #fff;
  animation: modal-show 0.3s;
  overflow: hidden;
  border: 1px solid #c26d53;
`;

const Main = styled.main`
  display: flex;
  padding: 16px;
  text-align: center;
  justify-content: center;
  margin-top: 10px;
`;

const Footer = styled.footer`
  //   padding: 12px 16px;
  display: flex;
  justify-content: center;
  text-align: center;
  align-items: center;
  div {
    width: 50px;
    height: 30px;
    padding: 6px 12px;
    border: 1px solid #c26d53;
    border-radius: 5px;
    font-size: 13px;
  }
`;

const GuideModal = (props) => {
  const { open, close, response, type, signupId, signupEmail } = props;

  return (
    <Page>
      <div className={open ? "openModal modal" : "modal"}>
        {open ? (
          <Section>
            {type === "checkId" ? (
              response === 405 || signupId.length < 6 ? (
                <Main>사용 불가능한 아이디입니다</Main>
              ) : (
                <Main>사용 가능한 아이디입니다</Main>
              )
            ) : null}
            {type === "checkEmail" ? (
              response === 200 && signupEmail.includes("@") ? (
                <Main>사용 가능한 이메일입니다</Main>
              ) : (
                <Main>사용 불가능한 이메일입니다</Main>
              )
            ) : null}

            <Footer>
              <div className="close" onClick={close}>
                확인
              </div>
            </Footer>
          </Section>
        ) : null}
      </div>
    </Page>
  );
};

export default GuideModal;
