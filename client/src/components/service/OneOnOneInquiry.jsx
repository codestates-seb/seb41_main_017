import styled from "styled-components";
import BasicInput from "../BasicInput";
import BasicButton from "../BasicButton";
import { BsTextarea } from "react-icons/bs";
import { useState } from "react";
const InquiryCotainer = styled.form`
  width: 900px;

  h2 {
    font-size: 20px;
    width: 100%;
  }
  .content_container {
    width: 100%;
    margin-top: 20px;
    margin-bottom: 10px;
  }

  .btn_container {
    margin-top: 20px;
    display: flex;
    align-item: center;
    justify-content: right;
  }

  textarea {
    width: 100%;

    height: 15.25em;
  }
  .title_content {
    width: 100%;
    height: 35px;
  }
`;

function OneOnOneInquiry() {
  const [question, setQuestion] = useState({
    title: "",
    content: "",
  });

  const handleChange = (e) => {
    e.preventDefault();
    const { name, value } = e.target;
    setQuestion(...setQuestion);
  };

  return (
    <InquiryCotainer>
      <div className="content_container">
        <h2>문의 작성</h2>
      </div>
      <div className="content_container">
        <label htmlFor="title">글 제목</label>
      </div>
      <input
        className="title_content"
        type="text"
        placeholder="  제목을 입력해주세요"
      />
      <div className="content_container">
        <label htmlFor="content">글 내용</label>
      </div>
      <textarea placeholder="내용을 입력해주세요"></textarea>
      <div className="btn_container">
        <BasicButton p_width={"20"} p_height={"7"}>
          문의 작성
        </BasicButton>{" "}
      </div>
    </InquiryCotainer>
  );
}

export default OneOnOneInquiry;
