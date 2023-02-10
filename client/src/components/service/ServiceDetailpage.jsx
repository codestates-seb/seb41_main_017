import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import BasicButton from "../BasicButton";

const DetailContainer = styled.div`
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
  line-height: 2;
`;

const Page = styled.div`
  max-width: 900px;
  .btn_container {
    margin-top: 20px;
    display: flex;
    align-item: center;
    justify-content: right;
  }
`;

function ServiceDetailpage({ Dummy }) {
  const navigate = useNavigate();

  const handleClickBtn = (e) => {
    e.preventDefault();
    navigate(`/service/announcement`);
  };
  return (
    <Page>
      <DetailContainer>
        <div className="content_container">
          <div>{Dummy.title}</div>
        </div>
        <DetailBar>
          <div className="writter">{Dummy.writter}</div>
          <div className="category">{Dummy.category}</div>
          <div className="time">{Dummy.time}</div>
        </DetailBar>
        <ContentBox>
          <p>안녕하세요 고객님, Culinari 서비스를 이용해주셔서 감사합니다.</p>
        </ContentBox>
        <ContentBox>{Dummy.content}</ContentBox>
        <ContentBox>감사합니다</ContentBox>
      </DetailContainer>
      <div className="btn_container" onClick={handleClickBtn}>
        <BasicButton p_width={"20"} p_height={"7"}>
          목록으로{" "}
        </BasicButton>{" "}
      </div>
    </Page>
  );
}

export default ServiceDetailpage;
