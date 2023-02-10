import React from "react";
import styled from "styled-components";
import AnswerItem from "./AnswerItem";

function AnswerList({ answer }) {
  return (
    <>
      {answer &&
        answer.map((answer) => {
          return <AnswerItem answer={answer} key={answer.id} />;
        })}
    </>
  );
}

export default AnswerList;
