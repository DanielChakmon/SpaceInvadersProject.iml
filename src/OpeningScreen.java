import OvalButton.OvalButton;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpeningScreen extends JPanel implements WindowDataReceiver{
    private Font emulogic;
    private Window window;
public OpeningScreen (int x, int y, int width, int height){
    this.setBackground(Color.BLACK);
    this.setLayout(null);
    try {

        emulogic=Font.createFont(Font.TRUETYPE_FONT, new File("src/emulogic.ttf")).deriveFont(Font.BOLD&Font.CENTER_BASELINE,20);
        GraphicsEnvironment ge= GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/emulogic.ttf")));
    }
    catch (IOException | FontFormatException e){
    }
    Constants constants=new Constants();

    this.setBounds(x, y, width, height);
JLabel title=new JLabel("SPACE INVADERS");
//Font myFont=emulogic.deriveFont(20f);
title.setFont(emulogic);
title.setForeground(Color.WHITE);
title.setBounds((3*constants.WINDOW_WIDTH)/8,(3* constants.WINDOW_HEIGHT/32)-60,constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/8);
this.add(title);
    OvalButton playButton=new OvalButton(OvalButton.SHAPE_OVAL,OvalButton.HORIZONTAL,constants.darkBlue,constants.newCyan,constants.bourdeaux,Color.MAGENTA);
    playButton.setBounds((3*constants.WINDOW_WIDTH)/8,(11*constants.WINDOW_HEIGHT/32)-60,constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/8);
    playButton.setText("PLAY!");
    playButton.setFont(emulogic);
    playButton.setForeground(Color.WHITE);
    this.add(playButton);
    final int MENU_TO_GAME_SCREEN=1;
    OvalButton scoreBoardButton=new OvalButton(OvalButton.SHAPE_OVAL,OvalButton.HORIZONTAL,constants.darkBlue,constants.newCyan,constants.bourdeaux,Color.MAGENTA);
    scoreBoardButton.setBounds((3*constants.WINDOW_WIDTH)/8,(19*constants.WINDOW_HEIGHT/32)-60,constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/8);
    scoreBoardButton.setText("SCOREBOARD");
    scoreBoardButton.setFont(emulogic);
    scoreBoardButton.setForeground(Color.WHITE);
    this.add(scoreBoardButton);
    final int MENU_TO_SCORE_BOARD = 2;
    playButton.addActionListener((event) -> {
        window.switchPanels(MENU_TO_GAME_SCREEN);
    });
    scoreBoardButton.addActionListener((event) -> {
        window.switchPanels(MENU_TO_SCORE_BOARD);
    });
    OvalButton howToPlayButton=new OvalButton(OvalButton.SHAPE_OVAL,OvalButton.HORIZONTAL,constants.darkBlue,constants.newCyan,constants.bourdeaux,Color.MAGENTA);
    howToPlayButton.setBounds((3*constants.WINDOW_WIDTH)/8,(27*constants.WINDOW_HEIGHT/32)-60,constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/8);
    howToPlayButton.setText("How to play");
    howToPlayButton.setFont(emulogic);
    howToPlayButton.setForeground(Color.WHITE);
    this.add(howToPlayButton);
    final int MENU_TO_INSTRUCTIONS=3;
    howToPlayButton.addActionListener((event) -> {
        window.switchPanels(MENU_TO_INSTRUCTIONS);
    });
}
public void sendWindowData(Window window){
    this.window=window;
}
}