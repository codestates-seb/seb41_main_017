import { useEffect, useState } from "react";

import styled from "styled-components";

import ProductAtf from "./productAtf/index";
import ProductContent from "./productContent/index";

const Container = styled.div`
  max-width: 1050px;
  margin: 44px auto;
  font-size: 14px;
`;

function ProductDetail() {
  const [quantity, setQuantity] = useState(1);
  const [totalPrice, setTotalPrice] = useState(0);

  useEffect(() => {
    setTotalPrice(getTotalPrice());
  }, [quantity]);

  const getTotalPrice = () => (quantity * data.price).toLocaleString();

  const data = {
    id: 1,
    image: "https://img-cf.kurly.com/shop/data/goods/1657528646107l0.jpg",
    name: "[순창성가정식품] 한식간장으로 담근 명이나물절임",
    content: "국산 명이나물에 담은 전라도 손맛",
    price: 5850.0,
    shipping: "샛별배송",
    brand: "브랜드 정보",
    seller: "컬리",
    packaging: "냉장 (종이포장)",
    unit: "1개",
    weight: "120g",
    countryOfOrigin: "상세페이지 별도표기",
    allergyInfo: `- 대두 함유\n- 본 제품은 알류(가금류에 한함), 우유, 메밀, 땅콩, 밀, 고등어, 게, 새우, 돼지고기, 복숭아, 토마토, 아황산류, 호두, 닭고기, 쇠고기, 오징어, 조개류(굴, 전복, 홍합 포함), 잣을 사용한 제품과 같은 제조시설에서 제조하고 있습니다.`,
    createdAt: "2023-01-14T01:59:40.007925",
    modifiedAt: "2023-01-14T01:59:40.007925",
    createdBy: "2023-01-14 01:59:40.007925",
    modifiedBy: "2023-01-14 01:59:40.007925",
    productInquiryDtos: [],
    productReviewDtos: [],
  };
  data.priceToLocaleString = data.price.toLocaleString();

  return (
    <Container>
      <ProductAtf data={data} quantity={quantity} setQuantity={setQuantity} totalPrice={totalPrice} />
      <ProductContent data={data} quantity={quantity} setQuantity={setQuantity} totalPrice={totalPrice} />
    </Container>
  );
}

export default ProductDetail;
