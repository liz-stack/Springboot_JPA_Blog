package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_incresement
	private int id;

	@Column(nullable = false, length = 100)
	private String title;

	@Lob // 대용량 데이터
	private String content;

	@ColumnDefault("0")
	private int count; // 조회수

	@ManyToOne(fetch = FetchType.EAGER) // Many=Board, User=One
	@JoinColumn(name = "userId")
	private User user; // db는 오브젝트를 저장할 수 없지만(FK사용), 자바는 저장 가능

	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) 
	//mappedBy: 연관관계의 주인이 아니다(FK 아님).db에 칼럼 만들지 마시오
	
	private List<Reply> reply; 

	@CreationTimestamp
	private Timestamp createDate;
}
