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
  //   margin: 6px 0;
  width: 100%;
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

export { Page, CheckboxContent, IdBlock };
