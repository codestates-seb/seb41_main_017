import styled from "styled-components";

const SignupInput = ({ type }) => {
  if (type === "id") {
    return <CheckId placeholder="아이디를 입력해주세요"></CheckId>;
  }
  if (type === "createPwd") {
    return <CreatePwd placeholder="비밀먼호를 입력해주세요"></CreatePwd>;
  }
  if (type === "checkPwd") {
    return <CheckPwd placeholder="비밀먼호를 확인"></CheckPwd>;
  }
  if (type === "name") {
    return <CheckName placeholder="이름을 입력해주세요"></CheckName>;
  }
  if (type === "email") {
    return <CheckEmail placeholder="이메일을 입력해주세요"></CheckEmail>;
  }
  if (type === "phoneNum") {
    return <CheckPhoneNum placeholder="연락처를 입력해주세요"></CheckPhoneNum>;
  }
  if (type === "address") {
    return (
      <CheckAddress
        placeholder="주소를 입력해주세요"
        type="text"
      ></CheckAddress>
    );
  }
  if (type === "detailAddress") {
    return (
      <DetailAddress placeholder="나머지 주소를 입력해주세요"></DetailAddress>
    );
  }
  if (type === "birth") {
    return <CheckBirth></CheckBirth>;
  }
};

const CheckId = styled.input.attrs({
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
  width: 50%;
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

const CheckBirth = styled.input.attrs({
  type: "text",
})`
  display: inline-block;
  width: 30%;
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

export default SignupInput;
