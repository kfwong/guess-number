import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class UtilsTest {

    Utils utils = new Utils();

    @Test
    public void must_be_four_numbers(){
        String[] data = {"1234", "0284"};

        for( String d : data){
            boolean isNumber = utils.isFourDigits(d);
            assertTrue(isNumber);
        }
    }

    @Test
    public void should_fail_if_invalid_numbers() {
        String[] data = {"abcd", ";[];", "12367923659"};

        for( String d : data){
            boolean isNumber = utils.isFourDigits(d);
            assertFalse(isNumber);
        }
    }

    @Test
    public void should_return_four_digits_that_is_not_repeated(){
        String data = utils.generateRandomFourNumbers();

        assertEquals(data.length(), 4);

        assertTrue(utils.isUniqueChars(data));
    }
}
