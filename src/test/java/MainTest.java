import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static final PrintStream standardOut = System.out;
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    static void beforeAll() {
        System.setOut(new PrintStream(outputStreamCaptor));
        ByteArrayInputStream inputStream = new ByteArrayInputStream("end".getBytes());
        System.setIn(inputStream);
    }
    @ParameterizedTest
    @CsvSource(value = {
            // Доход, ОР
            "1_000, 70",
            "25_851, 1_809",
            "0, 0",
    })
    void taxEarningsParametrized(int earnings, int expectedValue) {
        // Формула: (earnings * 7) / 100;
        int result = Main.taxEarnings(earnings);
        assertEquals(expectedValue, result);

    }
    @ParameterizedTest
    @CsvSource(value = {
            // Доход, расход, ОР
            "10_000, 5000, 750",
            "5_000, 5_000, 0",
            "10_000, 15_000, 0",
    })
    void taxEarningsMinusExpensesParametrized(int earnings, int expenses, int expectedValue) {
        // Формула: (earnings - expenses) * 15 / 100
        int result = Main.taxEarningsMinusExpenses(earnings, expenses);
        assertEquals(expectedValue, result);
    }

    @Test
    void dialogueWithUserExit() {
        Main.dialogueWithUser();
        assertTrue(outputStreamCaptor.toString().trim().contains("Программа завершена!"));
    }
    @AfterAll
    static void afterAll() {
        System.setOut(standardOut);
        System.setIn(System.in);
    }
}