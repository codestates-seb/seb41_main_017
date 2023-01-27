import { useEffect, useState } from "react";
import Mypagehead from "../../components/MypageHead";
import styled from "styled-components";
import BasicInput from "../../components/BasicInput";
import ModalContainer from "../../components/signup/ModalCotainer";
import { IdBlock } from "../../styles/signupStyle";
import GenderRadio from "../../components/signup/GenderRadio";
import BasicButton from "../../components/BasicButton";
import axios from "axios";
import Guidance from "../../components/Guidance";

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
  const [userName, setUserName] = useState(null);
  const [userPassword, setUserPassword] = useState(null);
  const [userEmail, setUserEmail] = useState(null);
  const [userPhone, setUserPhone] = useState(null);
  const [userYear, setUserYear] = useState(null);
  const [userMonth, setUserMonth] = useState(null);
  const [userDay, setUserDay] = useState(null);
  const [userCheck, setCheck] = useState(null);

  const userPatch = () => {
    
    axios.patch(
      `${process.env.REACT_APP_URL}/destination/users`,
      {
       name: userName,
      //  password : userPassword, 대기중
        email: userEmail,
        phoneNumber: userPhone,
        birthDate: `${userYear}-${userMonth}-${userDay}`,
        genderType: userCheck,
      },
      {
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      }
    )
    .then(() => alert("변경완료"))
    .catch(()=> alert("수정되지않음"))

  };

  return (
    <Mypagehead>
      <Layout>
        <IdBlock>
          <div className="input_cotainer">
            <BasicInput
              setValue={setUserName}
              label={"이름"}
              width={"100%"}
              type={"text"}
              placeholder={`새로운 이름을 입력해주세요`}
            />
          </div>
          <div className="input_cotainer">
            <BasicInput
              setValue={setUserPassword}
              label={"비밀번호"}
              width={"100%"}
              type={"password"}
              placeholder={"새로운 비밀번호를 입력해주세요."}
            />
          </div>
          <div className="input_cotainer">
            <div className="input_box">
              <BasicInput
                setValue={setUserEmail}
                label={"이메일"}
                type={"text"}
                width={"100%"}
                placeholder={`새로운 이메일을 입력해주세요`}
              />
            </div>
            <div className="check_btn">
              <ModalContainer type={"checkEmail"} signupEmail={userEmail} />
            </div>
          </div>
          <div className="input_cotainer">
            <BasicInput
              setValue={setUserPhone}
              label={"핸드폰"}
              width={"100%"}
              type={"text"}
              placeholder={`새로운 핸드폰 번호를 입력해주세요`}
            />
          </div>
          <div className="input_birth">
            <BasicInput
              setValue={setUserYear}
              label={"생년월일"}
              type={"text"}
              width={"90%"}
              placeholder={"YYYY"}
            ></BasicInput>
            <BasicInput
              setValue={setUserMonth}
              type={"text"}
              placeholder={"MM "}
              width={"90%"}
            ></BasicInput>
            <BasicInput
              setValue={setUserDay}
              type={"text"}
              placeholder={"DD"}
              width={"90%"}
            ></BasicInput>
          </div>
          <div>
            <span>성별</span>
            <GenderRadio setCheck={setCheck}></GenderRadio>
          </div>
          <div className="submit">
            <BasicButton
              children={"수정하기"}
              font={"20"}
              radius={"5"}
              p_height={"14"}
              p_width={"150"}
              onClick={userPatch}
            />
          </div>
        </IdBlock>
      </Layout>
    </Mypagehead>
  );
}

export default Userinfo;
