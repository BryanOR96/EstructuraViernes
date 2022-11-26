/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.StringTokenizer;


public class Date {

    private int day, moth, year;
    private char separator;
    public static char defaultSeparator='/';
    public Date(int day, int moth, int year) {
        this.day = day;
        this.moth = moth;
        this.year = year;
        separator=defaultSeparator;
    }

    public Date(String text, char separator) throws IllegalArgumentException {
        this.separator=separator;
        StringTokenizer cutter = new StringTokenizer(text.trim(), separator + "");
        if (cutter.countTokens() != 3) {
            throw new IllegalArgumentException("formato invalido : "+text);
        }
        try {
            day = Integer.parseInt(cutter.nextToken());
            moth = Integer.parseInt(cutter.nextToken());
            year = Integer.parseInt(cutter.nextToken());
        } catch (Exception e) {
             throw new IllegalArgumentException("formato invalido");
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMoth() {
        return moth;
    }

    public void setMoth(int moth) {
        this.moth = moth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return ""+day + separator + moth + separator + year ;
    }
    

}
