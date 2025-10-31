package mjyuu.vocaloidshop.entity;

import java.security.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Member {

    @Id // 회원 ID 필드가 추가되어야 함 JPA 1번문제
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 JPA 2번문제
    private Long id;
    private String name;
    private String email;
    private String password;
    private Timestamp joinDate;

}