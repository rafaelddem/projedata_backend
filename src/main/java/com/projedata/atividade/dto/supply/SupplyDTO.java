package com.projedata.atividade.dto.supply;

public class SupplyDTO {

    private int id;
    private String name;
    private int value;
    private int max_production;
    private int max_value;

    public SupplyDTO(int id, String name, int value, int max_production, int max_value){
        this.id = id;
        this.name = name;
        this.value = value;
        this.max_production = max_production;
        this.max_value = max_value;
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

    public int getMaxValue() {
        return this.max_value;
    }

    public void setMaxValue(int max_value) {
        this.max_value = max_value;
    }
}
