import styled from "styled-components";
import { IoIosAdd } from "react-icons/io";
import { useState } from "react";
import BasicInput from "../components/BasicInput";
import Basicbutton from "../components/BasicButton";
import ModalContainer from "../components/signup/ModalCotainer";
import { IdBlock } from "../styles/signupStyle";
import SideInput from "./SideInput";

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
      top: 5px;
      right: 5px;

      button {
        font-size: 18px;
      }
    }

    .modal_inbox_title {
      display: flex;
      justify-content: center;

      .title_child {
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
    padding-bottom: 10px;
  }
`;

function Modalbutton({ text }) {
  const [isopen, setIsopen] = useState(false);
  const [address, setAddress] = useState("");

  const [test, setTest] = useState("");

  return (
    <>
      <Layout onClick={() => setIsopen(true)}>
        <IoIosAdd className="icons" />
        <div>
          <button>{text}</button>
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
                <button className="title_child">{text}</button>
              </div>
            </div>
            <div className="modal_formBox">
              <SideInput
                label={"????????????"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"???????????? ?????? ????????? ?????????????????? ex)???,?????? ???"}
                // defaultValue={test}
                // onChange={(e) => setTest(e.target.value)}
              />
              <SideInput
                label={"?????????"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"?????? ???????????? ?????? ????????? ??????????????????"}
                // defaultValue={test}
                // onChange={(e) => setTest(e.target.value)}
              />
              <SideInput
                label={"?????????"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"???????????? ?????? ???????????? ??????????????????"}
                // defaultValue={test}
                // onChange={(e) => setTest(e.target.value)}
              />
              <IdBlock>
                <div className="input_cotainer">
                  <BasicInput
                    label={"??????"}
                    star={"*"}
                    width={"100%"}
                    type={"text"}
                    address={"address"}
                    min_height={"0"}
                    placeholder={"????????? ??????????????????"}
                    defaultValue={address}
                    onChange={(e) => setAddress(e.target.value)}
                  ></BasicInput>
                  <ModalContainer setAddress={setAddress} type={"address"} />
                </div>
              </IdBlock>
              <SideInput
                label={"???????????????"}
                flex_d={"column"}
                padding={"6px"}
                defaultValue={test}
                onChange={(e) => setTest(e.target.value)}
                type={"combo"}
                list={[
                  "?????? ??? ???????????? ???????????????",
                  "?????? ??? ??? ?????? ????????????",
                  "?????? ??? ??????????????????",
                  "?????? ????????????",
                  "?????? ??????",
                ]}
              />
            </div>
            <div className="btns">
              <div>
                <Basicbutton p_height={"5"}>????????????</Basicbutton>
              </div>
              <div onClick={() => setIsopen(false)}>
                <Basicbutton p_height={"5"}>??????</Basicbutton>
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

export default Modalbutton;
