/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectperformance;

import java.util.Date;

/**
 *
 * @author Michiel
 */
public class Person {
    private int id;
    private String name;
    private Date lesson;
    
    public Person() {}

    public Person(int id, String name, Date lesson) {
        this.id = id;
        this.name = name;
        this.lesson = lesson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLesson() {
        return lesson;
    }

    public void setLesson(Date lesson) {
        this.lesson = lesson;
    }
    
    @Override
    public String toString() {
        return "Tennis player: " + getName() + "\n needs to follow his lesson on " + getLesson();
    }
    
}
