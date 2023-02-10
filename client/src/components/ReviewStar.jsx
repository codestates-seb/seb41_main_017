import { AiFillStar } from "react-icons/ai";

export const ReviewStar = ({state,setState,disabled}) => {
    const starvalue = [1,2,3,4,5]
  
    const saveStar = (num) => {
      setState(num)
    };
  
    return (
      <div>
        {starvalue.map((num) => {
          return (
            <button 
            key={num}
            value={num}
            disabled={disabled}
            onClick={() => saveStar(num)}
            style={{margin:" 0 2px"}}
            >
              <AiFillStar
                size={20}
                color={num <= state ? "red" : "gray"}
              />
            </button>
          );
        })}
      </div>
    );
  };

  