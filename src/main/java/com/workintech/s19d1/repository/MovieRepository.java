package com.workintech.s19d1.repository;

import com.workintech.s19d1.dto.MovieResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie,Long> {



    @Query("DELETE FROM Movie m WHERE m.id=:id")
    Movie remove(long id);


}
