package action;

import java.util.ArrayList;
import java.util.List;

import model.Board101Bean;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.Board101DAOImpl;

@Controller
public class BoardAction {

	/* 글쓰기 폼*/
	@RequestMapping(value="/board_write")
	public String write(Model cm) throws Exception{
		/*String[] city={"서울","부산","인천","대구","광주","울산","경기","강원",
				       "충북","충남","전북","전남","제주","경북","경남","세종","대전"
		};
		 ====> DB로 입력
		*/
		
		Board101DAOImpl dao=new Board101DAOImpl();
		List<String> city= new ArrayList<String>();
		
		city = dao.getCity();
		
		cm.addAttribute("c",city);//c키에 배열을 저장
		return "board/board_write";
	}
	
	/* 저장 */
	@RequestMapping(value="/board_write_ok")
	public String ok(@ModelAttribute Board101Bean b) throws Exception{
		Board101DAOImpl dao=new Board101DAOImpl();
		if(dao.insert(b)>0){//저장메서드 호출
		return "redirect:/board_list.do";
		}
		return null;
	}
	
	/* 목록 */
	@RequestMapping(value="/board_list.do")
	public String list(Model listm) throws Exception{
		return "board/board_list";
	}
}
