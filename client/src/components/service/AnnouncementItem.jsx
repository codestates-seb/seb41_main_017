import { Link } from "react-router-dom";
import styled from "styled-components";
import Dummy from "./data.json";

const Page = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

const ItemCotainer = styled.div`
  width: 900px;
  height: 60px;
  border-bottom: 1px solid black;
  display: flex;
  align-items: center;
  justify-content: center;
  p {
    margin-left: 15px;
  }
`;

const Notice = styled.div`
  width: 15%;
`;
const Title = styled.div`
  width: 65%;
`;
const Writter = styled.div`
  width: 10%;
  text-align: center;
`;

const Time = styled.div`
  width: 10%;
  text-align: center;
`;

function AnnouncementItem() {
  return (
    <Page>
      <div>
        {Dummy.Announcement.map((el) => {
          return (
            <ItemCotainer key={el.id}>
              <Notice>
                <p>{el.notice}</p>
              </Notice>
              <Title>
                <Link to={`/service/announcement/detail/${el.id}`}>
                  {el.title}
                </Link>
              </Title>
              <Writter>{el.writter}</Writter>
              <Time>{el.time}</Time>
            </ItemCotainer>
          );
        })}
      </div>
    </Page>
  );
}

export default AnnouncementItem;
