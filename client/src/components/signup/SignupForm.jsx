import styled from "styled-components";
import SignupBtn from "./SignupBtn";
import GenderRadio from "./GenderRadio";
import { useState } from "react";
import ModalContainer from "./ModalCotainer";
import {
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
  CheckBirth,
} from "../../styles/signupStyle";

function SignForm() {
  const [address, setAddress] = useState("");
  const [postAddress, setPostAddress] = useState("");

  return (
    <Page>
      <IdBlock>
        <span>아이디</span>
        <span className="essential">*</span>
        <br />
        <CheckId placeholder="아이디를 입력해주세요"></CheckId>
        {/* <CheckIdBtn>중복확인</CheckIdBtn> */}
        <ModalContainer type={"checkId"} onClick />
        <br />
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
        <ModalContainer type={"checkId"} onClick />
        <span>휴대폰</span>
        <span className="essential">*</span>
        <CheckPhoneNum placeholder="연락처를 입력해주세요"></CheckPhoneNum>
        <span>주소</span>
        <span className="essential">*</span>
        <br />
        <CheckAddress
          type="text"
          value={address}
          onChange={(e) => setPostAddress(e.target.value)}
          placeholder="주소를 입력해주세요"
        ></CheckAddress>
        <ModalContainer setAddress={setAddress} type={"address"} />
        <DetailAddress placeholder="나머지 주소를 입력해주세요"></DetailAddress>
        <span>생년월일</span>
        <span className="essential">*</span>
        <br />
        <CheckBirth></CheckBirth>
        <CheckBirth></CheckBirth>
        <CheckBirth></CheckBirth>
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

export default SignForm;
