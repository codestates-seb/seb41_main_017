import styled from "styled-components";
import Mypagehead from "../../components/MypageHead";
import ListLayout from "../../components/ListLayout";
import ProductItem from "../../components/ProductItem";
import BasicButton from "../../components/BasicButton";
import { useEffect, useState} from "react";
import CountBox from "../../components/CountBox";
import axios from "axios";
import Guidance from "../../components/Guidance";

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
  const [item, setItem] = useState([]);
  
  useEffect(() => {
    
    axios.get(`${process.env.REACT_APP_URL}/collections/frequent`,{
    headers: {
      authorization: JSON.parse(localStorage.getItem("token"))
        .authorization,
    },
    }).then( res => setBuyItems(res.data))
  }, []);


  const isOpen = ()=>{
    setCartModal(true);
  }

  const countValue = (e)=>{
    setItem(e)
  }

  const cartAll = ()=>{
    console.log("테스트:",item)

      axios.post(`${process.env.REACT_APP_URL}/carts`,
    {
      cartItems:[item]
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
                  <CountBox itemId={data.id} props={countValue}/>
                </div>
                <div className="buttons">
                  <BasicButton p_height={6} onClick={isOpen}>장바구니 담기</BasicButton>
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
    </Mypagehead>
  );
}

export default Buyitem;
