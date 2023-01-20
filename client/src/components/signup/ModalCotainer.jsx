import React, { useState } from "react";
import Modal from "./Modal";
import GuideModal from "./GuideModal";
import Post from "./Post";
import styled from "styled-components";
import axios from "axios";
import BASE_URL from "../../constants/BASE_URL";

const AddressBtn = styled.button`
  width: 100%;
  margin-left: 20px;
  height: 50px;
  border: 1px solid gray;
  margin-top: 19.5px;
`;

function ModalContainer({ setSignupAddress, type, signupId, signupEmail }) {
  const [modalOpen, setModalOpen] = useState(false);
  const [response, setResponse] = useState("");

  const openModal = () => {
    setModalOpen(true);
  };
  const closeModal = () => {
    setModalOpen(false);
  };

  const handleCheckBtn = (e) => {
    e.preventDefault();
    axios
      .get(`${BASE_URL}/users/username-check?username=${signupId}`)

      .then((res) => {
        console.log(res);
        setResponse(res.status);

        openModal();
        // window.alert("사용가능한 아이디입니다");
      })

      .catch((err) => {
        console.log(err);
        setResponse(err.response.data.status);
        openModal();
      });
  };

  const handleCheckEmailBtn = (e) => {
    e.preventDefault();
    axios
      .get(`${BASE_URL}/users/email-check?email=${signupEmail}`)

      .then((res) => {
        console.log(res);
        setResponse(res.status);

        openModal();
        // window.alert("사용가능한 아이디입니다");
      })

      .catch((err) => {
        console.log(err);
        setResponse(err.response.data.status);
        openModal();
      });
  };

  if (type === "address") {
    return (
      <>
        <AddressBtn onClick={openModal}>주소검색</AddressBtn>

        <Modal open={modalOpen} close={closeModal} header="Modal heading">
          <Post
            setModalOpen={setModalOpen}
            setSignupAddress={setSignupAddress}
          ></Post>
        </Modal>
      </>
    );
  }
  if (type === "checkId") {
    return (
      <>
        <AddressBtn onClick={handleCheckBtn}>중복확인</AddressBtn>
        <GuideModal
          checkId={"checkId"}
          type={"checkId"}
          response={response}
          open={modalOpen}
          close={closeModal}
          header="Modal heading"
        ></GuideModal>
      </>
    );
  }
  if (type === "checkEmail") {
    return (
      <>
        <AddressBtn onClick={handleCheckEmailBtn}>중복확인</AddressBtn>
        <GuideModal
          type={"checkEmail"}
          checkEmail={"checkEmail"}
          response={response}
          open={modalOpen}
          close={closeModal}
          header="Modal heading"
        ></GuideModal>
      </>
    );
  }
}

export default ModalContainer;
