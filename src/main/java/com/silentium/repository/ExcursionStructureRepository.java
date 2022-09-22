package com.silentium.repository;

import com.silentium.model.Excursion;
import com.silentium.model.ExcursionStructure;
import com.silentium.model.TourStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("excursionStructureRepository")
public interface ExcursionStructureRepository extends JpaRepository<ExcursionStructure, Long> {
    @Query("select e.tourStructure from ExcursionStructure e where e.excursion.id=:id")
    List<TourStructure> findByExcursionId(@Param("id") int id);

    @Query("select e.excursion from ExcursionStructure e where e.tourStructure.id=:id")
    List<Excursion> findByTourStructureId(@Param("id") int id);
}
