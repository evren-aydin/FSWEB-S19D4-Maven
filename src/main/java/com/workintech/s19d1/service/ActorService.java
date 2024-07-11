package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;

import java.util.List;

public interface ActorService {

    List<Actor> findAll();

    Actor findById(long id);

    Actor save(Actor actor);

    Actor remove(long id);
}
