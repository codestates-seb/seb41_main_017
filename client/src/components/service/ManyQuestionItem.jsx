import { Link } from "react-router-dom";
import styled from "styled-components";
import Dummy from "./data.json";

const Page = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

const ItemCotainer = styled.div`
  width: 900px;
  height: 60px;
  border-top: 1px solid black;
  border-bottom: 1px solid black;
  display: flex;
  align-items: center;
  justify-content: center;
  p {
    margin-left: 15px;
  }
`;

const Notice = styled.div`
  width: 5%;
`;
const Category = styled.div`
  width: 15%;
  text-align: center;
`;
const Title = styled.div`
  width: 60%;
`;
const Writter = styled.div`
  width: 10%;
  text-align: center;
`;

const Time = styled.div`
  width: 10%;
  text-align: center;
`;

function ManyQuestionItem() {
  // const data = [
  //   {
  //     id: 1,
  //     notice: 1,
  //     category: "시스템 오류",
  //     title: "주문내역이 안보여요",
  //     writter: "운영팀",
  //     time: "2023.01.09",
  //   },
  //   {
  //     id: 2,
  //     notice: 2,
  //     category: "주문/결제",
  //     title: "결제가 안돼요.",
  //     writter: "운영팀",
  //     time: "2023.01.11",
  //   },
  //   {
  //     id: 3,
  //     notice: 3,
  //     category: "취소/교환/환불",
  //     title: "배송취소하고 싶어요.",
  //     writter: "운영팀",
  //     time: "2023.01.12",
  //   },
  //   {
  //     id: 4,
  //     notice: 4,
  //     category: "회원",
  //     title: "회원정보 수정하고싶어요.",
  //     writter: "운영팀",
  //     time: "2023.01.12",
  //   },
  //   {
  //     id: 5,
  //     notice: 5,
  //     category: "배송",
  //     title: "배송이 안와요.",
  //     writter: "운영팀",
  //     time: "2023.01.12",
  //   },
  // ];

  return (
    <Page>
      <div>
        {Dummy.Question.map((el) => {
          return (
            <ItemCotainer key={el.id}>
              <Notice>
                <p>{el.notice}</p>
              </Notice>
              <Category>{el.category}</Category>
              <Title>
                <Link to={`/question/often/${el.id}`}>{el.title}</Link>
              </Title>
              <Writter>{el.writter}</Writter>
              <Time>{el.time}</Time>
            </ItemCotainer>
          );
        })}
      </div>
    </Page>
  );
}

export default ManyQuestionItem;
