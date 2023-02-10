import axios from "axios";
import { useState, useEffect } from "react";
import styled from "styled-components";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { setInfo } from "../../app/reducer/productId2Pay";

import { ReactComponent as CartIcon } from "../../assets/cart_icon.svg";
import CartProductItem from "../../components/cart/CartProductItem";
import CheckBox from "../../components/CheckBox";
import BasicButton from "../../components/BasicButton";
import ColorButton from "../../components/ColorButton";
import ProductItemSlider from "../../components/ProductItemSlider";
import { Title, TodayRecommendProducts } from "..";
import noCartImg from "../../assets/no_carts.png";

const Container = styled.div`
  max-width: 1050px;
  margin: 44px auto;
  padding: 0 50px;
`;

const TitleContainer = styled.div`
  display: flex;
  width: 100%;
  border-bottom: 1px solid black;

  svg {
    margin-right: 5px;
  }

  h2 {
    padding-top: 4px;
    align-self: center;
    font-size: 24px;
  }
`;

const CartProductListContainer = styled.div`
  padding: 0 40px 20px 40px;
  border-bottom: 1px solid black;

  .no_cart_img {
    display: block;
    margin: 100px auto;
  }
`;

const SelectButtonContainer = styled.div`
  padding: 5px 0;
  margin-top: 10px;
  border-bottom: 1px solid #ddd;
  display: flex;
  align-items: center;

  .select_all_container {
    margin-right: 10px;
    display: flex;
    align-items: center;
    cursor: pointer;
  }

  .delete-selection {
    color: #c26d53;
    margin-right: 10px;
    cursor: pointer;
  }
`;

const TotalPriceBox = styled.div`
  height: 50px;
  margin: 26px 0;
  padding-top: 2px;
  border: 1px solid #ff6767;
  border-radius: 3px;
  display: flex;
  justify-content: center;
  align-items: center;

  span {
    margin-right: 5px;
  }

  .shipping-fee {
    display: flex;
    align-items: center;
  }

  .sign {
    color: #ff6767;
  }
`;

const OrderButtonContainer = styled.div`
  margin: 50px 0;
  display: flex;
  align-items: center;
  justify-content: center;

  span {
    font-size: 20px;
  }

  a {
    margin: 0 5px;
  }
`;

function Cart() {
  const [data, setData] = useState(null);
  const [totalPrice, setTotalPrice] = useState(0);
  const [checkedList, setCheckedList] = useState([]);
  const [selectAllChecked, setSelectAllChecked] = useState(true);
  const [bestProductData, setBestProductData] = useState(null);
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    const accessToken = JSON.parse(localStorage.getItem("token"))?.authorization;

    if (!accessToken) {
      if (window.confirm("해당 기능은 로그인 후에 사용할 수 있습니다. 로그인 페이지으로 이동하시겠습니까?")) {
        navigate("/login");

        return;
      }

      navigate(-1);
      return;
    }

    const getCartList = async () => {
      const config = {
        headers: {
          "Content-Type": `application/json`,
          Authorization: JSON.parse(localStorage.getItem("token")).authorization,
        },
      };

      try {
        const { data } = await axios.get(`${process.env.REACT_APP_URL}/carts`, config);

        return data;
      } catch (error) {
        console.error(error);
      }
    };

    (async () => {
      const data = await getCartList();
      setData(data);
      setCheckedList(
        data.data.map((element) => ({ id: element.id, quantity: element.quantity, price: element.product.price, productId: element.product.id }))
      );
    })();
  }, []);

  useEffect(() => {
    const calcTotalPrice = (checkedList) => {
      return checkedList.reduce((acc, cur) => acc + cur.quantity * cur.price, 0);
    };

    setTotalPrice(calcTotalPrice(checkedList));
  }, [checkedList]);

  useEffect(() => {
    const getBestProductData = async () => {
      const { data } = await axios.get(`${process.env.REACT_APP_URL}/collections/bestproducts?size=20`);

      return data;
    };

    (async () => {
      try {
        const bestProductData = await getBestProductData();

        setBestProductData(bestProductData);
      } catch (error) {
        console.error(error);
      }
    })();
  }, []);

  const handleSelectAllButtonClick = () => {
    if (selectAllChecked === true) {
      setCheckedList([]);
      setSelectAllChecked(!selectAllChecked);

      return;
    }

    if (selectAllChecked === false) {
      setCheckedList(
        data.data.map((element) => ({ id: element.id, quantity: element.quantity, price: element.product.price, productId: element.product.id }))
      );
      setSelectAllChecked(!selectAllChecked);
    }
  };

  const handleSelectDeleteButtonClick = () => {
    if (!window.confirm("체크된 상품 모두 삭제 하시겠습니까?")) return;

    try {
      const config = {
        data: {
          cartIds: checkedList.map((list) => list.id),
        },
        headers: {
          "Content-Type": `application/json`,
          authorization: JSON.parse(localStorage.getItem("token")).authorization,
        },
      };

      axios.delete(`${process.env.REACT_APP_URL}/carts`, config);

      checkedList.forEach((list) => {
        data.data = data.data.filter((element) => element.id !== list.id);
      });

      setCheckedList([]);
      setData(data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleOrderButtonClick = () => {
    if (checkedList.length === 0) {
      alert("체크한 상품이 없습니다. 구매하실 상품 체크하여 주문하기를 눌러주세요.");

      return;
    }

    dispatch(setInfo({ ids: checkedList.map((list) => list.productId), totalPrice }));
    navigate("/pay");
  };

  return (
    <Container>
      <TitleContainer>
        <CartIcon />
        <h2>장바구니</h2>
      </TitleContainer>
      <CartProductListContainer>
        <SelectButtonContainer>
          <div className="select_all_container" onClick={handleSelectAllButtonClick}>
            <CheckBox isChecked={data?.data.length === checkedList.length && checkedList.length} size="24px" />
            <span>전체 선택</span>
          </div>
          <span className="delete-selection" onClick={handleSelectDeleteButtonClick}>
            선택 삭제
          </span>
        </SelectButtonContainer>

        {data &&
          data.data.map((item, index) => (
            <CartProductItem
              item={item}
              data={data}
              setData={setData}
              index={index}
              checkedList={checkedList}
              setCheckedList={setCheckedList}
              selectAllChecked={selectAllChecked}
              key={item.id}
            />
          ))}
        {data && data.data.length ? null : <img className="no_cart_img" src={noCartImg}></img>}

        <TotalPriceBox>
          <div className="product-price">
            <span>상품 가격</span>
            <span>{totalPrice.toLocaleString()}원</span>
          </div>
          <div className="shipping-fee">
            <span className="sign">+</span>
            <span>배송비</span>
            <span>무료</span>
          </div>
          <div className="total-price">
            <span className="sign">=</span>
            <span>총 주문 금액</span>
            <strong>{totalPrice.toLocaleString()}원</strong>
          </div>
        </TotalPriceBox>

        <OrderButtonContainer>
          <BasicButton href={"/collections/best-product"} children={"상품 더 담기"} font={"20"} radius={"5"} p_height={"10"} p_width={"30"} />
          <div onClick={handleOrderButtonClick}>
            <ColorButton children={"주문하기"} font={"20"} radius={"5"} p_height={"10"} p_width={"30"} />
          </div>
        </OrderButtonContainer>
      </CartProductListContainer>

      <TodayRecommendProducts>
        <Title>이달의 추천 상품</Title>
        <ProductItemSlider data={bestProductData && bestProductData.data} />
      </TodayRecommendProducts>
    </Container>
  );
}

export default Cart;
