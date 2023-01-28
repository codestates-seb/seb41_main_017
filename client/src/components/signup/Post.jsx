import DaumPostcode from "react-daum-postcode";
import styled from "styled-components";
// import "./post.css";

const AddressModal = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Post = ({ setSignupAddress, setModalOpen }) => {
  const complete = (data) => {
    let fullAddress = setSignupAddress(data.address);
    let extraAddress = "";
    setModalOpen(false);
    if (data.addressType === "R") {
      if (data.bname !== "") {
        extraAddress += data.bname;
      }
      if (data.buildingName !== "") {
        extraAddress +=
          extraAddress !== "" ? `, ${data.buildingName}` : data.buildingName;
      }
      fullAddress += extraAddress !== "" ? ` (${extraAddress})` : "";
    }
  };

  return (
    <>
      <AddressModal>
        <DaumPostcode className="postmodal" autoClose onComplete={complete} />
      </AddressModal>
    </>
  );
};

export default Post;
