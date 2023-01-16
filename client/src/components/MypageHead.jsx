import styled from "styled-components";

const Layout = styled.div`
  width:80%;
  margin: 0 auto;
  
  .container {
    padding-bottom: ${ props => props.line === props.filltap ? "0px" : "10px"};
    margin-bottom: 10px;
    ${props => props.line ? "border-bottom:1px solid #D7D7D7" : null};
  }

  .simple{
    display:flex;
    align-items:center;
  }

  .subtitle{
    color:#C26D53;
    font-size:12px;
    margin-left: 10px;
  }

  .icons{
    margin-right:2px;
  }

  .filter{
    margin-top:${ props => props.filltap ? "20px" : "0px"};
  }
`;

function Mypagehead({
  children,title,subtitle,icon,
  line,filltap
}) {
  const styels = {line, filltap}
  return (
    <Layout {...styels}>
      <div className="container">
        <div className="simple">
          <div className="icons">{icon}</div>
          <span>{title}</span>
          {subtitle === null ? null : <span className="subtitle">{subtitle}</span>}
        </div>
        {filltap ? <div className="filter">탭컴포넌트 자리</div> : null}
      </div>
      <div>{children}</div>
    </Layout>
  );
}

Mypagehead.defaultProps = {
    children: null,
    title: null,
    subtitle: null,
    icon: null,
    line: false,
    filltap: false,


}

export default Mypagehead;
