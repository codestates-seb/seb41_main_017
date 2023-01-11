import styled from "styled-components";
import SignupBtn from "./SignupBtn";
import GenderRadio from "./GenderRadio";
import { useState } from "react";
import ModalContainer from "./ModalCotainer";
import SignupInput from "./SignupInput";

function SignForm() {
  const [address, setAddress] = useState("");
  const [postAddress, setPostAddress] = useState("");
  console.log(address);

  return (
    <Page>
      <IdBlock>
        <span>아이디</span>
        <span className="essential">*</span>
        <SignupInput type={"id"} />
        <span>비밀번호</span>
        <span className="essential">*</span>
        <SignupInput type={"createPwd"} />
        <SignupInput type={"checkPwd"} />
        <span>이름</span> <span className="essential">*</span>
        <SignupInput type={"name"} />
        <span>이메일</span>
        <span className="essential">*</span>
        <br></br>
        <SignupInput type={"email"} />
        <span>@</span>
        <Select>
          <option value="" hidden>
            이메일을 선택해주세요
          </option>
          <option value="1">gmail.com</option>
          <option value="2">naver.com</option>
          <option value="3">nate.com</option>
          <option value="4">daum.net</option>
        </Select>
        <span>휴대폰</span>
        <span className="essential">*</span>
        <SignupInput type={"phoneNum"} />
        <span>주소</span>
        <span className="essential">*</span>
        <br />
        <CheckAddress
          type="text"
          value={address}
          onChange={(e) => setPostAddress(e.target.value)}
          placeholder="주소를 입력해주세요"
        ></CheckAddress>
        {/* <SignupInput
          type={"address"}
          value={address}
          onChange={(e) => setPostAddress(e.target.value)}
        /> */}
        <ModalContainer setAddress={setAddress} />
        <SignupInput type={"detailAddress"} />
        <br />
        <span>생년월일</span>
        <br />
        <SignupInput type={"birth"} />
        <SignupInput type={"birth"} />
        <SignupInput type={"birth"} />
        <br />
        <span>성별</span>
        <GenderRadio></GenderRadio>
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

const IdBlock = styled.div`
  //   margin: 6px 0;
  width: 100%;
  .essential {
    color: red;
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

export default SignForm;
