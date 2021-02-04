package ru.geekbrains.course;

import ru.geekbrains.Member;

public class Running extends AbstractCourse {
    private String name = "running";
    private int distance;

    public Running (int distance) {
        this.distance = distance;
    }

    public Running () {
        this(200);
    }

    @Override
    protected void doIt(Member member) {
        member.setPassed(member.getMaxRunDistance() >= distance);
    }
}
