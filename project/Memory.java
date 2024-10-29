import javax.swing.*;
import java.awt.*;

public class Memory extends JFrame {
    private JProgressBar memoryProgressBar;

    // setup GUI
    public Memory(int totalMemory, int usedMemory) {

        // percentage of memory used
        int memoryUsagePercentage = (int) ((double) usedMemory / totalMemory * 100);

        //progress bar
        memoryProgressBar = new JProgressBar(0, 100);
        memoryProgressBar.setValue(memoryUsagePercentage); 
        memoryProgressBar.setStringPainted(true);  
        memoryProgressBar.setForeground(Color.BLUE); 
        memoryProgressBar.setBackground(Color.LIGHT_GRAY);  

        // print memory usage
        memoryProgressBar.setString((usedMemory / 1024) + "GB / " + (totalMemory / 1024) + "GB");

        // font
        Font font = new Font("SansSerif", Font.BOLD, 18); 
        memoryProgressBar.setFont(font);

        // layout
        setLayout(new BorderLayout());
        add(memoryProgressBar, BorderLayout.CENTER);

        // frame
        setTitle("Memory Usage");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // centers the window
    }

    // runs the GUI
    public static void main(String[] args) {
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();
        memInfo mem = new memInfo();
        mem.read();

        int usedMemory = mem.getUsed();  // used memory
        int totalMemory = mem.getTotal();  // total memory

        SwingUtilities.invokeLater(() -> {
            Memory Memory = new Memory(totalMemory, usedMemory);
            Memory.setVisible(true);
        });
    }
}
