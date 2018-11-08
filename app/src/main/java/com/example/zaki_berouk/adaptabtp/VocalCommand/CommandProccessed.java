package com.example.zaki_berouk.adaptabtp.VocalCommand;

import com.example.zaki_berouk.adaptabtp.model.Staff;

import java.util.List;

public class CommandProccessed {
    private String command;
    private List<Staff> targets;
    private String content;

    public CommandProccessed() {

    }

    public CommandProccessed(String command, List<Staff> targets, String content) {
        this.command = command;
        this.targets = targets;
        this.content = content;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public List<Staff> getTargets() {
        return targets;
    }

    public void setTargets(List<Staff> targets) {
        this.targets = targets;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
