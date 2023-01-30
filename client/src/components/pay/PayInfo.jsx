import styled from "styled-components";

const PayInfoContainer = styled.div`
  margin-top: 30px;
  width: 90%;
  border-bottom: 1px solid black;

  h2 {
    font-size: 16px;
    padding: 5px;
  }
`;

const PayInfoBox = styled.div`
  .price,
  .payment {
    width: 100%;
    display: flex;
  }

  .title {
    width: 15%;
    margin-left: 20px;
    margin-top: 25px;
  }

  .content {
    margin-top: 25px;
  }
`;

function PayInfo({ price }) {
  return (
    <>
      <PayInfoContainer>
        <h2>결제정보</h2>
      </PayInfoContainer>
      <PayInfoBox>
        <div className="price">
          <div className="title">총 결제 가격</div>
          <div className="content">{price.info.totalPrice}원</div>
        </div>
        <div className="payment">
          <div className="title">결제수단</div>
          <div className="content">토스페이먼츠</div>
        </div>
      </PayInfoBox>
    </>
  );
}

export default PayInfo;
