import styled from "styled-components";
import BasicInput from "../BasicInput";
import BasicButton from "../BasicButton";
import { BsTextarea } from "react-icons/bs";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

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
    resize: none;
    height: 15.25em;
  }
  .title_content {
    width: 70%;
    height: 35px;
  }
  .category {
    width: 27%;
    height: 35px;
    margin-left: 27px;
  }
`;

function OneOnOneInquiry() {
  const navigate = useNavigate();

  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [selectedOption, setSelectedOption] = useState("");

  const handleChangeTitle = (e) => {
    setTitle(e.target.value);
  };
  const handleChangeContent = (e) => {
    setContent(e.target.value);
  };

  const handleSubmitInquiry = (e) => {
    e.preventDefault();

    const token = localStorage.getItem("token");
    const parse = JSON.parse(token);
    const header = {
      headers: {
        "Content-Type": `application/json`,
        authorization: parse.authorization,
      },
    };

    let data = JSON.stringify({
      title: title,
      content: content,
      category: selectedOption,
    });

    axios
      .post(
        `http://ec2-3-37-105-24.ap-northeast-2.compute.amazonaws.com:8080/board/inquiry`,
        data,
        header
      )
      .then((res) => {})
      .catch((err) => {
        console.log(err);
      });
    navigate(`/service/one-on-one`);
    window.location.reload();
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
        value={title}
        onChange={handleChangeTitle}
      />
      <select
        className="category"
        name="inquirt_category"
        value={selectedOption}
        onChange={(e) => setSelectedOption(e.target.value)}
      >
        <option value="카테고리">카테고리를 선택해주세요</option>
        <option value="배송">배송</option>
        <option value="주문/결제">주문/결제</option>
        <option value="취소/교환/환불">취소/교환/환불</option>
        <option value="회원">회원</option>
        <option value="시스템오류">시스템오류</option>
      </select>

      <div className="content_container">
        <label htmlFor="content">글 내용</label>
      </div>
      <textarea
        placeholder="내용을 입력해주세요"
        value={content}
        onChange={handleChangeContent}
      ></textarea>

      <div className="btn_container" onClick={handleSubmitInquiry}>
        <BasicButton p_width={"20"} p_height={"7"}>
          문의 작성
        </BasicButton>{" "}
      </div>
    </InquiryCotainer>
  );
}

export default OneOnOneInquiry;
