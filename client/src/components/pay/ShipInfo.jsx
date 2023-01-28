import styled from "styled-components";

const ShippingContainer = styled.div`
  margin-top: 30px;
  width: 90%;
  border-bottom: 1px solid black;

  h2 {
    font-size: 16px;
    padding: 5px;
  }
`;

const ShippingBox = styled.div`
  .name,
  .phone,
  .address {
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

function ShipInfo() {
  return (
    <>
      <ShippingContainer>
        <h2>배송정보</h2>
      </ShippingContainer>
      <ShippingBox>
        <div className="name">
          <div className="title">수령인</div>
          <div className="content">1</div>
        </div>
        <div className="phone">
          <div className="title">수령인 연락처</div>
          <div className="content">2</div>
        </div>
        <div className="address">
          <div className="title">주소</div>
          <div className="content">3</div>
        </div>
      </ShippingBox>
    </>
  );
}

export default ShipInfo;
