import styled from "styled-components";
import Mypagehead from "../../components/MypageHead";
import ListLayout from "../../components/ListLayout";
import ProductItem from "../../components/ProductItem";
import BasicButton from "../../components/BasicButton";
import { useEffect, useState} from "react";
import CountBox from "../../components/CountBox";
import axios from "axios";
import Guidance from "../../components/Guidance";

const testData = {
  data: [
    {
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
    {
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
    {
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
  ],
  pageInfo: {
    page: 1,
    size: 10,
    totalElements: 3,
    totalPages: 1,
  },
  barNumber: [0],
};

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
  const [buyItems, setBuyItems] = useState(testData.data);
  const [cartModal, setCartModal] = useState(false);
  const [item, setItem] = useState([]);
  
  useEffect(() => {
    // setBuyItems(testData.data);
    // axios.get(`${process.env.REACT_APP_URL}/collections/frequent`,{
    // headers: {
    //   authorization: JSON.parse(localStorage.getItem("token"))
    //     .authorization,
    // },
    // }).then( res => setBuyItems(res.data))
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
        {buyItems.map((data) => {
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

/*
 [내가 해결해야할 문제점]
 * 결제부분문제로 현재 더미데이터를 사용중
 1. 장바구니에 담을시 다른 상품에 수량을 늘린후에 다른상품을 장바구니에 담을경우 전에 눌린 카운터로 장바구니에 담김
 & 해결방안 : 카운트 되는것을 배열에 객체로 아이템id와 수량을담고 다른 아이디값이 들어오면 현재 까지 클릭한
  정보를 배열에 담는다. 장바구니담기 버튼을 누르면 해당 아이템 id를 찾아서 그것만을 Post

 2. 이미지 or 상품이름 클릭시 해당상품으로 이동?
 3. 페이지네이션필요-> 페이지별 공통

*/