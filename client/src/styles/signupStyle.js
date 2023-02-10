import styled from "styled-components";

const Page = styled.div`
  width: 100%
  height: 100vh;
  //   background-color: #f1f2f3;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  h2 {
    font-size: 25px;
    margin-bottom: 50px;
  }

  .error_text{
    color : red;
    font-size: 8px;
    margin-top: -20px;
  }
  .error_text2{
    color : red;
    font-size: 8px;
    margin-top: -10px;
  }

  .error_box{
    display: flex;
    align-item : center;

  }
`;
const CheckboxContent = styled.div`
  display: flex;
  align-items: flex-start;
  width: 100%;
  flex-direction: column;
  .autoContent {
    align-items: left;
    margin-top: 8px;
  }
  .CheckboxText {
    margin-left: 10px;
    text-align: center;
  }
`;

const IdBlock = styled.div`
    

  width: 100%;

  .detail_address {
    margin-top: -32px;
  }

  .essential {
    color: red;
  }
  .input_cotainer {
    display: flex;
  }
  .input_birth {
    display: flex;
  }
  .input_title {
    display: flex;
    margin-bottom: 10px;
  }
  .birth_container {
    width: 25%;
    margin-right: 15px;
  }

  .check_btn {
    width: 20%
    display: flex;
    height:100%;
  }
  .input_box{
    width:76%;
  }
`;

const ButtonWrapper = styled.div`
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

const ModalWrapper = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1;
  flex-direction: column;
`;

const SignupSuccessContainer = styled.div`
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  width: 80%;
  max-width: 500px;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const SignupFailureContainer = styled.div`
  background-color: white;
  padding: 20px;
  border-radius: 10px;
  width: 80%;
  max-width: 500px;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const ModalTitle = styled.div`
  font-size: 22px;
  margin-bottom: 10px;
`;

const ModalText = styled.div`
  font-size: 18px;
  text-align: center;
  margin-bottom: 10px;
`;

const CloseButton = styled.button`
  background-color: #ff7f7f;
  color: white;
  font-size: 16px;
  padding: 8px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
  width: fit-content;
`;

export {
  Page,
  CheckboxContent,
  IdBlock,
  ModalTitle,
  ModalText,
  CloseButton,
  ButtonWrapper,
  ModalWrapper,
  SignupSuccessContainer,
  SignupFailureContainer,
};
