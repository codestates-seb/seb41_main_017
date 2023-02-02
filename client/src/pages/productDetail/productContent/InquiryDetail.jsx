import axios from "axios";
import { useState } from "react";
import styled from "styled-components";

import AnswerIcon from "../../../components/AnswerIcon";
import QuestionIcon from "../../../components/QuestionIcon";
import ModalComponent from "../../../pages/productDetail/productContent/ModalComponent";
import EditInquiry from "./EditInquiry";

const InquiryContainer = styled.tr`
  background-color: rgb(250, 250, 250);
  border-bottom: 1px solid rgb(244, 244, 244);

  .question,
  .answer {
    margin-left: 12px;
    padding-top: 2px;
    font-size: 14px;
    word-break: break-all;
    line-height: 19px;
    letter-spacing: -0.5px;
  }

  .edit_delete_button_container {
    display: flex;
    justify-content: end;
    margin: 0px 20px 10px 20px;

    .edit,
    .delete {
      margin-left: 10px;
      color: #8f8f8f;

      &:hover {
        color: #ff6767;
      }
    }
  }
`;

const QuestionWrapper = styled.div`
  display: flex;
  padding: 22px 20px 30px;
  align-items: flex-start;
`;

const AnswerWrapper = styled.div`
  display: flex;
  padding: 22px 20px 30px;
  align-items: flex-start;
`;

function InquiryDetail({ data, element }) {
  const parsedDate = new Date(element.createdAt);
  const year = parsedDate.getFullYear();
  const month = String(parsedDate.getMonth() + 1).padStart(2, "0");
  const day = String(parsedDate.getDate()).padStart(2, "0");
  const [isOpen, setIsOpen] = useState(false);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);

  const handleEditButtonClick = () => {
    setIsEditModalOpen(true);
  };

  const handleDeleteButtonClick = () => {
    if (window.confirm("해당 문의글을 삭제하시겠습니까?")) {
      const config = {
        headers: {
          "Content-Type": `application/json`,
          Authorization: JSON.parse(localStorage.getItem("token")).authorization,
        },
      };

      axios.delete(`${process.env.REACT_APP_URL}/product/inquiry/${element.id}`, config);

      window.location.reload();
    }
  };

  return (
    <>
      <tr>
        <td className="title" onClick={() => setIsOpen(!isOpen)}>
          {element.title}
        </td>
        <td className="author">{element.createdBy}</td>
        <td className="created_date">{`${year}-${month}-${day}`}</td>
        <td className="status">{"답변대기"}</td>
      </tr>
      {isOpen ? (
        <InquiryContainer>
          <td colSpan="4">
            <QuestionWrapper>
              <QuestionIcon />
              <div className="question">{element.content}</div>
            </QuestionWrapper>
            <div className="edit_delete_button_container">
              <button className="edit" onClick={handleEditButtonClick}>
                수정
              </button>
              <button className="delete" onClick={handleDeleteButtonClick}>
                삭제
              </button>
            </div>
            {element.status === "답변완료" ? (
              <AnswerWrapper>
                <AnswerIcon />
                <div className="answer">{element.answer}</div>
              </AnswerWrapper>
            ) : null}
            {isEditModalOpen ? (
              <ModalComponent component={<EditInquiry data={data} element={element} setIsEditModalOpen={setIsEditModalOpen} />} />
            ) : null}
          </td>
        </InquiryContainer>
      ) : null}
    </>
  );
}

export default InquiryDetail;
