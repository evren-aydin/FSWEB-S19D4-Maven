package com.workintech.s19d1.controller;


import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.dto.Converter;
import com.workintech.s19d1.dto.MovieResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/workintech")
public class MovieController {

    private MovieService movieService;
    private ActorService actorService;

    @Autowired
    public MovieController(MovieService movieService, ActorService actorService) {
        this.movieService = movieService;
        this.actorService = actorService;
    }

    @GetMapping("/movies")
    public List<MovieResponse> findAll(){
       List<Movie> movies = movieService.findAll();
       List<MovieResponse> movieResponseList = new ArrayList<>();
       for (Movie movie:movies){
           movieResponseList.add(new MovieResponse(movie.getName(),movie.getDirectorName(),movie.getRating()));
       }
       return movieResponseList;
    }

    @GetMapping("/movies/{id}")
    public MovieResponse findById(@PathVariable long id){
       Movie movie= movieService.findById(id);

       return new MovieResponse(movie.getName(),movie.getDirectorName(),movie.getRating());
    }

    @PostMapping("/movies")
    public ActorResponse save(@RequestBody ActorRequest actorRequest){
        Actor actor =actorRequest.getActor();
        List<Movie> movies = actorRequest.getMovies();

        for (Movie movie: movies){
            actor.addMovie(movie);
        }
        Actor savedActor= actorService.save(actor);

        return Converter.actorCreateResponseConvert(savedActor);

    }

    @PutMapping("/{id}")
    public MovieResponse uptade(@RequestBody Movie movie,@PathVariable long id){
        Movie foundMovie = movieService.findById(id);

        foundMovie.setName(movie.getName());
        foundMovie.setRating(movie.getRating());
        foundMovie.setDirectorName(movie.getDirectorName());
        foundMovie.setReleaseDate(movie.getReleaseDate());
        foundMovie.setActors(movie.getActors());
        movieService.save(foundMovie);

        return new MovieResponse(foundMovie.getName(), foundMovie.getDirectorName(), foundMovie.getRating());

    }


    @DeleteMapping("/movies/{id}")
    public MovieResponse remove(@PathVariable long id){
        Movie deletecMovie = movieService.remove(id);
        return new MovieResponse(deletecMovie.getName(),deletecMovie.getDirectorName(),deletecMovie.getRating());

    }




}
