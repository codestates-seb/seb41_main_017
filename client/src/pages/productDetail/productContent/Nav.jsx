import styled from "styled-components";
import { Link } from "react-scroll";

const Container = styled.nav`
  z-index: 20;
  position: sticky;
  top: 0;
  width: 750px;
  background-color: #fff;

  ul {
    height: 60px;
    display: flex;
    flex-wrap: wrap;
  }

  li {
    border-width: 1px 0 1px 1px;
    border-color: #eee;
    border-style: solid;
    background-color: #fafafa;
    display: flex;
    flex: 1;
  }

  li:last-of-type {
    border-right-width: 1px;
  }

  a {
    height: 100%;
    display: flex;
    flex: 1;
    justify-content: center;
    align-items: center;
    cursor: pointer;
  }

  span {
    font-size: 16px;
    font-weight: 500;
  }

  .active {
    background-color: #ffffff;
    color: #ff6767;
  }
`;

function Nav() {
  return (
    <Container>
      <ul>
        <li>
          <Link to="detail" spy={true} smooth={true} offset={-60} duration={400}>
            <span>상품상세</span>
          </Link>
        </li>
        <li>
          <Link to="review" spy={true} smooth={true} offset={-60} duration={400}>
            <span>후기</span>
          </Link>
        </li>
        <li>
          <Link to="inquiry" spy={true} smooth={true} offset={-60} duration={400}>
            <span>문의</span>
          </Link>
        </li>
      </ul>
    </Container>
  );
}

export default Nav;
