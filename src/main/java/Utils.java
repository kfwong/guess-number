import java.util.Arrays;
import java.util.Random;

public class Utils {

    public boolean isFourDigits(String data){
        try {
            Integer.parseInt(data);
            return data.length() == 4;
        }catch(Exception e){
            return false;
        }
    }

    public String generateRandomFourNumbers() {
        String result = "";
        int[] numbers = {0,1,2,3,4,5,6,7,8,9};
        Random random = new Random();

        for(int i =0; i < 4; i++ ){
            final int randomlyPickedNumber = numbers[random.nextInt(numbers.length)];
            numbers = Arrays.stream(numbers)
                    .filter( number -> number != randomlyPickedNumber)
                    .toArray();
            result += randomlyPickedNumber;
        }

        return result;
    }
}
