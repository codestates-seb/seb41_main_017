import { useState, useEffect } from "react";
import Mypagehead from "../../components/MypageHead";
import BeforeReviewList from "../../components/BeforeReviewList";
import AfterReviewList from "../../components/AfterReviewList";
import ItemreviewWrite from "./itemreviewWrite";
import styled from "styled-components";
import axios from "axios";



const TapLayout = styled.div`
  display: flex;
  gap: 15px;
  font-size: 15px;
  padding-bottom: 5px;
  & > div {
    cursor: pointer;
  }

  .first {
    color: ${(props) => (props.state ? "#FF6767" : "#7D7D7D")};
  }

  .second {
    color: ${(props) => (props.state ? "#7D7D7D" : "#FF6767")};
  }
`;


const Tab = ({ setState, state }) => {
  const styles = { state };
  return (
    <TapLayout {...styles}>
      <div className="first" onClick={() => setState(true)}>
        <span>{"✓"}</span>
        <span>{"작성 가능 후기"}</span>
      </div>
      <div className="second" onClick={() => setState(false)}>
        <span>{"✓"}</span>
        <span>{"작성한 후기"}</span>
      </div>
    </TapLayout>
  );
};

function ItemreviewList() {
  const [beforeData, setBeforeData] = useState([]);
  const [afterData, setAfterData] = useState([])
  const [tabs, setTabs] = useState(true);
  const [pageChange, setPageChange] = useState(undefined);
  

  useEffect(()=>{

    if(tabs === false){
      axios.get(`${process.env.REACT_APP_URL}/mypage/review?type=exist`,{
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        }
      })
      .then((res)=> setAfterData(res.data.data))
    }

    if(tabs === true){
      axios.get(`${process.env.REACT_APP_URL}/mypage/review?type=nonexistent`,{
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        }
      })
      .then((res)=> setBeforeData(res.data.data))
    } 
  },[tabs]);


  return (
    <div>
      {pageChange ? 
      <ItemreviewWrite item={pageChange} close={setPageChange}/>: 
      (
        <Mypagehead
          title={"내 후기"}
          subtitle={"후기 작성은 배송완료 후 1개월 이내 가능합니다."}
          line={true}
          filltap={true}
          tab={<Tab state={tabs} setState={setTabs} />}
        >
          {tabs === true ? 
          ( beforeData.map((item)=>{
            return(<BeforeReviewList key={item.id} item={item} setState={setPageChange}/>);
          })) 
          : (afterData.map((item, idx)=>{
            return(
              <AfterReviewList key={item.id} item={item} afterData={afterData} setAfterData={setAfterData} idx={idx}/>
            );
          }))
          }
        </Mypagehead>
      )}
    </div>
  );
}

export default ItemreviewList;
