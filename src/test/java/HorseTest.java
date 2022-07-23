import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {
    @Test
    public void whenConstructorGetsNullAsNameThenIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void whenConstructorGetsNullAsNameThenGetMessage() {
        String actual = "";
        String expected = "Name cannot be null.";
        try {
            new Horse(null, 1, 1);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\r", "\n", "\r\n"})
    public void whenConstructorGetsEmptyNameThenIAException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\r", "\n", "\r\n"})
    public void whenConstructorGetsEmptyNameThenGetMessage(String name) {
        String actual = "";
        String expected = "Name cannot be blank.";
        try {
            new Horse(name, 1, 1);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-2", "-10", "-999", "-999999"})
    public void whenConstructorGetsNegativeSpeedValueThenIAException(int speed) {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horsey", speed, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-2", "-10", "-999", "-999999"})
    public void whenConstructorGetsNegativeSpeedThenGetMessage(int speed) {
        String actual = "";
        String expected = "Speed cannot be negative.";
        try {
            new Horse("Horsey", speed, 1);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-2", "-10", "-999", "-999999"})
    public void whenConstructorGetsNegativeDistanceThenIAException(int distance) {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horsey", 1, distance));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-2", "-10", "-999", "-999999"})
    public void whenConstructorGetsSecondNegativeDistanceThenGetMessage(int distance) {
        String actual = "";
        String expected = "Distance cannot be negative.";
        try {
            new Horse("Horsey", 1, distance);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void whenConstructorGetsNameThenMethodGetNameReturnIt() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Horsey", 1, 1);
        Field field = Horse.class.getDeclaredField("name");
        field.setAccessible(true);
        String expected = (String) field.get(horse);
        field.setAccessible(false);
        String actual = horse.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void whenConstructorGetsSpeedThenMethodGetSpeedReturnIt() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Horsey", 2, 99);
        Field field = Horse.class.getDeclaredField("speed");
        field.setAccessible(true);
        double expected = (double) field.get(horse);
        field.setAccessible(false);
        double actual = horse.getSpeed();
        assertEquals(expected, actual);
    }

    @Test
    public void whenConstructorGetsDistanceThenMethodGetDistanceReturnIt() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("Horsey", 2, 99);
        Field field = Horse.class.getDeclaredField("distance");
        field.setAccessible(true);
        double expected = (double) field.get(horse);
        field.setAccessible(false);
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    public void whenConstructorDoesNotGetDistanceThenMethodGetDistanceReturnZero() {
        Horse horse = new Horse("Horsey", 2);
        double expected = 0;
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    public void whenHorseMovesThenGetRandomDoubleMethodInvoked() {
        try(MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Horsey", 2, 99);
            horse.move();
            mockStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.2", "0.3", "0.4", "0.5", "0.9"})
    public void whenHorseMovesThenGetDistanceByFormula(double randomNum) {
        try(MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)) {
            mockStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomNum);
            Horse horse = new Horse("Horsey", 2, 99);
            double expectedDistance = horse.getDistance() + horse.getSpeed() * randomNum;
            horse.move();
            double actualDistance = horse.getDistance();
            assertEquals(expectedDistance, actualDistance);
        }
    }



}