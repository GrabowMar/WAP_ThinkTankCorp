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
package org.springframework.samples.thinktankcorp.user;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
class AnswerController {

	private final UserRepository users;

	public AnswerController(UserRepository users) {
		this.users = users;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("answer")
	public Answer loadQuestionWithAnswer(@PathVariable("userId") int userId, @PathVariable("questionId") int questionId,
			Map<String, Object> model) {
		User user = this.users.findById(userId);

		Question question = user.getQuestion(questionId);
		model.put("question", question);
		model.put("user", user);

		Answer answer = new Answer();
		question.addAnswer(answer);
		return answer;
	}

	@GetMapping("/users/{userId}/questions/{questionId}/answers/new")
	public String initNewAnswerForm() {
		return "questions/createOrUpdateAnswerForm";
	}

	@PostMapping("/users/{userId}/questions/{questionId}/answers/new")
	public String processNewAnswerForm(@ModelAttribute User user, @PathVariable int questionId, @Valid Answer answer,
			BindingResult result) {
		if (result.hasErrors()) {
			return "questions/createOrUpdateAnswerForm";
		}

		user.addAnswer(questionId, answer);
		this.users.save(user);
		return "redirect:/users/{userId}";
	}

}
