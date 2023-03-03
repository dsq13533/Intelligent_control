package com.djht.fourteenth_session.HJK;

public class water_ItemModel_h {
    private String waterName;//空调名称
    private String roomName;//房间名称
    private boolean waterState;//开机状态

    public String getWaterName() {
        return waterName;
    }

    public void setWaterName(String waterName) {
        this.waterName = waterName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isWaterState() {
        return waterState;
    }

    public void setWaterState(boolean waterState) {
        this.waterState = waterState;
    }

    @Override
    public String toString() {
        return "water_ItemModel_h{" +
                "waterName='" + waterName + '\'' +
                ", roomName='" + roomName + '\'' +
                ", waterState=" + waterState +
                '}';
    }
}
