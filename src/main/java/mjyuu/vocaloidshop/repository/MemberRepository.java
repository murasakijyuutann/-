package mjyuu.vocaloidshop.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import mjyuu.vocaloidshop.dto.MemberDTO;

@Repository  
public class MemberRepository {
	
	private List<MemberDTO> list = new ArrayList<>();
	private long sequence = 0L;

	
	public MemberDTO save(@ModelAttribute MemberDTO member) {
		member.setId(++sequence);  
		list.add(member);
		return member;
	}
	
	
	public List<MemberDTO> findAll(){
		return list;
	}
}








