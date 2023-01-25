import styled from "styled-components";

const Layout = styled.div`
  display: flex;
  flex-direction: ${(props) =>
    props.flex_d !== "column" ? "row" : props.flex_d};
  align-items: ${(props) => (props.flex_d !== "column" ? "center" : null)};
  padding-bottom: ${(props) =>
    props.padding_b !== null ? props.padding_b : "10px"};
  .sideInput_text {
    display: flex;
    align-items: center;
    font-size: 16px;
    height: 25px;

    label {
      position: relative;
      padding-right: 10px;
      .mark {
        position: absolute;
        color: red;
        top: 0;
        right: 10;
      }
    }
  }

  .sideInput_data {
    flex: 1;
    input {
      border-radius: 5px;
      width: 100%;
      padding: ${(props) => (props.padding !== null ? props.padding : "10px")};
    }
  }

`;

function SideInput({
  flex_d,
  mark,
  placeholder,
  padding,
  defaultValue,
  onChange,
  padding_b,
  label,
}) {
  const styels = {
    flex_d,
    mark,
    padding,
    padding_b,
  };

  return (
    <Layout {...styels}>
      <div className="sideInput_text">
        <label htmlFor="simple">
          {label}
          {mark === "on" ? <span className="mark">*</span> : false}
        </label>
      </div>
      <div className="sideInput_data">
          <input
            type="text"
            id="simple"
            placeholder={placeholder}
            defaultValue={defaultValue}
            onChange={onChange}
          ></input>
      </div>
    </Layout>
  );
}

SideInput.defaultProps = {
  flex_d: null,
  mark: "off",
  placeholder: "",
  padding: null,
  padding_b: null,
  label: "",
};
export default SideInput;
