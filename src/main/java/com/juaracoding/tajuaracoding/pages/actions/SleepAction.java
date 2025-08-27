package com.juaracoding.tajuaracoding.pages.actions;

public class SleepAction implements CompositeAction {
    private long millis;
    private boolean completed = false;
    private String result = "";

    public SleepAction(long millis) {
        this.millis = millis;
    }

    public void execute() {
        try {
            Thread.sleep(millis);
            completed = true;
            result = "Slept for " + millis + " milliseconds";
        } catch (InterruptedException e) {
            completed = false;
            result = "Sleep interrupted: " + e.getMessage();
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getResult() {
        return result;
    }
}
