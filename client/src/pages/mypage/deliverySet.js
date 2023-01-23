import styled from "styled-components";
import BasicButton from "../../components/BasicButton";
import CheckBox from "../../components/CheckBox";
import Mypagehead from "../../components/MypageHead";
import ListLayout from "../../components/ListLayout";
import Postmodal from "../../components/Postmodal";
import { useEffect, useState,  } from "react";
import axios from 'axios';
import Guidance from "../../components/Guidance";
import PatchModal from "../../components/PatchModal";

const Classlist = styled.div`
  .check_box {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-basis: 60px;
    margin-right:10px;
    label {
      margin: 0;
    }
  }

  .list_content {
    flex: 1;
    .list_in_top{
      padding-bottom: 15px;
      font-size: 15px;
      span{
        color:#FF6767;
        padding-right: 5px;
      }
    }

    .list_in_mid{
      padding-bottom: 15px;
      span{
        padding-right: 15px;
      }
      p{
        padding-top:5px;
      }
    }

    .list_in_footer{

      & span:first-child{
        color:#676767;
      }

      span{
        padding-right: 15px;
      }
    }
  }

  .list_button{
    flex-basis: 120px;
    display:flex;
    justify-content:center;
    align-items:center;
    gap: 5px;
  }
`;

/*
    1. 비활성화 체크버튼을누르면 "기본배송지를 변경하시겠습니까?" 문구와함께 확인 과 취소 모달창
    3. 수정버튼을 누르면 "해당 배송지 정보가 입력된상태인 input태그들과 확인 과 취소 모달창

    배송지 추가 버튼누르는건 로그인 구현후로
*/

function Addressset() {
  const [addresList, setAddresList] = useState([]);
  const [keys, setKeys] = useState(0);
  const [ischeckd, setIscheckd] = useState(false);
  const [isdelete, setIsdelete] = useState(false);
  const [isfetch, setIsfetch] = useState(false);

  
  
  useEffect(()=>{
    
    axios.get(`${process.env.REACT_APP_URL}/destination`,{
      headers:{
        Authorization : localStorage.getItem("accessToken"),
      }
    })
    .then(res => setAddresList(res.data.data))
    .then(erros => erros)
    
  },[])

  // 중속성을 이용하여 window.location.reload(); 을사용하지않기
  const isOpen = (e,state)=>{
    setKeys(e)
    switch(state){
      case `ischeckd`: setIscheckd(!ischeckd);
      break;
      case `isdelete`: setIsdelete(!isdelete);
      break;
      case `isfetch` : setIsfetch(!isfetch);
      break;
      default : console.log("디폴트값이들어옴")
    }
  }


  const changeValue = (id, state)=>{
    if(state === "ischeckd"){
        axios.patch(`${process.env.REACT_APP_URL}/destination/${id}/representative`, undefined, {
          headers:{
            Authorization : localStorage.getItem("accessToken"),
          }
        }
      );
      setIscheckd(!ischeckd);
    }
    if(state === "isdelete") {
      axios.delete(`${process.env.REACT_APP_URL}/destination/${id}`, {
        headers:{
            Authorization : localStorage.getItem("accessToken"),
          }
        }
      );
      setIsdelete(!isdelete)
    }
    // if(state === "isfetch"){

    //     axios.patch(`${process.env.REACT_APP_URL}/destination/${id}`,{
    //       headers:{
    //         Authorization : localStorage.getItem("accessToken"),
    //       }
    //     }
    //   );
    //   setIsfetch(!isfetch);
    // }
    // 비동기문제
    setTimeout(() => window.location.reload(), 1000);
    
  }
  // 배송지 추가 할때 배송지명이 규칙이 있나? 현재 회사만 가능
  // 배송지 수정 바디는 타이틀,컨텐트.프로세스스테이터스는 어떤것인지 ?

  // console.log(addresList)
  // console.log(keys)
  return (
    <Mypagehead title={"배송지 설정"} side_title={<Postmodal/>}>
      <Classlist>
        {addresList.map((user)=>{
          return (
            <ListLayout
            key={user.id}
            radius={"5"}
            display={"flex"}
            justifyContent={"space-between"}
            padding_width={"10px"}
            padding_height={"20px"}
            >
          <div className="check_box">
            <CheckBox isChecked={user.defaultSelect} onClick={()=>isOpen(user.id,"ischeckd")}/>
              {ischeckd ? <Guidance text={"기본배송지를 변경 하시겠습니까?"} close={()=>setIscheckd(!ischeckd)} ok={()=> changeValue(keys,"ischeckd")}/> : null}
          </div>
          <div className="list_content">
            <div className="list_in_top">
              <span>{`${user.destinationName}`}</span>
              {user.defaultSelect === true ? <BasicButton radius={"5"} p_width={"10"} p_height={"1"}>
                {"기본"}
              </BasicButton> : null}
            </div>
            <div className="list_in_mid">
              <span>{`${user.receiverName}`}</span>
              <span>{`${user.receiverPhoneNumber}`}</span>
              <p>{`${user.address}`}</p>
            </div>
          </div>
          <div className="list_button">
            <BasicButton p_width={"15"} p_height={"5"} onClick={()=>isOpen(user.id,"isfetch")}>
              수정
            </BasicButton>
            {isfetch ? <PatchModal close={()=> setIsfetch(!isfetch)} data={addresList.filter((el)=> el.id === keys)}/> : null}

            <BasicButton p_width={"15"} p_height={"5"} onClick={()=>isOpen(user.id,"isdelete")}>
              삭제
            </BasicButton>
          {isdelete ? 
         ( addresList.length === 1 ? 
          <Guidance text={"배송지는 1개 이상이여야 합니다."} close={()=>setIsdelete(!isdelete)}/> 
          :<Guidance text={"해당 배송지를 삭제 하시겠습니까?"} close={()=>setIsdelete(!isdelete)} ok={()=> changeValue(keys,"isdelete")}/> 
          ): null}
          </div>
        </ListLayout>
          );
        })}
      </Classlist>
    </Mypagehead>
  );
}

export default Addressset;
