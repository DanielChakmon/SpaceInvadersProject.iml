import javax.swing.*;

public class Window extends JFrame {
    private Constants constants = new Constants();
    private JPanel currentPanel;

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
        currentPanel=openingScreen;
        repaint();
        openingScreen.sendWindowData(this);


    }

    public void switchPanels(int whichPanels) {
        final int MENU_TO_GAME_SCREEN = 1;
        final int MENU_TO_SCORE_BOARD = 2;
        final int MENU_TO_INSTRUCTIONS = 3;
        final int INSTRUCTIONS_TO_MENU = 4;
        switch (whichPanels) {
            case MENU_TO_GAME_SCREEN: {
                this.remove(currentPanel);
                GameScreen gameScreen = new GameScreen(0, 0, constants.WINDOW_WIDTH, constants.WINDOW_HEIGHT);
                gameScreen.sendWindowData(this);
                this.add(gameScreen);
                currentPanel=gameScreen;
                repaint();
                break;
            }
            case MENU_TO_SCORE_BOARD: {

                break;
            }
            case MENU_TO_INSTRUCTIONS: {
                Instructions instructions = new Instructions(0, 0, constants.WINDOW_WIDTH, constants.WINDOW_HEIGHT);
                instructions.sendWindowData(this);
                this.remove(currentPanel);
                this.add(instructions);
                currentPanel=instructions;
                repaint();
                break;
            }
            case INSTRUCTIONS_TO_MENU: {
                this.remove(currentPanel);
                OpeningScreen openingScreen = new OpeningScreen(0, 0, constants.WINDOW_WIDTH, constants.WINDOW_HEIGHT);
                openingScreen.sendWindowData(this);
                this.add(openingScreen);
                currentPanel=openingScreen;
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
