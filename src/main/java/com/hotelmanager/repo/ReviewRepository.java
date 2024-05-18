package com.hotelmanager.repo;

import com.hotelmanager.domain.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Integer>
{

    @Query("SELECT review FROM Review review WHERE review.hotelID = :hotelID")
    public List<Review> getReviewsForHotel(int hotelID);
}
