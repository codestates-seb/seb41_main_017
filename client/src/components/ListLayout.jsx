import styled from "styled-components";

const Container = styled.div`
  display: ${props => props.block === "off" ? "inline-block" : "block"};
  border-radius: 10px;
  border: 1px solid #d7d7d7;
  padding: 10px 7px;
  margin-bottom: 20px;
`;

function ListLayout({ children, block}) {
    const styles = {block}
  return <Container {...styles}>{children}</Container>;
}


ListLayout.defaultProps={
    children:null,
    block: null,

}

export default ListLayout;
