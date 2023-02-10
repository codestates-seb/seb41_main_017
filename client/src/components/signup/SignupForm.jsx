import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import styled from "styled-components";

import GenderRadio from "./GenderRadio";
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
  const [check, setCheck] = useState("남성");
  const [allChecked, setAllChecked] = useState(false);
  const [termsChecked, setTermsChecked] = useState(false);
  const [privacyChecked, setPrivacyChecked] = useState(false);
  const [response, setResponse] = useState("");
  const [successModal, setSuccessModal] = useState(false);
  const [rejectModal, setRecjectModal] = useState(false);
  const [failModal, setFailModal] = useState(false);

  const navigate = useNavigate();

  const nameCheck = /^[ㄱ-ㅎ|가-힣|A-Za-z]+\s*[ㄱ-ㅎ|가-힣|A-Za-z]+$/g;
  const emailCheck =
    /^^[A-Za-z0-9]+@[A-Za-z]+\.?[A-Za-z]{2,3}\.[A-Za-z]{2,3}$$/;
  const phoneCheck = /^\d{3}-\d{3,4}-\d{4}$/;
  const birthDateCheck =
    /^(19[0-9][0-9]|20[0-9][0-9])-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/g;

  const regexp =
    /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&])[A-Za-z\d!@#$%^&*]{8,20}$/;

  function handleAllChecked() {
    setAllChecked(!allChecked);
    setTermsChecked(!allChecked);
    setPrivacyChecked(!allChecked);
  }

  const isWriteUserInfo = () => {
    return (
      signupId !== "" &&
      signupPassword !== "" &&
      checkPassword !== "" &&
      name !== "" &&
      signupEmail !== "" &&
      phoneNum !== "" &&
      year !== "" &&
      month !== "" &&
      day !== "" &&
      detailAddress !== ""
    );
  };

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
      .post(
        `${process.env.REACT_APP_URL}/users/signup`,
        JSON.stringify(reqbody),
        { headers }
      )

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
        if (!isWriteUserInfo()) {
          setFailModal(true);
        }
      });
  };

  const handleClickSignup = (e) => {
    e.preventDefault();
    setSuccessModal(false);
    navigate("/login");
    window.location.reload();
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
        <div className="error_box">
          {signupId.length < 6 && 0 < signupId.length ? (
            <div className="error_text">
              6자 이상 16자 이하의 글자 수를 입력해주세요{" "}
            </div>
          ) : null}
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

        <div className="error_box">
          {signupPassword && !regexp.test(signupPassword) ? (
            <div className="error_text">
              숫자, 알파벳, 특수문자(!@#$%^&*) 포함 8자 이상 20자 이하로
              입력해주세요
            </div>
          ) : null}
        </div>
        <div className="error_box">
          {checkPassword && signupPassword !== checkPassword ? (
            <div className="error_text2">비밀번호가 일치하지 않습니다</div>
          ) : null}
        </div>

        <div className="input_cotainer">
          <BasicInput
            setValue={setName}
            star={"*"}
            label={"이름"}
            width={"100%"}
            type={"text"}
            placeholder={"이름을 입력해주세요"}
          ></BasicInput>
        </div>

        <div className="error_box">
          {name && name.length > 0 && !nameCheck.test(name) ? (
            <div className="error_text">
              올바르지 않은 이름입니다. (공백,특수문자,숫자는 사용불가합니다.)
            </div>
          ) : null}
        </div>

        <div className="input_cotainer">
          <div className="input_box">
            <BasicInput
              setValue={setSignupEmail}
              label={"이메일"}
              star={"*"}
              type={"text"}
              width={"100%"}
              placeholder={"ex) Culinari@gmail.com"}
            ></BasicInput>
          </div>
          <div className="check_btn">
            <ModalContainer type={"checkEmail"} signupEmail={signupEmail} />
          </div>
        </div>

        <div className="error_box">
          {signupEmail &&
          signupEmail.length > 0 &&
          !emailCheck.test(signupEmail) ? (
            <div className="error_text">
              올바르지 않은 이메일 입니다. [공백, 특수문자(!@#$%^&*-_)는
              사용불가합니다.]
            </div>
          ) : null}
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

        <div className="error_box">
          {phoneNum && phoneNum.length > 0 && !phoneCheck.test(phoneNum) ? (
            <div className="error_text">
              올바르지 않은 번호 입니다. [ex 010-0000-0000]
            </div>
          ) : null}
        </div>
        <div className="input_cotainer">
          <div className="input_box">
            <BasicInput
              disabled
              label={"주소"}
              star={"*"}
              width={"100%"}
              type={"text"}
              placeholder={"주소를 입력해주세요"}
              defaultValue={signupAddress}
              onChange={(e) => setPostAddress(e.target.value)}
            ></BasicInput>
            <div className="detail_address"></div>
            <BasicInput
              setValue={setDetailAddress}
              type={"text"}
              placeholder={"상세주소를 입력해주세요"}
              width={"100%"}
            />
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
            placeholder={"YYYY"}
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
        <GenderRadio check={check} setCheck={setCheck}></GenderRadio>

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

          <CloseButton onClick={handleClickSignup}>닫기</CloseButton>
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
      {failModal && (
        <ModalWrapper>
          <SignupFailureContainer>
            <ModalTitle>가입 실패</ModalTitle>
            <ModalText>필수입력사항을 입력해주세요</ModalText>
          </SignupFailureContainer>
          <CloseButton onClick={() => setFailModal(false)}>닫기</CloseButton>
        </ModalWrapper>
      )}
    </Page>
  );
}

export default SignForm;
