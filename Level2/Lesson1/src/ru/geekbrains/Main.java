package ru.geekbrains;

import ru.geekbrains.course.Course;
import ru.geekbrains.course.Running;
import ru.geekbrains.course.Swimming;

public class Main {
    public static void main(String[] args) {
        Team team = new Team(
           "Lorem",
            new Member("Kolya", 47, 183),
            new Member("Vasya", 55, 201),
            new Member("Nadya", 63, 196),
            new Member("Vlad", 49, 204)
        );

        Course[] courses = {
            new Swimming(),
            new Running()
        };

        System.out.println("Team Info:");
        team.printInfo();

        for (Course course : courses) {
            course.doIt(team);
            System.out.println("\nList of passed: ");
            team.printPassedDistance();
        }
    }
}
