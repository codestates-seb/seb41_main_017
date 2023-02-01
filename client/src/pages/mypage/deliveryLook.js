import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import axios from "axios";
import { format, add } from "date-fns";
import ListLayout from "../../components/ListLayout";
import Mypagehead from "../../components/MypageHead";
import BasicButton from "../../components/BasicButton";
import CreateInquiry from "../productDetail/productContent/CreateInquiry";



const ClassList = styled.div`
.items_question {
  position: fixed;
  top:0;
}
  
`

const Layout = styled.div`
  display: flex;
  flex-direction: column;

  .main_list {
    display: flex;
  }

  .left {
    flex: 1;
    text-align: center;
    cursor: pointer;

    img {
      border-radius: 5px;
      width: 100%;
      height: 140px;
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

      h5 {
        cursor: pointer;
      }
    }

    .count {
      margin-bottom: 10px;
      span {
        margin-right: 20px;
      }
    }

    .items_states {
      color: red;
      flex: 1;
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
    width: 610px;
    margin-top: 20px;
    display: flex;
    flex-direction: column;

    .sub_title {
      font-size: 15px;
      padding: 6px 10px;
      border-top: 1px solid #aeaeae;
      border-bottom: 1px solid #aeaeae;
      display: flex;

      & > div:first-child {
        flex: 1;
      }

      & div:last-child {
        flex: 2;
        text-align: center;
      }
    }

    .sub_content {
      padding: 10px;
      font-size: 14px;

      display: flex;
      flex-direction: column;

      gap: 10px;
      & > li {
        display: flex;
        & span:first-child {
          flex: 1;
        }
        & span:last-child {
          flex: 2;
          text-align: center;
        }
      }
    }
  }
`;

function DeliveryLook() {
  const time = format(add(new Date(), { days: 2 }), "MM/dd");
  const [ordersData, setOrders] = useState([]);
  const [datas, setDatas] = useState({});
  const [isOpen, setIsOpen] = useState(false);
  const navigate = useNavigate();

  

  useEffect(() => {
    axios.get(`${process.env.REACT_APP_URL}/orders/details`,{
      headers: {
        authorization: JSON.parse(localStorage.getItem("token"))
          .authorization,
      },
    }).then( res => setOrders(res.data))
  }, []);

  const setData = (id) => {
    setDatas(id);
    setIsOpen(true);
  };

  const movePage = (id) => {
    navigate(`/product/:${id}`);
  };

  return (
    <Mypagehead title={"배송 조회"}>
      <ClassList>
        {ordersData.data?.map((data) => {
          return (
            <ListLayout padding_width={"10px"} key={data.id}>
              <Layout>
                <div className="main_list">
                  <div
                    className="left"
                    onClick={() => movePage(data.product.id)}
                  >
                    <img
                      src={`${data.product.productImageDtos[0].imgUrl}`}
                      alt="#"
                    ></img>
                  </div>
                  <div className="center">
                    <div className="title">
                      <h5
                        onClick={() => movePage(data.product.id)}
                      >{`${data.product.name} ${data.product.brand}`}</h5>
                      <BasicButton>{"준비중"}</BasicButton>
                      <span>{`${time} 도착예정`}</span>
                    </div>
                    <div className="count">
                      <span>{`${data.product.price.toLocaleString()}원`}</span>
                      <span>{`${data.quantity}개`}</span>
                    </div>
                    <div className="items_states">
                      <div>{"준비중"}</div>
                    </div>
                  </div>
                  <div className="right">
                    <div className="btns">
                      <BasicButton>{"취소,교환,반품 신청"}</BasicButton>
                      <BasicButton>{"문의하기"}</BasicButton>
                      {/* <BasicButton onClick={() => setData(data)}>
                        {"문의하기"}
                      </BasicButton> */}
                    </div>
                  </div>
                </div>
              </Layout>
            </ListLayout>
          );
        })}
        <div className="items_question">
          {isOpen ? (
            <CreateInquiry
              data={{
                image: datas?.product?.productImageDtos[0]?.imgUrl,
                name: datas?.product?.name,
              }}
              setIsOpen={setIsOpen}
            />
          ) : null}
        </div>
      </ClassList>
    </Mypagehead>
  );
}

export default DeliveryLook;
