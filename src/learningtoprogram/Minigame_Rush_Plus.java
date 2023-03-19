package learningtoprogram;

/**
 * Author: Savan Class: ICS3U
 *
 * Program: Assignment X Question Y Input: (explain what is being input into the
 * program) Processing: (explain what is being done) Output: (Result of the
 * program)
 *
 */
//Import Statements Listed Alphabetically
import java.io.*;           //used for any type of input or output
import java.util.*;         //useful utilities like Scanner
import hsa.Console;
import hsa.*;

/**
 *
 * @author Savan
 */
public class Minigame_Rush_Plus {

    /**
     * * MAIN METHOD
     *
     **
     * @param args
     */
    public static boolean rockPaperScissors() {
        //declaration and initialization
        Random ai = new Random();
        String playerChoice = "";

        int aiPick;
        char playerPick;

        String aiChoice;
        String rock = "rock", paper = "paper", scissors = "scissors";

        boolean endGame = false, playerWin = false;
        
        //display rules
        chooseRules(1);

        //do loop: the minigame will occur at least once, the loop ends once a tie does not occur
        do {
            //ai random choice generation
            aiPick = ai.nextInt(3);

            switch (aiPick) {
                case 0:
                    //0 - rock
                    aiChoice = rock;
                    break;
                case 1:
                    //1 - paper
                    aiChoice = paper;
                    break;
                default:
                    //2 - scissors
                    aiChoice = scissors;
                    break;
            }

            //prompt player to enter their choice
            c.print("Pick an action: [r]ock, [p]aper, or [s]cissors");
            do{
                playerPick = c.getChar();
            }
            while(playerPick != 'r' && playerPick != 'p' && playerPick != 's');
                
            
            switch(playerPick){
                case 'r':
                    playerChoice = rock;
                    break;
                case 'p': 
                    playerChoice = paper;
                    break;
                case 's':
                    playerChoice = scissors;
                    break;
            }
            
            c.println("\nYour choice: " + playerChoice);

            //validation: if user input is not equal to any of the options, ask again
            while (!playerChoice.equals(rock) && !playerChoice.equals(paper) && !playerChoice.equals(scissors)) {
                c.println(design(2) + "\nERROR INVALID INPUT! PLEASE RE-ENTER");
                c.println("Your choice: ");
                playerChoice = c.readString().toLowerCase();
            }

            //print choice of AI
            c.println("AI Choice: " + aiChoice);

            //compare player choice and ai choice
            //if they match, a tie has occured and the loop continues
            if (playerChoice.equals(aiChoice)) {
                c.print(design(2) + "\nIt's a tie! Let's try that again...\n");
            } else {
                //else, a tie has not occured and the loop is broken
                endGame = true;
            }
        } while (!endGame);

        //check if any player win possibilities are true
        //possibility 1 - player as rock beats AI as scissors
        if (playerChoice.equals(rock) && aiChoice.equals(scissors)
                || //possibility 2 - player as paper beats AI as rock
                playerChoice.equals(paper) && aiChoice.equals(rock)
                || //possibility 3 - player as scissors beats AI as paper
                playerChoice.equals(scissors) && aiChoice.equals(paper)) {

            //any of the above cases will result in a win
            playerWin = true;
        }

        //based on whether player win has occured or not, display appropriate win or lose message
        if (playerWin) {
            c.println(design(3));
            return true;
        } else {
            c.println(design(4));
            return false;
        }
    }

