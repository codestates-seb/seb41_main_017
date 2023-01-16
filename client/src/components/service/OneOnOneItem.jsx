import styled from "styled-components";

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

function OneOnOneItem() {
  return (
    <Page>
      <div>
        <ItemCotainer>
          <Notice>
            <p>1</p>
          </Notice>
          <Category>시스템 오류</Category>
          <Title>
            <p>내가 작성한 문의</p>
          </Title>
          <Writter>나</Writter>
          <Time>23.01.10</Time>
        </ItemCotainer>
      </div>
    </Page>
  );
}

export default OneOnOneItem;
