import axios from "axios";
import { useParams } from "react-router-dom";
import styled from "styled-components";

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

  .top_box {
    display: flex;
    justify-content: space-between;
  }
`;

function AnswerItem({ answer }) {
  const { id } = useParams();

  const handleClickBtn = (e) => {
    e.preventDefault();

    if (window.confirm("삭제하시겠습니까?")) {
      axios
        .delete(`${process.env.REACT_APP_URL}/board/inquiry/comments/${e.target.id}`, {
          headers: {
            "Content-Type": `application/json`,
            authorization: JSON.parse(localStorage.getItem("token")).authorization,
          },
        })
        .then((res) => console.log(res))
        .catch((err) => {
          console.log(err);
        });

      window.location.reload();
    }
  };
  return (
    <ReviewContainer>
      <div className="review_box">
        <div className="top_box">
          <div className="writter">{answer.writer}</div>
          <div className="time">{answer.createdAt}</div>
        </div>

        <div className="content_box">
          <div>{answer.content}</div>
          <button id={answer.id} onClick={handleClickBtn}>
            삭제
          </button>
        </div>
      </div>
    </ReviewContainer>
  );
}

export default AnswerItem;
