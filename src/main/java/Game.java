import java.util.*;


public class Game {

    Scanner scanner;
    String answer;
    String guess;

    Utils utils;

    public Game(){
        this(new Utils());
        this.scanner = new Scanner(System.in);
    }

    public Game(Utils utils){
        this.utils = utils;
    }

    public void start(){
        /*
        1. System generate a random number
        2. User input a guess number
        3. System check the guess number against the answer
        4. System output XAXB format to the user
        5. repeat steps 2-4 until 4A4B.
         */

        displayWelcomeMessage();

        answer = utils.generateRandomFourNumbers();

        do {
            guess = promptForUserGuess();

            if (!utils.isFourDigits(guess) || !utils.isUniqueChars(guess)) {
                System.out.println(guess + " is not in numeric format, incorrect length or has repeated digits.");
            }else{
                int countA = correctNumberAndPosition(guess, answer);
                int countB = correctNumberCount(guess, answer);

                System.out.println(countA + "A" + countB + "B");
            }
        }while(!hasGuessCorrectly());

        displayWinningMessage();

    }

    public boolean hasGuessCorrectly(){
        return guess.equals(answer);
    }

    public void displayWinningMessage() {
        System.out.println("You won!");
    }

    public void displayWelcomeMessage() {
        System.out.println("Welcome!");
    }

    public String promptForUserGuess() {
        System.out.println("Enter four digits guess: ");
        return scanner.nextLine();
    }

    public int correctNumberCount(String data, String answer){
        int count = 0;


        for( char a: answer.toCharArray()){

            if(data.indexOf(a) >= 0){
                count ++;
            };
        }

        return count;
    }

    public int correctNumberAndPosition(String guess, String answer) {
        int count = 0;

        for(int i=0; i<answer.length(); i++){
            if(guess.toCharArray()[i] == answer.toCharArray()[i]){
                count++;
            }
        }

        return count;
    }
}
