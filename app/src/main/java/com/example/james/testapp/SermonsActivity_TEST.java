package com.example.james.testapp;

public class SermonsActivity_TEST {

    private String name;
    private String speaker;
    private int sermon;

    public SermonsActivity_TEST(String name, String speaker, int sermon){


        this.name = name;
        this.speaker = speaker;
        this.sermon = sermon;

    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSpeaker(){
        return speaker;
    }

    public void setSpeaker(String speaker){
        this.speaker = speaker;
    }

    public int getSermon(){
        return sermon;
    }

    public void setSermon(int sermon){
        this.sermon = sermon;
    }

}


