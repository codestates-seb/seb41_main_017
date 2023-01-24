import axios from "axios";
import { useState, useEffect } from "react";
import styled from "styled-components";

import { ReactComponent as CartIcon } from "../../assets/cart_icon.svg";
import CartProductItem from "../../components/cart/CartProductItem";
import CheckBox from "../../components/CheckBox";
import BasicButton from "../../components/BasicButton";
import ColorButton from "../../components/ColorButton";
import ProductItemSlider from "../../components/ProductItemSlider";
import { Title, TodayRecommendProducts } from "..";

import BASE_URL from "../../constants/BASE_URL";

const Container = styled.div`
  max-width: 1050px;
  margin: 44px auto;
  padding: 0 50px;
`;

const TitleContainer = styled.div`
  display: flex;
  width: 100%;
  border-bottom: 1px solid black;

  svg {
    margin-right: 5px;
  }

  h2 {
    padding-top: 4px;
    align-self: center;
    font-size: 24px;
  }
`;

const CartProductListContainer = styled.div`
  padding: 0 40px 20px 40px;
  border-bottom: 1px solid black;
`;

const SelectButtonContainer = styled.div`
  padding: 5px 0;
  margin-top: 10px;
  border-bottom: 1px solid #ddd;
  display: flex;
  align-items: center;

  span {
    margin-right: 10px;
  }

  .delete-selection {
    color: #c26d53;
  }
`;

const TotalPriceBox = styled.div`
  height: 50px;
  margin: 26px 0;
  padding-top: 2px;
  border: 1px solid #ff6767;
  border-radius: 3px;
  display: flex;
  justify-content: center;
  align-items: center;

  span {
    margin-right: 5px;
  }

  .shipping-fee {
    display: flex;
    align-items: center;
  }

  .sign {
    color: #ff6767;
  }
`;

const OrderButtonContainer = styled.div`
  margin: 50px 0;
  display: flex;
  align-items: center;
  justify-content: center;

  span {
    font-size: 20px;
  }

  a {
    margin: 0 5px;
  }
`;

function Cart() {
  const [data, setData] = useState(null);
  const [totalPrice, setTotalPrice] = useState(0);
  const [isClicked, setIsClicked] = useState(false);

  useEffect(() => {
    const getCartList = async () => {
      const config = {
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token")).authorization,
        },
      };

      try {
        const { data } = await axios.get(`${BASE_URL}/carts`, config);

        return data;
      } catch (error) {
        console.error(error);
      }
    };

    const calcTotalPrice = (data) => {
      return data.data.reduce((acc, cur) => {
        return acc + cur.quantity * cur.product.price;
      }, 0);
    };

    (async () => {
      const data = await getCartList();

      setData(data);
      setTotalPrice(calcTotalPrice(data));
    })();
  }, [isClicked]);

  return (
    <Container>
      <TitleContainer>
        <CartIcon />
        <h2>장바구니</h2>
      </TitleContainer>
      <CartProductListContainer>
        <SelectButtonContainer>
          <CheckBox size="24px" />
          <span>전체 선택</span>
          <span className="delete-selection">선택 삭제</span>
        </SelectButtonContainer>

        {data && data.data.map((item) => <CartProductItem item={item} setIsClicked={setIsClicked} key={item.id} />)}

        <TotalPriceBox>
          <div className="product-price">
            <span>상품 가격</span>
            <span>{totalPrice.toLocaleString()}원</span>
          </div>
          <div className="shipping-fee">
            <span className="sign">+</span>
            <span>배송비</span>
            <span>3,000원</span>
          </div>
          <div className="total-price">
            <span className="sign">=</span>
            <span>총 주문 금액</span>
            <strong>{(totalPrice + 3000).toLocaleString()}원</strong>
          </div>
        </TotalPriceBox>

        <OrderButtonContainer>
          <BasicButton children={"상품 더 담기"} font={"20"} radius={"5"} p_height={"10"} p_width={"30"} />
          <ColorButton children={"주문하기"} font={"20"} radius={"5"} p_height={"10"} p_width={"30"} />
        </OrderButtonContainer>
      </CartProductListContainer>

      <TodayRecommendProducts>
        <Title>이달의 추천 상품</Title>
        <ProductItemSlider />
      </TodayRecommendProducts>
    </Container>
  );
}

export default Cart;
