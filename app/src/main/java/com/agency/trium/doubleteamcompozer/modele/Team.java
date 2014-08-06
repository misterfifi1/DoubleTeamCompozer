package com.agency.trium.doubleteamcompozer.modele;

import com.agency.trium.doubleteamcompozer.utils.RandomUtils;

import java.io.Serializable;

/**
 * Created by dev-w8-gfi on 06/08/2014.
 */
public class Team implements Serializable{

    private String name;
    private int colorId;

    public Team(){
        this.colorId = RandomUtils.randomColor();
    }

    public Team(String name){
        this.name = name;
        this.colorId = RandomUtils.randomColor();
    }

    public Team(String name,int colorId){
        this.name = name;
        this.colorId = colorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}
