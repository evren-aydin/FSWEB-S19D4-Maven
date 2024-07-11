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
public class ActorController {

    private ActorService actorService;

    private MovieService movieService;

    @Autowired
    public ActorController(ActorService actorService, MovieService movieService) {
        this.actorService = actorService;
        this.movieService = movieService;
    }

    @GetMapping("/actors")
    public List<ActorResponse> findAll(){
        List<Actor> actors = actorService.findAll();
        List<ActorResponse> actorResponseList = new ArrayList<>();
        for (Actor actor:actors){
            actorResponseList.add(new ActorResponse(actor.getFirstName(), actor.getLastName(), actor.getGender()));
        }
        return actorResponseList;
    }

    @GetMapping("/actors/{id}")
    public ActorResponse findById(@PathVariable long id){
        Actor actor= actorService.findById(id);

        return new ActorResponse(actor.getFirstName(),actor.getLastName(),actor.getGender());
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
    public ActorResponse uptade(@RequestBody Actor actor,@PathVariable long id){
        Actor foundActor = actorService.findById(id);

       foundActor.setMovies(actor.getMovies());
       foundActor.setFirstName(actor.getFirstName());
       foundActor.setLastName(actor.getLastName());
       foundActor.setGender(actor.getGender());
       foundActor.setBirthDate(actor.getBirthDate());
        actorService.save(foundActor);

        return new ActorResponse(foundActor.getFirstName(), foundActor.getLastName(), foundActor.getGender());



    }


    @DeleteMapping("/actors/{id}")
    public ActorResponse remove(@PathVariable long id){
        Actor deletedActor = actorService.remove(id);
        return new ActorResponse(deletedActor.getFirstName(), deletedActor.getLastName(), deletedActor.getGender());

    }
}
