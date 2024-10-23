import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI {

    public static void main(String[] args) {
        // Create a new JFrame (main window)
        JFrame frame = new JFrame("Emer, David, Julia & Sebastian's Task Manager");

        // Set the default close operation to exit the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout manager to FlowLayout
        frame.setLayout(new FlowLayout());

        // Create the buttons
        JButton button1 = new JButton("CPU Information");
        button1.setPreferredSize(new Dimension(300, 200));  // Set button size

        JButton button2 = new JButton("PCI Information");
        button2.setPreferredSize(new Dimension(300, 200));  // Set button size

        JButton button3 = new JButton("USB Information");
        button3.setPreferredSize(new Dimension(300, 200));  // Set button size

        // Add the buttons to the main window
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);

        // Add action listeners to buttons to show new windows with placeholder information
        button1.addActionListener(e -> showCPUInfoScreen());  // Open new screen for CPU info
        button2.addActionListener(e -> showPCIInfoScreen());  // Open new screen for PCI info
        button3.addActionListener(e -> showUSBInfoScreen());  // Open new screen for USB info

        // Set the window size for the main frame
        frame.setSize(1500, 1000);

        // Make the main window visible
        frame.setVisible(true);
    }

    // Method to show CPU info in a new window
    public static void showCPUInfoScreen() {
        // Create a new JFrame for the CPU Information
        JFrame cpuInfoFrame = new JFrame("CPU Information");

        // Set size and layout
        cpuInfoFrame.setSize(500, 300);
        cpuInfoFrame.setLayout(new FlowLayout());

        // Create a label to display a placeholder message for CPU information
        JLabel cpuInfoLabel = new JLabel("Displaying CPU Information...");
        cpuInfoFrame.add(cpuInfoLabel);

        // Set the CPU info window to be visible
        cpuInfoFrame.setVisible(true);
    }

    // Method to show PCI info in a new window
    public static void showPCIInfoScreen() {
        // Create a new JFrame for the PCI Information
        JFrame pciInfoFrame = new JFrame("PCI Information");

        // Set size and layout
        pciInfoFrame.setSize(500, 300);
        pciInfoFrame.setLayout(new FlowLayout());

        // Create a label to display a placeholder message for PCI information
        JLabel pciInfoLabel = new JLabel("Displaying PCI Information...");
        pciInfoFrame.add(pciInfoLabel);

        // Set the PCI info window to be visible
        pciInfoFrame.setVisible(true);
    }

    // Method to show USB info in a new window
    public static void showUSBInfoScreen() {
        // Create a new JFrame for the USB Information
        JFrame usbInfoFrame = new JFrame("USB Information");

        // Set size and layout
        usbInfoFrame.setSize(500, 300);
        usbInfoFrame.setLayout(new FlowLayout());

        // Create a label to display a placeholder message for USB information
        JLabel usbInfoLabel = new JLabel("Displaying USB Information...");
        usbInfoFrame.add(usbInfoLabel);

        // Set the USB info window to be visible
        usbInfoFrame.setVisible(true);
    }
}
