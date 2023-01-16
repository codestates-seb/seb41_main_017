import styled from "styled-components";
import OneOnOneHeader from "./OneOnOneHeader";
import OneOnOneItem from "./OneOnOneItem";
import BasicButton from "../BasicButton";
import { Link, NavLink } from "react-router-dom";

function OneOnOne() {
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

  return (
    <Page>
      {/* <QuestionContainer> */}
      <OneOnOneHeader></OneOnOneHeader>

      <OneOnOneItem></OneOnOneItem>

      {/* </QuestionContainer> */}
      <div className="question_btn">
        <BasicButton href={"/one-on-one/inquiry"} p_width={"20"} p_height={"7"}>
          문의하기
        </BasicButton>
      </div>
    </Page>
  );
}

export default OneOnOne;