    public static String getRandomWord(){
       Random gen = new Random();

       //https://www.ef.com/ca/english-resources/english-vocabulary/top-1000-words/ used to get random words
       //create file class instance just to use .isFile() function
       File f = new File("random words.txt");
       
       //create empty text file
       TextOutputFile tof;

       //if file f does not exist, add the file "random words"
       if (!f.isFile()) {
           tof = new TextOutputFile("random words.txt");
           //the file starts out with these inital words when created
           tof.println("interesting\nbecome\nofficial\nwhile\nactivity\nknow\nlanguage\ncup\ncapital");
           tof.println("strong\nremove\naffect\nimagine\nsign\nmanager\nstuff\nalthough\nwide\nspend\nserious\nsection");
           tof.println("western\nhair\nmanage\ncall\nhand\nreach\nsell");
       } 
       else 
       {
           //if the file does exist, allow appendage to "random words"
           //allows words to be added directly to file, and implemented into game
           tof = new TextOutputFile("random words.txt", true);
       }
       tof.close();
       
       //open text input 
       TextInputFile tif = new TextInputFile("random words.txt");

       //declare dynamic array list
       ArrayList<String> wordArrList = new ArrayList<>();
       
       while(!tif.eof()){
           //read current word in file
           String word = tif.readString();
           //add any added words plus initial words into array list
           wordArrList.add(word);
       }
       //close text file input
       tif.close();
       
       //declare "wordArray" with the same size as array list
       String[] wordArray = new String[wordArrList.size()];
       
       //convert array list to string array or "wordArray"
       wordArray = wordArrList.toArray(wordArray);

       //pick a random index from string array
       int pickRand = gen.nextInt(wordArray.length);

       //the random word is the word in the randomized index 
       String randWord = wordArray[pickRand];

       //return random word
       return randWord;
    }
    public static boolean wordScramble() {
        //initialization and declaration
        Random gen = new Random();
        int randIndex;

        String Randword, scrambledWord, guess;

        //variable name clarification: current letter, and letter to be swapped with
        char currLet, swapLet;

        //get a random word
        Randword = getRandomWord();
        char[] wordSepArray;
        int len;

        chooseRules(2);

        //do loop to ensure the scrambled word does not scramble back to the original
        do {
            //convert word to char array
            wordSepArray = Randword.toCharArray();
            len = wordSepArray.length;
            
            //read through array
            //preferably this can be moved to its own method: wordScramblerAlgorithm or something of the like
            for (int i = 0; i < len; i++) {
                //the current letter is the letter currently being read through
                currLet = wordSepArray[i];

                //select a letter from a random index
                //currently, there is a possibility that the random index is the same as the current index; this may be changed
                randIndex = gen.nextInt(len);
                swapLet = wordSepArray[randIndex];

                //swap the two letters' indices/placement
                wordSepArray[randIndex] = currLet;
                wordSepArray[i] = swapLet;
            }
            //convert array to string
            scrambledWord = new String(wordSepArray);
            //the loop repeats if the scrambled word so happens to be the same as the original
            //usually does not happen, but shorter words tend to have such a problem
        } while (Randword.equals(scrambledWord));

        //display every letter as now they are swapped
        c.println(scrambledWord);
        c.println();
        c.print("Your guess: ");
        guess = c.readString();

        //if the player guesses the original word correctly, they win
        if (guess.equals(Randword)) {
            c.println(design(3));
            return true;
        } 
        else 
        {
            //else, they lose
            //reveal the word to the player after
            c.println(design(4));
            c.println("The word was " + Randword);
            return false;
        }
    }
    public static boolean shuffleMinigames() {
        Random choose = new Random();
        
        //randomly generate a minigame
        int minigameChoice = choose.nextInt(2);
        
        //controls whether player earns points
        boolean win = false;
        
        switch (minigameChoice) {
            //if the player wins a minigame, win will become true
            case 0:
                //0 - rock, paper, scissors 
                win = rockPaperScissors();
                break;
            case 1:
                //1 - word scramble
                win = wordScramble();
                break;
        }
        return win;
    }
    
    public static void showScreen(int score, String title) {
        c.clear();
        centerLabel(0, 5);
        c.println(title);
        
        centerLabel(2, 5);
        c.println("Points to win: " + WIN_TOTAL);
        
        if(score == -1){
            centerLabel(3, 5);
        }
        //display title in the middle of the console
        else
        {
            centerLabel(3, 5);
            c.println("Score: " + score);
            centerLabel(4, 5);
        }
        c.println("Press any key to continue...");
        c.getChar();
        c.clear();
    }
    
