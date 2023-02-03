import axios from "axios";
import { useEffect } from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
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

function SuccessPayment() {
  const navigate = useNavigate();
  const id = useParams();
  const location = useLocation();

  const handleClickBtn = (e) => {
    e.preventDefault();
    navigate("/mypage/orderitem");
    window.location.reload();
  };

  const FetchData = () => {
    axios
      .get(`${process.env.REACT_APP_URL}/payments/success${location.search}`)
      .then((res) => console.log(res))
      .catch((err) => console.error(err));
  };

  useEffect(() => {
    FetchData();
  }, []);

  return (
    <Container>
      <TitleContainer>
        <h2>결제하기</h2>
      </TitleContainer>
      <SuccessContainer>
        <div className="top_text">결제가 완료되었습니다.</div>

        <div className="bottom_text">주문 내역을 확인해보세요!</div>
      </SuccessContainer>

      <ButtonWrapper onClick={handleClickBtn}>
        <BasicButton p_width={"28"} p_height={"13"}>
          주문 내역 보기{" "}
        </BasicButton>{" "}
      </ButtonWrapper>
    </Container>
  );
}

export default SuccessPayment;
