import { useState } from "react";
import styled from "styled-components";
import AnswerIcon from "../../../components/AnswerIcon";
import QuestionIcon from "../../../components/QuestionIcon";

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

function InquiryDetail({ element }) {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <>
      <tr>
        <td className="title" onClick={() => setIsOpen(!isOpen)}>
          {element.title}
        </td>
        <td className="author">{element.createdBy}</td>
        <td className="created_date">{element.createdAt}</td>
        <td className="status">{"답변대기"}</td>
      </tr>
      {isOpen ? (
        <InquiryContainer>
          <td colSpan="4">
            <QuestionWrapper>
              <QuestionIcon />
              <div className="question">{element.content}</div>
            </QuestionWrapper>
            {element.status === "답변완료" ? (
              <AnswerWrapper>
                <AnswerIcon />
                <div className="answer">{element.answer}</div>
              </AnswerWrapper>
            ) : null}
          </td>
        </InquiryContainer>
      ) : null}
    </>
  );
}

export default InquiryDetail;
