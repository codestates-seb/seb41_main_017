import React, { useState } from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import styled from "styled-components";

import { ReactComponent as GrayArrow } from "../assets/gray_arrow.svg";
import bannerImage1 from "../assets/banner-image1.png";
import bannerImage2 from "../assets/banner-image2.png";
import bannerImage3 from "../assets/banner-image3.png";

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
    max-width: 700px;
    margin: 0 auto;
    background-color: #f0f9ff;
  }

  .slick-slide {
    width: 700px !important;
  }
`;

const PrevButtonContainer = styled.div`
  position: absolute;
  width: 60px;
  height: 60px;
  top: 42%;
  left: 0px;
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
  top: 43.6%;
  right: 0px;
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
  width: 100%;
  height: 100%;

  object-fit: cover;
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

  const srcArr = [bannerImage1, bannerImage2, bannerImage3];

  return (
    <>
      <StyledSlideWrapper>
        <StyledSlide {...settings}>
          {srcArr.map((src, index) => {
            return <Image src={src} key={index} alt="" />;
          })}
        </StyledSlide>
        <PageBoxWrapper>
          <PageBox>{`${currentPage} / ${srcArr.length}`}</PageBox>
        </PageBoxWrapper>
      </StyledSlideWrapper>
    </>
  );
}

export default MainBanner;
