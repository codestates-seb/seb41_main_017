import styled from "styled-components";

const Page = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;

  p {
    margin-left: 15px;
  }
`;

const ItemCotainer = styled.div`
  width: 900px;
  height: 60px;
  border-top: 1px solid black;
  border-bottom: 1px solid black;
  display: flex;
  align-items: center;
  justify-content: center;
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

function ManyQuestionHeader() {
  return (
    <Page>
      <ItemCotainer>
        <Notice>
          <p>번호</p>
        </Notice>
        <Category>분류</Category>
        <Title>
          <p>제목</p>
        </Title>
        <Writter>작성자</Writter>
        <Time>시간</Time>
      </ItemCotainer>
    </Page>
  );
}

export default ManyQuestionHeader;
