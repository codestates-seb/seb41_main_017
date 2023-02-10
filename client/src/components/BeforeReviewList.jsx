import styled from "styled-components";
import BasicButton from "./BasicButton";

const Layout = styled.div`
  border: 1px solid #d7d7d7;
  border-radius: 10px;
  padding: 10px;
  display: flex;
  margin-bottom: 10px;

  .left {
        flex: 1;
        h6{
            font-size: 14px;
        }
    
        .reviewList_item{
            display: flex;

            .reviewList_item_image{
                flex-basis: 100px;
                display:flex;
                padding:10px;
                justify-content:center;
                align-items:center;

                img{
                    border-radius: 10px;
                    width:150px;
                    height: 120px;
                }
            }

        }
        .reviewList_item_content{
            padding:10px;
            flex:1;
            h5{
                padding-bottom:10px;
            }
            span{
                padding-right:10px;
            }
        }
    }

    .right{
        display:flex;
        align-items:center;
    }
`;

function BeforeReviewList({setState, item}) {
  
  return (
    <Layout>
      <div className="left">
        <h6>{"2023.01.01 배송"}</h6>

        <div className="reviewList_item">
          <div className="reviewList_item_image">
            <img src={`${item.images[0].imgUrl}`} alt={`${item.name}`} />
          </div>

          <div className="reviewList_item_content">
            <h5>{`${item.name}`}</h5>
            <span>{`${item.price.toLocaleString()}원`}</span>
            <span>{`${item.quantity}개`}</span>
          </div>
        </div>

      </div>

      <div className="right">
        <div onClick={()=>setState(
            {
                id: item.id,
                images: item.images[0].imgUrl,
                name: item.name,
                productId: item.productId
            }
        )}><BasicButton
        p_height={"5"}
        p_width={"30"}
        >{"후기작성"}</BasicButton></div>
        </div>
    </Layout>
  );
}

export default BeforeReviewList;
