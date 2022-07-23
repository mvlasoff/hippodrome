import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    public void whenConstructorGetNullThenIAException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void whenConstructorGetNullThenGetMessage() {
        String actual = "";
        String expected = "Name cannot be null.";
        try {
            new Horse(null, 1, 1);
        } catch (Exception e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }


}