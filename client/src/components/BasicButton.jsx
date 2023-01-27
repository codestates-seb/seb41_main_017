import styled from "styled-components";


const BasicButton = ({href, children, font, radius, p_height, p_width, onClick, disabled}) =>{
    const styles = {font, radius, p_height, p_width, disabled};
    return(
            <Colbtn {...styles} href={href} onClick={onClick}>
                {children}
            </Colbtn>
    )
}

BasicButton.defaultProps = {
    children: null,
    font:"12",
    radius:"4",
    p_height:"4",
    p_width:"7", 
}

const Colbtn = styled.a`
    background-color: ${props => props.disabled ? "rgba(128, 128, 128, 0.597)" : "#FFF7F5"};
    color: ${props => props.disabled ? "white" : "#ff6767"};
    border: 1px solid ${props => props.disabled ? "rgba(0, 0, 0, 0.491)" : "#ff6767"};
    cursor: pointer;
    ${props => props.disabled ? "pointer-events: none;" : null}
    vertical-align: middle;
    border-radius: ${props => props.radius}px;
    font-size: ${props => props.font}px;
    padding: ${props => props.p_height}px ${props => props.p_width}px;
`
export default BasicButton;
