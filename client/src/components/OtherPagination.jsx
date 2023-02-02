import styled from "styled-components";

const Layout = styled.div`
    justify-content: center;
    display: flex;
    font-family: 'Times New Roman', Times, serif;

    & > div:first-child{
        border-radius:10px 0px 0px 10px;
    }
    & > div:last-child{
        border-radius:0px 10px 10px 0px;
    }


  .box {
    display: flex;
    align-items: center;
    height: 100%;
    background-color: #ffeaea;

    span {
      padding: 10px;
      display: inline-block;
      cursor: pointer;
      border-radius: 8px;

      &:hover{
        background-color: #ff6767;
        color: white;
        border-radius: 8px;
      }
    }
  }



  .current {
    background-color: #ff6767;
    color: white;
  }
`;

export const OtherPagination = ({ state, setState, pageInfo }) => {
  const pageData = pageInfo;
  

  const left = () => {
    if (state === 0) {
      return setState(0);
    } else {
      setState(state - 1);
    }
  };
  const right = () => {
    if (state === pageData.totalPages - 1) {
      setState(pageData.totalPages - 1);
    } else {
      setState(state + 1);
    }
  };
  const clickNum = (e) => {
    setState(e - 1);
  };

  return (
    <Layout>
        <div className="box" onClick={left}>
            <span>{"<"}</span>
          </div>
        <div className="box">
          {Array(pageData?.totalPages)
            .fill()
            .map((el, idx) => {
              return (
                <span
                  onClick={(e) => clickNum(e.target.innerText)}
                  className={`${state === idx ? "current" : "simple"}`}
                  key={idx}
                >
                  {idx + 1}
                </span>
              );
            })}
        </div>
        <div className="box" onClick={right}>
            <span>{">"}</span>
          </div>
    </Layout>
  );
};
