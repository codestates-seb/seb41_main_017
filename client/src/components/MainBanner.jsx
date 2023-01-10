import React, { useState } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import styled from "styled-components";
import { ReactComponent as GrayArrow } from "../assets/gray_arrow.svg";

const StyledSlideWrapper = styled.div`
  display: flex;
  justify-content: center;
  margin-bottom: 60px;

  &:hover {
    svg {
      display: block !important;
      animation: fadein 0.5s;
      -moz-animation: fadein 0.5s;
      /* Firefox */
      -webkit-animation: fadein 0.5s;
      /* Safari and Chrome */
      -o-animation: fadein 0.5s;
      /* Opera */
      opacity: 0.7;
    }
  }

  &:not(:hover) {
    svg {
      animation: fadeout 0.5s;
      -moz-animation: fadeout 0.5s;
      /* Firefox */
      -webkit-animation: fadeout 0.5s;
      /* Safari and Chrome */
      -o-animation: fadeout 0.5s;
      /* Opera */
      opacity: 0;
    }
  }

  @keyframes fadein {
    from {
      opacity: 0;
    }

    to {
      opacity: 0.7;
    }
  }

  @-moz-keyframes fadein {
    /* Firefox */
    from {
      opacity: 0;
    }

    to {
      opacity: 0.7;
    }
  }

  @-webkit-keyframes fadein {
    /* Safari and Chrome */
    from {
      opacity: 0;
    }

    to {
      opacity: 0.7;
    }
  }

  @-o-keyframes fadein {
    /* Opera */
    from {
      opacity: 0;
    }
    to {
      opacity: 0.7;
    }
  }

  @keyframes fadeout {
    from {
      opacity: 0.7;
    }

    to {
      opacity: 0;
    }
  }

  @-moz-keyframes fadeout {
    /* Firefox */
    from {
      opacity: 0.7;
    }

    to {
      opacity: 0;
    }
  }

  @-webkit-keyframes fadeout {
    /* Safari and Chrome */
    from {
      opacity: 0.7;
    }

    to {
      opacity: 0;
    }
  }

  @-o-keyframes fadeout {
    /* Opera */
    from {
      opacity: 0.7;
    }
    to {
      opacity: 0;
    }
  }
`;

const StyledSlide = styled(Slider)`
  .slick-list {
    max-width: 1050px;
    margin: 0 auto;
    background-color: #f0f9ff;
  }
`;

const PrevButtonContainer = styled.div`
  position: absolute;
  width: 60px;
  height: 60px;
  top: 35%;
  left: 100px;
  z-index: 3;
  transform: rotate(180deg);
`;

function PrevButton({ onClick }) {
  return (
    <PrevButtonContainer onClick={onClick}>
      <GrayArrow />
    </PrevButtonContainer>
  );
}

const NextButtonContainer = styled.div`
  position: absolute;
  width: 60px;
  height: 60px;
  top: 39%;
  right: 100px;
  z-index: 3;
`;

function NextButton({ onClick }) {
  return (
    <NextButtonContainer onClick={onClick}>
      <GrayArrow />
    </NextButtonContainer>
  );
}

const Image = styled.img`
  display: block;
  margin: 0 auto;
`;

const PageBoxWrapper = styled.div`
  position: relative;
  user-select: none;
`;

const PageBox = styled.div`
  position: absolute;
  color: rgb(255, 255, 255);
  background: rgba(0, 0, 0, 0.15);
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 20px;
  right: 110px;
  bottom: 20px;
  line-height: 23px;
  font-size: 14px;
  font-weight: 400;
  border-radius: 12px;
`;

function MainBanner() {
  const [currentPage, setCurrentPage] = useState(1);

  const settings = {
    arrows: true,
    autoplay: true,
    audoplaySpeed: 1000,
    infinite: true,
    speed: 500,
    pauseOnHover: true,
    prevArrow: <PrevButton></PrevButton>,
    nextArrow: <NextButton></NextButton>,
    beforeChange: (_, index) => setCurrentPage(index + 1),
  };

  const srcArr = [
    "https://product-image.kurly.com/cdn-cgi/image/quality=85/banner/main/pc/img/129f346e-53a2-4251-bad4-d69e6dfdd048.jpg",
    "https://product-image.kurly.com/cdn-cgi/image/quality=85/banner/main/pc/img/e4622953-61b8-4062-830d-8435fa11efa3.jpg",
    "https://product-image.kurly.com/cdn-cgi/image/quality=85/banner/main/pc/img/080a72ca-8a03-4532-a9f3-60d3630be21d.jpg",
  ];

  return (
    <StyledSlideWrapper>
      <StyledSlide {...settings}>
        {srcArr.map((src, index) => {
          return <Image src={src} key={index}></Image>;
        })}
      </StyledSlide>
      <PageBoxWrapper>
        <PageBox>{`${currentPage} / ${srcArr.length}`}</PageBox>
      </PageBoxWrapper>
    </StyledSlideWrapper>
  );
}

export default MainBanner;
