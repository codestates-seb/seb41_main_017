import styled from "styled-components";

const DetailContainer = styled.div`
  width: 900px;
  height: 550px;
  border-bottom: 1px solid gray;
  h2 {
    font-size: 20px;
    width: 100%;
  }
  .content_container {
    width: 100%;

    font-size: 18px;
    display: flex;
    justify-content: space-between;
  }
  button {
    margin-right: 15px;
  }

  .detail_title {
  }
`;
const DetailBar = styled.div`
  width: 100%;
  background-color: #ffdede;
  font-size: 15px;
  margin-top: 15px;
  display: flex;
  border-top: 1px solid pink;
  border-bottom: 1px solid pink;

  .writter {
    width: 80%;
    margin-left: 10px;
  }
  .category {
    width: 10%;
    text-align: center;
  }
  .time {
    width: 10%;
    text-align: center;
  }
`;

const ReviewContainer = styled.div`
  width: 900px;
  border-bottom: 1px solid gray;

  .review_box {
    margin-top: 15px;
    margin-bottom: 15px;
  }
  .writter {
    margin-bottom: 25px;
  }
  .content_box {
    display: flex;
    justify-content: space-between;
  }

  button {
    color: #c26d53;
  }
`;

const ContentBox = styled.div`
  margin-top: 45px;
  margin-left: 10px;
`;

const AnswerContainer = styled.div`
  display: flex;

  .answer_box {
    margin-top: 20px;
  }

  .answer_btn {
    margin-top: 48px;
    margin-left: 20px;
  }
  textarea {
    width: 710px;
    height: 75px;
    border: 1px solid gray;
    border-radius: 5px;
    padding: 10px;
  }
`;

const Page = styled.div`
  max-width: 900px;
  .btn_container {
    margin-top: 20px;
    display: flex;
    align-item: center;
    justify-content: right;
  }
`;

export {
  DetailContainer,
  DetailBar,
  ReviewContainer,
  ContentBox,
  AnswerContainer,
  Page,
};
