import { BiHeartCircle } from "react-icons/bi";
import Mypagehead from "../../components/MypageHead";


function Serachitem(){
    return(
        <Mypagehead title={'찜한 상품'} subtitle={"최대 N개까지 저장됩니다."} icon={<BiHeartCircle color="red" size={15}/>} line={true}>
        </Mypagehead>
    );
}


export default Serachitem;