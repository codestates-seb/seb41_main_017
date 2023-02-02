import Mypagehead from "../../components/MypageHead";
import styled from "styled-components";
import BasicButton from "../../components/BasicButton";
import { ReviewStar } from "../../components/ReviewStar";
import { useState } from "react";
import axios from "axios";


const Layout = styled.div`
  padding: 10px;
  .write_header {
    display: flex;
    padding-bottom: 10px;
    margin-bottom: 10px;
    border-bottom: 1px solid #d7d7d7;
    img {
      width: 100px;
    }
    .write_header_content {
      padding: 10px;
      display: flex;
      justify-content: space-between;
      flex-direction: column;
    }
  }

  .write_main {
    .write_main_content {
      padding-bottom: 15px;
      h5 {
        font-size: 18px;
        padding-bottom: 5px;
      }
      textarea {
        padding: 8px;
        border: 1px solid #d7d7d7;
        resize: none;
        width: 100%;
        height: 150px;
      }
    }

    .write_main_file {
      border: 5px dashed #d7d7d7;
      padding: 30px;
      display: flex;
      align-items: center;
      margin-bottom: 30px;
      text-align: center;


      input {
        width: 200px;
        border: none;
        margin: 0 20px;
        color: red;
      }

      span {
        color: #ff6767;
        font-size: 12px;
      }
    }
  }

  .write_footer {
    display: flex;
    justify-content: center;
    gap: 10px;
  }
`;

function ItemreviewWrite({ item, close}) {
  const [reviewStar, setReviewStar] = useState(0);
  const [content, setContent] = useState("");
  const [file, setFile] = useState("");
  

  const postReview = (data) => {
    const formData = new FormData();
    formData.append('request', new Blob([JSON.stringify({content,reviewStar})], {type: "application/json"}))
    if (file) {
      formData.append('images', file);
    } else {
      formData.append('images', new Blob(), '');
    }
    
    axios.post(
      `${process.env.REACT_APP_URL}/product/${data.productId}/review?order-id=${data.id}`,
      formData,
      {
        headers: {
          authorization: JSON.parse(localStorage.getItem("token"))
            .authorization,
          "Content-Type": `multipart/form-data`,
        },
      }
    )
    .then( () => close(undefined))
    .catch( error => console.log(error))
  };
  
  return (
    <Mypagehead title={"후기 작성"} line={true}>
      <Layout>
        <div className="write_header">
          <div className="write_header_img">
            <img src={item.images} alt={item.name} />
          </div>
          <div className="write_header_content">
            <div>{item.name}</div>
            <div>
              <ReviewStar state={reviewStar} setState={setReviewStar} />
            </div>
          </div>
        </div>
        <div className="write_main">
          <div className="write_main_content">
            <h5>{"사용후기"}</h5>
            <textarea
              value={content}
              onChange={(e) => setContent(e.target.value)}
              placeholder="상품에 대한 솔직한 후기를 알려주세요! (최대 300자)"
            />
          </div>

          <div className="write_main_file">
            <h5>{"사진첨부"}</h5>
            <input 
            accept='image/jpg,impge/png,image/jpeg,image/gif' 
            onChange={(e)=>setFile(e.target.files[0])}
            type="file" 
            name="file" />
            <span>
              {"최대 20MB 이하의 JPG,PNG 파일만 가능합니다(최대 5개)"}
            </span>
          </div>
        </div>
        <div className="write_footer">
          <BasicButton
            font={"17"}
            p_height={"12"}
            p_width={"35"}
            onClick={() => postReview(item)}
            disabled={ reviewStar === 0 ? true : content === "" ? true : false}
          >
            후기작성
          </BasicButton>
          <BasicButton
            href={"/mypage/itemreviewList"}
            font={"17"}
            p_height={"12"}
            p_width={"35"}
          >
            목록으로
          </BasicButton>
        </div>
      </Layout>
    </Mypagehead>
  );
}

export default ItemreviewWrite;
