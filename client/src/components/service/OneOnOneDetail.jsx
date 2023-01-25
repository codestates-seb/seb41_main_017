import axios from "axios";
import { useEffect } from "react";
import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import BASE_URL from "../../constants/BASE_URL";
import styled from "styled-components";

const DetailContainer = styled.div`
  //   background-color: red;
  width: 900px;
  height: 550px;
  border-bottom: 1px solid gray;
  h2 {
    font-size: 20px;
    width: 100%;
  }
  .content_container {
    width: 100%;
    margin-left: 10px;
    font-size: 18px;
    display: flex;
    justify-content: space-between;
  }
  button {
    margin-right: 15px;
  }
`;
const DetailBar = styled.div`
  width: 100%;
  background-color: #ffdede;
  font-size: 15px;
  margin-top: 15px;
  display: flex;
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

const ContentBox = styled.div`
  margin-top: 45px;
  margin-left: 10px;
`;

function OneOnOneDetail() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [data, setData] = useState("");

  const fetchData = () => {
    axios
      .get(`${BASE_URL}/board/inquiry/${id}`, {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      })
      .then((res) => setData(res.data.data))
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleDelete = (e) => {
    e.preventDefault();

    if (window.confirm("삭제하시겠습니까?")) {
      axios
        .delete(`${BASE_URL}/board/inquiry/${id}`, {
          headers: {
            "Content-Type": `application/json`,
            authorization: JSON.parse(localStorage.getItem("token"))
              .authorization,
          },
        })
        .catch((err) => {
          console.log(err);
        });

      navigate(`/service/one-on-one`);
      window.location.reload();
    }
  };

  return (
    <DetailContainer>
      <div className="content_container">
        <div>{data.title}</div>
        <button onClick={handleDelete}>삭제</button>
      </div>
      <DetailBar>
        <div className="writter">나</div>
        <div className="category">카테고리</div>
        <div className="time">23.01.24</div>
      </DetailBar>
      <ContentBox>{data.content}</ContentBox>
    </DetailContainer>
  );
}

export default OneOnOneDetail;
