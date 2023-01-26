import React from "react";
import styled from "styled-components";
import { Route, Routes, NavLink } from "react-router-dom";

const Container = styled.div`
  text-align: left;
  .colbtn{
    display:flex;
    justify-content: center;
    align-items: center;
    background-color: #FFF7F5;
    border: 1px solid #ff6767;
    border-radius: 12px;
    color: #ff6767;
    cursor: pointer;
    font-size: 12px;
    padding: 7px 12px 4px 12px;
    
  }

  .nav {
      h3{
        font-size: 20px;
        margin-bottom:15px;
      }

      ul{
        display:flex;
        margin-top: 10px;
        gap: 7px;

        li{
          flex:${props => props.flex};
        }

      }
    
  }

  .content {
      border-top: 1px solid #D7D7D7;
      margin-top: 10px;
      padding: 30px;
  }

  .active{
    color: white;
    background-color: #FF6767;
  }

  
`;

const Tab = ({title, list, flex = null})=>{
    let styles = {flex};
    const taptitle = []
    const tapcontent = []
    const children = []

      for(let i in list){
        taptitle.push(
          <li key={i}>
            <NavLink
            to={Object.keys(list[i])[0]}
            className={`colbtn ${({isActive})=> (isActive) ? "active" : ""}`}
            >{i}</NavLink>
          </li>
        );
        tapcontent.push(
          <Route key={i} path={Object.keys(list[i])[0]} element={Object.values(list[i])[0]}/>
        );

        if(list[i].children){
          for(let j of list[i].children){
            children.push(
              <Route key={j} path={Object.keys(j)[0]} element={Object.values(j)[0]}/>
            )
          }
        };
      }
    
    return(
        <Container {...styles}>
          <div className="nav">
            <h3>{title}</h3>
            <ul>{taptitle}</ul>
          </div>
          <div className="content">
            <Routes>
            {tapcontent}
            {children}
            </Routes>
          </div>
        </Container>
    );
}

export default Tab;


