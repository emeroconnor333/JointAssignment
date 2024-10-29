import javax.swing.*;
import java.awt.*;

public class Memory extends JFrame {
    private JProgressBar memoryProgressBar;

    // Constructor to initialize memory values and setup GUI
    public Memory(int totalMemory, int usedMemory) {
        memInfo mem = new memInfo();
        mem.read();

        // Calculate the percentage of memory used
        int memoryUsagePercentage = (int) ((double) usedMemory / totalMemory * 100);

        // Initialize the progress bar
        memoryProgressBar = new JProgressBar(0, 100);  // Progress bar from 0% to 100%
        memoryProgressBar.setValue(memoryUsagePercentage);  // Set current memory usage
        memoryProgressBar.setStringPainted(true);  // Display the percentage on the bar
        memoryProgressBar.setForeground(Color.BLUE);  // Color of the used memory portion
        memoryProgressBar.setBackground(Color.LIGHT_GRAY);  // Color of the free memory portion

        // Set a custom string to show the actual memory usage in MB
        memoryProgressBar.setString((usedMemory / 1024) + "GB / " + (totalMemory / 1024) + "GB");

        // Set a larger font for the text on the progress bar
        Font font = new Font("SansSerif", Font.BOLD, 18);  // Customize the font size as needed
        memoryProgressBar.setFont(font);

        // Setup the main frame layout
        setLayout(new BorderLayout());
        add(memoryProgressBar, BorderLayout.CENTER);

        // Frame settings
        setTitle("Memory Usage");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Center the window
    }

    // Main method to run the GUI
    public static void main(String[] args) {
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();
        memInfo mem = new memInfo();
        mem.read();

        // Assuming `mem` is an instance of your Memory class with getUsed() and getTotal() methods
        int usedMemory = mem.getUsed();  // Get used memory
        int totalMemory = mem.getTotal();  // Get total memory

        SwingUtilities.invokeLater(() -> {
            Memory memoryDisplay = new Memory(totalMemory, usedMemory);
            memoryDisplay.setVisible(true);
        });
    }
}
