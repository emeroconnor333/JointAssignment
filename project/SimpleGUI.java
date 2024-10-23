import javax.swing.*;
import java.awt.*;

public class SimpleGUI {
    public static void main(String[] args) {
        // Create a new JFrame (window)
        JFrame frame = new JFrame("Emer, David, Julia & Sebastian's Task Manager");

        // Set the default close operation to exit the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout manager to FlowLayout
        frame.setLayout(new FlowLayout());

        // Create the buttons
        JButton button1 = new JButton("CPU Information");
        button1.setPreferredSize(new Dimension(250, 150));  // Set button size

        JButton button2 = new JButton("PCI Information");
        button2.setPreferredSize(new Dimension(250, 150));  // Set button size

        JButton button3 = new JButton("USB Information");
        button3.setPreferredSize(new Dimension(250, 150));  // Set button size

        // Add the buttons to the window
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);

        // Set the window size
        frame.setSize(1500, 1000);

        // Make the window visible
        frame.setVisible(true);
    }
}
