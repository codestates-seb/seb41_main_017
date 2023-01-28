import styled from "styled-components";

const OrderContainer = styled.div`
  display: flex;
  padding: 5px;

  width: 90%;
  border-bottom: 1px solid black;
  margin-top: 40px;
`;

const OrderBox = styled.div`
  .name,
  .phone,
  .email {
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

function OrderInfo() {
  return (
    <>
      <OrderContainer>
        <h2>주문자정보</h2>
      </OrderContainer>
      <OrderBox>
        <div className="name">
          <div className="title">이름</div>
          <div className="content">최준호</div>
        </div>
        <div className="phone">
          <div className="title">휴대폰</div>
          <div className="content">휴대폰 번호</div>
        </div>
        <div className="email">
          <div className="title">이메일</div>
          <div className="content">hello@gmail.com</div>
        </div>
      </OrderBox>
    </>
  );
}

export default OrderInfo;
