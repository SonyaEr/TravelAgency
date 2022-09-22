package com.silentium.repository;

import com.silentium.dto.TourDto;
import com.silentium.model.StatusVoucher;
import com.silentium.model.TourDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("tourDateRepository")
public interface TourDateRepository extends JpaRepository<TourDate, Long> {

    @Query("select c from TourDate c where c.id=:id")
    TourDate findById(@Param("id") int id);

    @Query("select c from TourDate c order by c.dateArrival")
    List<TourDate> findAll();

    @Query(value = "select *, min(price)\n" +
            "from tour_date\n" +
            "where date_arrival >= now()\n" +
            "group by fk_tour_id order by fk_tour_id limit 20", nativeQuery = true)
    List<TourDate> findAllSearch();

    @Query(value = "select *\n" +
            "from tour_date\n" +
            "where date_arrival >= now() and fk_tour_id = :tour_id\n" +
            "order by date_arrival", nativeQuery = true)
    List<TourDate> findByTourId(@Param("tour_id") int tour_id);

    @Query(value = "select *\n" +
            "from tour_date\n" +
            "where fk_tour_id = :tour_id\n" +
            "order by date_arrival", nativeQuery = true)
    List<TourDate> findByTourIdAll(@Param("tour_id") int tour_id);

    @Query(value = "select *, MATCH(t.name, t.description) AGAINST(:search_text_cont IN BOOLEAN MODE) as k\n" +
            "\n" +
            "from tour_date td\n" +
            "         left join tour t on td.fk_tour_id = t.tour_id\n" +
            "where (:quantity_day <= 0 or t.quantity_night = :quantity_day)\n" +
            "  and (:typetour_id = -1 or t.fk_type_tour_id = :typetour_id)\n" +
            "  and (:typefood_id = -1 or t.fk_type_food_id = :typefood_id)\n" +
            "  and (:typetransport_id = -1 or t.fk_type_transport_id = :typetransport_id)\n" +
            "  and (:touroperator_id = -1 or t.fk_tour_operator_id = :touroperator_id)\n" +
            "  and (td.price >= :price_min)\n" +
            "  and (:price_max = 0 or td.price <= :price_max)\n" +
            "  and (td.date_arrival >= now())\n" +
            "  and (:date_travel is null or td.date_arrival >= :date_travel)\n" +
            "  and (:search_text = '' or MATCH(t.name, t.description) AGAINST(:search_text_cont IN BOOLEAN MODE))\n" +
            "order by k desc, td.date_arrival\n" +
            "limit 20", nativeQuery = true)
    List<TourDate> findAllSearchByParameter(@Param("quantity_day") int quantity_day,
                                            @Param("typetour_id") int typetour_id,
                                            @Param("typefood_id") int typefood_id,
                                            @Param("typetransport_id") int typetransport_id,
                                            @Param("touroperator_id") int touroperator_id,
                                            @Param("price_min") int price_min,
                                            @Param("price_max") int price_max,
                                            @Param("date_travel") Date date_travel,
                                            @Param("search_text") String search_text,
                                            @Param("search_text_cont") String search_text_cont);
}
