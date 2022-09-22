package com.silentium.repository;

import com.silentium.model.City;
import com.silentium.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cityRepository")
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c where c.id=:id")
    City findById(@Param("id") int id);

    @Query("select c from City c where c.name=:name")
    City findByName(@Param("name") String name);

    @Query("select c from City c order by c.name")
    List<City> findAll();

    @Query("select c.name from City c join Country co where c.country.name=:name order by co.name")
   City findByCountryName(@Param("name") String name);


}