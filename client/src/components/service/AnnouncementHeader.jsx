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
  width: 15%;
`;
const Title = styled.div`
  width: 65%;
`;
const Writter = styled.div`
  width: 10%;
  text-align: center;
`;

const Time = styled.div`
  width: 10%;
  text-align: center;
`;

function AnnouncementHeader() {
  return (
    <Page>
      <ItemCotainer>
        <Notice>
          <p>공지</p>
        </Notice>
        <Title>
          <p>제목</p>
        </Title>
        <Writter>작성자</Writter>
        <Time>시간</Time>
      </ItemCotainer>
    </Page>
  );
}

export default AnnouncementHeader;
