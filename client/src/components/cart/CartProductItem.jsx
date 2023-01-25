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

function CartProductItem({ item, data, setData, index, checkedList, setCheckedList }) {
  const [quantity, setQuantity] = useState(item.quantity);
  const [isChecked, setIsChecked] = useState(false);

  useEffect(() => {
    const patchQuantity = () => {
      const config = {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token")).authorization,
        },
      };
      try {
        axios.patch(`${BASE_URL}/carts/${item.id}`, { quantity }, config);
      } catch (error) {
        console.error(error);
      }
    };

    patchQuantity();
  }, [quantity]);

  const handleDeleteButtonClick = () => {
    if (window.confirm("해당 상품을 삭제하시겠습니까?")) {
      const deleteCartList = async () => {
        const config = {
          headers: {
            "Content-Type": `application/json`,
            authorization: JSON.parse(localStorage.getItem("token")).authorization,
          },
        };
        try {
          await axios.delete(`${BASE_URL}/carts/${item.id}`, config);

          data.data = data.data.filter((_, idx) => idx !== index);
          setCheckedList(checkedList.filter((element) => element.id !== item.id));

          setData({ ...data });
        } catch (error) {
          console.error(error);
        }
      };

      deleteCartList();
    }
  };

  const handleCheckBoxClick = () => {
    if (isChecked === false) {
      setCheckedList([...checkedList, { id: item.id, quantity: item.quantity, price: item.product.price }]);
    }

    if (isChecked === true) {
      setCheckedList(checkedList.filter((element) => element.id !== item.id));
    }

    setIsChecked(!isChecked);
  };

  return (
    <Container>
      <div onClick={handleCheckBoxClick}>
        <CheckBox isChecked={isChecked} size="24px" />
      </div>

      <ImageWrapper>
        <img src={"https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/DaangnMarket_logo.png/800px-DaangnMarket_logo.png"} />
      </ImageWrapper>

      <ProductInfo>
        <ProductTitle>{item.product.name}</ProductTitle>
        <ProductPrice>{item.product.price.toLocaleString()}원</ProductPrice>
        <QuantityBoxWrapper>
          <QuantityBox quantity={quantity} setQuantity={setQuantity} />
        </QuantityBoxWrapper>
      </ProductInfo>

      <TotalPrice>{(quantity * item.product.price).toLocaleString()}원</TotalPrice>

      <DeleteButtonWrapper onClick={handleDeleteButtonClick}>
        <DeleteButton />
      </DeleteButtonWrapper>
    </Container>
  );
}

export default CartProductItem;
