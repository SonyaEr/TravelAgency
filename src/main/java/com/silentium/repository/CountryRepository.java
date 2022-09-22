package com.silentium.repository;

import com.silentium.model.City;
import com.silentium.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("countryRepository")
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("select c from Country c where c.id=:id")
    Country findById(@Param("id") int id);

    @Query("select c from Country c where c.name=:name")
    Country findByName(@Param("name") String name);

    @Query("select c from Country c order by c.name")
    List<Country> findAll();

    @Query(value = "select * from Country c ORDER BY RAND() limit 10", nativeQuery = true)
    List<Country> findAllLimit();

}
