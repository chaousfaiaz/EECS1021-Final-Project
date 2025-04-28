package MinorProject;

import org.firmata4j.*;
import org.firmata4j.firmata.FirmataDevice;
import org.firmata4j.ssd1306.SSD1306;

import java.io.IOException;
import java.util.Timer;
public class MajorProj{
    public static void main(String[] args) throws InterruptedException, IOException {
        IODevice arduino = new FirmataDevice("COM3");
        //Start and initialize arduino board connection
        arduino.start();
        arduino.ensureInitializationIsDone();
        // Initialize OLED display
        I2CDevice i2cObject = arduino.getI2CDevice((byte) 0x3C); // Use 0x3C for the Grove OLED
        SSD1306 display = new SSD1306(i2cObject, SSD1306.Size.SSD1306_128_64); // 128x64 OLED SSD1515
        display.init();
        Pin pot = arduino.getPin(14);// Pin A0
        Pin button = arduino.getPin(6);// Pin D6
        Pin IR = arduino.getPin(17);// Pin A3
        Pin buzz = arduino.getPin(5);// Pin A2
        //Set pin modes
        pot.setMode(Pin.Mode.ANALOG);
        button.setMode(Pin.Mode.INPUT);
        IR.setMode(Pin.Mode.ANALOG);
        buzz.setMode(Pin.Mode.OUTPUT);
        //Menu
        var menu = new Menu(IR,button,pot,buzz,display,arduino);
        new Timer().schedule(menu,0,100);
        arduino.addEventListener(new ButtonListener(button,arduino));
    }
}
