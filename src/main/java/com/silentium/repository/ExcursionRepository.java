package com.silentium.repository;

import com.silentium.model.Excursion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("excursionRepository")
public interface ExcursionRepository extends JpaRepository<Excursion, Long> {
    @Query("select c from Excursion c where c.id=:id")
    Excursion findById(@Param("id") int id);

    @Query("select c from Excursion c order by c.name")
    List<Excursion> findAll();
}
