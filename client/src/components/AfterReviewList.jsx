import styled from "styled-components";
import BasicButton from "./BasicButton";
import { ReviewStar } from "../components/ReviewStar";
import { useEffect, useState } from "react";
import axios from "axios";
import Guidance from "../components/Guidance";

const Layout = styled.div`
  border: 1px solid #d7d7d7;
  border-radius: 10px;
  padding: 10px;
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;

  .afterReviewList_header {
    display: flex;
    border-bottom: 1px solid #d7d7d7;
    margin-bottom: 10px;

    .left {
      flex: 1;
      h6 {
        font-size: 14px;
      }

      .afterReviewList_item {
        display: flex;

        .afterReviewList_item_image {
          display: flex;
          padding: 10px;
          justify-content: center;
          align-items: center;

          img {
            border-radius: 10px;
            width: 70px;
            height: 70px;
          }
        }
      }
      .afterReviewList_item_content {
        padding: 10px;
        flex: 1;
        h5 {
          padding-bottom: 10px;
        }
        span {
          padding-right: 10px;
        }
      }
    }

    .right {
      display: flex;
      align-items: center;
      .right_btns {
        display: flex;
        flex-direction: column;
        gap: 5px;
      }
    }
  }

  .afterReviewList_sub {
    display: flex;

    .afterReviewList_sub_img {
      img {
        width: 100px;
        border-radius: 5px;
      }
    }

    .afterReviewList_sub_content {
      flex: 1;
      padding: 10px;
      display: flex;
      flex-direction: column;

      .afterReviewList_sub_text {
        margin: 10px 5px;
        flex: 1;
        position: relative;

        textarea{
          font-size: 14px;
          resize: none;
          border: none;
          width:91%;
          height:100%;
          background-color: ${(props) =>
            props.listModify ? "rgba(128, 128, 128, 0.15)" : "white"};
        }
        
        .submit{
          position: absolute;
          right:0;
          bottom:25px;
        }
        
      }
    }
  }
`;

function AfterReviewList({item, afterData, setAfterData, idx}) {
  const [listDelete, setListDelete] = useState(false);
  const [listModify, setListModify] = useState(false);
  const [changeText, setChangeText] = useState(item.content);
  const [isOpen, setIsOpen] = useState(false);
  const styles = { listModify };

  const itemModify = (text) => {

    if(text === "확인"){
      axios.patch(`${process.env.REACT_APP_URL}/product/review/${item.id}`,{
        content:changeText
      },{
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
        }
      }).then(()=>{
        afterData.data[idx].content = changeText; 
        setAfterData(afterData);
        setIsOpen(false);
        setListModify(false);
      })
    }

    if(text === "취소"){
      setChangeText(item.content);
      setIsOpen(false);
      setListModify(false);
    }

  };

  const itemDelete = () => {
    axios
      .delete(
        `${process.env.REACT_APP_URL}/product/review/${item.id}?product-id=${item.productId}`,
        {
          headers: {
            authorization: JSON.parse(localStorage.getItem("token"))
              .authorization,
          },
        }
      )
      .then(() => {
        setListDelete(false);
        window.location.reload();
      });
  };
  return (
    <Layout {...styles}>
      <div className="afterReviewList_header">
        <div className="left">
          <h6>{"2023.01.01 배송"}</h6>
          <div className="afterReviewList_item">
            <div className="afterReviewList_item_image">
              <img src={`${item.images[0].imgUrl}`} alt="#" />
            </div>

            <div className="afterReviewList_item_content">
              <h5>{`${item.name}`}</h5>
              <span>{`${item.price.toLocaleString()} 원`}</span>
              <span>{`${item.quantity} 개`}</span>
            </div>
          </div>
        </div>
        <div className="right">
          <div className="right_btns">
            <BasicButton
              onClick={() => setListModify(true)}
              p_height={"5"}
              p_width={"15"}>
              {"수정"}
            </BasicButton>
            <BasicButton
              onClick={() => setListDelete(true)}
              p_height={"5"}
              p_width={"15"}
            >
              {"삭제"}
            </BasicButton>
            {listDelete ? (
              <Guidance
                text={"해당 후기를 삭제하시겠습니까?"}
                ok={itemDelete}
                close={() => setListDelete(false)}
              />
            ) : null}
          </div>
        </div>
      </div>
      <div className="afterReviewList_sub">
        <div className="afterReviewList_sub_img">
          <img src={item.reviewImages[0]?.imgUrl} alt="" />
        </div>
        <div className="afterReviewList_sub_content">
          <div className="afterReviewList_sub_star">
            <ReviewStar state={item.reviewStar} disabled={true} />
          </div>
          <div className="afterReviewList_sub_text">
            <textarea
              value={changeText}
              onChange={(e) => setChangeText(e.target.value)}
              disabled={listModify ? false : true}>
              {item.content}
            </textarea>
            {listModify ? 
            <div className="submit">
              <BasicButton 
              onClick={()=>setIsOpen(true)}
              p_height={25}>수정하기</BasicButton>
            </div> : null}
            {isOpen ? (
              <Guidance
                text={"해당내용으로 변경하시겠습니까?"}
                ok={(e)=>itemModify(e.target.innerText)}
                close={(e)=>itemModify(e.target.innerText)}
              />
            ) : null}
          </div>
        </div>
      </div>
    </Layout>
  );
}

export default AfterReviewList;
