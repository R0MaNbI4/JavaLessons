package ru.geekbrains.course;

import ru.geekbrains.Member;

public class Swimming extends AbstractCourse {
    private int distance;

    public Swimming (int distance) {
        this.distance = distance;
    }

    public Swimming () {
        this(50);
    }

    @Override
    protected void doIt(Member member) {
        member.setPassed(member.getMaxSwimDistance() >= distance);
    }
}
