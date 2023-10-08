package org.example;

import org.example.polymorphism.Animal;
import org.example.polymorphism.Cat;
import org.example.polymorphism.Monkey;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        Animal cat = new Cat();
        Animal monkey = new Monkey();
        cat.울다();
        monkey.울다();
    }
}