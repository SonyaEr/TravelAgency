package com.silentium.repository;

import com.silentium.model.TourDate;
import com.silentium.model.TourStructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("tourStructureRepository")
public interface TourStructureRepository extends JpaRepository<TourStructure, Long> {

    @Query("select t from TourStructure t where t.id=:id")
    TourStructure findById(@Param("id") int id);

    @Query("select t from TourStructure t where t.name=:name")
    TourStructure findByName(@Param("name") String name);

//    @Query(value = "select *\n" +
//            "from tour_structure\n" +
//            "where fk_tour_id = :id", nativeQuery = true)
    @Query("select t from TourStructure t where t.tour.id=:id")
    List<TourStructure> findByTourId(@Param("id") int id);

//    @Query("select t from TourStructure t where t.id=:id")
//    List<TourStructure> findByTourId(@Param("id") int id);

    @Query("select c from TourStructure c order by c.name")
    List<TourStructure> findAll();
}
