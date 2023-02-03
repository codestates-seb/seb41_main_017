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
                label={"배송지명"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"배송받을 곳의 이름을 입력해주세요 ex)집,회사 등"}
                // defaultValue={test}
                // onChange={(e) => setTest(e.target.value)}
              />
              <SideInput
                label={"수령인"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"직접 수령하실 분의 이름을 입력해주세요"}
                // defaultValue={test}
                // onChange={(e) => setTest(e.target.value)}
              />
              <SideInput
                label={"연락처"}
                flex_d={"column"}
                mark={"on"}
                placeholder={"수령하실 분의 연락처를 입력해주세요"}
                // defaultValue={test}
                // onChange={(e) => setTest(e.target.value)}
              />
              <IdBlock>
                <div className="input_cotainer">
                  <BasicInput
                    label={"주소"}
                    star={"*"}
                    width={"100%"}
                    type={"text"}
                    address={"address"}
                    min_height={"0"}
                    placeholder={"주소를 입력해주세요"}
                    defaultValue={address}
                    onChange={(e) => setAddress(e.target.value)}
                  ></BasicInput>
                  <ModalContainer setAddress={setAddress} type={"address"} />
                </div>
              </IdBlock>
              <SideInput
                label={"배송메시지"}
                flex_d={"column"}
                padding={"6px"}
                defaultValue={test}
                onChange={(e) => setTest(e.target.value)}
                type={"combo"}
                list={[
                  "부재 시 경비실에 맡겨주세요",
                  "부재 시 문 앞에 놔주세요",
                  "배송 전 연락해주세요",
                  "직접 받을게요",
                  "직접 입력",
                ]}
              />
            </div>
            <div className="btns">
              <div>
                <Basicbutton p_height={"5"}>저장하기</Basicbutton>
              </div>
              <div onClick={() => setIsopen(false)}>
                <Basicbutton p_height={"5"}>닫기</Basicbutton>
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
