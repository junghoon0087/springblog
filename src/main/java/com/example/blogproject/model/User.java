package com.example.blogproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

// ORM -> Java Object를 테이블로 맵핑
@Entity  // User 클래스가 MySQL에 테이블이 생성이 된다.
@Data  //Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder  // 빌더 패턴
@DynamicInsert  //insert시 null 인 필드를 제외시켜준다.
public class User {
    @Id  // primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id; // 시퀀스 , auto_increment

    @Column(nullable=false,length=50, unique = true)
    private String username; // 아이디

    @Column(nullable=false,length=100) // 123456=>해쉬(비밀번호 암호화)
    private String password;  //

    @Column(nullable=false,length=50)
    private String email;

    @ColumnDefault("'user'")
    // DB는 RoleType이라는 게 없다.
    //private String role;
    @Enumerated(EnumType.STRING)
    private RoleType role;  // Enum을 쓰는게 좋다. ADMIN, USER

    @CreationTimestamp  // 시간이 자동 입력
    private Timestamp createDate;

}
