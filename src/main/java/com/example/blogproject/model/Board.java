package com.example.blogproject.model;

// ORM -> Java Object를 테이블로 맵핑
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data //Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
public class Board {
    @Id // primary key
    @GeneratedValue(strategy=GenerationType.IDENTITY) // 프로젝트에서 연결된

    private int id; // 시퀀스 , auto_increment

    @Column(nullable=false,length=100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리 <html>태그

    //@ColumnDefault("0")
    @Column
    private int count; //조회수

    @ManyToOne// Many=Board, User=One , 연관관계를 설정한다.
    @JoinColumn(name="userId") // DB에는 userId가 들어간다.
    private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
    // 자바와 데이터베이스가 충돌난다.

    @OneToMany(mappedBy="board", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board"})
    @OrderBy("id desc")
    private List<Reply> replies;

    @CreationTimestamp
    private Timestamp createDate;
}
