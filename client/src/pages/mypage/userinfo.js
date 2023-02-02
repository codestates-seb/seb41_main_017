import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import { IdBlock } from "../../styles/signupStyle";
import Mypagehead from "../../components/MypageHead";
import BasicInput from "../../components/BasicInput";
import ModalContainer from "../../components/signup/ModalCotainer";
import GenderRadio from "../../components/signup/GenderRadio";
import BasicButton from "../../components/BasicButton";
import Guidance from "../../components/Guidance";

// id2
// !@#123password


const Layout = styled.div`
  width: 450px;
  margin: 0 auto;

  .input_cotainer {
    position: relative;
  }

  .validation{
    position: absolute;
    bottom: 8px;
    color: red;
    font-size: 12px;
  }


  .input_birth{
    position: relative;
  }
  .validation_date{
    position: absolute;
    bottom: 8px;
    color: red;
    font-size: 12px;
  }

  .submit {
    text-align: center;
    padding-top: 20px;
    padding-bottom: 20px;
  }

  .error_text {
    font-size: 12px;
    position: absolute;
    left: 0;
    bottom: 8px;
    color: red;
  }

  .password {
    position: relative;
    .cover {
      position: absolute;
      z-index: 1;
      top: 20px;
      display: flex;
      justify-content: center;
      align-items: center;
      width: 100%;
      height: 50px;
      border: 1px solid #ff6767;
      border-radius: 3px;
      background-color: #fff7f5;
      color: #ff6767;
      cursor: pointer;
      transition: 1s;

      &:hover {
        transform: scale(1.05);
      }
    }

    .passwordBtn {
      position: absolute;
      right: 10px;
      top: 31px;
      color: #ff6767;
      border: 1px solid #ff6767;
      border-radius: 3px;
      background-color: #fff7f5;
      padding:5px;

    }
  }
`;
function Userinfo() {
  const [userData, setUserData] = useState({});
  const [userName, setUserName] = useState(undefined);
  const [userPassword, setUserPassword] = useState(undefined);
  const [userEmail, setUserEmail] = useState(undefined);
  const [userPhone, setUserPhone] = useState(undefined);
  const [userYear, setUserYear] = useState(undefined);
  const [userMonth, setUserMonth] = useState(undefined);
  const [userDay, setUserDay] = useState(undefined);
  const [userCheck, setUserCheck] = useState(undefined);
  const [isdisabled, setIsdisabled] = useState(false);
  const [ismodal, setIsmodal] = useState(false);
  const userBirthDate = `${userYear}-${userMonth}-${userDay}`;
  const nameCheck = /^[ㄱ-ㅎ|가-힣|A-Za-z]+\s*[ㄱ-ㅎ|가-힣|A-Za-z]+$/g;
  const passwordCheck = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&])[A-Za-z\d!@#$%^&*]{8,20}$/;
  const emailCheck = /^^[A-Za-z0-9]+@[A-Za-z]+\.?[A-Za-z]{2,3}\.[A-Za-z]{2,3}$$/;
  const phoneCheck = /^\d{3}-\d{3,4}-\d{4}$/;
  const birthDateCheck = /^(19[0-9][0-9]|20[0-9][0-9])-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/g;
  const navigate = useNavigate();


  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_URL}/users`, {
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      })
      .then((res) => {
        setUserCheck(res.data.data.gender);
        setUserData(res.data.data);
      })
      .catch((error) => {
        console.log("실패");
      })
  }, []);


  const userPatch = () => {
    
    if (isdisabled === true) {
      axios
        .patch(
          `${process.env.REACT_APP_URL}/users/password-edit`,
          { password: userPassword },
          {
            headers: {
              authorization: JSON.parse(localStorage.getItem("token"))
                .authorization,
            },
          }
        )
        .then(() => {
          window.localStorage.removeItem("token");
          navigate("/login");
        });
    }

    if (isdisabled === false) {
      axios
        .patch(
          `${process.env.REACT_APP_URL}/users`,
          {
            name: userName,
            email: userEmail,
            phoneNumber: userPhone,
            birthDate:
              userYear === undefined ||
              userMonth === undefined ||
              userDay === undefined
                ? userData.birthDate
                : userBirthDate,
            genderType: userCheck,
          },
          {
            headers: {
              authorization: JSON.parse(localStorage.getItem("token"))
                .authorization,
            },
          }
        )
        .then(() => {
          setIsmodal(false);
          window.location.reload();
        })
        .catch(() => alert("변경사항이 적용되지 않았습니다."));
    }
  };

  return (
    <Mypagehead>
      <Layout isdisabled={isdisabled}>
        <IdBlock>
          <div className="input_cotainer" onClick={() => setIsdisabled(false)}>
            <BasicInput
              setValue={setUserName}
              label={"이름"}
              width={"100%"}
              type={"text"}
              placeholder={`${userData.name}`}
              disabled={isdisabled ? true : false}
            />
            {userName && userName.length > 0 && !nameCheck.test(userName) ?(
              <div className="validation">올바르지 않은 이름입니다. (공백,특수문자,숫자는 사용불가합니다.)</div>
            ): null}
          </div>
          <div className="input_cotainer password">
            <BasicInput
              setValue={setUserPassword}
              label={"비밀번호"}
              width={"100%"}
              type={"password"}
              placeholder={"비밀번호를 입력해주세요"}
              disabled={isdisabled ? false : true}
            ></BasicInput>
            {isdisabled ? null : (
              <div className="cover" onClick={() => setIsdisabled(!isdisabled)}>
                {"비밀번호 변경하기"}
              </div>
            )}
            {!passwordCheck.test(userPassword) === false ? (
              <button className="passwordBtn" onClick={() => setIsmodal(true)}>
                {"변경하기"}
              </button>
            ) : null}
            {isdisabled ? (
              <div className="error_box">
                {userPassword && !passwordCheck.test(userPassword) ? (
                  <div className="error_text">
                    숫자, 알파벳, 특수문자(!@#$%^&*) 포함 8자 이상 20자 이하로
                    입력해주세요
                  </div>
                ) : null}
              </div>
            ) : null}
          </div>

          <div className="input_cotainer" onClick={() => setIsdisabled(false)}>
            <div className="input_box">
              <BasicInput
                setValue={setUserEmail}
                label={"이메일"}
                type={"text"}
                width={"100%"}
                placeholder={`${userData.email}`}
                disabled={isdisabled ? true : false}
              />
              {userEmail && userEmail.length > 0 && !emailCheck.test(userEmail) ?(
              <div className="validation">올바르지 않은 이메일 입니다. [공백, 특수문자(!@#$%^&*-_)는 사용불가합니다.]</div>
            ): null}
            </div>
            <div className="check_btn">
              <ModalContainer
                type={"checkEmail"}
                signupEmail={userEmail}
                disabled={isdisabled ? true : false}
              />
            </div>
          </div>
          <div className="input_cotainer" onClick={() => setIsdisabled(false)}>
            <BasicInput
              setValue={setUserPhone}
              label={"핸드폰"}
              width={"100%"}
              type={"text"}
              placeholder={`${userData.phoneNumber}`}
              disabled={isdisabled ? true : false}
            />
             {userPhone && userPhone.length > 0 && !phoneCheck.test(userPhone) ?(
              <div className="validation">올바르지 않은 번호 입니다. [ex 010-0000-0000]</div>
            ): null}
          </div>
          <div className="input_birth" onClick={() => setIsdisabled(false)}>
            <BasicInput
              setValue={setUserYear}
              label={"생년월일"}
              type={"text"}
              width={"90%"}
              placeholder={`${userData.birthDate?.slice(0, 4)}`}
              disabled={isdisabled ? true : false}
            ></BasicInput>
            <BasicInput
              setValue={setUserMonth}
              type={"text"}
              placeholder={`${userData.birthDate?.slice(5, 7)}`}
              width={"90%"}
              disabled={isdisabled ? true : false}
            ></BasicInput>
            <BasicInput
              setValue={setUserDay}
              type={"text"}
              placeholder={`${userData.birthDate?.slice(8, 10)}`}
              width={"90%"}
              disabled={isdisabled ? true : false}
            ></BasicInput>
            {userBirthDate.length !== 29 && !birthDateCheck.test(userBirthDate) ?(
              <div className="validation_date">올바르지 않습니다. [ex 2000-12-12]</div>
            ): null}
          </div>
          <div onClick={() => setIsdisabled(false)}>
            <span>성별</span>
            <GenderRadio
              check={userCheck}
              setCheck={setUserCheck}
              isdisabled={isdisabled}
            ></GenderRadio>
          </div>
          <div className="submit">
            <BasicButton
              children={"수정하기"}
              font={"20"}
              radius={"5"}
              p_height={"14"}
              p_width={"150"}
              onClick={() => setIsmodal(true)}
              disabled={isdisabled}/>
            {ismodal ? (
              <Guidance
                text={"해당변경 사항을 적용하시겠습니까?"}
                ok={userPatch}
                close={() => setIsmodal(false)}/>
            ) : null}
          </div>
        </IdBlock>
      </Layout>
    </Mypagehead>
  );
}
export default Userinfo;
