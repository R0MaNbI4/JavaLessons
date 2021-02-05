package ru.geekbrains.course;

import ru.geekbrains.Member;
import ru.geekbrains.Team;

public abstract class AbstractCourse implements Course {
    public void doIt(Team team) {
        for (Member member : team.getMembers()) {
            doIt(member);
        }
    }

    protected abstract void doIt(Member member);
}
