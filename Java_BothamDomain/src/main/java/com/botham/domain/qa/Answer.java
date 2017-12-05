package com.botham.domain.qa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "answers")
public class Answer implements Serializable {

	private static final long serialVersionUID = 7433754875115883083L;

	public Answer() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "answer")
	private String answer;


	@Column(name = "correct")
	private String correct;
	
	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public String getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answer=" + answer + ", questionId=" + questionId + "]";
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Column(name = "question_id")
	private Integer questionId;

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

}
