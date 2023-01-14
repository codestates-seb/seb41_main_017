import styled from "styled-components";
import ColorButton from "../../components/ColorButton";
import QuantityBox from "../../components/QuantityBox";

const HeaderContainer = styled.div`
  padding-bottom: 40px;

  .data_list_wrapper {
    border-bottom: 1px solid #f4f4f4;
  }

  dl {
    width: 100%;
    padding: 17px 0px 18px;
    border-top: 1px solid #f4f4f4;
    display: flex;
    align-items: flex-start;
    letter-spacing: -0.5px;
  }

  dt {
    width: 128px;
    height: 100%;
    line-height: 19px;
    font-weight: 400;
    color: #666;
  }

  dd {
    display: flex;
    flex-direction: column;
    flex: 1;
  }

  .cart_option {
    width: 100%;
    padding: 11px 10px 11px 15px;
    font-size: 13px;
    border: 1px solid #f4f4f4;
  }

  .cart_option_item {
    padding-top: 20px;
    display: flex;
    align-items: center;
    justify-content: space-between;

    span {
      font-weight: bold;
    }
  }

  .total_price_wrapper {
    padding-top: 30px;
    letter-spacing: -0.5px;
    display: flex;
    justify-content: flex-end;
    align-items: flex-end;
  }

  .total_price_text {
    padding-right: 12px;
    font-size: 14px;
    font-weight: 500;
    color: #333;
    line-height: 20px;
  }

  .total_price_number {
    font-weight: bold;
    font-size: 32px;
    line-height: 36px;
    font-family: Tahoma;
  }

  .total_price_unit {
    padding-left: 5px;
    font-size: 20px;
    font-weight: 600;
    line-height: 30px;
  }

  .add_cart_button_wrapper {
    margin-top: 20px;
    font-weight: bold;
    display: flex;

    a {
      width: 100%;
      display: flex;
      justify-content: center;
    }
  }
`;

function ProductSelection({ position, name, priceToLocaleString, quantity, setQuantity, totalPrice }) {
  return (
    <HeaderContainer>
      {position === "header" ? (
        <div className="data_list_wrapper">
          <dl>
            <dt>상품선택</dt>
            <dd>
              <div className="cart_option">
                <div>
                  <span>{name}</span>
                </div>
                <div className="cart_option_item">
                  <QuantityBox quantity={quantity} setQuantity={setQuantity} />
                  <span>{priceToLocaleString}원</span>
                </div>
              </div>
            </dd>
          </dl>
        </div>
      ) : (
        <></>
      )}
      <div className="total_price_wrapper">
        <span className="total_price_text">총 상품금액 : </span>
        <span className="total_price_number">{totalPrice}</span>
        <span className="total_price_unit">원</span>
      </div>
      <div className="add_cart_button_wrapper">
        <ColorButton children="장바구니 담기" font="16" radius="3" p_height="19" />
      </div>
    </HeaderContainer>
  );
}

export default ProductSelection;
