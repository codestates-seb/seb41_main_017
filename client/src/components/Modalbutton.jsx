import styled from "styled-components";
import { IoIosAdd } from "react-icons/io";
import { useState } from "react";
import BasicInput from "../components/BasicInput";
import ModalContainer from "../components/signup/ModalCotainer";
import { IdBlock } from "../styles/signupStyle";

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
    height: 700px;
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

    .mpdal_inbox_title {
      display: flex;
      justify-content: center;

      .title_child {
        font-size: 18px;
      }
    }
  }

  .modal_formBox {
    padding: 30px 50px;
  }
`;
function Modalbutton({ text }) {
  const [isopen, setIsopen] = useState(false);
  const [address, setAddress] = useState("");
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
            <div className="mpdal_inbox_title">
              <div>
                <button className="title_child">{text}</button>
              </div>
            </div>
            <div className="modal_formBox">
              <IdBlock>
                <div className="input_cotainer">
                  <BasicInput
                    label={"배송지명"}
                    star={"*"}
                    type={"text"}
                    width={"100%"}
                    min_height={"0"}
                    placeholder={
                      "배송받을 곳의 이름을 입력해주세요 ex)집,회사 등"
                    }
                    // defaultValue={test}
                    // onChange={e => setTest(e.target.value)}
                  ></BasicInput>
                </div>
                <div className="input_cotainer">
                  <BasicInput
                    label={"수령인"}
                    star={"*"}
                    type={"text"}
                    width={"100%"}
                    min_height={"0"}
                    placeholder={"직접 수령하실 분의 이름을 입력해주세요"}
                    // defaultValue={test}
                    // onChange={e => setTest(e.target.value)}
                  ></BasicInput>
                </div>
                <div className="input_cotainer">
                  <BasicInput
                    label={"연락처"}
                    star={"*"}
                    type={"text"}
                    width={"100%"}
                    min_height={"0"}
                    placeholder={"수령하실 분의 연락처를 입력해주세요"}
                    // defaultValue={test}
                    // onChange={e => setTest(e.target.value)}
                  ></BasicInput>
                </div>
                <div className="input_cotainer">
                  <BasicInput
                    label={"주소"}
                    star={"*"}
                    width={"100%"}
                    type={"text"}
                    address={"address"}
                    min_height={"0"}
                    placeholder={"주소를 입력해주세요"}
                    // defaultValue={address}
                    // onChange={(e) => console.log("asdf")}
                  ></BasicInput>
                  <ModalContainer setAddress={setAddress} type={"address"} />
                </div>
              </IdBlock>
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
