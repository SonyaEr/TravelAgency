package com.silentium.repository;

import com.silentium.model.TypeFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("typeFoodRepository")
public interface TypeFoodRepository extends JpaRepository<TypeFood, Long> {
    @Query("select t from TypeFood t where t.id=:id")
    TypeFood findById(@Param("id") int id);

    @Query("select t from TypeFood t where t.name=:name")
    TypeFood findByName(@Param("name") String name);

    @Query("select c from TypeFood c order by c.name")
    List<TypeFood> findAll();
}