    public static void chooseRules(int minigame){
        String minigameName = "";
        
        //minigame will contain integer from 0 to 2, which is indicative of board name or specific minigame name
        switch(minigame){
            case 0:
                //game board
                minigameName = "GAME BOARD";
                break;
            case 1:
                //rock, paper, scissors
                minigameName = "Rock, Paper, Scissors!";
                break;
            case 2:
                //word scrambler
                minigameName = "Word Scrambler!";
                break;
        }
        
        //display minigame name and rules
        c.println(minigameName + "\n" + design(1));
        
        //displays corresponding rules for corresponding minigame
        switch(minigame){
            case 0:
                c.println("Press [" + GO_FORWARD + "] to move forward along the board");
                break;
            case 1:
                c.println("Rules:\nJust a classic game of rock, paper, scissors");
                c.println("Rock beats scissors, scissors beats paper, and paper beats rock");
                break;
            case 2:
                c.println("Rules:\nIn this game, you have to guess a scrambled word");
                c.println("If you guess the original correctly, you win");
                break;
        }
        //if this is not the game board screen, every minigame screen will inform the player that they will gain points
        if(minigame != 0){
            c.println("If you win, you gain points, or else you gain nothing; good luck!\n" + design(1));
        }
    }
    public static void centerLabel(int rowDownUp, int colDownUp){
        //centers text; isolated repetitive code
        c.setCursor((c.getMaxRows() / 2) + rowDownUp, (c.getMaxColumns() / 3) + colDownUp);
    }
    public static void showBoard(char[] board) {
        //prints every character in array
        for (int i = 0; i < board.length; i++) {
            c.print("| " + board[i] + " ");
        }
    }
    public static void addScore(int points){
        //create file class instance just to use .isFile() function
        File f = new File("score.txt");
        
        //create empty text file
        TextOutputFile tof;
        
        //if file f does not exist, add the file "score"
        if (!f.isFile()) {
            tof = new TextOutputFile("score.txt");
        } 
        else 
        {
            //if the file does exist, append to file "score"
            tof = new TextOutputFile("score.txt", true);
        }
        
        //get points, and current date
            tof.println("Score: " + points + " " + c.getDate());
            tof.close();
            
    }
    public static void showScores() {
        //open text file input
        TextInputFile tif = new TextInputFile("score.txt");
        String scoreLabel;
        int score;
        String date;
        
            c.clear();
            c.println("PREVIOUS SCORES\n" + design(1));

            //reads through any previous score information until end of file
            int i = 0;
            while(!tif.eof()){
                //read score label, score, and date
                scoreLabel = tif.readString();
                score = tif.readInt();
                date= tif.readString();
                //display previous score information
                i++;
                c.println(i + ". " + scoreLabel + "..........." + score + " (" + date + ")");
            }
            //close text file input
            tif.close();
            c.println("\nPress [" + BACK_PAGE + "] to go back to end screen");
            
        char keyBack;    
        do{
            keyBack = c.getChar();
        } while(keyBack != BACK_PAGE);
 
    }
    public static void showEndScreenMessage(){
            c.clear();
            centerLabel(-2, 0);
            c.println("END OF GAME");
            
            //display end of game choices 
            centerLabel(1, 0);
            c.println("Press [" + REPLAY + "] to replay"); 
            
            //view scores
            centerLabel(2, 0);
            c.println("Press [" + VIEW_SCORES + "] to view previous scores");
            
            //end game
            centerLabel(3, 0);
            c.println("Press [" + QUIT + "] to end the game");
    }
    public static boolean endScreen(){
            boolean endGame = false;
            
            //display end screen title
            showEndScreenMessage();
            
            //get player's key press for action
            char keyEnd;
            
            do{
                keyEnd = c.getChar();
            
                //if player chooses a specific key, do corresponding action
                switch(keyEnd){
                    case VIEW_SCORES:
                        //v - display scores
                        showScores();
                        //once the player presses back from scores, display end screen choices again
                        showEndScreenMessage();
                        break;
                        //r - 
                    case REPLAY:
                        endGame = false;
                        break;
                    //q - display goodbye and end game
                    case QUIT:
                        centerLabel(5, 0);
                        c.println("Goodbye!");
                        endGame = true;
                        break;
                } 
                //this
            }while(keyEnd != REPLAY && keyEnd != QUIT);
            
            return endGame;
    }
    //controls key presses for certain actions (constants)
    public static final char 
            //symbols
            PLAYER_SYMBOL = 'x', 
            POINTS_SYMBOL = 'p',
            MINIGAME_SYMBOL = 'm',
            
            //player movement
            GO_FORWARD = 'd',
            PAST_SPACE = '0',
            
            //end of game choices
            REPLAY = 'r', 
            VIEW_SCORES = 'v', 
            QUIT = 'q',
            
