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
        button1.addActionListener(e -> template.showCPU());       // Open new screen for CPU info
        button2.addActionListener(e -> template.showPCI());                 // Open new screen for PCI info
        button3.addActionListener(e -> template.showUSB());                 // Open new screen for USB info
        button4.addActionListener(e -> template.showDISC());      // Open new screen for Disk info

        // Set the window size for the main frame
        frame.setSize(1500, 300);

        // Make the main window visible
        frame.setVisible(true);
    }

    public static void showDISCInfoScreen() {
        JFrame cpuInfoFrame = new JFrame("Disc Information");

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
    public static void displayAllPCIInfo() {
        pciInfo pci = new pciInfo();
        pci.read();

        int busCount = pci.busCount();

        JFrame pciInfoFrame = new JFrame("PCI Information");

        // Set size and layout
        pciInfoFrame.setSize(700, 500);
        pciInfoFrame.setLayout(new FlowLayout());

        JLabel pcibusInfoLabel = new JLabel("Your computer has " + pci.busCount() + " PCI buses");
        JLabel pcibusInfoDescriptionLabel = new JLabel("The average computer can have up to 256 PCI buses");

        pciInfoFrame.add(pcibusInfoLabel);
        pciInfoFrame.add(pcibusInfoDescriptionLabel);

        // Loop through PCI devices and functions
        for (int i = 0; i < pci.busCount(); i++) {
            for (int j = 0; j < pci.deviceCount(i); j++) {
                if (pci.functionCount(i, j) > 0) {
                    JLabel pcifunctionInfoLabel = new JLabel("Bus " + i + " Device " + j + " has " + pci.functionCount(i, j) + " functions");
                    JLabel pcifunctionInfoDescriptionLabel = new JLabel("The average bus can have up to 8 functions");
                    pciInfoFrame.add(pcifunctionInfoLabel);
                    pciInfoFrame.add(pcifunctionInfoDescriptionLabel);

                    for (int k = 0; k < pci.functionCount(i, j); k++) {
                        if (pci.functionPresent(i, j, k) > 0) {
                            JLabel pcivendorInfoDescriptionLabel = new JLabel("Bus " + i + " device " + j + " function " + k +
                                    " has vendor " + String.format("0x%04X", pci.vendorID(i, j, k)) +
                                    " and product " + String.format("0x%04X", pci.productID(i, j, k)));
                            pciInfoFrame.add(pcivendorInfoDescriptionLabel);
                        }
                    }
                }
            }
        }

        // Set the PCI info window to be visible
        pciInfoFrame.setVisible(true);
    }

    // Method to show USB info in a new window
    public static void showUSB() {
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
}