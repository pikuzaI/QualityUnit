package com.company;

public class Line {
    private char character;
    private double service_id;
    private double question_type_id;
    private char response_type;

    Line() { }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public double getService_id() {
        return service_id;
    }

    public void setService_id(double service_id) {
        this.service_id = service_id;
    }

    public double getQuestion_type_id() {
        return question_type_id;
    }

    public void setQuestion_type_id(double question_type_id) {
        this.question_type_id = question_type_id;
    }

    public char getResponse_type() {
        return response_type;
    }

    public void setResponse_type(char response_type) {
        this.response_type = response_type;
    }
}
