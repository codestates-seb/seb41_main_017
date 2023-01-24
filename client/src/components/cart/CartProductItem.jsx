import axios from "axios";
import { useEffect, useState } from "react";
import styled from "styled-components";
import BASE_URL from "../../constants/BASE_URL";
import CheckBox from "../CheckBox";
import DeleteButton from "../DeleteButton";
import QuantityBox from "../QuantityBox";

const Container = styled.div`
  height: 140px;
  padding: 20px 0;
  border-bottom: 1px solid #ddd;
  display: flex;
  align-items: center;
`;

const ImageWrapper = styled.div`
  width: 100px;
  height: 100px;
  margin-left: 10px;
  margin-right: 20px;

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
`;

const ProductInfo = styled.div`
  width: calc(100% - 300px);
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
  min-width: 150px;
  text-align: end;
`;

const DeleteButtonWrapper = styled.div`
  margin: 0 24px;
`;

function CartProductItem({ data }) {
  const [quantity, setQuantity] = useState(data.quantity);

  useEffect(() => {
    const patchQuantity = () => {
      const body = {
        quantity,
      };
      const header = {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token")).authorization,
        },
      };

      axios.patch(`${BASE_URL}/carts/${data.id}`, body, header);
    };

    patchQuantity();
  }, [quantity]);

  return (
    <Container>
      <CheckBox size="24px" />

      <ImageWrapper>
        <img src={"https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/DaangnMarket_logo.png/800px-DaangnMarket_logo.png"} />
      </ImageWrapper>

      <ProductInfo>
        <ProductTitle>{data.product.name}</ProductTitle>
        <ProductPrice>{data.product.price.toLocaleString()}원</ProductPrice>
        <QuantityBoxWrapper>
          <QuantityBox quantity={quantity} setQuantity={setQuantity} />
        </QuantityBoxWrapper>
      </ProductInfo>

      <TotalPrice>{(data.quantity * data.product.price).toLocaleString()}원</TotalPrice>

      <DeleteButtonWrapper>
        <DeleteButton />
      </DeleteButtonWrapper>
    </Container>
  );
}

export default CartProductItem;
