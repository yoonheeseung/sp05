package action;

import java.util.ArrayList;
import java.util.List;

import model.Board101Bean;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dao.Board101DAO;
import dao.Board101DAOImpl;

@Controller
public class BoardAction {

	private Board101DAO boardService;
	
	public void setBoardService(Board101DAO boardService) {
		this.boardService = boardService;
	}//스프링의 특징인 setter 메서드를 통해서 의존관계
	 //즉 DI를 만듬.

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
	public String list(Model listM) throws Exception{
		List<Board101Bean> list=this.boardService.getList();
		//디비로 부터 목록을 가져옴.
		
		listM.addAttribute("list",list);//list키에 목록을 저장
		
		return "board/board_list";
	}
	
	/*내용보기 + 수정폼+삭제폼*/
	@RequestMapping(value="/board_cont")
	public ModelAndView cont(@RequestParam("no") int no,
			@RequestParam("state") String state,Model cm)
			throws Exception{
		Board101Bean bcont=this.boardService.getCont(no);
		
		System.out.println(no);
		String cont=bcont.getCont().replace("\n","<br/>");
		//textarea박스에서 내용을 입력하다 엔터키 친부분을
		//웹상에서 출력할때 다음줄로 개행(<br/>) 시킴.
		
		ModelAndView bm=new ModelAndView();
		bm.addObject("bc",bcont);
		bm.addObject("cont",cont);
		
		Board101DAOImpl dao=new Board101DAOImpl();
		List<String> city= new ArrayList<String>();
		
		city = dao.getCity();
		
		cm.addAttribute("c",city);//c키에 배열을 저장
		
		
		if(state.equals("cont")){//내용보기
			bm.setViewName("board/board_cont");
		}else if(state.equals("edit")){//수정폼
			bm.setViewName("board/board_edit");
		}else if(state.equals("del")){//삭제폼
			bm.setViewName("board/baord_del");
		}
		return bm;
	}
	
}
