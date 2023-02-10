import styled from "styled-components";
import ListLayout from "../../components/ListLayout";
import Mypagehead from "../../components/MypageHead";
import BasicButton from "../../components/BasicButton";
import Guidance from "../../components/Guidance";
import { useState, useEffect } from "react";
import { OtherPagination } from "../../components/OtherPagination";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Layout = styled.div`
  display: flex;
  flex-direction: column;

  .main_list {
    display: flex;
  }

  .left {
    padding-top: 30px;
    flex-basis: 120px;
    text-align: center;

    img {
      border-radius: 5px;
      width: 100%;
      cursor: pointer;
    }
  }

  .center {
    padding-top: 30px;
    flex: 3;
    margin: 0 10px;
    display: flex;
    flex-direction: column;
    justify-content: space-between;

    .title {
      display: flex;
      align-items: center;
      gap: 10px;
      margin-bottom: 5px;

      h5 {
        cursor: pointer;
      }

      span {
        color: #067303;
        font-size: 14px;
      }
    }

    .items_price {
      display: flex;
      gap: 20px;
      font-size: 13px;
      span {
        display: inline-block;
        padding: 3px;
      }
    }
  }

  .right {
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: space-between;

    .btns {
      display: flex;
      flex-direction: column;
      gap: 7px;
    }
  }

  .sub_list {
    align-self: flex-end;
    width: 650px;
    margin-top: 20px;
    font-size: 14px;
    display: flex;
    flex-direction: column;

    .sub_title {
      padding: 5px;
      border-bottom: 2px solid #aeaeae;
      display: flex;

      & > div {
        text-align: center;
        flex: 1;
      }
    }

    .sub_content {
      padding: 0 3px;
      margin-bottom: 20px;

      .sub_content_list {
        display: flex;
        align-items: center;
        border-bottom: 2px solid #aeaeae;
        padding: 10px 0;

        img {
          padding-left: 10px;
          width: 100px;
        }
        & > div {
          text-align: center;
          flex: 1;
        }

        .sub_items {
          display: flex;
        }
      }

      .sub_items_price {
        text-align: left;
        span {
          display: inline-block;
          padding: 5px 8px;
        }
      }
    }

    .user_info {
      margin-bottom: 15px;

      h5 {
        border-bottom: 2px solid #aeaeae;
        padding-bottom: 5px;
      }
      .user_info_detail {
        display: flex;
        padding: 10px;
        span {
          padding: 5px 10px;
          display: block;
        }
      }
    }

    .money_info {
      h5 {
        border-bottom: 2px solid #aeaeae;
        padding-bottom: 5px;
      }

      .money_info_detail {
        display: flex;
        padding: 10px;
        span {
          padding: 5px 10px;
          display: block;
        }
      }
    }
  }
`;

