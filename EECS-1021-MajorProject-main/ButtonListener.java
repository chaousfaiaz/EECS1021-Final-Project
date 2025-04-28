package MinorProject;
import org.firmata4j.IODevice;
import org.firmata4j.IODeviceEventListener;
import org.firmata4j.IOEvent;
import org.firmata4j.Pin;
import java.io.IOException;
public class ButtonListener implements IODeviceEventListener {
    private final Pin buttonPin;
    private final IODevice arduino;
    // constructor
    ButtonListener(Pin buttonPin, IODevice A) {
        this.buttonPin = buttonPin;
        this.arduino = A;
    }

    @Override
    public void onStart(IOEvent event) {

    }

    @Override
    public void onStop(IOEvent event) {

    }

    @Override
    public void onPinChange(IOEvent event) {
        // Return right away if the even isn't from the Button.
        if (event.getPin().getIndex() != buttonPin.getIndex()) {
            return;
        }
        //turn on LED light once pressed
        try {
                if (arduino.getPin(4).getValue() == 0) {
                    arduino.getPin(4).setValue(1);
                } else{
                    arduino.getPin(4).setValue(0);
                }
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onMessageReceive(IOEvent event, String message) {

    }
}