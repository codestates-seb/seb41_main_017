import styled from "styled-components";
import BasicButton from "../../components/BasicButton";
import CheckBox from "../../components/CheckBox";
import Mypagehead from "../../components/MypageHead";
import ListLayout from "../../components/ListLayout";
import Modalbutton from "../../components/Modalbutton";
import { useEffect, useState,  } from "react";
import axios from 'axios';
import Guidance from "../../components/Guidance";




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
  "id": 1,
    "destinationName": "test destinationName", // 목적지
    "address": "test address", // 주소
    "receiverName": "test name", // 수취인
    "receiverPhoneNumber": "010-9999-9999", // 폰번호
    "defaultSelect": false
    ** 배송메세지 부분이 빠졌음

    1. 비활성화 체크버튼을누르면 "기본배송지를 변경하시겠습니까?" 문구와함께 확인 과 취소 모달창
    2. 삭제버튼을 누르면 "해당배송지를 삭제하시겠습니까?"  문고와함께 확인 과 취소 모달창
    3. 수정버튼을 누르면 "해당 배송지 정보가 입력된상태인 input태그들과 확인 과 취소 모달창
    4. 기본배송지가 하나일경우는 삭제 버튼을 누를시에 "기본배송지는 1개 이상이여야합니다" 경고문과 취소버튼

    배송지 추가 버튼누르는건 로그인 구현후로
*/

function Addressset() {
  const [addresList, setAddresList] = useState([]);
  const [ischeckd, setIscheckd] = useState(false);
  const [keys, setKeys] = useState(0);
  const [isdelete, setIsdelete] = useState(false);


  useEffect(()=>{
    
    axios.get(`${process.env.REACT_APP_URL}/destination`,{
      headers:{
        Authorization : localStorage.getItem("accessToken"),
      }
    })
    .then(res => setAddresList(res.data.data))
    .then(erros => erros)
    
  },[])

  const isOpen = (e,state)=>{
    setKeys(e)
    switch(state){
      case `ischeckd`: setIscheckd(!ischeckd);
      break;
      case `isdelete`: setIsdelete(!isdelete);
      break;
      default : console.log("디폴트값이들어옴")
    }
  }

  const changeValue = (id, state)=>{
    console.log(state)
    console.log(addresList.length)

    if(state === "ischeckd"){
        axios.patch(`${process.env.REACT_APP_URL}/destination/${id}/representative`,{
          defaultSelect: true,
        },{
          headers:{
            Authorization : localStorage.getItem("accessToken"),
          }
        },
        setIscheckd(!ischeckd)
      );
    }
    if(state === "isdelete"){

        axios.delete(`${process.env.REACT_APP_URL}/destination/${id}`,{
          headers:{
            Authorization : localStorage.getItem("accessToken"),
          }
        },
        setIsdelete(isdelete)
      );
    }

    window.location.replace("/mypage/addressSet");
  }


  
  
  
  return (
    <Mypagehead title={"배송지 설정"} side_title={<Modalbutton text={"배송지 추가"}/>}>
      <Classlist>
        {addresList.map((user, idx)=>{
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
            {/* 보류 */}
            {/* <div className="list_in_footer">
              <span>{"배송메세지"}</span>
              <span>{"문 앞에 놔주세요"}</span>
            </div> */}
          </div>
          <div className="list_button">
            <BasicButton p_width={"15"} p_height={"5"}>
              수정
            </BasicButton>
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
