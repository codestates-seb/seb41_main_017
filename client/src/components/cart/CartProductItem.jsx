import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

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
  cursor: pointer;

  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
`;

const ProductInfo = styled.div`
  width: calc(100% - 390px);
  height: 100%;
  display: flex;
  flex-direction: column;
`;

const ProductTitle = styled.div`
  margin-bottom: 5px;
  cursor: pointer;
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

function CartProductItem({ item, data, setData, index, checkedList, setCheckedList, selectAllChecked }) {
  const [quantity, setQuantity] = useState(item.quantity);
  const [isChecked, setIsChecked] = useState(!!checkedList.find((element) => element.id === item.id));
  const navigate = useNavigate();

  useEffect(() => {
    const patchQuantity = () => {
      const config = {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token")).authorization,
        },
      };
      try {
        axios.patch(`${process.env.REACT_APP_URL}/carts/${item.id}`, { quantity }, config);
      } catch (error) {
        console.error(error);
      }
    };

    patchQuantity();

    if (isChecked) {
      checkedList = checkedList.filter((element) => element.id !== item.id);
      setCheckedList([...checkedList, { id: item.id, quantity, price: item.product.price, productId: item.product.id }]);
    }
  }, [quantity]);

  useEffect(() => {
    setIsChecked(selectAllChecked);
  }, [selectAllChecked]);

  const handleDeleteButtonClick = () => {
    if (window.confirm("해당 상품을 삭제하시겠습니까?")) {
      const deleteCartList = async () => {
        const config = {
          data: {
            cartIds: [item.id],
          },
          headers: {
            "Content-Type": `application/json`,
            Authorization: JSON.parse(localStorage.getItem("token")).authorization,
          },
        };

        try {
          await axios.delete(`${process.env.REACT_APP_URL}/carts`, config);

          setCheckedList(checkedList.filter((element) => element.id !== item.id));
          data.data = data.data.filter((_, idx) => idx !== index);

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
      setCheckedList([...checkedList, { id: item.id, quantity, price: item.product.price }]);
    }

    if (isChecked === true) {
      setCheckedList(checkedList.filter((element) => element.id !== item.id));
    }

    setIsChecked(!isChecked);
  };

  const handleProductNameAndImgClick = () => {
    navigate(`/product/${data.data[index].product.id}`);
  };

  return (
    <Container>
      <div onClick={handleCheckBoxClick}>
        <CheckBox isChecked={isChecked} size="24px" />
      </div>

      <ImageWrapper onClick={handleProductNameAndImgClick}>{<img src={item.product.productImageDtos[0].imgUrl} />}</ImageWrapper>

      <ProductInfo>
        <ProductTitle onClick={handleProductNameAndImgClick}>{item.product.name}</ProductTitle>
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
