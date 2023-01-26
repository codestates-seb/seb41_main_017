import styled from "styled-components";
import Mypagehead from "../../components/MypageHead";
import ListLayout from "../../components/ListLayout";
import ProductItem from "../../components/ProductItem";
import QuantityBox from "../../components/QuantityBox";
import BasicButton from "../../components/BasicButton";
import {useState} from "react";

const Layout = styled.div`
  flex-wrap: wrap;
  display: flex;
  gap: 10px;

  & > div {
    width: calc(24%);
  }
`;

const ItemLayout = styled.div`
  display: flex;
  flex-direction: column;
  padding: 10px;

  .items {
    width: 150px;
    height: 220px;
    
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
  const [item1, setItem1] = useState(0);
  const [item2, setItem2] = useState(0);
  const [item3, setItem3] = useState(0);
  const [item4, setItem4] = useState(0);
  const [item5, setItem5] = useState(0);

  const items1 = {
    image:
      "https://t1.daumcdn.net/cfile/tistory/992642435D04DE9B27",
    name: "상품 이름입니다",
    price: 10000,
  };
  const items2 = {
    image:
      "https://t1.daumcdn.net/cfile/tistory/99885A485D04E42234",
    name: "상품 이름입니다",
    price: 10000,
  };
  const items3 = {
    image:
      "https://t1.daumcdn.net/cfile/tistory/99256C3D5D04E12810",
    name: "상품 이름입니다",
    price: 10000,
  };
  const items4 = {
    image:
      "https://t1.daumcdn.net/cfile/tistory/99D71F485D04E42205",
    name: "상품 이름입니다",
    price: 10000,
  };
  const items5 = {
    image:
      "https://t1.daumcdn.net/cfile/tistory/99D970465D04E18205",
    name: "상품 이름입니다",
    price: 10000,
  };

  

  return (
    <Mypagehead
      title={"자주 산 상품"}
      subtitle={"최근 12개월동안 3번 이상 주문한 상품입니다."}
    >
      <Layout>
        <ListLayout>
          <ItemLayout>
            <div className="items">
              <ProductItem element={items1}></ProductItem>
            </div>
            <div className="counts">
              <QuantityBox quantity={item1} setQuantity={setItem1}></QuantityBox>
            </div>
            <div className="buttons">
              <BasicButton p_height={6}>장바구니 담기</BasicButton>
            </div>
          </ItemLayout>
        </ListLayout>
        <ListLayout>
          <ItemLayout>
            <div className="items">
              <ProductItem element={items2}></ProductItem>
            </div>
            <div className="counts">
              <QuantityBox quantity={item2} setQuantity={setItem2}></QuantityBox>
            </div>
            <div className="buttons">
              <BasicButton p_height={6}>장바구니 담기</BasicButton>
            </div>
          </ItemLayout>
        </ListLayout>
        <ListLayout>
          <ItemLayout>
            <div className="items">
              <ProductItem element={items3}></ProductItem>
            </div>
            <div className="counts">
              <QuantityBox quantity={item3} setQuantity={setItem3}></QuantityBox>
            </div>
            <div className="buttons">
              <BasicButton p_height={6}>장바구니 담기</BasicButton>
            </div>
          </ItemLayout>
        </ListLayout>
        <ListLayout>
          <ItemLayout>
            <div className="items">
              <ProductItem element={items4}></ProductItem>
            </div>
            <div className="counts">
              <QuantityBox quantity={item4} setQuantity={setItem4}></QuantityBox>
            </div>
            <div className="buttons">
              <BasicButton p_height={6}>장바구니 담기</BasicButton>
            </div>
          </ItemLayout>
        </ListLayout>
        <ListLayout>
          <ItemLayout>
            <div className="items">
              <ProductItem element={items5}></ProductItem>
            </div>
            <div className="counts">
              <QuantityBox quantity={item5} setQuantity={setItem5}></QuantityBox>
            </div>
            <div className="buttons">
              <BasicButton p_height={6}>장바구니 담기</BasicButton>
            </div>
          </ItemLayout>
        </ListLayout>
      </Layout>
    </Mypagehead>
  );
}

export default Buyitem;
