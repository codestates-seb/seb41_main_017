import styled from "styled-components";
import CheckBox from "../CheckBox";
import DeleteButton from "../DeleteButton";
import QuantityBox from "../QuantityBox";

const Container = styled.div`
  height: 124px;
  padding: 20px 0;
  border-bottom: 1px solid #ddd;
  display: flex;
  align-items: center;
`;

const Image = styled.img`
  width: 85px;
  height: 100%;
  margin-left: 39px;
  margin-right: 33px;
`;

const ProductInfo = styled.div`
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
`;

const ProductTitle = styled.div`
  margin-bottom: 5px;
`;

const ProductPrice = styled.div`
  margin-bottom: 5px;
`;

const QuantityBoxWrapper = styled.div`
  margin-top: auto;
`;

const TotalPrice = styled.div`
  width: 150px;
`;

const DeleteButtonWrapper = styled.div`
  margin: 0 24px;
`;

function CartProductItem({ src }) {
  return (
    <Container>
      <CheckBox />

      <Image src={src} />

      <ProductInfo>
        <ProductTitle>상품 이름입니다.</ProductTitle>
        <ProductPrice>10,000원</ProductPrice>
        <QuantityBoxWrapper>
          <QuantityBox quantity={10} />
        </QuantityBoxWrapper>
      </ProductInfo>

      <TotalPrice>10,000,000원</TotalPrice>

      <DeleteButtonWrapper>
        <DeleteButton />
      </DeleteButtonWrapper>
    </Container>
  );
}

export default CartProductItem;
