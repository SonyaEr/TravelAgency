package com.silentium.repository;

import com.silentium.model.TourOperator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tourOperatorRepository")
public interface TourOperatorRepository extends JpaRepository<TourOperator, Long> {
    @Query("select t from TourOperator t where t.id=:id")
    TourOperator findById(@Param("id") int id);

    @Query("select t from TourOperator t where t.name=:name")
    TourOperator findByName(@Param("name") String name);

    @Query("select t from TourOperator t order by t.name")
    List<TourOperator> findAll();
}
