import { useState, useEffect } from "react";
import Mypagehead from "../../components/MypageHead";
import ListLayout from "../../components/ListLayout";
import BasicButton from "../../components/BasicButton";
import styled from "styled-components";


const TapLayout = styled.div`
  display:flex;
  gap:15px;
  font-size:15px;
  padding-bottom: 5px;
  & > div{
    cursor:pointer
  }

  .first{
    color: ${props => props.state ? '#FF6767' : '#7D7D7D'};
  }

  .second{
    color: ${props => props.state ? '#7D7D7D' : '#FF6767'};
  }
`

const Tab = ({setState, state})=>{
  const styles = {state}
  return(
    <TapLayout {...styles}>
      <div className="first" onClick={()=> setState(true)}><span>{"✓"}</span><span>{"작성 가능 후기"}</span></div>
      <div className="second" onClick={()=> setState(false)}><span>{"✓"}</span><span>{"작성한 후기"}</span></div>
    </TapLayout>
  );
}



function ItemreviewList() {
  const [tabs, setTabs] = useState(true);

  return (
    <Mypagehead
      title={"내 후기"}
      subtitle={"후기 작성은 배송완료 후 n개월 이내 가능합니다."}
      line={true}
      filltap={true}
      tab={<Tab state={tabs} setState={setTabs}/>}>
        {tabs === true ? <div>
          작성 가능 후기
        </div> :
        <div>
          작성한 후기
        </div>}
    </Mypagehead>
  );
}

export default ItemreviewList;
