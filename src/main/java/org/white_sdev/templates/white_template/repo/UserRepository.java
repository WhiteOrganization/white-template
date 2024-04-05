package org.white_sdev.templates.white_template.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.white_sdev.templates.white_template.model.persistence.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomUserRepository {
    User findByFirstName(String firstName);
    @Query(value = "CALL calculateSum(:num1, :num2)", nativeQuery = true)
    int callQueriedStoredProcedure(@Param("num1") int num1, @Param("num2") int num2);
}
