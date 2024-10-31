import javax.swing.*;
import java.awt.*;

public class Memory extends JFrame {
    private JProgressBar memoryProgressBar;

    // setup GUI
    public Memory(int totalMemory, int usedMemory) {

        // percentage of memory used
        int memoryUsagePercentage = (int) ((double) usedMemory / totalMemory * 100);

        // progress bar
        memoryProgressBar = new JProgressBar(0, 100);
        memoryProgressBar.setValue(memoryUsagePercentage);
        memoryProgressBar.setStringPainted(true);
        memoryProgressBar.setForeground(Color.BLUE);
        memoryProgressBar.setBackground(Color.LIGHT_GRAY);

        // format memory usage to 2 decimal places
        String formattedUsedMemory = String.format("%.2f", usedMemory / 1.0);
        String formattedTotalMemory = String.format("%.2f", totalMemory / 1.0);

        // set the formatted string on the progress bar
        memoryProgressBar.setString(formattedUsedMemory + "GB / " + formattedTotalMemory + "GB");

        // font
        Font font = new Font("SansSerif", Font.BOLD, 18);
        memoryProgressBar.setFont(font);

        // layout
        setLayout(new BorderLayout());
        add(memoryProgressBar, BorderLayout.CENTER);

        // frame
        setTitle("Memory Usage");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Only close the Memory window
        setLocationRelativeTo(null);  // centers the window
    }

    // runs the GUI
    public static void main(String[] args) {
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();
        memInfo mem = new memInfo();
        mem.read();

        int usedMemory = mem.getUsed() / (1024 * 1024);  // used memory in GB
        int totalMemory = mem.getTotal() / (1024 * 1024);  // total memory in GB

        SwingUtilities.invokeLater(() -> {
            Memory memoryFrame = new Memory(totalMemory, usedMemory);
            memoryFrame.setVisible(true);
        });
    }
}
