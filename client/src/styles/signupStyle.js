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
`;

const CheckId = styled.input.attrs({
  type: "text",
})`
  display: inline-block;
  width: 65%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-top: 10px;
  margin-bottom: 10px;
`;
const CreatePwd = styled.input.attrs({
  type: "password",
})`
  display: inline-block;
  width: 100%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-top: 10px;
`;
const CheckPwd = styled.input.attrs({
  type: "password",
})`
  display: inline-block;
  width: 100%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-top: 10px;
  margin-bottom: 10px;
`;

const CheckName = styled.input.attrs({
  type: "text",
})`
  display: inline-block;
  width: 100%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-top: 10px;
  margin-bottom: 10px;
`;

const CheckEmail = styled.input.attrs({
  type: "select",
})`
  display: inline-block;
  width: 65%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-top: 10px;
  margin-bottom: 10px;
`;

const CheckPhoneNum = styled.input.attrs({
  type: "text",
})`
  display: inline-block;
  width: 100%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-top: 10px;
  margin-bottom: 10px;
`;
const DetailAddress = styled.input.attrs({
  type: "text",
})`
  display: inline-block;
  width: 100%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-top: 10px;
  margin-bottom: 10px;
`;
const CheckAddress = styled.input.attrs({
  type: "text",
})`
  display: inline-block;
  width: 65%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-top: 10px;
  margin-bottom: 10px;
`;

const CheckBirthDD = styled.input.attrs({
  type: "text",
})`
  display: inline-block;
  width: 25%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-right: 10px;
`;
const CheckBirthYY = styled.input.attrs({
  type: "text",
})`
  display: inline-block;
  width: 25%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-right: 10px;
`;
const CheckBirthMM = styled.input.attrs({
  type: "text",
})`
  display: inline-block;
  width: 25%;
  min-height: 50px;
  padding: 7px 9px;
  text-align: start;
  font-size: 13px;
  line-height: normal;
  letter-spacing: normal;
  box-shadow: none;
  border-radius: 3px;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-right: 10px;
`;

export {
  Page,
  CheckboxContent,
  IdBlock,
  CheckId,
  CreatePwd,
  CheckPwd,
  CheckName,
  CheckEmail,
  CheckPhoneNum,
  DetailAddress,
  CheckAddress,
  CheckBirthYY,
  CheckBirthMM,
  CheckBirthDD,
};
