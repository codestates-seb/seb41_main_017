import { useEffect, useState} from "react";
import styled from "styled-components";

const Layout = styled.div`
    display:flex;
    justify-content:space-around;
    

    .countBox_event{
        color:#FF6767;
        cursor: pointer;
    }
    

`

function CountBox({setState}){
  const [conunt, setConunt] = useState(1)

  const changeValue = (e)=>{

    if(e === "+"){
      setConunt(conunt + 1);
    }

    if(e === "-"){

      if(conunt === 0){
        setConunt(conunt)
      }else{
        setConunt(conunt - 1);
      }

      
    }
    setState(conunt)
  }
  

    return(
      <Layout>
        <div className="countBox_event" onClick={(e) => changeValue(e.target.innerText)}>-</div>
        <div>{conunt}</div>
        <div className="countBox_event" onClick={(e) => changeValue(e.target.innerText)}>+</div>
      </Layout>
    );
  }

export default CountBox;