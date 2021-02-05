package ru.geekbrains;

public class Member {
    private String name;
    private int maxSwimDistance;
    private int maxRunDistance;
    private boolean isPassed;

    public Member (String name, int maxSwimDistance, int maxRunDistance) {
        this.name = name;
        this.maxSwimDistance = maxSwimDistance;
        this.maxRunDistance = maxRunDistance;
    }

    public int getMaxSwimDistance() {
        return maxSwimDistance;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public void setPassed(boolean passed) {
        isPassed = passed;
    }

    public boolean getPassed() {
        return isPassed;
    }

    public String getName() {
        return name;
    }
}
