package com.workintech.s19d1.dto;

import com.workintech.s19d1.entity.Actor;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<ActorResponse> actorResponseConvert(List<Actor> actors){
        List<ActorResponse> actorResponses = new ArrayList<>();

        for(Actor actor:actors){
            actorResponses.add(new ActorResponse(actor.getFirstName(),actor.getLastName(),actor.getGender()));
        }

        return actorResponses;
    }

    public static ActorResponse actorCreateResponseConvert(Actor savedActor){
        return new ActorResponse(savedActor.getFirstName(),savedActor.getLastName(),savedActor.getGender());
    }
}
