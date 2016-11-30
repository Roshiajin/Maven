package com.epam.maven.model.history;

/**
 * Created by Alexander_Gaptullin on 11/18/2016.
 */
public class Key {

    private int number;
    private String name;

    public Key() {
    }

    public Key(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Key key = (Key) o;

        if (number != key.number) return false;
        return name.equals(key.name);
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Key{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
