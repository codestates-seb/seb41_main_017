import React from "react";
import OneOnOneItem from "./OneOnOneItem";

function OneOnOneList({ question }) {
  return (
    <>
      {question &&
        question.map((question) => {
          return <OneOnOneItem question={question} key={question.id} />;
        })}
    </>
  );
}

export default OneOnOneList;
