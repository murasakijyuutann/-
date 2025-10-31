package mjyuu.vocaloidshop.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder   //데이터 생성
@ToString  //문자열 정보
@Setter
@Getter
public class User {
	private String id;  //아이디
	private String pw;  //비밀번호
}
