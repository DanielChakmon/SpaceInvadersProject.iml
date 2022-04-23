import OvalButton.OvalButton;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
public class Instructions extends JPanel implements WindowDataReceiver{
    private Font emulogic;
    private Window window;

    public void sendWindowData(Window window){
        this.window=window;
    }
    public Instructions(int x, int y, int width, int height) {
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
        Font myFont= emulogic.deriveFont( Font.ITALIC,10);
        JLabel title=new JLabel("INSTRUCTIONS");
        title.setForeground(Color.WHITE);
        title.setFont(emulogic);
        title.setBounds((3*constants.WINDOW_WIDTH)/8,(3* constants.WINDOW_HEIGHT/32)-60,constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/8);
        JLabel bodyOne=new JLabel("Your objective is to kill the as much aliens as you can without getting killed.");
        JLabel bodyTwo=new JLabel("Use the arrow keys to move to the sides and use the space bar to shot.");
        JLabel bodyThree=new JLabel("Your score would be displayed at the top of the screen, every alien");
        JLabel bodyFour=new JLabel("you kill would increase your score according to the number of waves you cleared ");
        JLabel bodyFive=new JLabel("Your live count would be shown in the bottom left corner, when it reaches 0, you're out!");
        bodyOne.setForeground(Color.WHITE);
        bodyOne.setFont(myFont);
        bodyOne.setBounds((3*constants.WINDOW_WIDTH)/16,(9* constants.WINDOW_HEIGHT/32)-60,3*constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/16);
        bodyTwo.setForeground(Color.WHITE);
        bodyTwo.setFont(myFont);
        bodyTwo.setBounds((3*constants.WINDOW_WIDTH)/16,(13* constants.WINDOW_HEIGHT/32)-60,3*constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/16);
        bodyThree.setForeground(Color.WHITE);
        bodyThree.setFont(myFont);
        bodyThree.setBounds((3*constants.WINDOW_WIDTH)/16,(17* constants.WINDOW_HEIGHT/32)-60,3*constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/16);
        bodyFour.setForeground(Color.WHITE);
        bodyFour.setFont(myFont);
        bodyFour.setBounds((3*constants.WINDOW_WIDTH)/16,(21* constants.WINDOW_HEIGHT/32)-60,3*constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/16);
        bodyFive.setForeground(Color.WHITE);
        bodyFive.setFont(myFont);
        bodyFive.setBounds((3*constants.WINDOW_WIDTH)/16,(25* constants.WINDOW_HEIGHT/32)-60,3*constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/16);
        this.add(title);
        this.add(bodyOne);
        this.add(bodyTwo);
        this.add(bodyThree);
        this.add(bodyFour);
        this.add(bodyFive);
       Font secondFont=emulogic.deriveFont(15f);
        OvalButton returnButton=new OvalButton(OvalButton.SHAPE_OVAL,OvalButton.HORIZONTAL,constants.darkBlue,constants.newCyan,constants.bourdeaux,Color.MAGENTA);
        returnButton.setBounds((3*constants.WINDOW_WIDTH)/8,(29*constants.WINDOW_HEIGHT/32)-100,constants.WINDOW_WIDTH/4,constants.WINDOW_HEIGHT/8);
        returnButton.setText("RETURN TO MENU");
        returnButton.setFont(secondFont);
        returnButton.setForeground(Color.WHITE);
        this.add(returnButton);
        final int INSTRUCTIONS_TO_MENU=4;
        returnButton.addActionListener((event) -> {
            window.switchPanels(INSTRUCTIONS_TO_MENU);
        });
    }
}
