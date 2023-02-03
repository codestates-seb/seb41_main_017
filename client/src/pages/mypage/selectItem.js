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
import { ReactComponent as Heart} from "../../assets/heart.svg";
import {OtherPagination} from "../../components/OtherPagination"



const Layout = styled.div`
  flex-wrap: wrap;
  display: flex;
  gap: 10px;
  justify-content: flex-start;
  position: relative;
`;

const ItemLayout = styled.div`
  padding: 6.6px;
  display: flex;
  flex-direction: column;
  position: relative;
  .items {
    position: relative;
    height: 330px;
  }

  .select_item{
    position: absolute;
    bottom:30%;
    right:10%;
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
  const [cartModal, setCartModal] = useState(false);
  const [productId, setProductId] = useState(0);
  const [quantity, setQuantity] = useState(1);
  const [page, setPage] = useState(0);
  const [selectsValue, setSelectsValue] = useState(0);
  const [deleteModal, setDeleteModal] = useState(false)

  useEffect(() => {
    
    axios.get(`${process.env.REACT_APP_URL}/mypage/productlike?page=${page}&size=5&searchMonths=12&frequency=3`,{
    headers: {
      authorization: JSON.parse(localStorage.getItem("token"))
        .authorization,
    },
    })
    .then( res => setSelectItems(res.data))

  }, [page,quantity]);


  const cartPost = ()=>{

      
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

  const selectPost = ()=>{
    axios.delete(`${process.env.REACT_APP_URL}/product/${selectsValue}/like`,
    {
      headers: {
        authorization: JSON.parse(localStorage.getItem("token"))
          .authorization,
      },
    }).then(()=>{
      setDeleteModal(false)
      window.location.reload();
    })
  }

  const selectDelete = (e)=>{
    setSelectsValue(e)
    setDeleteModal(true)
  }

  const cartKey = (e)=>{
    setProductId(e)
    setCartModal(true)
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
                    price={data.price}
                    id={data.productId}
                    />
                    <div className="select_item" onClick={()=>selectDelete(data.productId)}>
                      <Heart width="30" height="30" fill="red"/>
                    </div>
                    {deleteModal ? (
                      <Guidance
                        text={"찜 상품을 삭제하시겠습니까?"}
                        close={() => setDeleteModal(false)}
                        ok={selectPost}/>
                    ) : null}
                </div>
                <div className="counts">
                  <CountBox setState={setQuantity}/>
                </div>
                <div className="buttons">
                  <BasicButton p_height={6} onClick={()=>cartKey(data.productId)}>장바구니 담기</BasicButton>
                  {cartModal ? (
                      <Guidance
                        text={"해당 상품을 장바구니에 담으시겠습니까?"}
                        close={() => setCartModal(false)}
                        ok={cartPost}
                        />
                    ) : null}
                </div>
              </ItemLayout>
            </ListLayout>
          );
        })}
      </Layout>
      <OtherPagination state={page} setState={setPage} pageInfo={selectItems.pageInfo}/>
    </Mypagehead>
  );
}

export default Serachitem;
