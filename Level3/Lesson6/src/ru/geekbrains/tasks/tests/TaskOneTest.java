package ru.geekbrains.tasks.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import ru.geekbrains.tasks.TaskOne;

import java.util.stream.Stream;

public class TaskOneTest {

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowIllegalArgumentExceptionWhenEmptyOrNullArrayIn(int[] arr) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> TaskOne.doTaskOne(arr));
    }

    @ParameterizedTest
    @MethodSource("arrayArgumentProvider")
    void shouldReturnArrayWithElementsAfterTheLastDigitFour(int[] inputArr, int[] expected) {
        Assertions.assertArrayEquals(expected, TaskOne.doTaskOne(inputArr));
    }

    private static Stream<Arguments> arrayArgumentProvider() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[] {1, 7}),
                Arguments.of(new int[] {4, 1}, new int[] {1}),
                Arguments.of(new int[] {1, 4}, new int[] {}),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[] {5, 6, 7, 8, 9})
        );
    }

    @Test
    void shouldThrowRuntimeExceptionWhenDigitFourNotFoundInArray() {
        Assertions.assertThrows(RuntimeException.class, () -> TaskOne.doTaskOne(new int[] {1, 2, 3}));
    }
}