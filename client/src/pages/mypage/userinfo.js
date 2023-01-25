import { useEffect, useState } from "react";
import Mypagehead from "../../components/MypageHead";
import styled from "styled-components";
import BasicButton from "../../components/BasicButton";
import GenderRadio from "../../components/signup/GenderRadio";
import BasicInput from "../../components/BasicInput";
import ModalContainer from "../../components/signup/ModalCotainer";
import { Page, CheckboxContent, IdBlock } from "../../styles/signupStyle";


const Layout = styled.div`
  width: 450px;
  margin: 0 auto;

  .submit {
    text-align: center;
    padding-top: 20px;
    padding-bottom: 20px;
  }
`;
function Userinfo() {
  const [address, setAddress] = useState("");
  const [test, setTest] = useState("");
  return (
    <Mypagehead title={"유저정보"}>
      <Layout>
      </Layout>
    </Mypagehead>
  );
}

export default Userinfo;
