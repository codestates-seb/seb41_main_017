import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import { Link, NavLink } from "react-router-dom";
import styled from "styled-components";

import OneOnOneHeader from "./OneOnOneHeader";
import OneOnOneList from "./OneOnOneList";
import BasicButton from "../BasicButton";
import { OtherPagination } from "../OtherPagination";

const Page = styled.div`
  // max-width: 900px;
  .question_btn {
    margin-top: 50px;
    display: flex;
    align-items: center;
    justify-content: right;
  }
`;

const PagenationWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

function OneOnOne() {
  const [question, setQuestion] = useState("");
  const [page, setPage] = useState(0);

  const fetchData = async () => {
    await axios
      .get(`${process.env.REACT_APP_URL}/board/inquiry?page=${page}&size=10`, {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token")).authorization,
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

      <PagenationWrapper>
        <OtherPagination state={page} setState={setPage} pageInfo={question.pageInfo} />
      </PagenationWrapper>
    </Page>
  );
}

export default OneOnOne;
