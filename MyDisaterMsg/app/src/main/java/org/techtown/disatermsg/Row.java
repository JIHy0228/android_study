package org.techtown.disatermsg;

public class Row {
    String location_id;
    String create_date;
    String  location_name;
    String msg;

    public Row(String location_id, String create_date, String location_name, String msg){

        this.location_id = location_id;
        this.create_date = create_date;
        this.location_name = location_name;
        this.msg = msg;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
