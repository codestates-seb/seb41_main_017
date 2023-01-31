import { useEffect, useState} from "react";
import styled from "styled-components";

const Layout = styled.div`
    display:flex;
    justify-content:space-around;

    .countBox_event{
        color:#FF6767;
    }
    

`

function CountBox({itemId, props}){
    const [state, setState] = useState(0);
    
    if(state < 0){
      setState(0);
    }
    useEffect(() => {
  
      props({
        productId: itemId,
        quantity: state
      })
    }, [state]);
  
    return(
      <Layout>
        <div className="countBox_event" onClick={() => setState(state - 1)}>-</div>
        <div>{state}</div>
        <div className="countBox_event" onClick={() => setState(state + 1)}>+</div>
      </Layout>
    );
  }

export default CountBox;