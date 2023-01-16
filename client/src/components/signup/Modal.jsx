import React from "react";
import styled from "styled-components";

const Modal = (props) => {
  // 열기, 닫기, 모달 헤더 텍스트를 부모로부터 받아옴
  const { open, close, header } = props;

  return (
    // 모달이 열릴때 openModal 클래스가 생성된다.
    <Page>
      <div className={open ? "openModal modal" : "modal"}>
        {open ? (
          <Section>
            <Header>
              <button className="close" onClick={close}>
                &times;
              </button>
            </Header>
            <Main>{props.children}</Main>
            <Footer>
              <button className="close" onClick={close}>
                close
              </button>
            </Footer>
          </Section>
        ) : null}
      </div>
    </Page>
  );
};

const Page = styled.div`
  text-align: center;

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
  max-width: 450px;
  margin: 0 auto;
  border-radius: 0.3rem;
  background-color: #fff;
  animation: modal-show 0.3s;
  overflow: hidden;
`;

const Header = styled.header`
  position: relative;
  padding: 36px 64px 16px 16px;
  background-color: #f1f1f1;
  font-weight: 700;
  button {
    position: absolute;
    top: 15px;
    right: 15px;
    width: 30px;
    font-size: 21px;
    font-weight: 700;
    text-align: center;
    color: #999;
    background-color: transparent;
  }
`;

const Main = styled.main`
  padding: 16px;
  border-bottom: 1px solid #dee2e6;
  border-top: 1px solid #dee2e6;
`;

const Footer = styled.footer`
  padding: 12px 16px;
  text-align: right;
  button {
    padding: 6px 12px;
    color: #fff;
    background-color: #6c757d;
    border-radius: 5px;
    font-size: 13px;
  }
`;

export default Modal;
