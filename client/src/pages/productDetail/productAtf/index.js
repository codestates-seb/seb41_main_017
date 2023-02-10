import styled from "styled-components";

import ProductHeader from "./ProductHeader";
import ProductDataList from "./ProductDataList";
import ProductSelection from "./ProductSelection";

const Container = styled.main`
  display: flex;
  justify-content: space-between;

  .shipping {
    font-weight: 500;
    line-height: 1.36;
    letter-spacing: -0.5px;
    color: rgb(153, 153, 153);
    margin-bottom: 6px;
  }

  .title-container {
    display: flex;
    justify-content: space-between;
  }
`;

const ProductImage = styled.img`
  width: 430px;
  height: 552px;
`;

const ProductInfo = styled.section`
  width: 570px;

  h1 {
    font-weight: 500;
    font-size: 24px;
    line-height: 44px;
    letter-spacing: -0.5px;
    margin-right: 20px;
  }

  .content {
    padding-top: 5px;
    font-size: 14px;
    font-weight: 400;
    color: rgb(181, 181, 181);
    line-height: 19px;
    letter-spacing: -0.5px;
  }

  .price {
    display: flex;
    flex-direction: row;
    align-items: flex-end;
    padding-top: 19px;
    font-weight: bold;
    line-height: 30px;
    letter-spacing: -0.5px;

    .number {
      padding-right: 4px;
      font-size: 28px;
    }

    .unit {
      font-size: 18px;
      position: relative;
      top: 1.5px;
    }
  }
`;

function ProductAtf({ data, quantity, setQuantity, totalPrice }) {
  return (
    <Container>
      <ProductImage src={data.data && data.data.productImageDtos?.[0].imgUrl} />
      <ProductInfo>
        <ProductHeader data={data} />

        <ProductDataList data={data} />

        <ProductSelection position={"header"} data={data} quantity={quantity} setQuantity={setQuantity} totalPrice={totalPrice} />
      </ProductInfo>
    </Container>
  );
}

export default ProductAtf;
