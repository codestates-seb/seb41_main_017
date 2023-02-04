import { useState } from "react";
import axios from "axios";
import styled from "styled-components";
import SideInput from "./SideInput";
import BasicInput from "./BasicButton";

const Layout = styled.div`
  background-color: rgba(0, 0, 0, 0.333);
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;

  .patchModal_modal {
    position: relative;
    width: 400px;
    padding: 20px 20px;
    display: flex;
    border-radius: 10px;
    flex-direction: column;
    background-color: hsla(0, 0%, 100%, 0.936);
    border: 1px solid black;

    .patchModal_head {
      display: flex;
      justify-content: center;
      align-items: center;

      .close {
        position: absolute;
        top: 5px;
        right: 5px;
        button {
          font-size: 18px;
        }
      }
    }

    .patchModal_body {
    }

    .patchModal_btns {
      display: flex;
      justify-content: center;
      gap: 10px;
    }
  }
`;

function PatchModal({ close, data }) {
  const [destinationName, setDestinationName] = useState(data[0].destinationName);
  const [address, setAddress] = useState(data[0].address);
  const [receiverName, setReceiverName] = useState(data[0].receiverName);
  const [receiverPhoneNumber, setReceiverPhoneNumber] = useState(data[0].receiverPhoneNumber);

  const changeValue = () => {

    if(destinationName === "" ){
      return alert("배송지명을 입력해주세요")
    }
    if(address === ""){
      return alert("주소를 입력해주세요")
    }
    if(receiverName === ""){
      return alert("수취인을 입력해주세요")
    }
    if(receiverPhoneNumber === ""){
      return alert("연락처를 입력해주세요")
    }

    axios.patch(
      `${process.env.REACT_APP_URL}/destination/${data[0].id}`,
      {
        destinationName,
        address,
        receiverName,
        receiverPhoneNumber,
      },
      {
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      }
    ).then(() => window.location.reload());
    close();
  };

  return (
    <Layout>
      <div className="patchModal_modal">
        <div className="patchModal_head">
          <p>{"배송지 수정"}</p>
          <div className="close">
            <button onClick={close}>X</button>
          </div>
        </div>
        <div className="patchModal_body">
          <SideInput
            label={"배송지명"}
            flex_d={"column"}
            padding_b={"20px"}
            defaultValue={destinationName}
            placeholder={"변경하실 배송지명 을 적어주세요"}
            onChange={(e) => e === undefined ? setDestinationName(data[0].destinationName) : setDestinationName(e.target.value)}
          />
          <SideInput
            label={"주소"}
            flex_d={"column"}
            padding_b={"20px"}
            defaultValue={address}
            placeholder={"변경하실 주소를 적어주세요"}
            onChange={(e) => setAddress(e.target.value)}
          />
          <SideInput
            label={"수취인"}
            flex_d={"column"}
            padding_b={"20px"}
            defaultValue={receiverName}
            placeholder={"변경하실 수취인 명을 적어주세요"}
            onChange={(e) => setReceiverName(e.target.value)}
          />
          <SideInput
            label={"연락처"}
            flex_d={"column"}
            padding_b={"20px"}
            defaultValue={receiverPhoneNumber}
            placeholder={"새로운 번호를 '-'  포함하여 적어주세요"}
            onChange={(e) => setReceiverPhoneNumber(e.target.value)}
          />
        </div>
        <div className="patchModal_btns">
          <div onClick={changeValue}>
            <BasicInput>저장</BasicInput>
          </div>
          <div onClick={close}>
            <BasicInput>취소</BasicInput>
          </div>
        </div>
      </div>
    </Layout>
  );
}

export default PatchModal;

