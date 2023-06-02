/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.model.Person;
import org.springframework.util.Assert;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User extends Person {

	@Column(name = "email")
	@NotEmpty
	private String email;

	@Column(name = "description")
	@NotEmpty
	private String description;

	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	private List<Question> questions = new ArrayList<>();

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String address) {
		this.email = address;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void addQuestion(Question question) {
		if (question.isNew()) {
			getQuestions().add(question);
		}
	}


	public Question getQuestion(String title) {
		return getQuestion(title, false);
	}

	/**
	 * Return the Pet with the given id, or null if none found for this Owner.
	 * @param id to test
	 * @return a pet if pet id is already in use
	 */
	public Question getQuestion(Integer id) {
		for (Question question : getQuestions()) {
			if (!question.isNew()) {
				Integer compId = question.getId();
				if (compId.equals(id)) {
					return question;
				}
			}
		}
		return null;
	}


	public Question getQuestion(String title, boolean ignoreNew) {
		title = title.toLowerCase();
		for (Question question : getQuestions()) {
			if (!ignoreNew || !question.isNew()) {
				String compName = question.getName();
				compName = compName == null ? "" : compName.toLowerCase();
				if (compName.equals(title)) {
					return question;
				}
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this).append("id", this.getId())
			.append("new", this.isNew())
			.append("lastName", this.getLastName())
			.append("firstName", this.getFirstName())
			.append("email", this.email)
			.append("description", this.description)
			.append("telephone", this.telephone)
			.toString();
	}

	public void addAnswer(Integer questionId, Answer answer) {

		Assert.notNull(questionId, "Pet identifier must not be null!");
		Assert.notNull(answer, "Visit must not be null!");

		Question question = getQuestion(questionId);

		Assert.notNull(question, "Invalid Pet identifier!");

		question.addAnswer(answer);
	}

}
