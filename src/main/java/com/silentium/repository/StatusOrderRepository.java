package com.silentium.repository;


import com.silentium.model.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("statusOrderRepository")
public interface StatusOrderRepository extends JpaRepository<StatusOrder, Long> {
    @Query("select s from StatusOrder s where s.id=:id")
    StatusOrder findById(@Param("id") int id);

    @Query("select s from StatusOrder s where s.name=:name")
    StatusOrder findByName(@Param("name") String name);

    @Query("select s from StatusOrder s order by s.name")
    List<StatusOrder> findAll();
}
