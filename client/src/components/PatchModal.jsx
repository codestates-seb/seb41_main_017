import styled from "styled-components";
import { useEffect, useState } from "react";
import SideInput from "./SideInput";
import BasicInput from "./BasicButton";
import axios from "axios";

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
  const [addressid, setAddressId] = useState(data[0].id);
//   받은데이터로 초기값을 설정할 스테이트들
  const [title, setTitle] = useState("");
  const [processStatus, setProcessStatus] = useState("");
  const [content, setContent] = useState("");

  const changeValue = () => {
    axios.patch(
      `${process.env.REACT_APP_URL}/destination/${addressid}`,
      {
        addressid,
        title,
        content,
        processStatus,
      },
      {
        headers: {
          Authorization: localStorage.getItem("accessToken"),
        },
      }
    );
    close()
    setTimeout(() => window.location.reload(), 1000);
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
          {/* ? */}
          <SideInput
            label={"title"}
            flex_d={"column"}
            padding_b={"20px"}
            placeholder={`${data[0].destinationName}`}
            defaultValue={title}
            onChange={(e) => setTitle(e.target.value)}
          />
          {/* ? */}
          <SideInput
            label={"processStatus"}
            flex_d={"column"}
            padding_b={"20px"}
            placeholder={`${data[0].receiverName}`}
            defaultValue={processStatus}
            onChange={(e) => setProcessStatus(e.target.value)}
          />
          {/* ? */}
          <SideInput
            label={"content"}
            flex_d={"column"}
            padding_b={"20px"}
            placeholder={`${data[0].address}`}
            defaultValue={content}
            onChange={(e) => setContent(e.target.value)}
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
