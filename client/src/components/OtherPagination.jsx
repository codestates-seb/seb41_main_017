import { useState } from "react";
import styled from "styled-components";


const testData = {
    "pageInfo": {
        "page": 1,
        "size": 10,
        "totalElements": 3,
        "totalPages": 2
      },
      "barNumber": [
        0
      ]
}

const Layout = styled.div`
    justify-content:center;
    display:flex;

    .box{
        display:flex;
        align-items: center;
        height:100%;
        background-color:#FFEAEA;


        span{
            padding: 10px;
            display: inline-block;
        }
    }

    .current{
        background-color: #FF6767;
        color:white;
    }


`


export const OtherPagination = ()=>{
    const [pageData, setPageData] = useState(testData.pageInfo)
    const [currentPage, setCurrentPage] = useState(testData.pageInfo.page)
    

    const left = ()=>{

        if(currentPage === 1){
           return setCurrentPage(1)
        } else{
            setCurrentPage(currentPage - 1)
        }
    }

    const right = ()=>{

        if(currentPage === pageData.totalPages){
            setCurrentPage(pageData.totalPages)
        } else{
            setCurrentPage(currentPage + 1)
        }
        
    }   

    return(
        <Layout>
            {currentPage === 1 ? null : <div className="box" onClick={left}><span>{"<"}</span></div>}
            <div className="box">
                {Array(pageData.totalPages).fill(pageData.totalPages).map( (el,idx) => {
                    return  <span 
                    className={`${currentPage === idx + 1 ? "current" : null}`}
                    key={idx + 1}>{ idx + 1}</span>})}
            </div>
            {pageData.totalPages === 1 ? null :
             currentPage === pageData.totalPages ? null  : <div className="box" onClick={right}><span>{">"}</span></div>}
        </Layout>
    );
}