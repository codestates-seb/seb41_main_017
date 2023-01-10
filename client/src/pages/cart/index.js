import styled from "styled-components";
import { ReactComponent as CartIcon } from "../../assets/cart_icon.svg";
import CartProductItem from "../../components/CartProductItem";
import CheckBox from "../../components/CheckBox";

const Container = styled.div`
  padding: 0 100px;
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
  padding: 0 50px;
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
              {/* 전체선택, 선택삭제 */}
              <CheckBox />
              <span>전체 선택</span>
              <span className="delete-selection">선택 삭제</span>
            </SelectButtonContainer>
            {/* 리스트들 */}
            <CartProductItem></CartProductItem>
            <CartProductItem></CartProductItem>
            <CartProductItem></CartProductItem>
            <CartProductItem></CartProductItem>
            {/* 총 주문 금액 */}

            {/* 더담기, 주문하기(대충만들기) */}
          </CartProductListContainer>

          {/* 이달의 추천 상품 */}
        </Container>
      </div>
    </>
  );
}

export default Cart;
