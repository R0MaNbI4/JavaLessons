package ru.geekbrains.tasks.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.geekbrains.tasks.TaskTwo;

import java.util.stream.Stream;

public class TaskTwoTest {
    @ParameterizedTest
    @MethodSource("validArrayArgumentProvider")
    void shouldReturnTrueWhenArrayContainsOneDigitOrFourDigit(int[] inputArray) {
        Assertions.assertTrue(TaskTwo.doTaskTwo(inputArray));
    }

    private static Stream<Arguments> validArrayArgumentProvider() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3}),
                Arguments.of(new int[] {3, 2, 1}),
                Arguments.of(new int[] {4, 5, 6, 7}),
                Arguments.of(new int[] {5, 6, 4, 7, 8}),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 88})
        );
    }

    @ParameterizedTest
    @EmptySource
    @MethodSource("invalidArrayArgumentProvider")
    void shouldReturnFalseWhenArrayNotContainsOneDigitOrFourDigit(int[] inputArray) {
        Assertions.assertFalse(TaskTwo.doTaskTwo(inputArray));
    }

    private static Stream<Arguments> invalidArrayArgumentProvider() {
        return Stream.of(
                Arguments.of(new int[] {0, 2, 3, 5, 6, 7, 8, 9}),
                Arguments.of(new int[] {11, 14, 41, 44}),
                Arguments.of(new int[] {-1, -4})
        );
    }
}
