import styled from "styled-components";
import SignupBtn from "./SignupBtn";
import GenderRadio from "./GenderRadio";
import { useRef, useState } from "react";
import ModalContainer from "./ModalCotainer";
import BasicInput from "../BasicInput";
import { Page, CheckboxContent, IdBlock } from "../../styles/signupStyle";
import Signup from "../../pages/sign/signup";

function SignForm(
  {
    // singupId,
    // setSignupId,
    // signupPassword,
    // setSignupPassword,
    // name,
    // setName,
  }
) {
  const [address, setAddress] = useState("");
  const [postAddress, setPostAddress] = useState("");
  const [signupId, setSignupId] = useState("");
  const [signupPassword, setSignupPassword] = useState("");
  const [checkPassword, setCheckPassword] = useState("");
  const [name, setName] = useState("");
  const [signupEmail, setSignupEmail] = useState("");
  const [PhoneNum, setPhoneNum] = useState("");
  const [detailAddress, setDetailAddress] = useState("");
  const [year, setYear] = useState("");
  const [month, setMonth] = useState("");
  const [day, setDay] = useState("");

  return (
    <Page>
      <IdBlock>
        <div className="input_cotainer">
          <BasicInput
            // setValue={(e) => setSignupId(e.target.value)}
            label={"아이디"}
            star={"*"}
            type={"text"}
            width={"100%"}
            placeholder={"아이디를 입력해주세요"}
            defaultValue={""}
          ></BasicInput>
          <ModalContainer type={"checkId"} onClick />
        </div>
        {/* {console.log(setSignupId)} */}
        <div className="input_cotainer">
          <BasicInput
            setValue={setSignupPassword}
            setValue2={setCheckPassword}
            label={"비밀번호"}
            star={"*"}
            width={"100%"}
            type={"password"}
            password={"password"}
            placeholder={"비밀번호를 입력해주세요"}
          ></BasicInput>
        </div>
        {/* {console.log(checkPassword === signupPassword)} */}
        <div className="input_cotainer">
          <BasicInput
            setValue={(e) => setName(e.target.value)}
            label={"이름"}
            width={"100%"}
            type={"text"}
            placeholder={"이름을 입력해주세요"}
          ></BasicInput>
        </div>
        <div className="input_cotainer">
          <BasicInput
            setValue={(e) => setSignupEmail(e.target.value)}
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
            setValue={(e) => setPhoneNum(e.target.value)}
            label={"핸드폰"}
            star={"*"}
            width={"100%"}
            type={"text"}
            placeholder={"-를 포함한 숫자를 입력해주세요"}
          ></BasicInput>
        </div>
        <div className="input_cotainer">
          <BasicInput
            setValue2={setDetailAddress}
            label={"주소"}
            star={"*"}
            width={"100%"}
            type={"text"}
            address={"address"}
            placeholder={"주소를 입력해주세요"}
            defaultValue={address}
            onChange={(e) => setPostAddress(e.target.value)}
          ></BasicInput>
          <ModalContainer setAddress={setAddress} type={"address"} />
        </div>

        <div className="input_birth">
          <BasicInput
            setValue={setYear}
            label={"생년월일"}
            star={"*"}
            type={"text"}
            width={"80%"}
            placeholder={"YY"}
          ></BasicInput>
          <BasicInput
            setValue={setMonth}
            type={"text"}
            placeholder={"MM "}
            width={"80%"}
          ></BasicInput>
          <BasicInput
            setValue={setDay}
            type={"text"}
            placeholder={"DD"}
            width={"80%"}
          ></BasicInput>
        </div>
        {console.log(day)}
        {console.log(month)}
        {console.log(year)}
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
      </IdBlock>
    </Page>
  );
}

export default SignForm;
