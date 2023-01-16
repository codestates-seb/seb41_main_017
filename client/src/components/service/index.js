import styled from "styled-components";
import Tab from "../Tab";
import Announcement from "./Announcement";
import ManyQuestion from "./ManyQuestion";
import OneOnOne from "./OneOnOne";
import OneOnOneInquiry from "./OneOnOneInquiry";
import ServiceDetailpage from "./ServiceDetailpage";
import Dummy from "./data.json";

function ServiceHome() {
  const Page = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 40px;
  `;
  console.log(Dummy.Announcement[0]);

  const list = {
    공지사항: {
      "/announcement": <Announcement />,
      children: [
        {
          "/announcement/detail/1": (
            <ServiceDetailpage Dummy={Dummy.Announcement[0]} />
          ),
        },
        {
          "/announcement/detail/2": (
            <ServiceDetailpage Dummy={Dummy.Announcement[0]} />
          ),
        },
      ],
    },
    "자주묻는 질문": {
      "/question": <ManyQuestion />,
      children: {
        "/question/detail": <ServiceDetailpage />,
      },
    },

    "1:1문의": {
      "/one-on-one": <OneOnOne />,
      children: {
        "/one-on-one/inquiry": <OneOnOneInquiry />,
      },
    },
    test: {
      "/test": <ServiceDetailpage />,
    },
  };

  return (
    <Page>
      <div>
        <Tab list={list} title="고객센터" />
      </div>
    </Page>
  );
}

export default ServiceHome;
