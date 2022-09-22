package com.silentium.repository;

import com.silentium.dto.TourDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourDtoRepository {

    @Query("select t from Tour t where t.id=:id")
    TourDto findById(@Param("id") int id);

    @Query("select t from Tour t where t.name=:name")
    TourDto findByName(@Param("name") String name);

    @Query(value = "select * from tour t"
            +" join tour_date td"
            +" where MATCH(t.name, t.description) AGAINST(?1) and td.date_arrival > now() order by date_arrival ", nativeQuery = true)
    List<TourDto> searchByTourLike(String keyword);

    @Query(value = "select *\n" +
            "from tour t, tour_date td \n" +
            "         left join (select tour_date_id, price\n" +
            "                    from tour_date\n" +
            "                    where date_arrival > now()) td\n" +
            "                   ON t.tour_id = td.fk_tour_id\n" +
            "where not tour_date_id is null order by date_arrival ", nativeQuery = true)
    List<TourDto> findAll();

}
