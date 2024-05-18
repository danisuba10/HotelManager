package com.hotelmanager.service;

import com.hotelmanager.domain.Review;
import com.hotelmanager.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findAll()
    {
        List<Review> reviews = new ArrayList<Review>();
        Iterable<Review> iter = this.reviewRepository.findAll();
        for(Review review : iter)
        {
            reviews.add(review);
        }
        return reviews;
    }

    public void add(int roomID, int hotelID, int points, String message)
    {
        Review review = new Review(roomID, hotelID, points, message);
        this.reviewRepository.save(review);
    }

    public List<Review> getReviewsForHotel(int hotelID)
    {
        List<Review> reviews = new ArrayList<>();
        Iterable<Review>  iter = reviewRepository.getReviewsForHotel(hotelID);
        for(Review review : iter)
        {
            reviews.add(review);
        }
        return reviews;
    }

}
