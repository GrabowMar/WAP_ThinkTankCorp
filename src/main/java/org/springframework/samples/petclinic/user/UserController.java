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

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

@Controller
class UserController {

	private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "users/createOrUpdateUserForm";

	private final UserRepository users;

	public UserController(UserRepository clinicService) {
		this.users = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("user")
	public User findUser(@PathVariable(name = "userId", required = false) Integer userId) {
		return userId == null ? new User() : this.users.findById(userId);
	}

	@GetMapping("/users/new")
	public String initCreationForm(Map<String, Object> model) {
		User user = new User();
		model.put("user", user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/users/new")
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		}

		this.users.save(user);
		return "redirect:/users/" + user.getId();
	}

	@GetMapping("/users/find")
	public String initFindForm() {
		return "users/findUsers";
	}

	@GetMapping("/users")
	public String processFindForm(@RequestParam(defaultValue = "1") int page, User user, BindingResult result,
			Model model) {
		// allow parameterless GET request for /users to return all records
		if (user.getLastName() == null) {
			user.setLastName(""); // empty string signifies broadest possible search
		}

		// find users by last name
		Page<User> usersResults = findPaginatedForUsersLastName(page, user.getLastName());
		if (usersResults.isEmpty()) {
			// no users found
			result.rejectValue("lastName", "notFound", "not found");
			return "users/findUsers";
		}

		if (usersResults.getTotalElements() == 1) {
			// 1 user found
			user = usersResults.iterator().next();
			return "redirect:/users/" + user.getId();
		}

		// multiple users found
		return addPaginationModel(page, model, usersResults);
	}

	private String addPaginationModel(int page, Model model, Page<User> paginated) {
		model.addAttribute("listUsers", paginated);
		List<User> listUsers = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listUsers", listUsers);
		return "users/usersList";
	}

	private Page<User> findPaginatedForUsersLastName(int page, String lastname) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return users.findByLastName(lastname, pageable);
	}

	@GetMapping("/users/{userId}/edit")
	public String initUpdateUserForm(@PathVariable("userId") int userId, Model model) {
		User user = this.users.findById(userId);
		model.addAttribute(user);
		return VIEWS_USER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/users/{userId}/edit")
	public String processUpdateUserForm(@Valid User user, BindingResult result,
			@PathVariable("userId") int userId) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_OR_UPDATE_FORM;
		}

		user.setId(userId);
		this.users.save(user);
		return "redirect:/users/{userId}";
	}

	@GetMapping("/users/{userId}")
	public ModelAndView showUser(@PathVariable("userId") int userId) {
		ModelAndView mav = new ModelAndView("users/userDetails");
		User user = this.users.findById(userId);
		mav.addObject(user);
		return mav;
	}

}
