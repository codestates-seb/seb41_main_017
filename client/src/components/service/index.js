import styled from "styled-components";
import Tab from "../Tab";
import Announcement from "./Announcement";
import ManyQuestion from "./ManyQuestion";
import OneOnOne from "./OneOnOne";
import OneOnOneInquiry from "./OneOnOneInquiry";
import ServiceDetailpage from "./ServiceDetailpage";
import OftenDetail from "./OftenDetail";
import Dummy from "./data.json";
import OneOnOneDetail from "./OneOnOneDetail";

function ServiceHome() {
  const Page = styled.div`
    width: 1050px;
    margin: 0 auto;
    margin-top: 50px;
  `;

  const list = {
    공지사항: {
      "announcement?": <Announcement />,
      children: [
        {
          "/announcement/detail/1": (
            <ServiceDetailpage Dummy={Dummy.Announcement[0]} />
          ),
        },
        {
          "/announcement/detail/2": (
            <ServiceDetailpage Dummy={Dummy.Announcement[1]} />
          ),
        },
        {
          "/announcement/detail/3": (
            <ServiceDetailpage Dummy={Dummy.Announcement[2]} />
          ),
        },
      ],
    },

    "자주묻는 질문": {
      question: <ManyQuestion />,
      children: [
        {
          "/question/often/1": <OftenDetail Dummy={Dummy.Question[0]} />,
        },
        {
          "/question/often/2": <OftenDetail Dummy={Dummy.Question[1]} />,
        },
        {
          "/question/often/3": <OftenDetail Dummy={Dummy.Question[2]} />,
        },
        {
          "/question/often/4": <OftenDetail Dummy={Dummy.Question[3]} />,
        },
        {
          "/question/often/5": <OftenDetail Dummy={Dummy.Question[4]} />,
        },
      ],
    },

    "1:1문의": {
      "one-on-one": <OneOnOne />,
      children: [
        {
          "/one-on-one/inquiry": <OneOnOneInquiry />,
        },
        {
          "/one-on-one/:id": <OneOnOneDetail />,
        },
      ],
    },
  };

  return (
    <Page>
      <Tab list={list} title="고객센터" />
    </Page>
  );
}

export default ServiceHome;
