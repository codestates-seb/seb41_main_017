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
                <Link to={`/service/question/often/${el.id}`}>{el.title}</Link>
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
