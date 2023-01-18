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

function OftenDetail({ Dummy }) {
  console.log(Dummy);
  return (
    <DetailContainer>
      <div className="content_container">
        <div>{Dummy.title}</div>
      </div>
      <DetailBar>
        <div className="writter">{Dummy.writter}</div>
        <div className="category">{Dummy.category}</div>
        <div className="time">{Dummy.time}</div>
      </DetailBar>
      <ContentBox>내용</ContentBox>
    </DetailContainer>
  );
}

export default OftenDetail;
