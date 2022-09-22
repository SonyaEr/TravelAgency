package com.silentium.repository;

import com.silentium.model.TypeTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("typeTransportRepository")
public interface TypeTransportRepository extends JpaRepository<TypeTransport, Long> {

    @Query("select t from TypeTransport t where t.id=:id")
    TypeTransport findById(@Param("id") int id);

    @Query("select t from TypeTransport t where t.name=:name")
    TypeTransport findByName(@Param("name") String name);

    @Query("select c from TypeTransport c order by c.name")
    List<TypeTransport> findAll();
}
