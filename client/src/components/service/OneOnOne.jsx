import styled from "styled-components";
import OneOnOneHeader from "./OneOnOneHeader";
import OneOnOneList from "./OneOnOneList";
import BasicButton from "../BasicButton";
import { Link, NavLink } from "react-router-dom";
import { useState } from "react";
import axios from "axios";
import BASE_URL from "../../constants/BASE_URL";
import { useEffect } from "react";
import {OtherPagination} from "../../components/OtherPagination"

const QuestionContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;
const Page = styled.div`
  // max-width: 900px;
  .question_btn {
    margin-top: 50px;
    display: flex;
    align-items: center;
    justify-content: right;
  }
`;

function OneOnOne() {
  const [question, setQuestion] = useState("");
  const [page, setPage] = useState(0);

  const fetchData = async () => {
    await axios
      .get(`${BASE_URL}/board/inquiry?page=${page}&size=5`, {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      })
      .then((res) => setQuestion(res.data))
      .catch((err) => console.log(err));
  };
  useEffect(() => {
    fetchData();
  }, [page]);

  return (
    <Page>
      <OneOnOneHeader></OneOnOneHeader>
      <OneOnOneList question={question.data}></OneOnOneList>
      <div className="question_btn">
        <Link to={`/service/one-on-one/inquiry`}>
          <BasicButton p_width={"20"} p_height={"7"}>
            문의하기
          </BasicButton>
        </Link>
      </div>
      <OtherPagination state={page} setState={setPage} pageInfo={question.pageInfo}/>
    </Page>
  );
}

export default OneOnOne;
