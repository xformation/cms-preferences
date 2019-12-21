package com.synectiks.pref.repository.search;

import com.synectiks.pref.domain.User;
import com.synectiks.pref.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends JPASearchRepository<User, Long> {
}
