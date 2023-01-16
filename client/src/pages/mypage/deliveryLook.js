import styled from "styled-components";
import { useState } from "react";
import Mypagebtn from "../../components/BasicButton";
import TableBoard from "../../components/TableBoard";
import ListLayout from "../../components/ListLayout";
import Mypagehead from "../../components/MypageHead";

const Test = styled.div`
.sub_List {
  margin-top: 20px;
  display: flex;
  justify-content: flex-end;

  .table {
    flex-basis: calc(100% - 130px);
  }
}
`



const List = styled.div`
  display: flex;

  .item_Img {
    width: 130px;
    height: 130px;

    img {
      width: 100%;
      height: 100%;
    }
  }

  .list_Detail {
    flex: 1;
    margin-top: 8px;
    display: flex;
    flex-direction: column;
    padding: 0px 12px;

    .item_Name {
      display: flex;
      align-items: center;
      gap: 10px;
      margin-bottom: 8px;
    }

    .item_Money {
      margin-bottom: 8px;
      span {
        margin-right: 20px;
      }
    }

    .item_State {
      display: flex;
      justify-content: space-around;

      .item_Circle {
        display: flex;
        flex-direction: column;
        align-items: center;
      }

      .state_Icons {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        border: 1px solid black;
      }

      .state_Font {
        padding-top: 10px;
      }
    }
  }

  .list_Btns {
    display: flex;
    flex-direction: column;
    justify-content: space-between;

    .user_Btn {
      display: flex;
      flex-direction: column;
      gap: 8px;
    }

    .event_Btn {
      display: flex;
      justify-content: flex-end;
      text-align: center;
      span {
        font-size: 25px;
        margin: 0 10px;
      }
    }
  }
`;

function DeliveryLook() {
  const [subinfo, setSubinfo] = useState(false);

  return (
    <Mypagehead title={"배송 조회"}>
    <Test>
      <ListLayout>
        <List>
          <div className="item_Img">
            <img
              src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/DaangnMarket_logo.png/800px-DaangnMarket_logo.png"
              alt="#"
            ></img>
          </div>
          <div className="list_Detail">
            <div className="item_Name">
              <div>
                <span>상품 이름입니다</span>
              </div>
              <div>
                <Mypagebtn radius={5} p_width={10} p_height={1}>
                  배송 준비중
                </Mypagebtn>
              </div>
              <div>
                <span>MM/YY 도착예정</span>
              </div>
            </div>

            <div className="item_Money">
              <span>00,000원</span>
              <span>00개</span>
            </div>

            <div className="item_State">
              <div className="item_Circle">
                <div className="state_Icons">
                  <span>아이콘</span>
                </div>
                <div className="state_Font">주문접수</div>
              </div>
              <div className="item_Circle">
                <div className="state_Icons">
                  <span>아이콘</span>
                </div>
                <div className="state_Font">배송 준비중</div>
              </div>
              <div className="item_Circle">
                <div className="state_Icons">
                  <span>아이콘</span>
                </div>
                <div className="state_Font">배송 출발</div>
              </div>
              <div className="item_Circle">
                <div className="state_Icons">
                  <span>아이콘</span>
                </div>
                <div className="state_Font">배송중</div>
              </div>
              <div className="item_Circle">
                <div className="state_Icons">
                  <span>아이콘</span>
                </div>
                <div className="state_Font">배송 완료</div>
              </div>
            </div>
          </div>
          <div className="list_Btns">
            <div className="user_Btn">
              <Mypagebtn p_width={10} p_height={6}>
                취소,교환,반품 신청
              </Mypagebtn>
              <Mypagebtn p_width={35} p_height={6}>
                문의하기
              </Mypagebtn>
            </div>
            <div onClick={() => setSubinfo(!subinfo)} className="event_Btn">
              <button>자세히보기</button>
              {subinfo ? <span>⬆</span> :<span>⬇</span>}
            </div>
          </div>
        </List>
        {subinfo ? (
          <div className="sub_List">
            <div className="table">
              <TableBoard />
            </div>
          </div>
        ) : null}
      </ListLayout>
    </Test>
    </Mypagehead>
  );
}

export default DeliveryLook;
