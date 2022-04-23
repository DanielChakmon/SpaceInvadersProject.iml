import javax.swing.*;

public class Window extends JFrame {
    private Constants constants = new Constants();
    private JPanel currentPanel;
    private ScoreBoard scoreBoard = new ScoreBoard(0, 0, constants.WINDOW_WIDTH, constants.WINDOW_HEIGHT);

    public static void main(String[] args) {
        Window myWindow = new Window();
    }

    private void init() {
        this.setSize(constants.WINDOW_WIDTH, constants.WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }

    private void drawOnWindow() {
        OpeningScreen openingScreen = new OpeningScreen(0, 0, constants.WINDOW_WIDTH, constants.WINDOW_HEIGHT);
        this.add(openingScreen);
        currentPanel = openingScreen;
        repaint();
        openingScreen.sendWindowData(this);


    }

    public void switchPanels(int whichPanels) {
        final int MENU_TO_GAME_SCREEN = 1;
        final int MENU_TO_SCORE_BOARD = 2;
        final int MENU_TO_INSTRUCTIONS = 3;
        final int INSTRUCTIONS_TO_MENU = 4;
        final int GAME_SCREEN_TO_RESULTS = 5;
        final int RESULTS_TO_SCORE_BOARD = 6;
        final int SCORE_BOARD_TO_MENU = 7;
        scoreBoard.sendWindowData(this);
        switch (whichPanels) {
            case MENU_TO_GAME_SCREEN: {
                this.remove(currentPanel);
                GameScreen gameScreen = new GameScreen(0, 0, constants.WINDOW_WIDTH, constants.WINDOW_HEIGHT);
                gameScreen.sendWindowData(this);
                this.add(gameScreen);
                currentPanel = gameScreen;
                repaint();
                break;
            }
            case MENU_TO_SCORE_BOARD: {
                this.remove(currentPanel);
                this.add(scoreBoard);
                currentPanel = scoreBoard;
                repaint();
                break;
            }
            case MENU_TO_INSTRUCTIONS: {
                Instructions instructions = new Instructions(0, 0, constants.WINDOW_WIDTH, constants.WINDOW_HEIGHT);
                instructions.sendWindowData(this);
                this.remove(currentPanel);
                this.add(instructions);
                currentPanel = instructions;
                repaint();
                break;
            }
            case INSTRUCTIONS_TO_MENU: {
                this.remove(currentPanel);
                OpeningScreen openingScreen = new OpeningScreen(0, 0, constants.WINDOW_WIDTH, constants.WINDOW_HEIGHT);
                openingScreen.sendWindowData(this);
                this.add(openingScreen);
                currentPanel = openingScreen;
                repaint();
                break;
            }
            case GAME_SCREEN_TO_RESULTS: {
                int score = 0;
                if (currentPanel instanceof GameScreen) {
                    score = ((GameScreen) currentPanel).getScore();
                }
                this.remove(currentPanel);
                ResultsScreen resultsScreen = new ResultsScreen(0, 0, constants.WINDOW_WIDTH, constants.WINDOW_HEIGHT, score);
                resultsScreen.sendWindowData(this);
                this.add(resultsScreen);
                currentPanel = resultsScreen;
                repaint();
                break;
            }

            case RESULTS_TO_SCORE_BOARD: {
                if (currentPanel instanceof ResultsScreen) {
                    scoreBoard.checkQualificationAndInsert(((ResultsScreen) currentPanel).getPlayRecord());
                }
                this.remove(currentPanel);
                this.add(scoreBoard);
                currentPanel = scoreBoard;
                repaint();
                break;
            }
            case SCORE_BOARD_TO_MENU: {
                this.remove(currentPanel);
                OpeningScreen openingScreen = new OpeningScreen(0, 0, constants.WINDOW_WIDTH, constants.WINDOW_HEIGHT);
                openingScreen.sendWindowData(this);
                this.add(openingScreen);
                currentPanel = openingScreen;
                repaint();
                break;
            }

        }

    }

    public Window() {
        this.init();
        this.drawOnWindow();
        repaint();

    }
}
