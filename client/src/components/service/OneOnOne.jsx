import styled from "styled-components";
import OneOnOneHeader from "./OneOnOneHeader";
import OneOnOneList from "./OneOnOneList";
import BasicButton from "../BasicButton";
import { Link, NavLink } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import BASE_URL from "../../constants/BASE_URL";
import { useEffect } from "react";

const QuestionContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;
const Page = styled.div`
  .question_btn {
    margin-top: 50px;
    display: flex;
    align-items: center;
    justify-content: right;
  }
`;

function OneOnOne() {
  const [question, setQuestion] = useState("");

  const fetchData = async () => {
    await axios
      .get(`${BASE_URL}/board/inquiry?page=0&size=10`)
      .then((res) => setQuestion(res.data.data))
      .catch((err) => console.log(err));
  };
  useEffect(() => {
    fetchData();
  }, []);

  // console.log(question);

  return (
    <Page>
      <OneOnOneHeader></OneOnOneHeader>
      <OneOnOneList question={question}></OneOnOneList>
      <div className="question_btn">
        <Link to={`/service/one-on-one/inquiry`}>
          <BasicButton p_width={"20"} p_height={"7"}>
            문의하기
          </BasicButton>
        </Link>
      </div>
    </Page>
  );
}

export default OneOnOne;
