package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY) //auto_incresement
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;

	@ManyToOne //1게시글:여러 리플
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne //1유저:여러 답변
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
