package com.silentium.repository;

import com.silentium.dto.ParamSearchTourDto;
import com.silentium.model.Tour;
import com.silentium.model.TourDate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepositoryCustom {

    List<Tour> findSpecial(ParamSearchTourDto param);

    List<TourDate> findByTourDateByTourId(@Param("id") int id);
}