import styled from "styled-components";
import { Route, Routes, BrowserRouter, NavLink, Link } from "react-router-dom";



const Container = styled.div`
// 부모와 맞게 수정
  margin: 0 auto;
  width: 1050px;
  text-align: left;
  margin-top: 20px;

  .colbtn{
    display:flex;
    justify-content: center;
    align-items: center;
    background-color: #FFF7F5;
    border: 1px solid #ff6767;
    border-radius: 10px;
    color: #ff6767;
    cursor: pointer;
    font-size: 12px;
    padding: 5px 8px 3px 8px;
    
  }

  .nav {
      h3{
        font-size: 20px;
      }

      ul{
        display:flex;
        margin-top: 10px;
        gap: 10px;

        li{
          flex:1;
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
// props양식
// list = [{"탭이름": ["탭URI",컴포넌트]}] 
// title = "제목이름"


const Tap = ({title, list})=>{
    return(
        <BrowserRouter>
        <Container>
          <div className="nav">
            <h3>{title}</h3>
            <ul>
              {list.map((el, idx) => {
                return (
                <li key={idx}>
                    <NavLink 
                    to={Object.values(el)[0][0]}
                    className={`colbtn ${({isActive})=> (isActive) ? "active" : ""}`}>
                      {Object.keys(el)}
                    </NavLink>
                </li>
                );
              })}
            </ul>
          </div>
          <div className="content">
            <Routes>
                {list.map((el, idx)=>{
                    return <Route key={idx} path={Object.values(el)[0][0]} element={Object.values(el)[0][1]} />
                })}
            </Routes>
          </div>
        </Container>
        </BrowserRouter>
    );
}

export default Tap;


