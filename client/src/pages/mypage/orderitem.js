import styled from "styled-components";
import ListLayout from "../../components/ListLayout";
import Mypagehead from "../../components/MypageHead";
import BasicButton from "../../components/BasicButton";
import { format } from "date-fns";
import { useState } from "react";

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
  const days = format(new Date(), "yyyy.MM.dd");
  const time = format(new Date(), "[hh:mm:ss]");
  const [bottomTap, setBottomTap] = useState(false);

  return (
    <Mypagehead title={"주문 목록 조회"}>
      <ListLayout text={`${days} ${time}`} padding_width={"10px"}>
        <Layout>
          <div className="main_list">
            <div className="left">
              <img
                src="https://www.wjfood.co.kr/Psd/Main/3A3326.png"
                alt="#"
              ></img>
            </div>
            <div className="center">
              <div className="title">
                <h5>{"상품 이름 외 3건"}</h5>
              </div>
              <div className="items_price">
                <div>
                  <span>{"주문번호"}</span>
                  <br />
                  <span>{"총 결제 금액"}</span>
                </div>
                <div>
                  <span>{"20230125"}</span>
                  <br />
                  <span>{"00,000원"}</span>
                </div>
              </div>
            </div>
            <div className="right">
              <div className="btns">
                <BasicButton>{"취소,교환,반품 신청"}</BasicButton>
                <BasicButton>{"문의하기"}</BasicButton>
                <BasicButton>{"전체 장바구니 담기"}</BasicButton>
              </div>
              <div onClick={() => setBottomTap(!bottomTap)}>
                {"주문 상세보기"}
              </div>
            </div>
          </div>
          {bottomTap ? (
            <div className="sub_list">
              <div className="sub_title">
                <div>{"상품 정보"}</div>
                <div>{"배송 상태"}</div>
              </div>
              <ul className="sub_content">
                <li className="sub_content_list">
                  <div className="sub_items">
                    <img
                      src="https://www.wjfood.co.kr/Psd/Main/3A3326.png"
                      alt="#"
                    />

                    <div className="sub_items_price">
                      <span>{"상품이름1"}</span>
                      <br />
                      <span>{"00,000원"}</span>
                      <span>{"00개"}</span>
                    </div>
                  </div>
                  <div>
                    <BasicButton>배송중</BasicButton>
                  </div>
                </li>
                <li className="sub_content_list">
                  <div className="sub_items">
                    <img
                      src="https://www.wjfood.co.kr/Psd/Main/3A3326.png"
                      alt="#"
                    />

                    <div className="sub_items_price">
                      <span>{"상품이름1"}</span>
                      <br />
                      <span>{"00,000원"}</span>
                      <span>{"00개"}</span>
                    </div>
                  </div>
                  <div>
                    <BasicButton>배송중</BasicButton>
                  </div>
                </li>
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
                    <span>{"최준호"}</span>
                    <span>{"010-0000-0000"}</span>
                    <span>{"경기도 수원시 권선구 세류1동 256-24 201호"}</span>
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
                    <span>{"00,000원"}</span>
                    <span>{"-0원"}</span>
                    <span>{"3000원"}</span>
                    <span>{"00.000원"}</span>
                    <span>{"Toss"}</span>
                  </div>
                </div>
              </div>
            </div>
          ) : undefined}
        </Layout>
      </ListLayout>
    </Mypagehead>
  );
}

export default Orderitem;
