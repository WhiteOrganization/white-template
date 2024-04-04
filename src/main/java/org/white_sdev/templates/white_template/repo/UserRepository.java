package org.white_sdev.templates.white_template.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.white_sdev.templates.white_template.model.persistence.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByFirstName(String firstName);
}
