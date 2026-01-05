package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
}
