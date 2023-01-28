import React, { useState } from "react";
import { useEffect } from "react";

import axios from "axios";
import Slider from "react-slick";
import styled from "styled-components";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import { ReactComponent as WhiteArrow } from "../assets/white_arrow.svg";

import ProductItem from "./ProductItem";
import BASE_URL from "../constants/BASE_URL";

const PrevButtonContainer = styled.div`
  position: absolute;
  top: 97px;
  left: -25px;
  z-index: 3;
  display: ${({ isFirstPage }) => (isFirstPage ? "none" : "block")};

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

function PrevButton({ isFirstPage, onClick }) {
  return (
    <PrevButtonContainer isFirstPage={isFirstPage} onClick={onClick}>
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
  display: ${({ isLastPage }) => (isLastPage ? "none" : "block")};

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

function NextButton({ isLastPage, onClick }) {
  return (
    <NextButtonContainer isLastPage={isLastPage} onClick={onClick}>
      <WhiteArrow />
    </NextButtonContainer>
  );
}

function ProductItemSlider() {
  const [currentPage, setCurrentPage] = useState(1);
  const [data, setData] = useState([]);

  useEffect(() => {
    (async () => {
      try {
        const { data } = await axios.get(`${BASE_URL}/collections/newproduct?page=4&size=10`);

        setData(data);
      } catch (error) {
        console.error(error);
      }
    })();
  }, []);

  const slidesToShow = 5;
  const isFirstPage = () => currentPage === 1;
  const isLastPage = () => currentPage === data.length - slidesToShow + 1;

  const settings = {
    infinite: false,
    speed: 500,
    slidesToShow: slidesToShow,
    slidesToScroll: slidesToShow,
    prevArrow: <PrevButton isFirstPage={isFirstPage()} />,
    nextArrow: <NextButton isLastPage={isLastPage()} />,
    beforeChange: (_, index) => setCurrentPage(index + 1),
  };

  return (
    <Slider {...settings}>
      {data.data &&
        data.data.map((element) => (
          <ProductItem
            id={element.id}
            imgUrl={element.productImageDtos[0]?.imgUrl}
            name={element.name}
            price={element.price}
            key={element.id}
          ></ProductItem>
        ))}
    </Slider>
  );
}

export default ProductItemSlider;
