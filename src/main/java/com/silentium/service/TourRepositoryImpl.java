package com.silentium.service;

import com.silentium.dto.ParamSearchTourDto;
import com.silentium.model.Tour;
import com.silentium.model.TourDate;
import com.silentium.repository.TourRepositoryCustom;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class TourRepositoryImpl implements TourRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    public List<Tour> findSpecial(ParamSearchTourDto param) {
        List listtour = em.createNativeQuery(
                "select *" +
                        " from tour t" +
                        "where t.quantity_night = :quantity_day" +
                        "and where t.fk_typetour.id = :typetour_id"
                , Tour.class)
                .setParameter("quantity_day",param.getQuantity_day())
                .setParameter("typetour_id",param.getTypetour_id())
                .getResultList();
        return listtour;
    }

    public List<TourDate> findByTourDateByTourId(int id) {
        List listtourdate = em.createNativeQuery(
                        "select *" +
                                " from tour_date td" +
                                "where td.fk_tour_id = :fk_tour_id"
                        , TourDate.class)
                .setParameter("fk_tour_id",id)
                .getResultList();
        return listtourdate;
    }

}