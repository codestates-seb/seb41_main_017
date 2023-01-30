import { Link } from "react-router-dom";
import styled from "styled-components";

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
  display: flex;
  align-items: center;
  justify-content: center;
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

function OneOnOneItem({ question }) {
  return (
    <Page>
      <div>
        <ItemCotainer>
          <Notice>{question.id} </Notice>
          <Category>{question.category}</Category>

          <Title>
            <Link to={`${question.id}`}>{question.title}</Link>
          </Title>
          <Writter>나</Writter>
          <Time>{question.createdAt}</Time>
        </ItemCotainer>
      </div>
    </Page>
  );
}

export default OneOnOneItem;
