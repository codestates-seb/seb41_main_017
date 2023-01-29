import styled from "styled-components";
import ListLayout from "../../components/ListLayout";
import Mypagehead from "../../components/MypageHead";
import BasicButton from "../../components/BasicButton";
import Guidance from "../../components/Guidance";
import { format } from "date-fns";
import { useState, useEffect } from "react";
import axios from "axios";

const testData = {
  data: [
    {
      id: 2,
      address: "수원시",
      receiverName: "첫번째",
      receiverPhoneNumber: "010-0000-0000",
      status: "ORDER_RECEIVED",
      createdAt: "2023-01-27T13:57:24.538228",
      orderDetails: [
        {
          id: 4,
          quantity: 1,
          status: "ORDER_RECEIVED",
          product: {
            id: 1,
            name: "무농약 깐쪽파 200g",
            brand: null,
            price: 3090.0,
            productImageDtos: [
              {
                id: 1,
                imgUrl:
                  "https://culinari-images.s3.ap-northeast-2.amazonaws.com/products/20230124_001.png",
              },
            ],
          },
        },
        {
          id: 5,
          quantity: 1,
          status: "ORDER_RECEIVED",
          product: {
            id: 2,
            name: "친환경 마 300g",
            brand: null,
            price: 6900.0,
            productImageDtos: [
              {
                id: 2,
                imgUrl:
                  "https://culinari-images.s3.ap-northeast-2.amazonaws.com/products/20230124_002.png",
              },
            ],
          },
        },
      ],
    },
    {
      id: 1,
      address: "성남시",
      receiverName: "두번째",
      receiverPhoneNumber: "010-0000-0000",
      status: "ORDER_RECEIVED",
      createdAt: "2023-01-27T13:50:32.732622",
      orderDetails: [
        {
          id: 1,
          quantity: 1,
          status: "ORDER_RECEIVED",
          product: {
            id: 1,
            name: "무농약 깐쪽파 200g",
            brand: null,
            price: 3090.0,
            productImageDtos: [
              {
                id: 1,
                imgUrl:
                  "https://culinari-images.s3.ap-northeast-2.amazonaws.com/products/20230124_001.png",
              },
            ],
          },
        },
        {
          id: 2,
          quantity: 1,
          status: "ORDER_RECEIVED",
          product: {
            id: 2,
            name: "친환경 마 300g",
            brand: null,
            price: 6900.0,
            productImageDtos: [
              {
                id: 2,
                imgUrl:
                  "https://culinari-images.s3.ap-northeast-2.amazonaws.com/products/20230124_002.png",
              },
            ],
          },
        },
        {
          id: 3,
          quantity: 1,
          status: "ORDER_RECEIVED",
          product: {
            id: 3,
            name: "친환경 볶음밥용 채소 120gx3입",
            brand: null,
            price: 4990.0,
            productImageDtos: [
              {
                id: 3,
                imgUrl:
                  "https://culinari-images.s3.ap-northeast-2.amazonaws.com/products/20230124_003.png",
              },
            ],
          },
        },
      ],
    },
  ],
  pageInfo: {
    page: 1,
    size: 10,
    totalElements: 2,
    totalPages: 1,
  },
  barNumber: [0],
};

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
  // 하나의 state로 서브리스트 관리
  const [bottomTab, setBottomTab] = useState(0);
  // 상품데이터 뿌리는 용도
  const [ordersData, setOrders] = useState(testData.data);
  // 카트창
  const [cartModal, setCartModal] = useState(false);
  // 데이터값 구분
  const [keysData, setKeysData] = useState([]);

  useEffect(() => {
    // setOrders(testData.data);
    // axios.get(`${process.env.REACT_APP_URL}/orders`,{
    // headers: {
    //   authorization: JSON.parse(localStorage.getItem("token"))
    //     .authorization,
    // },
    // }).then( res => setOrders(res.data))
    // recv를 받으면 처리
    // test1
    // const test = testData.data.map((data) => {
    //   // const num = ;
    //   return { [data.id] : false};
    // });
  }, []);

  const isOpen = (data)=>{
    console.log(data);
    setCartModal(true);
    setKeysData(data)
  }

  const cartAll = () => {
   const cartItems = []
    for(let i of keysData){
      cartItems.push({
        productId: i.product.id,
        quantity: i.quantity
      })
    }
    axios.post(`${process.env.REACT_APP_URL}/carts`,
    {
      cartItems
    },
    {
      headers: {
        authorization: JSON.parse(localStorage.getItem("token"))
          .authorization,
      },
    }).then(()=>setCartModal(false))
  };

  return (
    <Mypagehead title={"주문 목록 조회"}>
      {ordersData.map((data) => {
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
                <div className="left">
                  <img
                    src={
                      data.orderDetails[0].product.productImageDtos[0].imgUrl
                    }
                    alt={data.orderDetails[0].product.name}
                  ></img>
                </div>
                <div className="center">
                  <div className="title">
                    <h5>{`${data.orderDetails[0].product.name}외 ${
                      data.orderDetails.length - 1
                    }건`}</h5>
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
                        .map((el) => el.product.price)
                        .reduce((acc, cur) => acc + cur)
                        .toLocaleString()}원`}</span>
                    </div>
                  </div>
                </div>

                <div className="right">
                  <div className="btns">
                    <BasicButton>{"취소,교환,반품 신청"}</BasicButton>
                    <BasicButton>{"문의하기"}</BasicButton>
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
                    onClick={(e) => setBottomTab(Number(e.target.value))}
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
                        <span>{`${data.orderDetails
                          .map((el) => el.product.price)
                          .reduce((acc, cur) => acc + cur + 3000)
                          .toLocaleString()}원`}</span>
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
    </Mypagehead>
  );
}

export default Orderitem;

/*
 [내가 해결해야할 문제점]
 * 결제부분문제로 현재 더미데이터를 사용중
 1. 주문상세보기를 클릭시 동시에 여러개를 열수가없음 개별적으로는 열림 다시클릭시 닫히지않음
 2. 문의하기 클릭시 어디로 ? 내문의 창으로?
 3. 취소,교환,반품,신청 클릭시
 4. 전체 장바구니 담기구현
 5. 이미지 or 상품이름 클릭시 해당상품으로 이동?
 6. 페이지네이션필요-> 페이지별 공통

 [요청할 문제점]
  1. 주문번호 DB가 빠져있음
  2. 결제시 배송요청사항의 DB가 주문목록조회에 빠져있음
  3. 결제방식DB도 빠져있음
*/
