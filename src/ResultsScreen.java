import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class ResultsScreen extends JPanel implements WindowDataReceiver {
    private Font emulogic;
    private Window window;
    JTextField obtainUsername;
    String userName;
    int score;

    public void sendWindowData(Window window) {
        this.window = window;
    }

    public ResultsScreen(int x, int y, int width, int height, int score) {
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.score=score;
        try {
            emulogic = Font.createFont(Font.TRUETYPE_FONT, new File("src/emulogic.ttf")).deriveFont(Font.BOLD & Font.CENTER_BASELINE, 20);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/emulogic.ttf")));
        } catch (IOException | FontFormatException e) {
        }
        Constants constants = new Constants();
        this.setBounds(x, y, width, height);
        Font gameOverFont = emulogic.deriveFont(64f);
        JLabel gameOverStroke = new JLabel("GAME OVER");
        JLabel gameOverTitle = new JLabel("GAME OVER");
        Color orangeRed = new Color(255, 67, 0);
        gameOverStroke.setFont(gameOverFont);
        gameOverTitle.setFont(gameOverFont);
        gameOverStroke.setForeground(Color.white);
        gameOverTitle.setForeground(orangeRed);
        gameOverStroke.setBounds((169 * constants.WINDOW_WIDTH / 720) + 4, 2 * constants.WINDOW_HEIGHT / 16 - 65, 5 * constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 4);
        gameOverTitle.setBounds((169 * constants.WINDOW_WIDTH / 720), 2 * constants.WINDOW_HEIGHT / 16 - 70, 5 * constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 4);
        this.add(gameOverTitle);
        this.add(gameOverStroke);
        JLabel scoreDisplay = new JLabel("final score: " + score);
        scoreDisplay.setForeground(Color.white);
        scoreDisplay.setFont(emulogic);
        scoreDisplay.setBounds(3 * constants.WINDOW_WIDTH / 8 - 30, 6 * constants.WINDOW_HEIGHT / 16 - 70, constants.WINDOW_WIDTH/2, constants.WINDOW_HEIGHT / 8);
        this.add(scoreDisplay);
        obtainUsername = new JTextField();
        obtainUsername.setFont(emulogic);
        obtainUsername.setForeground(Color.white);
        obtainUsername.setBounds(10*constants.WINDOW_WIDTH / 16 - 30, 10 * constants.WINDOW_HEIGHT / 16 - 70, constants.WINDOW_WIDTH / 4, 1 * constants.WINDOW_HEIGHT / 16);
        obtainUsername.setOpaque(false);
        this.add(obtainUsername);
        JLabel enterYourName = new JLabel("enter your display name: ");
        enterYourName.setFont(emulogic);
        enterYourName.setForeground(Color.white);
        enterYourName.setBounds(constants.WINDOW_WIDTH / 8 - 30, 10 * constants.WINDOW_HEIGHT / 16 - 70, constants.WINDOW_WIDTH / 2, 1 * constants.WINDOW_HEIGHT / 16);
        this.add(enterYourName);
        JLabel instructions=new JLabel("press enter to lock your name and continue ");
        instructions.setFont(emulogic.deriveFont(17f));
        instructions.setForeground(Color.white);
        instructions.setBounds(5*constants.WINDOW_WIDTH/32,13*constants.WINDOW_HEIGHT/16-70,3*constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/16);
        this.add(instructions);
        obtainUsername.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {

            }

            public void keyPressed(KeyEvent keyEvent) {
                int keyCode = keyEvent.getKeyCode();
                if(keyCode==KeyEvent.VK_ENTER){
                    userName = obtainUsername.getText();
                    final int RESULTS_TO_SCORE_BOARD = 6;
                    window.switchPanels(RESULTS_TO_SCORE_BOARD);
                }
            }

            public void keyReleased(KeyEvent e) {

            }

        });
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        final int TEXT_FIELD_BORDER_THICKNESS = 10;
        Constants constants = new Constants();
        CustomRectangleBorder obtainUsernameBorder = new CustomRectangleBorder(10*constants.WINDOW_WIDTH / 16 - 30, 10 * constants.WINDOW_HEIGHT / 16 - 70, constants.WINDOW_WIDTH / 4, 1 * constants.WINDOW_HEIGHT / 16, Color.BLUE, TEXT_FIELD_BORDER_THICKNESS);
        Graphics2D graphics2D = (Graphics2D) (graphics);
        obtainUsernameBorder.paint(graphics2D);

    }


    public String getUserName(){
        return userName;
    }
    public PlayRecord getPlayRecord(){
        PlayRecord playRecord=new PlayRecord(score,userName);
        return playRecord;
    }
}