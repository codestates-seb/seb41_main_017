import styled from "styled-components";

const ColorButton = ({ href, children, font, radius, p_height, p_width }) => {
  const styles = { font, radius, p_height, p_width };
  return (
      <Colbtn {...styles} href={href}>
        {children}
      </Colbtn>
  );
};

ColorButton.defaultProps = {
  children: null,
  font: "12",
  radius: "4",
  p_height: "4",
  p_width: "7",
};

const Colbtn = styled.a`
  background-color: #ff6767;
  border: 1px solid #ff6767;
  cursor: pointer;
  color: #ffffff;
  vertical-align: middle;
  border-radius: ${(props) => props.radius}px;
  font-size: ${(props) => props.font}px;
  padding: ${(props) => props.p_height}px ${(props) => props.p_width}px;
`;
export default ColorButton;
