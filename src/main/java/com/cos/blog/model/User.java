package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@DynamicInsert //insert시에 null인 필드를 제외시켜준다
@Builder
@Entity//User클래스가 자동으로 읽고 MySQL에 테이블 생성
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//해당 프로젝트에서 연결된 db의 넘버링 전략을 따라가겠다.
	//ex. 오라클이면 시퀀스, mysql이면 auto incresement 등
	private int id; //시퀀스, auto-increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;
	
	@Column(nullable = false, length = 100)// pw 해쉬로 변경해 암호화할 예정
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("user")
	//db는 RoleType이 없음
	@Enumerated(EnumType.STRING)
	private RoleType role; //String 대신 Enum타입을 쓰면 어떤 데이터의 도메인을 만들 수 있음. //ADMIN, USER
	
	@CreationTimestamp //시간 자동입력
	private Timestamp createDate;
}
