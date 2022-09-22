package com.silentium.repository;

import com.silentium.dto.TourDto;
import com.silentium.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tourRepository")
public interface TourRepository extends JpaRepository<Tour, Integer>{

    @Query("select t from Tour t where t.id=:id")
    Tour findById(@Param("id") int id);

    @Query("select t from Tour t where t.name=:name")
    Tour findByName(@Param("name") String name);

    @Query(value = "select * from tour t", nativeQuery = true)
    List<Tour> findAll();

    @Query(value = "select * from tour t order by tour_id desc limit :start,:size ", nativeQuery = true)
    List<Tour> findAll(@Param("start") int start,
                       @Param("size") int size);

    @Query(value = "select * from tour t"
            +" join tour_date td"
            +" where MATCH(t.name, t.description) AGAINST(?1) and td.date_arrival > now()", nativeQuery = true)
    List<Tour> searchByTourLike(String keyword);

    @Query(value = "select *\n" +
            "from tour t\n" +
            "         left join (select tour_date_id, price\n" +
            "                    from tour_date\n" +
            "                    where date_arrival > now()) td\n" +
            "                   ON t.tour_id = td.tour_date_id\n" +
            "where not tour_date_id is null", nativeQuery = true)
    List<TourDto> findAllforSearch();


}
