import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import BasicButton from "../BasicButton";

const Container = styled.div`
  max-width: 1050px;
  margin: 44px auto;
  padding: 0 50px;
`;

const TitleContainer = styled.div`
  display: flex;
  width: 100%;
  border-bottom: 1px solid black;
  padding: 5px;

  svg {
    margin-right: 5px;
  }

  h2 {
    padding-top: 4px;
    align-self: center;
    font-size: 24px;
  }
`;

const SuccessContainer = styled.div`
  //   display: flex;
  //   align-items: center;
  //   justify-content: center;
  font-size: 24px;
  margin-top: 85px;

  .top_text {
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .bottom_text {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
    margin-bottom: 40px;
  }
`;

const ButtonWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

function FailPayment() {
  const navigate = useNavigate();

  const handleClickBtn = (e) => {
    e.preventDefault();
    navigate("/pay");
    window.location.reload();
  };
  return (
    <Container>
      <TitleContainer>
        <h2>결제하기</h2>
      </TitleContainer>
      <SuccessContainer>
        <div className="top_text">결제가 실패하였습니다.</div>

        <div className="bottom_text">재시도를 눌러주세요!</div>
      </SuccessContainer>

      <ButtonWrapper onClick={handleClickBtn}>
        <BasicButton p_width={"28"} p_height={"13"}>
          결제 재시도{" "}
        </BasicButton>{" "}
      </ButtonWrapper>
    </Container>
  );
}

export default FailPayment;
