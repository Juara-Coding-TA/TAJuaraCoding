package com.juaracoding.tajuaracoding.pages.actions;

public interface CompositeAction {
    void execute();
    boolean isCompleted();
    String getResult();
}
