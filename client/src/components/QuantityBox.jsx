import styled from "styled-components";
import { ReactComponent as MinusIcon } from "../assets/minus-icon.svg";
import { ReactComponent as PlusIcon } from "../assets/plus-icon.svg";

const Container = styled.div`
  width: 88px;
  display: flex;
  align-items: center;
  border: 1px solid #fd6c40;
  border-radius: 3px;
`;

const Quantity = styled.div`
  width: 100%;
  display: inherit;
  justify-content: center;
  padding-top: 3px;
  font-size: 14px;
`;

const Button = styled.button`
  width: 28px;
  height: 28px;
`;

function QuantityBox({ quantity, setQuantity }) {
  const handleIncrease = () => {
    setQuantity((quantity += 1));
  };

  const handleDecrease = () => {
    if (quantity === 1) return;

    setQuantity((quantity -= 1));
  };

  return (
    <Container>
      <Button onClick={handleDecrease}>
        <MinusIcon fill={quantity === 1 ? "#DDD" : "#fd6c40"} />
      </Button>
      <Quantity>{quantity}</Quantity>
      <Button onClick={handleIncrease}>
        <PlusIcon fill={"#fd6c40"} />
      </Button>
    </Container>
  );
}

export default QuantityBox;
