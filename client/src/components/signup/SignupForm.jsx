import styled from "styled-components";
import SignupBtn from "./SignupBtn";

function SignForm() {
  return (
    <Page>
      <IdBlock>
        <span>아이디</span>
        <span className="essential">*</span>
        <CheckId placeholder="아이디를 입력해주세요"></CheckId>
        <span>비밀번호</span>
        <span className="essential">*</span>{" "}
        <CreatePwd placeholder="비밀먼호를 입력해주세요"></CreatePwd>
        <CheckPwd placeholder="비밀번호를 확인"></CheckPwd>
        <span>이름</span> <span className="essential">*</span>
        <CheckName placeholder="이름을 입력해주세요"></CheckName>
        <span>이메일</span>
        <span className="essential">*</span>
        <br></br>
        <CheckEmail placeholder="이메일을 입력해주세요"></CheckEmail>
        <span>@</span>
        <Select>
          <option value="" hidden>
            Type
          </option>
          <option value="1">gmail.com</option>
          <option value="2">naver.com</option>
          <option value="3">nate.com</option>
          <option value="4">daum.net</option>
        </Select>
        <span>휴대폰</span>
        <span className="essential">*</span>
        <CheckPhoneNum placeholder="연락처를 입력해주세요"></CheckPhoneNum>
        <span>주소</span>
        <span className="essential">*</span>
        <CheckAddress></CheckAddress>
        <span>생년월일</span>
        <span className="essential">*</span>
        <br />
        <CheckBirth></CheckBirth>
        <CheckBirth></CheckBirth>
        <CheckBirth></CheckBirth>
      </IdBlock>
      <CheckboxContent>
        <div className="autoContent">
          <input type="checkbox" />
          <span className="CheckboxText">전체 동의하기</span>
        </div>
        <div className="autoContent">
          <input type="checkbox" />
          <span className="CheckboxText">이용약관 동의하기(필수)</span>
        </div>
        <div className="autoContent">
          <input type="checkbox" />
          <span className="CheckboxText">개인정보 수집 이용동의(필수)</span>
        </div>
      </CheckboxContent>
      <SignupBtn type={"signup"} />
    </Page>
  );
}
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
  width: 52%;
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

const Select = styled.select`
  width: 40%;
  min-height: 50px;
  background: white;
  //   color: gray;
  padding-left: 5px;
  font-size: 14px;

  margin-left: 10px;

  option {
    color: black;
    background: white;
    display: flex;
    white-space: pre;
    min-height: 20px;
    padding: 0px 2px 1px;
  }
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

export default SignForm;
