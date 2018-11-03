package com.example.zaki_berouk.adaptabtp.model;

public class Staff {
    private String name;
    private String role;
    private String present;
    private String phone;

    public Staff(){
        this.name = "";
        this.role = "";
        this.present = "";
        this.phone = "";
    }

    public Staff(String name, String role, String present, String phone) {
        this.name = name;
        this.role = role;
        this.present = present;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
