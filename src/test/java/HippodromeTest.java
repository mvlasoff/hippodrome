import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    private static List<Horse> horses;

    @BeforeAll
    public static void setUp() {
        horses = List.of(
                new Horse("Horsey01", 2.4, 1),
                new Horse("Horsey02", 2.5, 2),
                new Horse("Horsey03", 2.6, 3),
                new Horse("Horsey04", 2.7, 4),
                new Horse("Horsey05", 2.8, 5),
                new Horse("Horsey06", 2.9, 6),
                new Horse("Horsey07", 3, 7),
                new Horse("Horsey08", 2.8, 8),
                new Horse("Horsey09", 2.9, 9),
                new Horse("Horsey10", 3, 10),
                new Horse("Horsey11", 2.4, 11),
                new Horse("Horsey12", 2.5, 12),
                new Horse("Horsey13", 2.6, 13),
                new Horse("Horsey14", 2.7, 14),
                new Horse("Horsey15", 2.8, 15),
                new Horse("Horsey16", 2.9, 16),
                new Horse("Horsey17", 3, 17),
                new Horse("Horsey18", 2.8, 18),
                new Horse("Horsey19", 2.9, 19),
                new Horse("Horsey20", 3, 20),
                new Horse("Horsey21", 2.4, 21),
                new Horse("Horsey22", 2.5, 22),
                new Horse("Horsey23", 2.6, 23),
                new Horse("Horsey24", 2.7, 24),
                new Horse("Horsey25", 2.8, 25),
                new Horse("Horsey26", 2.9, 26),
                new Horse("Horsey27", 3, 27),
                new Horse("Horsey28", 2.8, 28),
                new Horse("Horsey29", 2.9, 29),
                new Horse("Horsey30", 3, 30)
        );
    }


    @Test
    public void whenConstructorGetsNullHorsesThenIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void whenConstructorGetsNullHorsesThenGetMessage() {
        String expected = "Horses cannot be null.";
        String actual = "";
        try {
            new Hippodrome(null);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void whenConstructorGetsEmptyHorsesListThenIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void whenConstructorGetsEmptyHorsesListThenGetMessage() {
        String expected = "Horses cannot be empty.";
        String actual = "";
        try {
            new Hippodrome(new ArrayList<>());
        } catch (Exception e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void whenConstructorGetsHorseListThenMethodGetHorsesReturnIt() {
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> actualHorseList = hippodrome.getHorses();
        assertArrayEquals(horses.toArray(), actualHorseList.toArray());
    }

    @Test
    public void whenMoveHorseListThenEachHorseMoves() {
        ArrayList<Horse> listHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            listHorses.add(Mockito.mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(listHorses);
        hippodrome.move();
        for (Horse listHors : listHorses) {
            Mockito.verify(listHors).move();
        }
    }

    @Test
    public void whenInvokeGetWinnerThenGetHorseWithLongestDistance() {
        Hippodrome hippodrome = new Hippodrome(horses);
        String expectedHorse = horses.get(horses.size() - 1).getName();
        String actualHorse = hippodrome.getWinner().getName();
        assertEquals(expectedHorse, actualHorse);
    }
}