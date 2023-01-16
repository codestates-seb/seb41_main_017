import styled from "styled-components";
import Mypagehead from "../../components/MypageHead";
import ListLayout from "../../components/ListLayout";
import ProductItem from "../../components/ProductItem";
import QuantityBox from "../../components/QuantityBox";
import BasicButton from "../../components/BasicButton";

const Testlayout = styled.div`
  flex-wrap: wrap;
  display: flex;

  & > div {
    width: calc(25%);
  }
`;

const ItemLayout = styled.div`
  display: flex;
  flex-direction: column;

  .items {
    width: 150px;
    margin: 0 auto;
  }

  .counts {
    flex: 1;
    display: flex;
    justify-content: center;
    margin-bottom: 10px;
    & > * {
      flex-basis: 110px;
    }
  }

  .buttons {
    flex: 1;
    display: flex;
    text-align: center;
    justify-content: center;
    & > * {
      flex-basis: 110px;
    }
  }
`;

function Buyitem() {
  const test = {
    image:
      "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/DaangnMarket_logo.png/800px-DaangnMarket_logo.png",
    name: "상품 이름입니다",
    price: 10000,
  };

  return (
    <Mypagehead
      title={"자주 산 상품"}
      subtitle={"최근 12개월동안 3번 이상 주문한 상품입니다."}
    >
      <Testlayout>
        <ListLayout>
          <ItemLayout>
            <div className="items">
              <ProductItem element={test}></ProductItem>
            </div>
            <div className="counts">
              <QuantityBox quantity={0}></QuantityBox>
            </div>
            <div className="buttons">
              <BasicButton p_height={8}>장바구니 담기</BasicButton>
            </div>
          </ItemLayout>
        </ListLayout>
        <ListLayout>
          <ItemLayout>
            <div className="items">
              <ProductItem element={test}></ProductItem>
            </div>
            <div className="counts">
              <QuantityBox quantity={0}></QuantityBox>
            </div>
            <div className="buttons">
              <BasicButton p_height={8}>장바구니 담기</BasicButton>
            </div>
          </ItemLayout>
        </ListLayout>
        <ListLayout>
          <ItemLayout>
            <div className="items">
              <ProductItem element={test}></ProductItem>
            </div>
            <div className="counts">
              <QuantityBox quantity={0}></QuantityBox>
            </div>
            <div className="buttons">
              <BasicButton p_height={8}>장바구니 담기</BasicButton>
            </div>
          </ItemLayout>
        </ListLayout>
        <ListLayout>
          <ItemLayout>
            <div className="items">
              <ProductItem element={test}></ProductItem>
            </div>
            <div className="counts">
              <QuantityBox quantity={0}></QuantityBox>
            </div>
            <div className="buttons">
              <BasicButton p_height={8}>장바구니 담기</BasicButton>
            </div>
          </ItemLayout>
        </ListLayout>
        <ListLayout>
          <ItemLayout>
            <div className="items">
              <ProductItem element={test}></ProductItem>
            </div>
            <div className="counts">
              <QuantityBox quantity={0}></QuantityBox>
            </div>
            <div className="buttons">
              <BasicButton p_height={8}>장바구니 담기</BasicButton>
            </div>
          </ItemLayout>
        </ListLayout>
      </Testlayout>
    </Mypagehead>
  );
}

export default Buyitem;
