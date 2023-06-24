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
package org.grabowskiandgajda.thinktankcorp.user;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users/{userId}")
class QuestionController {

	private static final String VIEWS_QUESTIONS_CREATE_OR_UPDATE_FORM = "questions/createOrUpdateQuestionForm";

	private final UserRepository users;

	public QuestionController(UserRepository users) {
		this.users = users;
	}


	@ModelAttribute("categories")
	public Collection<QuestionCategory> populateQuestionCategories() {
		return this.users.findQuestionCategories();
	}

	@ModelAttribute("user")
	public User findUser(@PathVariable("userId") int userId) {
		return this.users.findById(userId);
	}

	@ModelAttribute("question")
	public Question findQuestion(@PathVariable("userId") int userId,
			@PathVariable(name = "questionId", required = false) Integer questionId) {
		return questionId == null ? new Question() : this.users.findById(userId).getQuestion(questionId);
	}

	@InitBinder("user")
	public void initUserBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}


	@GetMapping("/questions/new")
	public String initCreationForm(User user, ModelMap model) {
		Question question = new Question();
		user.addQuestion(question);
		model.put("question", question);
		return VIEWS_QUESTIONS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/questions/new")
	public String processCreationForm(User user, @Valid Question question, BindingResult result, ModelMap model) {
		if (StringUtils.hasLength(question.getName()) && question.isNew() && user.getQuestion(question.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}

		user.addQuestion(question);
		if (result.hasErrors()) {
			model.put("question", question);
			return VIEWS_QUESTIONS_CREATE_OR_UPDATE_FORM;
		}

		this.users.save(user);
		return "redirect:/users/{userId}";
	}

	@GetMapping("/questions/{questionId}/edit")
	public String initUpdateForm(User user, @PathVariable("questionId") int questionId, ModelMap model) {
		Question question = user.getQuestion(questionId);
		model.put("question", question);
		return VIEWS_QUESTIONS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/questions/{questionId}/edit")
	public String processUpdateForm(@Valid Question question, BindingResult result, User user, ModelMap model) {
		if (result.hasErrors()) {
			model.put("question", question);
			return VIEWS_QUESTIONS_CREATE_OR_UPDATE_FORM;
		}

		user.addQuestion(question);
		this.users.save(user);
		return "redirect:/users/{userId}";
	}

}
