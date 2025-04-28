package MinorProject;
import edu.princeton.cs.introcs.In;
import org.firmata4j.IODevice;
import org.firmata4j.IODeviceEventListener;
import org.firmata4j.IOEvent;
import org.firmata4j.Pin;
import org.firmata4j.ssd1306.MonochromeCanvas;
import org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

public class Menu extends TimerTask{

    private final Pin ir;
    private final Pin button;
    private final Pin pot;
    private final SSD1306 display;
    private final IODevice a;
    private final Pin buzz;
    private int opt;//option flag
    private int point;//passcode pointer
    private ArrayList<String> pointer = new ArrayList<String>(3);//menu pointer
    private int k;//screen flag
    private int t=0;//time variable
    private String[] s = {"0","0","0","0"};//passcode storage
    private String[] answer = {"1","2","3","4"};//answer


    public Menu(Pin IR, Pin BUTTON, Pin POT,Pin BUZZ, SSD1306 DISPLAY, IODevice A) {
        // Assigning variables to the pins and display
        this.buzz =BUZZ;
        this.a = A;
        this.ir = IR;
        this.button = BUTTON;
        this.pot = POT;
        this.display = DISPLAY;
    }
    @Override
    public void run() {
        double dis = 10650.08 * Math.pow(ir.getValue(), -0.935)-10;//https://gist.github.com/reefwing/6bb74409774d5152f904 the change from volt to cm
        if(dis < 50){//check for intruder
            try {
                buzz.setValue(1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (k == 1){//Select menu screen
            t++;
            display.getCanvas().setTextsize(1);
            pointer.add(0, "");
            pointer.add(1, "");
            pointer.add(2, "");
            //check for position in menu screen
            if (pot.getValue() < 1023 / 3 && opt != 3) {//bottom
            pointer.add(0, "");
                pointer.add(1, "");
                pointer.add(2, "<");//pointer placement
                point = 3;
                opt = 3;
                display.clear();
            } else if (pot.getValue() < (1023 / 3) + (1023 / 3) && pot.getValue() > 1023 / 3 && opt != 2) {//middle
                pointer.add(0, "");
                pointer.add(1, "<");//pointer placement
                pointer.add(2, "");
                point = 2;
                opt = 2;
                display.clear();
            } else if (pot.getValue() < (1023 / 3) + 2 * (1023 / 3) && pot.getValue() > 1023 / 3 && pot.getValue() > ((1023 / 3) + (1023 / 3)) && opt != 1) {//top
                pointer.add(0, "<");//pointer placement
                pointer.add(1, "");
                pointer.add(2, "");
                point = 1;
                opt = 1;
                display.clear();
            }
            //display options and pointer
            display.getCanvas().drawString(0, 0, "\n\tPass" + pointer.get(0) + "\n\tTurn off alarm" + pointer.get(1) + "\n\tShutdown system" + pointer.get(2));
            display.display();

            if (a.getPin(4).getValue() == 1) {
                if (point == 1) {

                        display.clear();
                    s[0] = "0";s[1] = "0";s[2] = "0";s[3] = "0";//reset pass code
                    try {
                        Thread.sleep(10000);//temperoraly stop
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    k=0;//change screen
                } else if (point == 2) {
                    try {
                        buzz.setValue(0);//stop alarm
                        display.clear();
                        k=0;//change screen
                        s[0] = "0";s[1] = "0";s[2] = "0";s[3] = "0";
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (point == 3) {
                    try {
                        display.clear();
                        buzz.setValue(0);
                        a.stop();
                        System.exit(1);//shutdown
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    display.clear();
                }
            }
            if(t==20){//time checker
                t=0;
                k=0;
                display.clear();}
        }
        else{
            drawPin();//call passcode screen
            System.out.println(pot.getValue()+"  "+point);//for testing
            if (pot.getValue() < 1023 / 5 && opt != 5) {// Top left
                display.getCanvas().setTextsize(2);
                point = 0;
                opt = 5;
                display.clear();
                display.getCanvas().drawRect(0,0,20,26, MonochromeCanvas.Color.BRIGHT);
            } else if (pot.getValue() < 2*(1023 / 5) && pot.getValue() > 1023/5 && opt != 4) {//Bottom left
                point = 1;
                opt = 4;
                display.clear();
                display.getCanvas().drawRect(0,36,20,26, MonochromeCanvas.Color.BRIGHT);
            } else if (pot.getValue() < 3 * (1023 / 5) && pot.getValue() > 2*(1023/5) && opt != 3) {//Enter
                point = 4;
                opt = 3;
                display.clear();
                display.getCanvas().drawRect(32,22,63,19, MonochromeCanvas.Color.BRIGHT);
            }
            else if (pot.getValue() < 4*(1023 / 5)  && pot.getValue() > 3*(1023 / 5) && opt != 2) {//Top right
                point = 2;
                opt = 2;
                display.clear();
                display.getCanvas().drawRect(106,0,20,26, MonochromeCanvas.Color.BRIGHT);
            }
            else if (pot.getValue() < 5*(1023 / 5)  && pot.getValue() > 4*(1023 / 5) && opt != 1) {//Bottom right
                point = 3;
                opt = 1;
                display.clear();
                display.getCanvas().drawRect(106,36,20,26, MonochromeCanvas.Color.BRIGHT);
            }
            if (a.getPin(4).getValue() == 1) {// check LED for button press
                int i;
                //Check position and excute
                if(point == 4){
                    if (Arrays.equals(s,answer)){
                        k=1;
                        display.clear();
                    }
                }else {
                    if (Integer.parseInt(s[point]) != 9) {
                        i = (Integer.parseInt(s[point])) + 1;
                        s[point] =Integer.toString(i);
                    } else {
                        s[point] = Integer.toString(0);
                    }
                }
            }
        }
    }
    public void drawPin(){//Draw passcode screen
        display.getCanvas().setTextsize(3);
        display.getCanvas().drawString(2,2,s[0]);//Top left
        display.getCanvas().drawString(2,38,s[1]);//Bottom left
        display.getCanvas().drawString(108,2,s[2]);//Top right
        display.getCanvas().drawString(108,38,s[3]);//Bottom right

        display.getCanvas().setTextsize(2);
        display.getCanvas().drawString(34,24,"ENTER");
        display.display();
    }
}

