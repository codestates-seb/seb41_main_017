import styled from "styled-components";
import Mypagehead from "./MypageHead";
import axios from "axios";
import Guidance from "../components/Guidance"
import { useEffect, useState } from "react";

const Layout = styled.div`

    h5{
        padding: 10px 0;
    }
  .top_line {
    display: flex;
    text-align: center;
    justify-content: space-around;
    background-color: #ffdede;
    padding: 5px 10px;
    & > p {
      flex: 1;
    }

    & > p:first-child {
      text-align: left;
      flex: 5;
    }
  }

  .content_line {
    padding: 50px;
    min-height: 300px;
    word-break: break-all;
    border-bottom: 1px solid ;
  }

  .section_line{
    margin-bottom: 10px;
    li{
        padding: 10px 10px;
        margin-bottom: 10px;
        border-bottom: 1px solid black;
    }

    .section_line_info{
        display:flex;
        justify-content: space-between;
        margin-bottom: 5px;
    }
  }
  
  .comment_line{
    display: flex;
    gap:10px;

    textarea{
        flex:4;
        min-height: 100px;
        padding: 8px;
        border: 1px solid #d7d7d7;
        resize: none;
    }
    button{
        background-color:#FFF7F5;
        border: 1px solid #FF6767;
        border-radius:5px;
        text-align:center;
        color: #FF6767;
        flex:1;
    }
    
  }

  .btns_line{
    padding: 10px 0;
    text-align: right;
    button{
        margin: 0 5px;
        padding: 5px;
        background-color:#FFF7F5;
        border: 1px solid #FF6767;
        border-radius:5px;
        text-align:center;
        color: #FF6767;
    }
  }
`;

export const MypageItem = ({ data, setState}) => {
    const [isOpen, setIsOpen]= useState(false);


    useEffect(()=>{
        // axios.get{`${process.env.REACT_APP_URL}/board/inquiry/${}/comments`}
    },[])

  const deletetMyQuestion = ()=>{
    axios.delete(`${process.env.REACT_APP_URL}/board/inquiry/${data.id}`,{
        headers: {
            authorization: JSON.parse(localStorage.getItem("token"))
              .authorization,
          },
    }).then(()=>{
        setState(undefined);
        window.location.reload();
    })
  }

  
  
  return (
    <Mypagehead title={"??? ??????"} line={true}>
      <Layout>
        <h5>{`${data.title}`}</h5>
        <div className="top_line">
          <p>{"???"}</p>
          <p>{`${data.category === undefined ? "??????" : data.category}`}</p>
          <p>{`${data.createdAt}`}</p>
        </div>
        <div  className="content_line">
          <p>{`${data.content}`}</p>
        </div>
        <ul className="section_line">
          <li>
            <div className="section_line_info">
              <p>{`??????`}</p>
              <p>{`2023-02-03`}</p>
            </div>
            <p>?????? ????????? ????????????????????????.</p>
          </li>
        </ul>
        <div className="comment_line">
          <textarea />
          <button>????????????</button>
        </div>
        <div className="btns_line">
          <button onClick={()=>setState(undefined)}>????????????</button>
          <button onClick={()=>setIsOpen(true)}>??????????????????</button>
          {isOpen ? 
          <Guidance
            text={"?????? ????????? ?????????????????????????"}
            close={()=>setIsOpen(false)}
            ok={deletetMyQuestion}
          />: null}
        </div>
      </Layout>
    </Mypagehead>
  );
};
