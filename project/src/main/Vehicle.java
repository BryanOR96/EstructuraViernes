/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import ObjectSerializator.*;


public class Vehicle implements Saveable {

    private String number="000000";
    private String mark="", model="", color="negro", extra="";
    private int cilinderCapacity, fuelType, capacity;
    private double price;

    public Vehicle() {
    }

    public Vehicle(String number, String mark, String model, String color, String extra, int cilinderCapacity, int fuelType, int capacity, double price) {
        this.number = number;
        this.mark = mark;
        this.model = model;
        this.color = color;
        this.extra = extra;
        this.cilinderCapacity = cilinderCapacity;
        this.fuelType = fuelType;
        this.capacity = capacity;
        this.price = price;
    }public void set(Vehicle other){
        this.number = other.number;
        this.mark =  other.mark;
        this.model =  other.model;
        this.color =  other.color;
        this.extra =  other.extra;
        this.cilinderCapacity =  other.cilinderCapacity;
        this.fuelType =  other.fuelType;
        this.capacity =  other.capacity;
        this.price =  other.price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getCilinderCapacity() {
        return cilinderCapacity;
    }

    public void setCilinderCapacity(int cilinderCapacity) {
        this.cilinderCapacity = cilinderCapacity;
    }

    public int getFuelType() {
        return fuelType;
    }

    public void setFuelType(int fuelType) {
        this.fuelType = fuelType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void load(DataReader reader) throws ReadException {
        setNumber(reader.readString());
        setMark(reader.readString());
        setModel(reader.readString());
        setColor(reader.readString());
        setCilinderCapacity(reader.readInt());
        setCapacity(reader.readInt());
        setFuelType(reader.readInt());
        setPrice(reader.readDouble());
        setExtra(reader.readString());
    }

    @Override
    public void print(DataWriter writer) throws WriteException {
        writer.writeString(number);
        writer.writeString(mark);
        writer.writeString(model);
        writer.writeString(color);
        writer.writeInt(cilinderCapacity);
        writer.writeInt(capacity);
        writer.writeInt(fuelType);
        writer.writeDouble(price);
        writer.writeString(extra);
    }

    @Override
    public String toString() {
        return number;
    }
    

}
