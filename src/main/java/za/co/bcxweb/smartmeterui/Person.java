/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.bcxweb.smartmeterui;

import javax.annotation.ManagedBean;


/**
 *
 * @author Leole
 */
@ManagedBean
public class Person {
    private String id;
    private int year;
    private String color;

    public Person(String randomId, int randomYear, String randomColor) {
        id = randomId;
        year = randomYear;
        color = randomColor;
    }

    /**
     * @return the id
     */
    public String getId() {
        return "7845215014";
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return 1990;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return "Black";
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }
}
