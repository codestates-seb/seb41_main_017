import styled from "styled-components";
import Mypagehead from "../../components/MypageHead";
import ListLayout from "../../components/ListLayout";
import ProductItem from "../../components/ProductItem";
import BasicButton from "../../components/BasicButton";
import { useEffect, useState} from "react";
import CountBox from "../../components/CountBox";
import axios from "axios";
import Guidance from "../../components/Guidance";
import {OtherPagination} from "../../components/OtherPagination"

const Layout = styled.div`
  flex-wrap: wrap;
  display: flex;
  gap: 10px;
  justify-content: flex-start;
`;

const ItemLayout = styled.div`
  padding: 6.6px;
  display: flex;
  flex-direction: column;
  .items {
    // margin: 0 auto;
  }

  .counts {
    flex: 1;
    display: flex;
    justify-content: center;
    margin-bottom: 10px;
    & > * {
      flex-basis: 110px;
    }
  }

  .buttons {
    flex: 1;
    display: flex;
    text-align: center;
    justify-content: center;
    & > * {
      flex-basis: 110px;
    }
  }
`;


function Buyitem() {
  const [buyItems, setBuyItems] = useState([]);
  const [cartModal, setCartModal] = useState(false);
  const [productId, setProductId] = useState(0);
  const [page, setPage] = useState(0)
  const [quantity, setQuantity] = useState(1);
  
  
  useEffect(() => {
    
    axios.get(`${process.env.REACT_APP_URL}/collections/frequent?page=${page}&size=10&searchMonths=12&frequency=3`,{
    headers: {
      authorization: JSON.parse(localStorage.getItem("token"))
        .authorization,
    },
    }).then( res => setBuyItems(res.data))
  }, [page,quantity]);


  const cartKey = (e)=>{
    setProductId(e)
    setCartModal(true)
  }

  const cartAll = ()=>{
    console.log(quantity)
    console.log("테스트")

      axios.post(`${process.env.REACT_APP_URL}/carts`,
    {
      cartItems:[{"productId":productId, "quantity":quantity}]
    },
    {
      headers: {
        authorization: JSON.parse(localStorage.getItem("token"))
          .authorization,
      },
    }).then(()=>setCartModal(false))
  }

  return (
    <Mypagehead
      title={"자주 산 상품"}
      subtitle={"최근 12개월동안 3번 이상 주문한 상품입니다."}
      width={"90%"}>
      <Layout>
        {buyItems.data?.map((data) => {
          return (
            <ListLayout key={data.id}>
              <ItemLayout>
                <div className="items">
                  <ProductItem
                    imgUrl={data.productImageDtos[0].imgUrl}
                    name={data.name}
                    price={data.price}/>
                </div>
                <div className="counts">
                  <CountBox setState={setQuantity}/>
                </div>
                <div className="buttons">
                  <BasicButton p_height={6} onClick={()=>cartKey(data.id)}>장바구니 담기</BasicButton>
                  {cartModal ? (
                      <Guidance
                        text={"해당 상품을 장바구니에 담으시겠습니까?"}
                        close={() => setCartModal(false)}
                        ok={cartAll}/>
                    ) : null}
                </div>
              </ItemLayout>
            </ListLayout>
          );
        })}
      </Layout>
      <OtherPagination state={page} setState={setPage} pageInfo={buyItems.pageInfo}/>
    </Mypagehead>
  );
}

export default Buyitem;
