import { useState, useEffect } from "react";
import Mypagehead from "../../components/MypageHead";
import styled from "styled-components";
import ListLayout from "../../components/ListLayout";
import ProductItem from "../../components/ProductItem";
import BasicButton from "../../components/BasicButton";
import axios from "axios";
import CountBox from "../../components/CountBox";
import Guidance from "../../components/Guidance";
import { BiHeartCircle } from "react-icons/bi";


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
    position: relative;
  }

  .select_item{
    position: absolute;
    bottom:60px;
    right:15px;
    font-size:30px;
    color:red;
    cursor: pointer;
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

function Serachitem() {
  const [selectItems, setSelectItems] = useState({});
  const [selects, setSelects] = useState(false);
  const [cartModal, setCartModal] = useState(false);
  const [item, setItem] = useState([]);
  const [keysData, setKeysData] = useState(0);
  
  useEffect(() => {
    
    axios.get(`${process.env.REACT_APP_URL}/mypage/productlike`,{
    headers: {
      authorization: JSON.parse(localStorage.getItem("token"))
        .authorization,
    },
    })
    .then( res => setSelectItems(res.data))

  }, []);

  const isOpen = ()=>{
    setCartModal(true);
  }

  const selectsValue = (id)=>{
    setKeysData(id.productImageDtos[0].id);
    setSelects(true);
  }

  const selectsPost = ()=>{

    axios.delete(`${process.env.REACT_APP_URL}/product/${keysData}/like`,
    {
      headers: {
        authorization: JSON.parse(localStorage.getItem("token"))
          .authorization,
      },
    }).then(()=>{
      setSelects(false);
      window.location.reload();
    }).catch(error => console.log("실패"));
    
  }


  const countValue = (e)=>{
    setItem(e)
  }

  const cartPost = ()=>{
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
      title={"찜한 상품"}
      subtitle={"최대 N개까지 저장됩니다."}
      icon={<BiHeartCircle color="red" size={15} />}
      width={"90%"}
      line={true}
      >
      <Layout>
        {selectItems?.data?.map((data) => {
          return (
            <ListLayout key={data.id}>
              <ItemLayout>
                <div className="items">
                  <ProductItem
                    imgUrl={data.productImageDtos[0].imgUrl}
                    name={data.name}
                    price={data.price}/>
                    <div className="select_item" onClick={()=>selectsValue(data)}><BiHeartCircle/></div>
                    {selects ? (
                      <Guidance
                        text={"찜 상품을 삭제하시겠습니까?"}
                        close={() => setSelects(false)}
                        ok={selectsPost}/>
                    ) : null}
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
                        ok={cartPost}/>
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

export default Serachitem;


/*
 [내가 해결해야할 문제점]
 * 결제부분문제로 현재 더미데이터를 사용중
 1. 장바구니에 담을시 다른 상품에 수량을 늘린후에 다른상품을 장바구니에 담을경우 전에 눌린 카운터로 장바구니에 담김
 & 해결방안 : 카운트 되는것을 배열에 객체로 아이템id와 수량을담고 다른 아이디값이 들어오면 현재 까지 클릭한
  정보를 배열에 담는다. 장바구니담기 버튼을 누르면 해당 아이템 id를 찾아서 그것만을 Post

 2. 이미지 or 상품이름 클릭시 해당상품으로 이동?
 3. 페이지네이션필요-> 페이지별 공통

*/