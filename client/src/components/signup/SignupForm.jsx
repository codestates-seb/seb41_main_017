import styled from "styled-components";
import SignupBtn from "./SignupBtn";
import GenderRadio from "./GenderRadio";
import { useState } from "react";
import ModalContainer from "./ModalCotainer";
import BasicInput from "../BasicInput";
import { Page, CheckboxContent, IdBlock } from "../../styles/signupStyle";

function SignForm() {
  const [address, setAddress] = useState("");
  const [postAddress, setPostAddress] = useState("");

  return (
    <Page>
      <IdBlock>
        <div className="input_title">
          <span>아이디</span>
          <span className="essential">*</span>
        </div>
        <div className="input_cotainer">
          <BasicInput
            type={"text"}
            placeholder={"아이디를 입력해주세요"}
          ></BasicInput>
          <ModalContainer type={"checkId"} onClick />
        </div>
        <div className="input_title">
          <span>비밀번호</span>
          <span className="essential">*</span>
        </div>
        <div className="input_container">
          <BasicInput
            type={"password"}
            placeholder={"비밀번호를 입력해주세요"}
          ></BasicInput>
          <BasicInput
            type={"password"}
            placeholder={"비밀번호를 확인"}
          ></BasicInput>
        </div>
        <div className="input_title">
          <span>이름</span>
          <span className="essential">*</span>
        </div>
        <div className="input_container">
          <BasicInput
            type={"text"}
            placeholder={"이름을 입력해주세요"}
          ></BasicInput>
        </div>
        <div className="input_title">
          <span>이메일</span>
          <span className="essential">*</span>
        </div>
        <div className="input_cotainer">
          <BasicInput
            type={"text"}
            placeholder={" ex) Culinari@gmail.com"}
          ></BasicInput>
          <ModalContainer type={"checkId"} onClick />
        </div>

        <div className="input_title">
          <span>휴대폰</span>
          <span className="essential">*</span>
        </div>
        <div className="input_container">
          <BasicInput
            type={"text"}
            placeholder={"-를 포함한 숫자를 입력해주세요"}
          ></BasicInput>
        </div>
        <div className="input_title">
          <span>주소</span>
          <span className="essential">*</span>
        </div>

        <div className="input_cotainer">
          <BasicInput
            type={"text"}
            placeholder={"주소를 입력해주세요"}
            defaultValue={address}
            onChange={(e) => setPostAddress(e.target.value)}
          ></BasicInput>
          <ModalContainer setAddress={setAddress} type={"address"} />
        </div>
        <div className="input_cotainer">
          <BasicInput
            type={"text"}
            placeholder={"나머지 주소를 입력해주세요"}
          ></BasicInput>
        </div>
        <div className="input_title">
          <span>생년월일</span>
          <span className="essential">*</span>
        </div>
        <div className="input_cotainer">
          <div className="birth_container">
            <BasicInput type={"text"} placeholder={"YY"}></BasicInput>
          </div>
          <div className="birth_container">
            <BasicInput type={"text"} placeholder={"MM"}></BasicInput>
          </div>
          <div className="birth_container">
            <BasicInput type={"text"} placeholder={"DD"}></BasicInput>
          </div>
        </div>

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