            //page control
            BACK_PAGE = 'b';
    
    
    public static final int 
            //controls number of board spaces; changed multiple times during testing
            BOARD_SIZE = 10,
            //points needed to win
            WIN_TOTAL = 15;
    
    
    //global console
    public static Console c = new Console();
    
    public static void main(String[] args) {
        //FIRST WRITE YOUR PSEUDOCODE USING COMMENTS, THEN FILL IN WITH CODE
        //initialization and declaration
        Random gen = new Random();
        char[] board = new char[BOARD_SIZE];
        int chooseNature;
        char getNature;
        
        int currentSpace;
        int spacesMoved;
        int pointsTotal;
        
        //contains index of player location on board
        int playerLocation;
        
        //controls whether player won minigame
        boolean winMinigame;
        
        //controls whether game ends or repeats
        boolean endGame;

        //points gained initialized as won, amount won depends on gameplay
        int ptsGain = 0;
        
        do{
            //for every new game, "reset" these values
            currentSpace = 0;
            spacesMoved = 0;
            playerLocation = 0;
            pointsTotal = 0;
            endGame = false;
            
            //the player (x) begins at index[0] or the first space
            board[0] = PLAYER_SYMBOL;

            //start screen
            showScreen(-1, "MINIGAME RUSH!");

            //fills the board randomly with points (p) or minigames (m)
            for (int i = 1; i < board.length; i++) {
                chooseNature = gen.nextInt(2);
                switch (chooseNature) {
                    case 0:
                        //0 - points space
                        board[i] = POINTS_SYMBOL;
                        break;
                    case 1:
                        //1 - minigame space
                        board[i] = MINIGAME_SYMBOL;
                        break;
                }
            }

            //display game board instructions
            chooseRules(0);
            
            //display board at start of game
            showBoard(board);

            while(spacesMoved < BOARD_SIZE-1){
                //get key press of player
                char keyBoard = c.getChar();
                //if player presses 'd'
                switch(keyBoard){
                    //player movement
                    case GO_FORWARD:
                        spacesMoved++;
                        currentSpace++;
                        c.clear();
                        //get nature of current space
                        getNature = board[currentSpace];
                        if (getNature == MINIGAME_SYMBOL) {
                            //if it's a minigame space, call minigame shuffler
                            winMinigame = shuffleMinigames();
                            if (winMinigame) {
                                //if player won minigame, add 1 point
                                ptsGain = 1;
                            }
                        } 
                        else 
                        {
                            //else it's a point space, then simply add points (amount added can range from 1 to 3)
                            ptsGain = gen.nextInt(3) + 1;
                            c.println("You gained " + ptsGain + " free points!");
                            
                        }
                        //add earned points
                        pointsTotal += ptsGain;
                        //reset amount of points gained
                        ptsGain = 0; 
                        
                        //shows current points
                        c.println(design(1));
                        c.println("Points: " + pointsTotal);

                        //place '0' for previous player location to represent a past space
                        board[playerLocation] = PAST_SPACE;
                        //the current space becomes the location of x
                        board[currentSpace] = PLAYER_SYMBOL;
                        //set player location as the new space moved to
                        playerLocation = currentSpace;
                        
                        //display board
                        showBoard(board);
                        break;
                }
            }

            //prompt player for key press to go to results
            c.println("\n" + design(1) + "\nThat concludes the game! Press any key to continue to the results!");
            c.getChar();

            //if player's earned points reach the win total (total points needed to wins), show win screen
            if (pointsTotal >= WIN_TOTAL) {
                showScreen(pointsTotal, design(3));
            } 
            else 
            {
                //else show end screen
                showScreen(pointsTotal, design(4));
            }
            
            //add results/score to file
            addScore(pointsTotal);
            
            //prompt user for key press to go to end screen
            endGame = endScreen();
            
            //keeps looping as long as the game doesn't end
        }while(!endGame);
    }
    public static String design(int choice) {
        //simply keeps track of various designs and sends them as needed
        //this method was created mostly for convenience and consistency in the game
        String bigSeparater = "-----------------------------------------------------";
        String smallSeparater = "---------";
        String genericWinMsg = "YOU WIN!";
        String genericLoseMsg = "YOU LOSE!";
        String none = "";

        switch (choice) {
            case 1:
                return bigSeparater;
            case 2:
                return smallSeparater;
            case 3:
                return genericWinMsg;
            case 4:
                return genericLoseMsg;
        }
        return none;
    }
}
