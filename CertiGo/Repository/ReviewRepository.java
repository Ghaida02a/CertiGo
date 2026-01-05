package com.codeline.CertiGo.Repository;

import com.codeline.CertiGo.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    @Query(" SELECT r FROM Review r WHERE r.id=:id AND r.isActive=true")
    Review getReviewById(Integer id);

    @Query("SELECT r FROM Review r WHERE r.isActive=true AND r.id IN (:id) ")
    List<Review> getReviewById(List<Integer> id);
}
