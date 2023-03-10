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
      return alert("??????????????? ???????????? ???????????????.");
    }
    if (receiverName === "") {
      return alert("???????????? ???????????? ???????????????.");
    }
    if (receiverPhoneNumber === "") {
      return alert("???????????? ???????????? ???????????????");
    }
    if (signupAddress === "") {
      return alert("????????? ???????????? ???????????????");
    }
    if (detailAddress === "") {
      return alert("??????????????? ???????????? ???????????????.");
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
          <button>{"????????? ??????"}</button>
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
                <p className="title_child">{"????????? ??????"}</p>
              </div>
            </div>
            <div className="modal_formBox">
              <SideInput
                label={"????????????"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"???????????? ?????? ????????? ?????????????????? ex)???,?????? ???"}
                defaultValue={destinationName}
                onChange={(e) => setDestinationName(e.target.value)}
              />
              <SideInput
                label={"?????????"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"?????? ???????????? ?????? ????????? ??????????????????"}
                defaultValue={receiverName}
                onChange={(e) => setReceiverName(e.target.value)}
              />
              <SideInput
                label={"?????????"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"???????????? ?????? ???????????? ??????????????????"}
                defaultValue={receiverPhoneNumber}
                onChange={(e) => setReceiverPhoneNumber(e.target.value)}
              />

              <IdBlock>
                <div className="input_cotainer">
                  <div className="input_cotainer_input">
                    <BasicInput
                      disabled
                      label={"??????"}
                      star={"*"}
                      width={"100%"}
                      type={"text"}
                      // address={"address"}
                      min_height={"0"}
                      placeholder={"????????? ??????????????????"}
                      // setValue2={setAddress}
                      defaultValue={signupAddress}
                      onChange={(e) => setPostAddress(e.target.value)}
                    ></BasicInput>
                    <div className="detail_address"><BasicInput setValue={setDetailAddress} type={"text"} placeholder={"??????????????? ??????????????????"} width={"100%"} /></div>
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
                  ????????????
                </Basicbutton>
              </div>
              <div onClick={() => setIsopen(false)}>
                <Basicbutton p_height={"10"} p_width={"10"}>??????</Basicbutton>
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
