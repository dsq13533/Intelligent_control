package com.djht.fourteenth_session.HJK;

public class air_ItemModel_h {
    private String airName;//空调名称
    private String roomName;//房间名称
    private String airState;//开机状态
    private String temperature;
    private String number;

    public air_ItemModel_h(String airName, String roomName, String airState, String temperature, String number) {
        this.airName = airName;
        this.roomName = roomName;
        this.airState = airState;
        this.temperature = temperature;
        this.number = number;
    }

    public String getAirName() {
        return airName;
    }

    public void setAirName(String airName) {
        this.airName = airName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getAirState() {
        return airState;
    }

    public void setAirState(String airState) {
        this.airState = airState;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "air_ItemModel_h{" +
                "airName='" + airName + '\'' +
                ", roomName='" + roomName + '\'' +
                ", airState='" + airState + '\'' +
                ", temperature='" + temperature + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