function Orderitem() {
  const [bottomTab, setBottomTab] = useState("");
  const [ordersData, setOrders] = useState([]);
  const [cartModal, setCartModal] = useState(false);
  const [keysData, setKeysData] = useState([]);
  const [isDelete, setIsDelete] = useState(false);
  const [page, setPage] = useState(0);
  const [item, setItem] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get(
        `${process.env.REACT_APP_URL}/orders?page=${page}&size=5&searchMonths=3`,
        {
          headers: {
            authorization: JSON.parse(localStorage.getItem("token"))
              .authorization,
          },
        }
      )
      .then((res) => {
        setOrders(res.data);
      });
  }, [page]);

  const isOpen = (data) => {
    setCartModal(true);
    setKeysData(data);
  };

  const cartAll = () => {
    const cartItems = [];
    for (let i of keysData) {
      cartItems.push({
        productId: i.product.id,
        quantity: i.quantity,
      });
    }
    axios
      .post(
        `${process.env.REACT_APP_URL}/carts`,
        {
          cartItems,
        },
        {
          headers: {
            authorization: JSON.parse(localStorage.getItem("token"))
              .authorization,
          },
        }
      )
      .then(() => setCartModal(false));
  };

  const itemsDelete = (e) => {
    setItem(e);
    setIsDelete(true);
  };

  const refund = () => {
    const arr = [];
    for (const i of item.orderDetails) {
      arr.push(i.id);
    }
    console.log(arr);

    axios
      .post(
        `${process.env.REACT_APP_URL}/payments/cancel`,
        {
          orderDetailIds: arr,
          cancelReason: "null",
        },
        {
          headers: {
            authorization: JSON.parse(localStorage.getItem("token"))
              .authorization,
          },
        }
      )
      .then((res) => {
        setIsDelete(false);
        window.location.reload();
      });
  };

  const movePage = (id) => {
    navigate(`/product/${id}`);
  };

  return (
    <Mypagehead title={"주문 목록 조회"}>
      {ordersData.data?.map((data) => {
        return (
          <ListLayout
            key={data.id}
            text={`${data.createdAt.slice(0, 10)} [${data.createdAt.slice(
              11,
              19
            )}]`}
            padding_width={"10px"}
          >
            <Layout>
              <div className="main_list">
                <div
                  className="left"
                  onClick={() => movePage(data.orderDetails[0].product.id)}
                >
                  <img
                    src={
                      data.orderDetails[0].product.productImageDtos[0].imgUrl
                    }
                    alt={data.orderDetails[0].product.name}
                  ></img>
                </div>
                <div className="center">
                  <div className="title">
                    {data.orderDetails.length - 1 === 0 ? (
                      <h5
                        onClick={() =>
                          movePage(data.orderDetails[0].product.id)
                        }
                      >{`${data.orderDetails[0].product.name}`}</h5>
                    ) : (
                      <h5
                        onClick={() =>
                          movePage(data.orderDetails[0].product.id)
                        }
                      >{`${data.orderDetails[0].product.name}외 ${
                        data.orderDetails.length - 1
                      }건`}</h5>
                    )}
                  </div>
                  <div className="items_price">
                    <div>
                      <span>{"주문번호"}</span>
                      <br />
                      <span>{"총 상품 금액"}</span>
                    </div>
                    <div>
                      <span>{`${data.id}`}</span>
                      <br />
                      <span>{`${data.orderDetails
                        .map((el) => el.product.price * el.quantity)
                        .reduce((acc, cur) => acc + cur)
                        .toLocaleString()}원`}</span>
                    </div>
                  </div>
                </div>

                <div className="right">
                  <div className="btns">
                    <BasicButton onClick={() => itemsDelete(data)}>
                      {"취소,교환,반품 신청"}
                    </BasicButton>
                    {isDelete ? (
                      <Guidance
                        text={"해당상품을 취소하시겠습니까?"}
                        ok={refund}
                        close={() => setIsDelete(false)}
                      />
                    ) : null}
                    <BasicButton onClick={() => isOpen(data.orderDetails)}>
                      {"전체 장바구니 담기"}
                    </BasicButton>
                    {cartModal ? (
                      <Guidance
                        text={"해당 상품들을 장바구니에 담으시겠습니까?"}
                        close={() => setCartModal(false)}
                        ok={cartAll}
                      />
                    ) : null}
                  </div>
                  <button
                    value={data.id}
                    onClick={(e) => setBottomTab(e.target.value)}
                  >
                    {"주문 상세보기"}
                  </button>
                </div>
              </div>
              {data.id === bottomTab ? (
                <div className="sub_list">
                  <div className="sub_title">
                    <div>{"상품 정보"}</div>
                    <div>{"배송 상태"}</div>
                  </div>
                  <ul className="sub_content">
                    {data.orderDetails.map((details) => {
                      return (
                        <li className="sub_content_list" key={details.id}>
                          <div className="sub_items">
                            <img
                              src={`${details.product.productImageDtos[0].imgUrl}`}
                              alt={`${details.product.name}`}
                            />

                            <div className="sub_items_price">
                              <span>{`${details.product.name}`}</span>
                              <br />
                              <span>{`${details.product.price.toLocaleString()}원`}</span>
                              <span>{`${details.quantity}개`}</span>
                            </div>
                          </div>
                          <div>
                            <BasicButton>준비중</BasicButton>
                          </div>
                        </li>
                      );
                    })}
                  </ul>
                  <div className="user_info">
                    <h5>{"배송정보"}</h5>
                    <div className="user_info_detail">
                      <div>
                        <span>{"수령인"}</span>
                        <span>{"연락처"}</span>
                        <span>{"주소"}</span>
                        <span>{"배송요청사항"}</span>
                      </div>
                      <div>
                        <span>{`${data.receiverName}`}</span>
                        <span>{`${data.receiverPhoneNumber}`}</span>
                        <span>{`${data.address}`}</span>
                        <span>{"문앞에 놔주세요"}</span>
                      </div>
                    </div>
                  </div>
                  <div className="money_info">
                    <h5>{"배송정보"}</h5>
                    <div className="money_info_detail">
                      <div>
                        <span>{"상품가격"}</span>
                        <span>{"힐인"}</span>
                        <span>{"배송비"}</span>
                        <span>{"총 결제 금액"}</span>
                        <span>{"결제 방식"}</span>
                      </div>
                      <div>
                        <span>{`${data.orderDetails
                          .map((el) => el.product.price)
                          .reduce((acc, cur) => acc + cur)
                          .toLocaleString()}원`}</span>
                        <span>{"0원"}</span>
                        <span>{"3000원"}</span>
                        <span>
                          {`${data.orderDetails
                            .map((el) => el.product.price * el.quantity)
                            .reduce((acc, cur) => acc + cur + 3000)
                            .toLocaleString()}원`}
                        </span>
                        <span>{"Toss"}</span>
                      </div>
                    </div>
                  </div>
                </div>
              ) : undefined}
            </Layout>
          </ListLayout>
        );
      })}

      {ordersData.data?.length === 0 ? null : (
        <OtherPagination
          state={page}
          setState={setPage}
          pageInfo={ordersData.pageInfo}
        />
      )}
    </Mypagehead>
  );
}

export default Orderitem;
