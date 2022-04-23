import OvalButton.OvalButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ScoreBoard extends JPanel implements WindowDataReceiver {
    private Font emulogic;
    private Window window;
    private PlayRecord firstPlace;
    private PlayRecord secondPlace;
    private PlayRecord thirdPlace;
    private PlayRecord fourthPlace;
    private PlayRecord fifthPlace;
    private LeaderBoardJLabels leaderBoardJLabels;

    public void sendWindowData(Window window) {
        this.window = window;
    }

    public ScoreBoard(int x, int y, int width, int height) {
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        try {
            emulogic = Font.createFont(Font.TRUETYPE_FONT, new File("src/emulogic.ttf")).deriveFont(Font.BOLD & Font.CENTER_BASELINE, 20);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/emulogic.ttf")));
        } catch (IOException | FontFormatException e) {
        }
        Constants constants = new Constants();
        this.setBounds(x, y, width, height);
        firstPlace = new PlayRecord(0, "empty spot");
        secondPlace = new PlayRecord(0, "empty spot");
        thirdPlace = new PlayRecord(0, "empty spot");
        fourthPlace = new PlayRecord(0, "empty spot");
        fifthPlace = new PlayRecord(0, "empty spot");
        JLabel firstPlaceName = new JLabel(firstPlace.getUserName());
        JLabel secondPlaceName = new JLabel(secondPlace.getUserName());
        JLabel thirdPlaceName = new JLabel(thirdPlace.getUserName());
        JLabel fourthPlaceName = new JLabel(fourthPlace.getUserName());
        JLabel fifthPlaceName = new JLabel(fifthPlace.getUserName());
        JLabel firstPlaceScore = new JLabel("" + firstPlace.getScore());
        JLabel secondPlaceScore = new JLabel("" + secondPlace.getScore());
        JLabel thirdPlaceScore = new JLabel("" + thirdPlace.getScore());
        JLabel fourthPlaceScore = new JLabel("" + fourthPlace.getScore());
        JLabel fifthPlaceScore = new JLabel("" + fifthPlace.getScore());
        this.add(firstPlaceName);
        this.add(secondPlaceName);
        this.add(thirdPlaceName);
        this.add(fourthPlaceName);
        this.add(fifthPlaceName);
        this.add(firstPlaceScore);
        this.add(secondPlaceScore);
        this.add(thirdPlaceScore);
        this.add(fourthPlaceScore);
        this.add(fifthPlaceScore);
        leaderBoardJLabels=new LeaderBoardJLabels(firstPlaceName, secondPlaceName,thirdPlaceName, fourthPlaceName, fifthPlaceName, firstPlaceScore, secondPlaceScore, thirdPlaceScore, fourthPlaceScore, fifthPlaceScore);
        createAndUpdateTextOnScreen(true);
        OvalButton returnToMenuButton=new OvalButton(OvalButton.SHAPE_OVAL,OvalButton.HORIZONTAL,constants.darkBlue,constants.newCyan,constants.bourdeaux,Color.MAGENTA);
        returnToMenuButton.setBounds(5*constants.WINDOW_WIDTH / 16, 24 * constants.WINDOW_HEIGHT / 32, 3*constants.WINDOW_WIDTH /8, constants.WINDOW_HEIGHT / 8);
        returnToMenuButton.setText("return to menu");
        returnToMenuButton.setFont(emulogic);
        returnToMenuButton.setForeground(Color.WHITE);
        this.add(returnToMenuButton);
        final int SCORE_BOARD_TO_MENU=7;
        returnToMenuButton.addActionListener((event) -> {
            window.switchPanels(SCORE_BOARD_TO_MENU);
        });
    }

    public void checkQualificationAndInsert(PlayRecord qualifierWannaBe) {
        if (qualifierWannaBe.getScore() > firstPlace.getScore()) {
            fifthPlace = fourthPlace;
            fourthPlace = thirdPlace;
            thirdPlace = secondPlace;
            secondPlace = firstPlace;
            firstPlace = qualifierWannaBe;
        } else{
            if (qualifierWannaBe.getScore()>secondPlace.getScore()){
                fifthPlace = fourthPlace;
                fourthPlace = thirdPlace;
                thirdPlace = secondPlace;
                secondPlace =qualifierWannaBe;
            } else {
                if (qualifierWannaBe.getScore()>thirdPlace.getScore()){
                    fifthPlace = fourthPlace;
                    fourthPlace = thirdPlace;
                    thirdPlace = qualifierWannaBe;
                } else{
                    if(qualifierWannaBe.getScore()>fourthPlace.getScore()){
                        fifthPlace = fourthPlace;
                        fourthPlace = qualifierWannaBe;
                    } else {
                        if (qualifierWannaBe.getScore()>fifthPlace.getScore()){
                            fifthPlace=qualifierWannaBe;
                        }
                    }
                }
            }

        }

            createAndUpdateTextOnScreen(false);
    }

    public void createAndUpdateTextOnScreen(boolean firstTimeRun) {
        Constants constants=new Constants();
        Font leaderBoardFont = emulogic.deriveFont(15f);
        leaderBoardJLabels.getFirstPlaceName().setText (firstPlace.getUserName());
        leaderBoardJLabels.getSecondPlaceName().setText (secondPlace.getUserName());
        leaderBoardJLabels.getThirdPlaceName().setText (thirdPlace.getUserName());
        leaderBoardJLabels.getFourthPlaceName().setText (fourthPlace.getUserName());
        leaderBoardJLabels.getFifthPlaceName().setText (fifthPlace.getUserName());
        leaderBoardJLabels.getFirstPlaceScore() .setText("" + firstPlace.getScore());
        leaderBoardJLabels.getSecondPlaceScore() .setText("" + secondPlace.getScore());
        leaderBoardJLabels.getThirdPlaceScore() .setText("" + thirdPlace.getScore());
        leaderBoardJLabels.getFourthPlaceScore() .setText("" + fourthPlace.getScore());
        leaderBoardJLabels.getFifthPlaceScore() .setText("" + fifthPlace.getScore());

        if(firstTimeRun) {
            JLabel firstPlaceLabel = new JLabel("#1");
            JLabel secondPlaceLabel = new JLabel("#2");
            JLabel thirdPlaceLabel = new JLabel("#3");
            JLabel fourthPlaceLabel = new JLabel("#4");
            JLabel fifthPlaceLabel = new JLabel("#5");
            firstPlaceLabel.setForeground(Color.white);
            secondPlaceLabel.setForeground(Color.white);
            thirdPlaceLabel.setForeground(Color.white);
            fourthPlaceLabel.setForeground(Color.white);
            fifthPlaceLabel.setForeground(Color.white);
            firstPlaceLabel.setFont(leaderBoardFont);
            secondPlaceLabel.setFont(leaderBoardFont);
            thirdPlaceLabel.setFont(leaderBoardFont);
            fourthPlaceLabel.setFont(leaderBoardFont);
            fifthPlaceLabel.setFont(leaderBoardFont);
            firstPlaceLabel.setBounds(constants.WINDOW_WIDTH / 8, 8 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 8);
            secondPlaceLabel.setBounds(constants.WINDOW_WIDTH / 8, 11 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 8);
            thirdPlaceLabel.setBounds(constants.WINDOW_WIDTH / 8, 14 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 8);
            fourthPlaceLabel.setBounds(constants.WINDOW_WIDTH / 8, 17 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 8);
            fifthPlaceLabel.setBounds(constants.WINDOW_WIDTH / 8, 20 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 8);
            this.add(firstPlaceLabel);
            this.add(secondPlaceLabel);
            this.add(thirdPlaceLabel);
            this.add(fourthPlaceLabel);
            this.add(fifthPlaceLabel);
            JLabel title = new JLabel("SCORE BOARD");
            title.setForeground(Color.white);
            title.setFont(emulogic.deriveFont(50f));
            title.setBounds(constants.WINDOW_WIDTH / 4, 3 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 2, 3 * constants.WINDOW_HEIGHT / 32);
            this.add(title);

             leaderBoardJLabels.getFirstPlaceName().setForeground(Color.white);
             leaderBoardJLabels.getSecondPlaceName().setForeground(Color.white);
             leaderBoardJLabels.getThirdPlaceName().setForeground(Color.white);
             leaderBoardJLabels.getFourthPlaceName().setForeground(Color.white);
             leaderBoardJLabels.getFifthPlaceName().setForeground(Color.white);
             leaderBoardJLabels.getFirstPlaceName().setFont(leaderBoardFont);
             leaderBoardJLabels.getSecondPlaceName().setFont(leaderBoardFont);
             leaderBoardJLabels.getThirdPlaceName().setFont(leaderBoardFont);
             leaderBoardJLabels.getFourthPlaceName().setFont(leaderBoardFont);
             leaderBoardJLabels.getFifthPlaceName().setFont(leaderBoardFont);
             leaderBoardJLabels.getFirstPlaceName().setBounds(constants.WINDOW_WIDTH / 4, 8 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 2, constants.WINDOW_HEIGHT / 8);
             leaderBoardJLabels.getSecondPlaceName().setBounds(constants.WINDOW_WIDTH / 4, 11 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 2, constants.WINDOW_HEIGHT / 8);
             leaderBoardJLabels.getThirdPlaceName().setBounds(constants.WINDOW_WIDTH / 4, 14 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 2, constants.WINDOW_HEIGHT / 8);
             leaderBoardJLabels.getFourthPlaceName().setBounds(constants.WINDOW_WIDTH / 4, 17 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 2, constants.WINDOW_HEIGHT / 8);
             leaderBoardJLabels.getFifthPlaceName().setBounds(constants.WINDOW_WIDTH / 4, 20 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 2, constants.WINDOW_HEIGHT / 8);
             leaderBoardJLabels.getFirstPlaceScore().setForeground(Color.white);
             leaderBoardJLabels.getSecondPlaceScore().setForeground(Color.white);
             leaderBoardJLabels.getThirdPlaceScore().setForeground(Color.white);
             leaderBoardJLabels.getFourthPlaceScore().setForeground(Color.white);
             leaderBoardJLabels.getFifthPlaceScore().setForeground(Color.white);
             leaderBoardJLabels.getFirstPlaceScore().setFont(leaderBoardFont);
             leaderBoardJLabels.getSecondPlaceScore().setFont(leaderBoardFont);
             leaderBoardJLabels.getThirdPlaceScore().setFont(leaderBoardFont);
             leaderBoardJLabels.getFourthPlaceScore().setFont(leaderBoardFont);
             leaderBoardJLabels.getFifthPlaceScore().setFont(leaderBoardFont);
             leaderBoardJLabels.getFirstPlaceScore().setBounds(3 * constants.WINDOW_WIDTH / 4, 8 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 8);
             leaderBoardJLabels.getSecondPlaceScore().setBounds(3 * constants.WINDOW_WIDTH / 4, 11 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 8);
             leaderBoardJLabels.getThirdPlaceScore().setBounds(3 * constants.WINDOW_WIDTH / 4, 14 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 8);
             leaderBoardJLabels.getFourthPlaceScore().setBounds(3 * constants.WINDOW_WIDTH / 4, 17 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 8);
             leaderBoardJLabels.getFifthPlaceScore().setBounds(3 * constants.WINDOW_WIDTH / 4, 20 * constants.WINDOW_HEIGHT / 32, constants.WINDOW_WIDTH / 8, constants.WINDOW_HEIGHT / 8);
        }
    }
}
