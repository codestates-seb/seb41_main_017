import axios from "axios";
import { useState } from "react";
import { useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import styled from "styled-components";

import { ReactComponent as Heart } from "../../../assets/heart.svg";

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

      return axios.get(`${process.env.REACT_APP_URL}/mypage/productlike`, config);
    };

    (async () => {
      const likes = await getProductLikes();
      setIsLiked(likes.data.data.some((element) => element.productId == id ?? false));
    })();
  }, []);

  const handleIconClick = async () => {
    const accessToken = JSON.parse(localStorage.getItem("token"))?.authorization;

    if (!accessToken) {
      if (window.confirm("찜 기능은 로그인 후에 사용할 수 있습니다. 로그인 페이지으로 이동하시겠습니까?")) {
        navigate("/login");

        return;
      }

      return;
    }

    if (isLiked === true) {
      const deleteProductLike = async () => {
        const config = {
          headers: {
            "Content-Type": `application/json`,
            Authorization: accessToken,
          },
        };
        try {
          return await axios.delete(`${process.env.REACT_APP_URL}/product/${id}/like`, config);
        } catch (error) {
          console.error(error);
        }
      };

      const response = await deleteProductLike();

      if (response.status === 200) {
        setIsLiked(false);
        alert("해당 상품의 찜을 취소 했습니다.");
      }
    }

    if (isLiked === false) {
      const postProductLike = async () => {
        const config = {
          headers: {
            "Content-Type": `application/json`,
            Authorization: accessToken,
          },
        };
        try {
          return await axios.post(`${process.env.REACT_APP_URL}/product/${id}/like`, null, config);
        } catch (error) {
          console.error(error);
        }
      };

      const response = await postProductLike();

      if (response.status === 200) {
        setIsLiked(true);
        alert("해당 상품을 찜했습니다! [마이페이지 > 찜한 상품]에서 확인하실 수 있습니다.");
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
