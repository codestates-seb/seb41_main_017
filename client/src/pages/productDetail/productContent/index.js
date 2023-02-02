import React from "react";

import styled from "styled-components";

import ProductSelection from "../productAtf/ProductSelection";
import Nav from "./Nav";
import Review from "./Review";
import Inquiry from "./Inquiry";

const Container = styled.div`
  display: flex;
  margin-top: 50px;
`;

const DetailContainer = styled.div`
  width: 750px;

  img {
    width: 100%;
    height: auto;
  }
`;

const ProductNoticeInfo = styled.h3`
  padding-top: 100px;
  font-weight: 500;
  font-size: 28px;
  line-height: 41px;
  text-align: center;
  letter-spacing: -0.5px;
  color: rgb(102, 102, 102);
`;

const ProductNoticeInfoUl = styled.ul`
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  padding-top: 36px;
  font-size: 13px;
  color: rgb(51, 51, 51);
  line-height: 18px;
  letter-spacing: -0.5px;

  .product_notice_info_li_title {
    padding-top: 18px !important;
    width: 180px;
    padding: 0px 18px 18px;
    background-color: rgb(247, 247, 247);
  }

  .product_notice_info_li_content {
    padding-top: 18px !important;
    display: flex;
    width: 195px;
    padding: 0px 18px 18px;
    color: rgb(102, 102, 102);
  }
`;

function ProductContent({ data, quantity, setQuantity, totalPrice }) {
  return (
    <Container>
      <DetailContainer>
        <Nav />

        <div id="detail">
          <img src={data.data && data.data.productImageDtos[0].imgUrl}></img>

          <ProductNoticeInfo>상품고시정보</ProductNoticeInfo>
          <ProductNoticeInfoUl>
            <li className="product_notice_info_li_title">제품명</li>
            <li className="product_notice_info_li_content">상품설명 및 상품이미지 참조</li>
            <li className="product_notice_info_li_title">식품의 유형</li>
            <li className="product_notice_info_li_content">상품설명 및 상품이미지 참조</li>
            <li className="product_notice_info_li_title">생산자 및 소재지 (수입품의 경우 생산자, 수입자 및 제조국)</li>
            <li className="product_notice_info_li_content">상품설명 및 상품이미지 참조</li>
            <li className="product_notice_info_li_title">제조연월일, 소비기한 또는 품질유지기한</li>
            <li className="product_notice_info_li_content">상품설명 및 상품이미지 참조</li>
            <li className="product_notice_info_li_title">포장단위별 내용물의 용량(중량), 수량</li>
            <li className="product_notice_info_li_content">상품설명 및 상품이미지 참조</li>
            <li className="product_notice_info_li_title">
              원재료명 (｢농수산물의 원산지 표시 등에 관한 법률｣에 따른 원산지 표시 포함) 및 함량(원재료 함량 표시대상 식품에 한함)
            </li>
            <li className="product_notice_info_li_content">상품설명 및 상품이미지 참조</li>
            <li className="product_notice_info_li_title">영양성분 (영양성분 표시대상 식품에 한함)</li>
            <li className="product_notice_info_li_content">상품설명 및 상품이미지 참조</li>
            <li className="product_notice_info_li_title">유전자변형식품에 해당하는 경우의 표시</li>
            <li className="product_notice_info_li_content">상품설명 및 상품이미지 참조</li>
            <li className="product_notice_info_li_title">
              소비자 안전을 위한 주의사항 (｢식품 등의 표시ㆍ광고에 관한 법률 시행규칙｣ 제5조 및 [별표 2]에 따른 표시사항을 말함)
            </li>
            <li className="product_notice_info_li_content">상품설명 및 상품이미지 참조</li>
            <li className="product_notice_info_li_title">수입식품의 경우 “수입식품안전관리 특별법에 따른 수입신고를 필함”의 문구</li>
            <li className="product_notice_info_li_content">상품설명 및 상품이미지 참조</li>
            <li className="product_notice_info_li_title">소비자 상담 관련 전화번호</li>
            <li className="product_notice_info_li_content">컬리나리 고객행복센터 (1717-1717)</li>
            <li className="product_notice_info_li_title"></li>
          </ProductNoticeInfoUl>
        </div>

        <Review productName={data.data && data.data.name} />

        <Inquiry data={data} />
      </DetailContainer>
      <ProductSelection data={data} quantity={quantity} setQuantity={setQuantity} totalPrice={totalPrice} />
    </Container>
  );
}

export default ProductContent;
