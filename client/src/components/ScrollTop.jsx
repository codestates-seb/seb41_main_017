import { useEffect } from "react";
import { useState } from "react";
import styled from "styled-components";
import icon from "../assets/scoll_top.png";

const Icon = styled.img`
  position: fixed;
  bottom: 0px;
  right: 50px;
  cursor: pointer;
  user-select: none;

  transition: transform 0.5s;
  transform: ${({ position }) => (position > 500 ? "translate(0px, -50px)" : "translate(0px, 100px)")};
`;

function ScrollTop() {
  const [position, setPosition] = useState(0);

  const onScroll = () => {
    setPosition(window.scrollY);
  };

  useEffect(() => {
    window.addEventListener("scroll", onScroll);
    return () => {
      window.removeEventListener("scroll", onScroll);
    };
  }, []);

  return <Icon position={position} onClick={() => window.scrollTo(0, 0)} src={icon} width="60"></Icon>;
}

export default ScrollTop;
