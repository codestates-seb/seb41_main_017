import React from "react";
import Slider from "react-slick";
import styled from "styled-components";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { ReactComponent as WhiteArrow } from "../assets/white_arrow.svg";
import ProductItem from "./ProductItem";

const PrevButtonContainer = styled.div`
  position: absolute;
  top: 97px;
  left: -25px;
  z-index: 3;

  &:hover {
    path {
      transition: 0.3s ease 0s;
      fill: #fd6c40;
    }
  }

  &:not(:hover) {
    path {
      transition: 0.3s ease 0s;
      fill: black;
    }
  }
`;

function PrevButton({ onClick }) {
  return (
    <PrevButtonContainer onClick={onClick}>
      <WhiteArrow />
    </PrevButtonContainer>
  );
}

const NextButtonContainer = styled.div`
  position: absolute;
  top: 100px;
  right: -25px;
  z-index: 3;
  transform: rotate(180deg);

  &:hover {
    path {
      transition: 0.3s ease 0s;
      fill: #fd6c40;
    }
  }

  &:not(:hover) {
    path {
      transition: 0.3s ease 0s;
      fill: black;
    }
  }
`;

function NextButton({ onClick }) {
  return (
    <NextButtonContainer onClick={onClick}>
      <WhiteArrow />
    </NextButtonContainer>
  );
}

function ProductItemListSlider() {
  const settings = {
    infinite: false,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 5,
    prevArrow: <PrevButton></PrevButton>,
    nextArrow: <NextButton></NextButton>,
  };

  const productArr = [
    {
      id: 0,
      name: "[비비드키친] 저칼로리 숯불매콤치킨 소스",
      price: 3980,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1657781547680l0.jpg",
    },
    {
      id: 1,
      name: "[파제르 핀란드 시그니처 코촐릿 4종]",
      price: 12000,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1607930413465l0.jpg",
    },
    {
      id: 2,
      name: "[카스0.0] 논알콜 음료 8캔 & 헌터즈트러플 감자칩 2개",
      price: 9990,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1658317867100l0.jpg",
    },
    {
      id: 3,
      name: "한뿌리 수삼 45g",
      price: 3490,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1576555719180l0.jpg",
    },
    {
      id: 4,
      name: "[순백수] 하노키 우디 디퓨저 세트 (본품 150ml)",
      price: 26900,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1655455835441l0.jpeg",
    },
    {
      id: 5,
      name: "[상하목장] 얼려먹는 아이스크림 초코",
      price: 5200,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/16499051856l0.jpg",
    },
    {
      id: 6,
      name: "[창화당] 육즙 팡팡 소룡포",
      price: 8830,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1548292714543l0.jpg",
    },
    {
      id: 7,
      name: "[만토바] 비보 오가닉 엑스트버진 스프레이 화이트트러플향 200ml",
      price: 3980,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1641272547982l0.jpg",
    },
    {
      id: 8,
      name: "[설치배송][LG전자] 울트라 HDTV (스탠드형)",
      price: 633800,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1634802297108l0.jpg",
    },
    {
      id: 9,
      name: "[하선정] 김밥 단무지",
      price: 2380,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/1657529753879l0.jpg",
    },
    {
      id: 10,
      name: "[제니튼] 닥터제니 저불소 어린이 치약 60g",
      price: 6300,
      image: "https://img-cf.kurly.com/cdn-cgi/image/quality=85,width=400/shop/data/goods/16505299970l0.jpg",
    },
  ];

  return (
    <Slider {...settings}>
      {productArr.map((element) => {
        return <ProductItem element={element} key={element.id}></ProductItem>;
      })}
    </Slider>
  );
}

export default ProductItemListSlider;
