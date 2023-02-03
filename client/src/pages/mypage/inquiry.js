import Mypagehead from "../../components/MypageHead";
import styled from "styled-components";
import { useEffect } from "react";
import axios from "axios";
import { useState } from "react";
import {MypageItem} from "../../components/MypageItem"

const Layout = styled.div`
  min-height: 300px;
  display: flex;
  flex-direction: column;

  .my_question {
    border-bottom: 1px solid #d7d7d7;
    flex: 1;
    .my_question_header {
      border-top: 1px solid #515151;
      border-bottom: 1px solid #515151;
      padding: 10px 5px;
      display: flex;

      & > div {
        flex-basis: 120px;
        text-align: center;
      }

      .q_header_title {
        text-align: left;
        flex: 1;
      }
    }

    .my_question_list {
      cursor: pointer;
      justify-content: space-around;
      border-bottom: 1px solid #9b9898;
      padding: 12px 5px;
      display: flex;
      & > div {
        flex-basis: 120px;
        text-align: center;
      }

      .q_list_title {
        text-align: left;
        flex: 1;
      }

      .q_list_state {
        color: #c26d53;
      }
    }
  }

`;

function Inquiry() {
  const [myQuestion, setMyQuestion] = useState({});
  const [isId, setIsid] = useState(undefined);

  useEffect(() => {
    axios
      .get(`${process.env.REACT_APP_URL}/board/inquiry`, {
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        },
      })
      .then((res) => setMyQuestion(res.data));
  }, []);

  return (
    <div>
      {isId ? <MypageItem data={isId} setState={setIsid}/> : 
      <Mypagehead
      title={"내 1:1 문의"}
      side_title={
        <a href="/service/one-on-one">
          <span>{"고객센터 "}</span>
          <span style={{ color: "#FF6767" }}>{">"}</span>
        </a>
      }
    >
      <Layout>
        <div className="my_question">
          <div className="my_question_header">
            <div>
              <span>{"분류"}</span>
            </div>
            <div className="q_header_title">
              <span>{"제목"}</span>
            </div>
            <div>
              <span>{"작성자"}</span>
            </div>
            <div>
              <span>{"시간"}</span>
            </div>
            <div>
              <span>{"답변 상태"}</span>
            </div>
          </div>
          <ul>
            {myQuestion?.data?.map((el) => {
              return (
                <li 
                className="my_question_list" 
                key={el.id}
                onClick={()=> setIsid(el)}
                >
                  <div>
                    <span>{`${
                      el.category === "" ? "일반" : el.category
                    }`}</span>
                  </div>
                  <div className="q_list_title">
                    <span>{`${el.title}`}</span>
                  </div>
                  <div>
                    <span>{"나"}</span>
                  </div>
                  <div>
                    <span>{`${el.createdAt}`}</span>
                  </div>
                  <div className="q_list_state">
                    <span>{`${
                      el.processStatus === "STAND_BY"
                        ? "대기중"
                        : el.processStatus
                    }`}</span>
                  </div>
                </li>
              );
            })}
          </ul>
        </div>
      </Layout>
    </Mypagehead>
      }
      
    </div>
  );
}

export default Inquiry;
