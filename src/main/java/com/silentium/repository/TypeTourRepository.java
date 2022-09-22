package com.silentium.repository;

import com.silentium.model.TypeTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("typeTourRepository")
public interface TypeTourRepository extends JpaRepository<TypeTour, Long> {
    @Query("select t from TypeTour t where t.id=:id")
    TypeTour findById(@Param("id") int id);

    @Query("select t from TypeTour t where t.name=:name")
    TypeTour findByName(@Param("name") String name);

    @Query("select c from TypeTour c order by c.name")
    List<TypeTour> findAll();
}
