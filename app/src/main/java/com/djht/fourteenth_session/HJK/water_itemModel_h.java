package com.djht.fourteenth_session.HJK;

public class water_itemModel_h {
    private String airName;//热水器名称
    private String roomName;//房间名称
    private String state;//开机状态
    private String temperature;
    private String number;

    public water_itemModel_h(){};
    public water_itemModel_h(String number, String airName, String roomName, String state, String temperature) {
        this.number = number;
        this.airName = airName;
        this.roomName = roomName;
        this.state = state;
        this.temperature = temperature;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
        return "itemModel_h{" +
                "airName='" + airName + '\'' +
                ", roomName='" + roomName + '\'' +
                ", state='" + state + '\'' +
                ", temperature='" + temperature + '\'' +
                ", number=" + number +
                '}';
    }
}
