import axios from "axios";
import React from "react";
import { useEffect } from "react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import styled from "styled-components";
import { ReviewContainer, AnswerContainer } from "../../styles/OneOnOneStyle";
import BasicButton from "../BasicButton";
import AnswerList from "./AnswerList";

function Answer() {
  const { id } = useParams();
  const [content, setContent] = useState("");
  const [answer, setAnswer] = useState("");

  const handleChangeContent = (e) => {
    setContent(e.target.value);
  };

  const handleClickBtn = (e) => {
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
      content: content,
    });

    axios
      .post(`${process.env.REACT_APP_URL}/board/inquiry/${id}/comments`, data, header)
      .then((res) => console.log(res))
      .catch((err) => console.log(err));
    window.location.reload();
  };

  const fetchData = () => {
    axios
      .get(`${process.env.REACT_APP_URL}/board/inquiry/${id}/comments`, {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token")).authorization,
        },
      })
      .then((res) => setAnswer(res.data.data))
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    fetchData();
  }, []);
  return (
    <>
      <ReviewContainer>
        <div className="review_box">
          <div className="writter">운영팀</div>

          <div className="content_box">
            <div>죄송합니다. 고객님의 문의사항은 현재 처리중에 있습니다.</div>{" "}
          </div>
        </div>
      </ReviewContainer>

      <AnswerList answer={answer} />
      <AnswerContainer>
        <div className="answer_box">
          <textarea value={content} onChange={handleChangeContent} placeholder="답변을 입력해주세요"></textarea>
        </div>
        <div onClick={handleClickBtn} className="answer_btn">
          <BasicButton p_width={"60"} p_height={"31"}>
            답변 등록
          </BasicButton>
        </div>
      </AnswerContainer>
    </>
  );
}

export default Answer;
