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

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends Repository<User, Integer> {

	@Query("SELECT categories FROM QuestionCategory categories ORDER BY categories.name")
	@Transactional(readOnly = true)
	List<QuestionCategory> findQuestionCategories();

	@Query("SELECT DISTINCT user FROM User user left join  user.questions WHERE user.lastName LIKE :lastName% ")
	@Transactional(readOnly = true)
	Page<User> findByLastName(@Param("lastName") String lastName, Pageable pageable);
	@Query("SELECT user FROM User user left join fetch user.questions WHERE user.id =:id")
	@Transactional(readOnly = true)
	User findById(@Param("id") Integer id);

	void save(User user);



}
