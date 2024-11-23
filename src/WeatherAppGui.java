import javax.swing.*;

public class WeatherAppGui extends JFrame {
    public WeatherAppGui(){
        // setup our gui and add a title
        super("Weather App");

        // Configure gui to en the program's process once it has been closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // set the size of our gui (in pixel)
        setSize(450, 650);

        // load our gui ot the center of the screen
        setLocationRelativeTo(null);

        // make our layout manager null to manually position our components within the gui
        setLayout(null);

        // prevent ony resize og our gui
        setResizable(false);
    }
}
