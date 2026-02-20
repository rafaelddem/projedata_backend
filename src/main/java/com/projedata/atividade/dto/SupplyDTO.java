package com.projedata.atividade.dto;

import java.text.DecimalFormat;

public class SupplyDTO {

    private int id;
    private String name;
    private int value;
    private int max_production;

    public SupplyDTO(int id, String name, int value, int max_production){
        this.id = id;
        this.name = name;
        this.value = value;
        this.max_production = max_production;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMaxProduction() {
        return this.max_production;
    }

    public void setMaxProduction(int max_production) {
        this.max_production = max_production;
    }

    public double getMaxValue() {
        double valueFormated = this.max_production * ((double) this.value / 100);
        DecimalFormat formater = new DecimalFormat("#.##");

        return Double.parseDouble(formater.format(valueFormated));
    }
}
