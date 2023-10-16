package org.example;

public abstract class Poketmon {

    private String name;

    private PoketmonType type;

    public Poketmon() {
    }

    public Poketmon(PoketmonType type) {
        this.type = type;
    }

    public Poketmon(String name) {
        this.name = name;
    }

    public Poketmon(String name, PoketmonType type) {
        this.name = name;
        this.type = type;
    }
}
