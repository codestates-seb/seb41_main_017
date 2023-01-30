import axios from "axios";
import { useState } from "react";
import { useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import styled from "styled-components";

import { ReactComponent as Heart } from "../../../assets/heart.svg";

import BASE_URL from "../../../constants/BASE_URL";

const HeartIconWrapper = styled.div`
  width: 30px;
  height: 30px;
  cursor: pointer;
`;

function ProductHeader({ data }) {
  const [isLiked, setIsLiked] = useState(false);
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    const accessToken = JSON.parse(localStorage.getItem("token"))?.authorization;

    if (!accessToken) return;

    const getProductLikes = () => {
      const config = {
        headers: {
          "Content-Type": `application/json`,
          Authorization: accessToken,
        },
      };

      return axios.get(`${BASE_URL}/mypage/productlike`, config);
    };

    (async () => {
      const likes = await getProductLikes();
      setIsLiked(likes.data.data.some((element) => element.productId == id ?? false));
    })();
  }, []);

  const handleIconClick = () => {
    const accessToken = JSON.parse(localStorage.getItem("token"))?.authorization;

    if (!accessToken && window.confirm("찜 기능은 로그인 후에 가능합니다. 로그인 페이지으로 이동하시겠습니까?")) {
      navigate("/login");

      return;
    }

    if (isLiked === true) {
      const deleteProductLike = () => {
        const config = {
          headers: {
            "Content-Type": `application/json`,
            Authorization: accessToken,
          },
        };

        axios.delete(`${BASE_URL}/product/${id}/like`, config);
      };

      try {
        deleteProductLike();
        setIsLiked(false);
      } catch (error) {
        console.error(error);
      }
    }

    if (isLiked === false) {
      const postProductLike = () => {
        const config = {
          headers: {
            "Content-Type": `application/json`,
            Authorization: accessToken,
          },
        };

        axios.post(`${BASE_URL}/product/${id}/like`, null, config);
      };

      try {
        postProductLike();
        setIsLiked(true);
      } catch (error) {
        console.error(error);
      }
    }
  };

  return (
    <>
      <div className="shipping">{data.data && data.data.shipping}</div>

      <div className="title-container">
        <h1>{data.data && data.data.name}</h1>
        <HeartIconWrapper onClick={handleIconClick}>
          {isLiked ? <Heart width="30" height="30" fill="red" /> : <Heart width="30" height="30" fill="#ddd" />}
        </HeartIconWrapper>
      </div>

      <h2 className="content">{data.data && data.data.content}</h2>

      <h2 className="price">
        <span className="number">{data.data && data.data.price.toLocaleString()}</span>
        <span className="unit">원</span>
      </h2>
    </>
  );
}

export default ProductHeader;
