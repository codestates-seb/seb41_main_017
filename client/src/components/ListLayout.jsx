import styled from "styled-components";

const Container = styled.div`
  display: ${props => props.display !== null ? props.display : "block"};
  justify-content: ${props => props.justifyContent !== null ? props.justifyContent : "none"};
  border-radius: ${props => props.radius}px;
  padding: ${props => props.padding_height} ${props => props.padding_width};
  border: 1px solid #d7d7d7;
  margin-bottom: 20px;

  .text{
    font-size:13px;
    position: absolute;
    padding: 5px;
  }
`;

function ListLayout({ 
  children, 
  display, 
  radius = 10, 
  text = null,
  justifyContent = null,
  padding_width,
  padding_height
}) {
    const styles = {display,radius,justifyContent,padding_width,padding_height}
  return (
    <Container {...styles}>
      {text !== null ? <p className="text">{text}</p> : null}
      {children}
      </Container>
  );
}


ListLayout.defaultProps={
    children:null,
    block: null,

}

export default ListLayout;
