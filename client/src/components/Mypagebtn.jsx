import styled from "styled-components";
// 해당컴포넌트는 div를 감싸서 써야 글씨가 정렬됨
const Mypagebtn = ({radius, p_width, p_height, children}) =>{
    const styles = {radius, p_width, p_height};
    return(
        <>
            <Colbtn {...styles}><span>{children}</span></Colbtn>
        </>
    )
}

Mypagebtn.defaultProps = {
    children: null,
    radius: "0",
    p_width: "0",
    p_height: "0",
}

const Colbtn = styled.a` 
    display:block;
    background-color: #FFF7F5;
    border: 1px solid #ff6767;
    border-radius: ${props => props.radius}px;
    color: #ff6767;
    cursor: pointer;
    font-size: 12px;
    padding: ${props => props.p_height}px ${props => props.p_width}px;

    span{
        vertical-align: text-bottom;
    }
`

export default Mypagebtn;