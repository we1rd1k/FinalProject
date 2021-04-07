package ru.innopolis.at.api;

public enum Endpoints {
    AUTHORIZED("Account/v1/Authorized"),
    LOGIN("Account/v1/Login"),
    GENERATE("Account/v1/GenerateToken"),
    BOOKS("/BookStore/v1/Books");

    private String endPoint;

    Endpoints(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }
}
