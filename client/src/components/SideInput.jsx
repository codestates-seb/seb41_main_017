import styled from "styled-components";

const Layout = styled.div`
  display: flex;
  flex-direction: ${(props) =>
    props.flex_d !== "column" ? "row" : props.flex_d};
  align-items: ${(props) => (props.flex_d !== "column" ? "center" : null)};
  padding-bottom: ${(props) =>
    props.padding_b !== null ? props.padding_b : "10px"};

  .text {
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

  .data {
    flex: 1;
    input {
      border-radius: 5px;
      width: 100%;
      padding: ${(props) => (props.padding !== null ? props.padding : "10px")};
    }
  }

  .double {
    width: 100%;
    padding: 5px 3px;
    border-radius: 5px;
    margin-bottom: 4px;
  }
`;

function SideInput({
    type,
  flex_d,
  mark,
  placeholder,
  padding,
  defaultValue,
  onChange,
  padding_b,
  label,
  list,
  value,
}) {
  const styels = {
    flex_d,
    mark,
    padding,
    padding_b,
  };

  return (
    <Layout {...styels}>
      <div className="text">
        <label htmlFor="simple">
          {label}
          {mark === "on" ? <span className="mark">*</span> : false}
        </label>
      </div>
      <div className="data">
        {type !== "combo" ? (
          <input
            type="text"
            id="simple"
            placeholder={placeholder}
            defaultValue={defaultValue}
            onChange={onChange}
          ></input>
        ) : (
          <>
            <select 
            name="choice" 
            className="double"
            defaultValue={defaultValue}
            onChange={onChange}
            >
              {["---선택---",...list,"---직접입력---"].map((el, idx) => {
                return <option 
                key={idx}
                value={el}
                >{el}</option>   
              })}
            </select>
            <input
            type="text"
            id="simple"
            defaultValue={defaultValue}
            onChange={onChange}
          ></input>
          </>
        )}
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
  type: null,
  list: [],
};
export default SideInput;
