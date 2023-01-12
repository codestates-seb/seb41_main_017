import React, { useState } from "react";
import Modal from "./Modal";
import GuideModal from "./GuideModal";
import Post from "./Post";
import styled from "styled-components";

const AddressBtn = styled.button`
  width: 30%;
  margin-left: 20px;
  min-height: 50px;
  border: 1px solid gray;
`;

function ModalContainer({ setAddress, type }) {
  // useState를 사용하여 open상태를 변경한다. (open일때 true로 만들어 열리는 방식)
  const [modalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };
  const closeModal = () => {
    setModalOpen(false);
  };
  if (type === "address") {
    return (
      <>
        <AddressBtn onClick={openModal}>주소검색</AddressBtn>
        {/* <button onClick={openModal}>주소검색</button> */}

        <Modal open={modalOpen} close={closeModal} header="Modal heading">
          <Post setModalOpen={setModalOpen} setAddress={setAddress}></Post>
        </Modal>
      </>
    );
  }
  if (type === "checkId") {
    return (
      <>
        <AddressBtn onClick={openModal}>중복확인</AddressBtn>
        <GuideModal open={modalOpen} close={closeModal} header="Modal heading">
          {/* 사용 불가능한 아이디입니다 */}
        </GuideModal>
      </>
    );
  }
}

export default ModalContainer;
