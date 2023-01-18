import styled from "styled-components";
import SignupBtn from "./SignupBtn";
import GenderRadio from "./GenderRadio";
import { useEffect, useState } from "react";
import ModalContainer from "./ModalCotainer";
import BasicInput from "../BasicInput";
import { Page, CheckboxContent, IdBlock } from "../../styles/signupStyle";

function SignForm() {
  const [address, setAddress] = useState("");
  const [postAddress, setPostAddress] = useState("");

  const [test, setTest] = useState("ss");


  return (
    <Page>
      <IdBlock>
        {/* <div className="input_title">
          <span>아이디</span>
          <span className="essential">*</span>
        </div> */}
        <div className="input_cotainer">
          <BasicInput
            label={"아이디"}
            star={"*"}
            type={"text"}
            width={"100%"}
            placeholder={"아이디를 입력해주세요"}
            defaultValue={test}
            onChange={e => setTest(e.target.value)}
          ></BasicInput>
          <ModalContainer type={"checkId"} onClick />
        </div>
        <div className="input_cotainer">
          <BasicInput
            label={"비밀번호"}
            star={"*"}
            width={"100%"}
            type={"text"}
            password={"password"}
            placeholder={"비밀번호를 입력해주세요"}
          ></BasicInput>
        </div>
        <div className="input_cotainer">
          <BasicInput
            label={"이메일"}
            star={"*"}
            type={"text"}
            width={"100%"}
            placeholder={"이메일을 입력해주세요"}
          ></BasicInput>
          <ModalContainer type={"checkId"} onClick />
        </div>
        <div className="input_cotainer">
          <BasicInput
            label={"핸드폰"}
            star={"*"}
            width={"100%"}
            type={"text"}
            placeholder={"-를 포함한 숫자를 입력해주세요"}
          ></BasicInput>
        </div>
        <div className="input_cotainer">
          <BasicInput
            label={"주소"}
            star={"*"}
            width={"100%"}
            type={"text"}
            address={"address"}
            placeholder={"주소를 입력해주세요"}
            defaultValue={address}
            onChange={(e) => console.log("asdf")}
          ></BasicInput>
          <ModalContainer setAddress={setAddress} type={"address"} />
        </div>

        <div className="input_birth">
          <BasicInput
            label={"생년월일"}
            star={"*"}
            type={"text"}
            width={"80%"}
            placeholder={"YY"}
          ></BasicInput>
          <BasicInput
            type={"text"}
            placeholder={"MM "}
            width={"80%"}
          ></BasicInput>
          <BasicInput
            type={"text"}
            placeholder={"DD"}
            width={"80%"}
          ></BasicInput>
        </div>
        <span>성별</span>
        <GenderRadio></GenderRadio>
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
        {/*

       
        
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
        </div> */}

        {/* <br />
        <span>성별</span>
        <GenderRadio></GenderRadio> */}
      </IdBlock>
      {/* <CheckboxContent>
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
      </CheckboxContent> */}
      {/* <SignupBtn type={"signup"} /> */}
    </Page>
  );
}

export default SignForm;
