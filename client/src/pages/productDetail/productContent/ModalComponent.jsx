import { useEffect } from "react";
import styled from "styled-components";

const RootContainer = styled.div`
  position: fixed;
  z-index: 1300;
  inset: 0px;
`;

const RootBackDrop = styled.div`
  opacity: 1;
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: center;
  inset: 0px;
  background-color: rgba(0, 0, 0, 0.3);
  z-index: -1;
`;

const ScrollPaper = styled.div`
  opacity: 1;
  height: 100%;
  outline: 0px;
  display: flex;
  justify-content: center;
`;

const Paper = styled.div`
  display: flex;
  overflow: hidden;
  flex-direction: column;
`;

const Modal = styled.div`
  max-width: fit-content;
  margin: 0px auto;
  background-color: #ffffff;
  margin: 20px;
  border-radius: 12px;
  overflow-y: auto;
`;

function ModalComponent({ component }) {
  useEffect(() => {
    document.body.style.cssText = `overflow-y: hidden;`;
    return () => {
      document.body.style.cssText = "";
    };
  }, []);

  return (
    <RootContainer>
      <RootBackDrop />
      <ScrollPaper>
        <Paper>
          <Modal>{component}</Modal>
        </Paper>
      </ScrollPaper>
    </RootContainer>
  );
}

export default ModalComponent;
