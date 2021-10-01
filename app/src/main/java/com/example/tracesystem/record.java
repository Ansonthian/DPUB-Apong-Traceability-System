package com.example.tracesystem;

public class record {
    private String datetime;
    private String mass;
    private String pH;
    private String comment;
    private String sapCollector;



    public record() {

    }

    public record(String datetime, String mass, String pH, String comment, String sapCollector){

        this.datetime = datetime;
        this.mass = mass;
        this.pH = pH;
        this.sapCollector = sapCollector;
        this.comment = comment;
    }


    public String getDatetime(){ return datetime; }

    public String getMass() { return mass; }

    public String getpH() { return pH; }

    public String getComment() { return comment; }

    public String getSapCollector() { return sapCollector; }
}
