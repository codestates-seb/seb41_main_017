import styled from "styled-components";
import { IoIosAdd } from "react-icons/io";
import { useState } from "react";
import BasicInput from "./BasicInput";
import Basicbutton from "./BasicButton";
import ModalContainer from "./signup/ModalCotainer";
import { IdBlock } from "../styles/signupStyle";
import SideInput from "./SideInput";
import axios from "axios";

const Layout = styled.div`
  display: flex;
  align-items: center;
  cursor: pointer;

  .icons {
    border: 1px solid red;
    color: red;
    border-radius: 50%;
  }
`;

const Modal = styled.div`
  position: fixed;
  background-color: rgba(0, 0, 0, 0.333);
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;

  .modal_inbox {
    position: relative;
    padding: 10px;
    width: 500px;
    border: 1px solid black;
    border-radius: 10px;
    background-color: hsla(0, 0%, 100%, 0.936);
    display: flex;
    flex-direction: column;

    .close {
      position: absolute;
      top: 10px;
      right: 10px;

      button {
        font-size: 18px;
      }
    }

    .modal_inbox_title {
      display: flex;
      justify-content: center;

      .title_child {
        padding-top: 10px;
        font-size: 18px;
      }
    }
  }

  .modal_formBox {
    padding: 30px 50px 5px 50px;
  }

  .btns {
    display: flex;
    justify-content: center;
    gap: 10px;
    padding-top: 60px;
    padding-bottom: 10px;
  }

  .input_cotainer {
    flex-direction: column;

    .input_cotainer_input {
      position: relative;
      .input_cotainer_addresbtn {
        position: absolute;
        margin-top:32%;
        top:0;
        right:20px;
        bottom:0;
        left:-20px;
        & button{
          background-color:#BDBDBD;
        }

      }
    }
  }
`;

function Postmodal() {
  const [signupAddress, setSignupAddress] = useState("");
  const [postAddress, setPostAddress] = useState("");
  const [address, setAddress] = useState("");
  const [isopen, setIsopen] = useState(false);
  const [destinationName, setDestinationName] = useState("");
  const [receiverName, setReceiverName] = useState("");
  const [receiverPhoneNumber, setReceiverPhoneNumber] = useState("");
  const [detailAddress, setDetailAddress] = useState("");

  const save = () => {
    if (destinationName === "") {
      return alert("배송지명이 입력되지 않았습니다.");
    }
    if (receiverName === "") {
      return alert("수령인이 입력되지 않았습니다.");
    }
    if (receiverPhoneNumber === "") {
      return alert("연락처가 입력되지 않았습니다");
    }
    if (signupAddress === "") {
      return alert("주소가 입력되지 않았습니다");
    }
    if (detailAddress === "") {
      return alert("상세주소가 입력되지 않았습니다.");
    }

    axios
      .post(
        `${process.env.REACT_APP_URL}/destination`,
        {
          destinationName,
          receiverName,
          receiverPhoneNumber,
          address: `${signupAddress} ${detailAddress}`,
          defaultSelect: false,
        },
        {
          headers: {
            authorization: JSON.parse(localStorage.getItem("token"))
              .authorization,
          },
        },
        setIsopen(false)
      )
      .then(() => window.location.reload());
  };

  return (
    <>
      <Layout onClick={() => setIsopen(true)}>
        <IoIosAdd className="icons" />
        <div>
          <button>{"배송지 추가"}</button>
        </div>
      </Layout>
      {isopen ? (
        <Modal>
          <div className="modal_inbox">
            <div className="close" onClick={() => setIsopen(false)}>
              <button>X</button>
            </div>
            <div className="modal_inbox_title">
              <div>
                <p className="title_child">{"배송지 추가"}</p>
              </div>
            </div>
            <div className="modal_formBox">
              <SideInput
                label={"배송지명"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"배송받을 곳의 이름을 입력해주세요 ex)집,회사 등"}
                defaultValue={destinationName}
                onChange={(e) => setDestinationName(e.target.value)}
              />
              <SideInput
                label={"수령인"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"직접 수령하실 분의 이름을 입력해주세요"}
                defaultValue={receiverName}
                onChange={(e) => setReceiverName(e.target.value)}
              />
              <SideInput
                label={"연락처"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"수령하실 분의 연락처를 입력해주세요"}
                defaultValue={receiverPhoneNumber}
                onChange={(e) => setReceiverPhoneNumber(e.target.value)}
              />

              <IdBlock>
                <div className="input_cotainer">
                  <div className="input_cotainer_input">
                    <BasicInput
                      disabled
                      label={"주소"}
                      star={"*"}
                      width={"100%"}
                      type={"text"}
                      // address={"address"}
                      min_height={"0"}
                      placeholder={"주소를 입력해주세요"}
                      // setValue2={setAddress}
                      defaultValue={signupAddress}
                      onChange={(e) => setPostAddress(e.target.value)}
                    ></BasicInput>
                    <div className="detail_address"><BasicInput setValue={setDetailAddress} type={"text"} placeholder={"상세주소를 입력해주세요"} width={"100%"} /></div>
                    <div className="input_cotainer_addresbtn">
                      <ModalContainer
                        setSignupAddress={setSignupAddress}
                        type={"address"}
                      />
                    </div>
                  </div>
                </div>
              </IdBlock>
            </div>
            <div className="btns">
              <div>
                <Basicbutton onClick={save} p_height={"10"} p_width={"10"}>
                  저장하기
                </Basicbutton>
              </div>
              <div onClick={() => setIsopen(false)}>
                <Basicbutton p_height={"10"} p_width={"10"}>닫기</Basicbutton>
              </div>
            </div>
          </div>
        </Modal>
      ) : (
        false
      )}
    </>
  );
}

export default Postmodal;
