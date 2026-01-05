package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {

    @Query(" SELECT op FROM Option op WHERE op.id=:id AND op.isActive=true")
    Option getOptionById(Integer id);

    @Query("SELECT op FROM Option op WHERE op.isActive=true AND op.id IN (:id) ")
    List<Option> findOptionById(List<Integer> id);
}
