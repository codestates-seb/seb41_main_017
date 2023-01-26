import styled from "styled-components";
import ListLayout from "../../components/ListLayout";
import Mypagehead from "../../components/MypageHead";
import BasicButton from "../../components/BasicButton";
import { format, add } from "date-fns";
import { useState } from "react";

const Layout = styled.div`
  display:flex;
  flex-direction: column;
  
  


  .main_list {
    display: flex;
  }

  .left {
    flex: 1;

    img {
      border-radius: 5px;
      width: 100%;
    }
  }

  .center {
    flex: 3;
    margin: 0 10px;
    display: flex;
    flex-direction: column;

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

    .count {
      margin-bottom: 10px;
      span {
        margin-right: 20px;
      }
    }

    .items_states {
      color:red;
      flex: 1;
    }
  }

  .right {
    text-align: center;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    
    .btns{
      display:flex;
      flex-direction: column;
      gap: 7px;
      
    }
  }

  .sub_list{
    align-self: flex-end;
    width:610px;
    margin-top: 20px;
    display:flex;
    flex-direction: column;

    .sub_title{
      font-size:15px;
      padding:6px 10px;
      border-top:1px solid #AEAEAE;
      border-bottom:1px solid #AEAEAE;
      display:flex;

      & > div:first-child{
        flex:1;
      }

      & div:last-child{
        flex:2;
        text-align:center;
      }
    }

    .sub_content{      
      padding:10px;
      font-size:14px;

      display:flex;
      flex-direction: column;

      gap:10px;
      & > li {
        display:flex;
        & span:first-child {
          flex:1;
        }
        & span:last-child{
          flex:2;
          text-align:center;
        }
      }

    }
    
  }
`;

function DeliveryLook() {
  const time = format(add(new Date(), { days: 2 }), "MM/dd");
  const [bottomTap, setBottomTap] = useState(false)

  return (
    <Mypagehead title={"배송 조회"}>
      <ListLayout padding_width={"10px"}>
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
                <h5>상품이름입니다</h5>
                <BasicButton>배송중</BasicButton>
                <span>{`${time} 도착예정`}</span>
              </div>
              <div className="count">
                <span>{"00,000원"}</span>
                <span>{"00개"}</span>
              </div>
              <div className="items_states">
                <div>{"상태들어올 예정"}</div>
              </div>
            </div>
            <div className="right">
              <div className="btns">
                <BasicButton>{"취소,교환,반품 신청"}</BasicButton>
                <BasicButton>{"문의하기"}</BasicButton>
              </div>
              <div onClick={()=> setBottomTap(!bottomTap)}>
                {"자세히보기"}
              </div>
            </div>
          </div>
          {bottomTap ? 
          <div className="sub_list">
            <div className="sub_title">
              <div>{"시간"}</div>
              <div>{"위치"}</div>
              <div>{"상태"}</div>
            </div>
            <ul className="sub_content">
              <li><span>{"YY/MM/DD hh:mm:ss"}</span><span>{"위치"}</span><span>{"배송중(배송기사: 최준호 010-0000-0000)"}</span></li>
              <li><span>{"YY/MM/DD hh:mm:ss"}</span><span>{"위치"}</span><span>{"간선 상차"}</span></li>
              <li><span>{"YY/MM/DD hh:mm:ss"}</span><span>{"위치"}</span><span>{"배송 준비중"}</span></li>
            </ul>
          </div> : undefined}
        </Layout>
      </ListLayout>
    </Mypagehead>
  );
}

export default DeliveryLook;
