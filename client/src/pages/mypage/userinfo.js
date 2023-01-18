import { useEffect, useState } from "react";
import Mypagehead from "../../components/MypageHead";
import styled from "styled-components";
import BasicButton from "../../components/BasicButton";
import GenderRadio from "../../components/signup/GenderRadio"
import BasicInput from "../../components/BasicInput";
import ModalContainer from "../../components/signup/ModalCotainer"
import SignupBtn from "../../components/signup/SignupBtn";
import { Page, CheckboxContent, IdBlock } from "../../styles/signupStyle";

const Layout = styled.div`
  width: 450px;
  margin: 0 auto;

  .submit{
    text-align:center;
    padding-top:20px;
    padding-bottom:20px;
  }
`
function Userinfo() {
  const [address, setAddress] = useState("");
  const [test, setTest] = useState("");
  return (
    <Mypagehead>
      <Layout>
      <Page>
      <IdBlock>
        <div className="input_cotainer">
          <BasicInput
            label={"아이디"}
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
            width={"100%"}
            type={"text"}
            placeholder={"변경하실 비밀번호를 입력해주세요"}
          ></BasicInput>
        </div>
        <div className="input_cotainer">
          <BasicInput
            label={"이름"}
            width={"100%"}
            type={"text"}
            placeholder={"이름을 입력해주세요"}
          ></BasicInput>
        </div>
        <div className="input_cotainer">
          <BasicInput
            label={"이메일"}
            type={"text"}
            width={"100%"}
            placeholder={"이메일을 입력해주세요"}
          ></BasicInput>
          <ModalContainer type={"checkId"} onClick />
        </div>
        <span>성별</span>
        <GenderRadio></GenderRadio>
        <div className="input_cotainer">
          <BasicInput
            label={"핸드폰"}
            width={"100%"}
            type={"text"}
            placeholder={"-를 포함한 숫자를 입력해주세요"}
          ></BasicInput>
        </div>
        <div className="input_birth">
          <BasicInput
            label={"생년월일"}
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
        <div className="submit"><BasicButton p_height={"20"} p_width={"80"}>수정하기</BasicButton></div>
        </IdBlock>
        </Page>
      </Layout>
    </Mypagehead>
  );
}

export default Userinfo;
