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
    position: relative;

    .side{
      position: absolute;
      right:0;
    }
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
  line,filltap,side_title
}) {
  const styels = {line, filltap}
  return (
    <Layout {...styels}>
      <div className="container">
        <div className="simple">
          {icon !== null ? <div className="icons">{icon}</div> : null}
          <span>{title}</span>
          {side_title !== null ? <div className="side">{side_title}</div> : null}
          {subtitle !== null ?  <span className="subtitle">{subtitle}</span> : null}
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
    side_title:null,
    line: false,
    filltap: false,



}

export default Mypagehead;
