package org.example.spring_realization_transactions.repository;

import org.example.spring_realization_transactions.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Integer> {

    Optional<UsersEntity> findByUsername(String username);

//    @Query("select u from UsersEntity u where u.firstName = :name and u.age = :age")
//    List<UsersEntity> findAllByFirstNameAndAge(@Param("name") String firstName, @Param("age") Integer age);
//    List<UsersNameView> findAllByAgeGreaterThan(Integer age);
}
