import javax.swing.*;
import java.awt.*;

public class emer_david_julia_sebastians_taskmanager {

    public static void main(String[] args) {
        // Create a new JFrame (main window)
        JFrame frame = new JFrame("Emer, David, Julia & Sebastian's Task Manager");

        // Set the default close operation to exit the application
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set layout manager to FlowLayout
        frame.setLayout(new FlowLayout());

        // Create the buttons
        JButton button1 = new JButton("CPU Information");
        button1.setPreferredSize(new Dimension(300, 200));  // button size

        JButton button2 = new JButton("PCI Information");
        button2.setPreferredSize(new Dimension(300, 200));

        JButton button3 = new JButton("USB Information");
        button3.setPreferredSize(new Dimension(300, 200));

        JButton button4 = new JButton("Disk Information");
        button4.setPreferredSize(new Dimension(300, 200));

        // Add the buttons to the main window
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);

        // Add action listeners to buttons to show new windows with placeholder information
        button1.addActionListener(e -> showCPUInfoScreen());  // Open new screen for CPU info
        button2.addActionListener(e -> showPCIInfoScreen());  // Open new screen for PCI info
        button3.addActionListener(e -> showUSBInfoScreen());  // Open new screen for USB info
        button4.addActionListener(e -> showDISCInfoScreen());  // Open new screen for Disk info

        // Set the window size for the main frame
        frame.setSize(1500, 300);

        // Make the main window visible
        frame.setVisible(true);
    }

    public static void showDISCInfoScreen() {
        JFrame cpuInfoFrame = new JFrame("Disk Information");

        // Set size and layout
        cpuInfoFrame.setSize(700, 500);
        cpuInfoFrame.setLayout(new FlowLayout());

        JLabel usbInfoLabel = new JLabel("Displaying Disc Information...");
        cpuInfoFrame.add(usbInfoLabel);

        // Set the CPU info window to be visible
        cpuInfoFrame.setVisible(true);
    }

    // Method to show CPU info in a new window
    public static void showCPUInfoScreen() {
        // Create a new JFrame for the CPU Information
        JFrame cpuInfoFrame = new JFrame("CPU Information");

        // Set size and layout
        cpuInfoFrame.setSize(700, 500);
        cpuInfoFrame.setLayout(new FlowLayout());

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
        pciInfoFrame.setSize(700, 500);
        pciInfoFrame.setLayout(new FlowLayout());

        pciInfo pci = new pciInfo();
        pci.read();

        // Create a label to display a placeholder message for PCI information
        JLabel pciInfoLabel = new JLabel("\nThis machine has " + pci.busCount() +" PCI buses ");


        pciInfoFrame.add(pciInfoLabel);

        // Set the PCI info window to be visible
        pciInfoFrame.setVisible(true);
    }

    // Method to show USB info in a new window
    public static void showUSBInfoScreen() {
        // Create a new JFrame for the USB Information
        JFrame usbInfoFrame = new JFrame("USB Information");

        // Set size and layout
        usbInfoFrame.setSize(700, 500);
        usbInfoFrame.setLayout(new FlowLayout());

        // Create a label to display a placeholder message for USB information
        JLabel usbInfoLabel = new JLabel("Displaying USB Information...");
        usbInfoFrame.add(usbInfoLabel);

        // Set the USB info window to be visible
        usbInfoFrame.setVisible(true);
    }

    // Define the pciInfo class outside the main method
    static class pciInfo {
        public void read() {
            // Implementation for reading PCI information
        }

        public int busCount() {
            // Return the number of PCI buses
            return 0; // Placeholder return value
        }
    }
}