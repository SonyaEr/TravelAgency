package com.silentium.repository;

import com.silentium.model.Account;
import com.silentium.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("select a from Account a where a.login=:login")
    Account findByLogin(@Param("login") String login);

    @Query(value = "select p.* from Account a LEFT OUTER JOIN Person p ON a.fk_person_id = p.person_id WHERE a.login=:login LIMIT 1",  nativeQuery = true)
    Person findPersonByLogin(@Param("login") String login);

    @Query("select a from Account a where a.person.id =:id")
    Person findByUserId(@Param("id") int id);

    @Query("select r.role from Account a left join Role r on a.role.id=r.id where a.login =:login")
    String findRoleByLogin(@Param("login") String login);
}