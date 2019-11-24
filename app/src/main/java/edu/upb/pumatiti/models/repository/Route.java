package edu.upb.pumatiti.models.repository;

import java.util.List;

public class Route {

    private String uuid;

    private String name;

    private List<Stop> stopList;

    private List<Bus> busList;

    public Route(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
