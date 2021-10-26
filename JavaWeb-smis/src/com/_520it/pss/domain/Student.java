package com._520it.pss.domain;

import lombok.AllArgsConstructor;
import lombok.Data;		// automatically generates setters, getters, and toString method
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("jdbcdemo.t_student_02")
public class Student {
	@ID
	private Long id;
	@Column("name2")
	private String name;
	@Column("age22222")
	private Integer age;
}
