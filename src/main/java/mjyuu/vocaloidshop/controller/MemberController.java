package mjyuu.vocaloidshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import mjyuu.vocaloidshop.dto.MemberDTO;
import mjyuu.vocaloidshop.repository.MemberRepository;

import lombok.AllArgsConstructor;

@RequestMapping("/members")
@AllArgsConstructor  //생성자 주입(new와 동일)
@Controller
public class MemberController {
	
	private MemberRepository repository;
	
	//회원 가입 화면 요청
	@GetMapping("/signup")
	public String addForm() {
		return "member/signup";  //addForm.html
	}
	
	//회원 등록 처리
	@PostMapping("/signup")
	public String add(@Valid @ModelAttribute MemberDTO member) {
		System.out.println("member: " + member);

		repository.save(member);
		return "redirect:/members";
	}
	
	//회원 목록 
	@GetMapping
	public String list(Model model) {
		List<MemberDTO> members = repository.findAll();  //목록 보기 메서드 호출
		//members 모델을 저장해서 뷰로 보냄
		model.addAttribute("members", members);
		return "member/members";
	}
}








