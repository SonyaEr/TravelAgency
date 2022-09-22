package com.silentium.repository;


import com.silentium.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select t from Person t where t.id=:id")
    Person findById(@Param("id") int id);

    @Query("select t.email from Person t where t.id=:id")
    String findByEmail(@Param("id") int id);

    @Query(value = "select p.* from Account a LEFT OUTER JOIN Person p ON a.fk_person_id = p.person_id WHERE a.account_id=:account_id LIMIT 1",  nativeQuery = true)
    Person findByAccountId(@Param("account_id") int id);

    @Query("select c from Person c order by c.surname, c.name")
    List<Person> findAll();

    @Query(value = "select * from Person c order by c.surname, c.name limit 3",nativeQuery = true)
    List<Person> findAllpage();

    List<Person> findByNameAndSurname(String name, String surname);

}