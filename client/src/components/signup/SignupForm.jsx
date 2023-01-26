import styled from "styled-components";
import GenderRadio from "./GenderRadio";
import { useState } from "react";
import ModalContainer from "./ModalCotainer";
import BasicInput from "../BasicInput";
import {
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
} from "../../styles/signupStyle";
import BasicButton from "../BasicButton";
import axios from "axios";

import BASE_URL from "../../constants/BASE_URL";

function SignForm() {
  const [signupAddress, setSignupAddress] = useState("");
  const [postAddress, setPostAddress] = useState("");
  const [signupId, setSignupId] = useState("");
  const [signupPassword, setSignupPassword] = useState("");
  const [checkPassword, setCheckPassword] = useState("");
  const [name, setName] = useState("");
  const [signupEmail, setSignupEmail] = useState("");
  const [phoneNum, setPhoneNum] = useState("");
  const [detailAddress, setDetailAddress] = useState("");
  const [year, setYear] = useState("");
  const [month, setMonth] = useState("");
  const [day, setDay] = useState("");
  const [check, setCheck] = useState("");
  const [allChecked, setAllChecked] = useState(false);
  const [termsChecked, setTermsChecked] = useState(false);
  const [privacyChecked, setPrivacyChecked] = useState(false);
  const [response, setResponse] = useState("");

  const [successModal, setSuccessModal] = useState(false);
  const [rejectModal, setRecjectModal] = useState(false);

  function handleAllChecked() {
    setAllChecked(!allChecked);
    setTermsChecked(!allChecked);
    setPrivacyChecked(!allChecked);
  }

  const handleSignupBtn = (e) => {
    e.preventDefault();

    const reqbody = {
      username: signupId,
      password: signupPassword,
      name: name,
      email: signupEmail,
      phoneNumber: phoneNum,
      address: `${signupAddress} ${detailAddress}`,
      genderType: check,
      birthDate: `${year}-${month}-${day}`,
    };

    const headers = {
      "Content-Type": "application/json",
    };

    axios
      .post(`${BASE_URL}/users/signup`, JSON.stringify(reqbody), { headers })

      .then((res) => {
        if (termsChecked === true && privacyChecked === true) {
          setSuccessModal(true);
        }
      })
      .catch((err) => {
        setResponse(err.response.data.status);

        if (
          (termsChecked === false && privacyChecked === false) ||
          response === 405
        ) {
          setRecjectModal(true);
        }
      });
  };

  return (
    <Page>
      <IdBlock>
        <div className="input_cotainer">
          <div className="input_box">
            <BasicInput
              setValue={setSignupId}
              label={"아이디"}
              star={"*"}
              type={"text"}
              width={"100%"}
              placeholder={"아이디를 입력해주세요"}
              defaultValue={""}
            ></BasicInput>
          </div>
          <div className="check_btn">
            <ModalContainer type={"checkId"} signupId={signupId} />
          </div>
        </div>

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

        <div className="input_cotainer">
          <BasicInput
            setValue={setName}
            label={"이름"}
            width={"100%"}
            type={"text"}
            placeholder={"이름을 입력해주세요"}
          ></BasicInput>
        </div>

        <div className="input_cotainer">
          <div className="input_box">
            <BasicInput
              setValue={setSignupEmail}
              label={"이메일"}
              star={"*"}
              type={"text"}
              width={"100%"}
              placeholder={"이메일을 입력해주세요"}
            ></BasicInput>
          </div>
          <div className="check_btn">
            <ModalContainer type={"checkEmail"} signupEmail={signupEmail} />
          </div>
        </div>

        <div className="input_cotainer">
          <BasicInput
            setValue={setPhoneNum}
            label={"핸드폰"}
            star={"*"}
            width={"100%"}
            type={"text"}
            placeholder={"-를 포함한 숫자를 입력해주세요"}
          ></BasicInput>
        </div>
        <div className="input_cotainer">
          <div className="input_box">
            <BasicInput
              setValue2={setDetailAddress}
              label={"주소"}
              star={"*"}
              width={"100%"}
              type={"text"}
              address={"address"}
              placeholder={"주소를 입력해주세요"}
              defaultValue={signupAddress}
              onChange={(e) => setPostAddress(e.target.value)}
            ></BasicInput>
          </div>
          <div className="check_btn">
            <ModalContainer
              setSignupAddress={setSignupAddress}
              type={"address"}
            />
          </div>
        </div>
        <div className="input_birth">
          <BasicInput
            setValue={setYear}
            label={"생년월일"}
            star={"*"}
            type={"text"}
            width={"90%"}
            placeholder={"YY"}
          ></BasicInput>
          <BasicInput
            setValue={setMonth}
            type={"text"}
            placeholder={"MM "}
            width={"90%"}
          ></BasicInput>
          <BasicInput
            setValue={setDay}
            type={"text"}
            placeholder={"DD"}
            width={"90%"}
          ></BasicInput>
        </div>

        <span>성별</span>
        <GenderRadio setCheck={setCheck}></GenderRadio>

        <CheckboxContent>
          <div className="autoContent">
            <input
              type="checkbox"
              checked={allChecked}
              onChange={handleAllChecked}
            />
            <span className="CheckboxText">전체 동의하기</span>
          </div>

          <div className="autoContent">
            <input
              type="checkbox"
              checked={termsChecked}
              onChange={() => setTermsChecked(!termsChecked)}
            />
            <span className="CheckboxText">이용약관 동의하기(필수)</span>
          </div>

          <div className="autoContent">
            <input
              type="checkbox"
              checked={privacyChecked}
              onChange={() => setPrivacyChecked(!privacyChecked)}
            />
            <span className="CheckboxText">개인정보 수집 이용동의(필수)</span>
          </div>
        </CheckboxContent>

        <ButtonWrapper onClick={handleSignupBtn}>
          <BasicButton
            children={"가입하기"}
            font={"20"}
            radius={"5"}
            p_height={"14"}
            p_width={"150"}
          />
        </ButtonWrapper>
      </IdBlock>
      {successModal && (
        <ModalWrapper>
          <SignupSuccessContainer>
            <ModalTitle>가입 성공</ModalTitle>
            <ModalText>가입이 완료되었습니다.</ModalText>
          </SignupSuccessContainer>

          <CloseButton onClick={() => setSuccessModal(false)}>닫기</CloseButton>
        </ModalWrapper>
      )}
      {rejectModal && (
        <ModalWrapper>
          <SignupFailureContainer>
            <ModalTitle>가입 실패</ModalTitle>
            <ModalText>
              이용약관과 개인정보 수집 이용동의를 모두 동의해주세요.
            </ModalText>
          </SignupFailureContainer>
          <CloseButton onClick={() => setRecjectModal(false)}>닫기</CloseButton>
        </ModalWrapper>
      )}
    </Page>
  );
}

export default SignForm;
