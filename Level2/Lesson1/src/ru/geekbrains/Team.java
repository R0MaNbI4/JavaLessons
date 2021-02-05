package ru.geekbrains;

public class Team {
    private String name;
    private Member[] member;

    public Team (String name, Member...  member) {
        this.name = name;
        this.member = member;
    }

    public Member[] getMembers() {
        return member;
    }

    public void printPassedDistance() {
        for (Member member : this.member) {
            if (member.getPassed()) {
                System.out.println("Name: " + member.getName());
            }
        }
    }

    public void printInfo() {
        System.out.println(name);
        for (Member member : this.member) {
            System.out.println("Name: " + member.getName() + ", " +
                    "maxSwimDistance: " + member.getMaxSwimDistance());
        }
    }
}