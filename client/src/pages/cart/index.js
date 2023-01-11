import styled from "styled-components";
import { ReactComponent as CartIcon } from "../../assets/cart_icon.svg";
import CartProductItem from "../../components/cart/CartProductItem";
import CheckBox from "../../components/CheckBox";
import BasicButton from "../../components/BasicButton";
import ColorButton from "../../components/ColorButton";
import ProductItemSlider from "../../components/ProductItemSlider";
import { Title, TodayRecommendProducts } from "..";

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
  padding: 0 50px 20px 50px;
  border-bottom: 1px solid black;
`;

const SelectButtonContainer = styled.div`
  padding: 5px 0;
  margin-top: 10px;
  border-bottom: 1px solid #ddd;
  display: flex;

  span {
    margin: 10px;
  }

  .delete-selection {
    color: #c26d53;
  }
`;

const TotalPriceBox = styled.div`
  height: 50px;
  margin: 26px 0;
  border: 1px solid #ff6767;
  border-radius: 3px;
  display: flex;
  justify-content: center;
  align-items: center;

  span {
    margin-right: 5px;
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
  return (
    <>
      <div>
        <Container>
          <TitleContainer>
            <CartIcon />
            <h2>장바구니</h2>
          </TitleContainer>
          <CartProductListContainer>
            <SelectButtonContainer>
              <CheckBox />
              <span>전체 선택</span>
              <span className="delete-selection">선택 삭제</span>
            </SelectButtonContainer>

            <CartProductItem></CartProductItem>
            <CartProductItem></CartProductItem>
            <CartProductItem></CartProductItem>
            <CartProductItem></CartProductItem>

            <TotalPriceBox>
              <div className="product-price">
                <span>상품 가격</span>
                <span>204,000원</span>
              </div>
              <div className="shipping-fee">
                <span>+</span>
                <span>배송비</span>
                <span>3,000원</span>
              </div>
              <div className="total-price">
                <span>=</span>
                <span>총 주문 금액</span>
                <strong>207,000원</strong>
              </div>
            </TotalPriceBox>

            <OrderButtonContainer>
              <BasicButton radius={"5"} p_width={"30"} p_height={"10"} children={"상품 더 담기"} />
              <ColorButton radius={"5"} p_width={"30"} p_height={"10"} children={"주문하기"} />
            </OrderButtonContainer>
          </CartProductListContainer>

          <TodayRecommendProducts>
            <Title>이달의 추천 상품</Title>
            <ProductItemSlider />
          </TodayRecommendProducts>
        </Container>
      </div>
    </>
  );
}

export default Cart;
