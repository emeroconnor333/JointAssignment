import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
        JFrame discInfoFrame = new JFrame("Disk Information");

        // Set size and layout
        discInfoFrame.setSize(700, 500);
        discInfoFrame.setLayout(new FlowLayout());

        JLabel discInfoLabel = new JLabel("Displaying Disk Information...");
        discInfoFrame.add(discInfoLabel);

        // Set the DISC info window to be visible
        discInfoFrame.setVisible(true);
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
        JFrame pciInfoFrame = new JFrame("PCI Information");

        // Set size and layout
        pciInfoFrame.setSize(700, 500);
        pciInfoFrame.setLayout(new FlowLayout());

        pciInfo pci = new pciInfo();
        pci.read();

        // Display each PCI device entry in the frame
        for (String device : pci.getDevices()) {
            JLabel deviceLabel = new JLabel(device);
            pciInfoFrame.add(deviceLabel);
        }

        // Set the PCI info window to be visible
        pciInfoFrame.setVisible(true);
    }

    // Method to show USB info in a new window (now outside showPCIInfoScreen)
    public static void showUSBInfoScreen() {
        JFrame usbInfoFrame = new JFrame("USB Information");

        // Set size and layout
        usbInfoFrame.setSize(700, 500);
        usbInfoFrame.setLayout(new FlowLayout());

        JLabel usbInfoLabel = new JLabel("Displaying USB Information...");
        usbInfoFrame.add(usbInfoLabel);

        // Set the USB info window to be visible
        usbInfoFrame.setVisible(true);
    }

     {
        // Create a new JFrame for the PCI Information
        JFrame pciInfoFrame = new JFrame("PCI Information");

        // Set size and layout
        pciInfoFrame.setSize(800, 600);
        pciInfoFrame.setLayout(new BorderLayout());

        pciInfo pci = new pciInfo();
        pci.read();

        // Create a text area to display formatted PCI information
        JTextArea pciTextArea = new JTextArea();
        pciTextArea.setEditable(false);
        pciTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Use a monospaced font for better alignment

        // Add a scroll pane for easier viewing of long output
        JScrollPane scrollPane = new JScrollPane(pciTextArea);
        pciInfoFrame.add(scrollPane, BorderLayout.CENTER);

        // Populate the text area with formatted PCI information
        StringBuilder displayText = new StringBuilder();
        displayText.append("Detected PCI Devices:\n\n");

        for (String deviceInfo : pci.getDevices()) {
            displayText.append(deviceInfo).append("\n\n");
        }

        pciTextArea.setText(displayText.toString());

        // Set the PCI info window to be visible
        pciInfoFrame.setVisible(true);
    }

    // Inner class to handle PCI information retrieval using `lspci`
    static class pciInfo {

        private List<String> pciDevices;

        public pciInfo() {
            pciDevices = new ArrayList<>();
        }

        // Executes the `lspci` command and reads its output
        public void read() {
            try {
                Process process = Runtime.getRuntime().exec("lspci -nn");
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;

                // Read each line of lspci output
                while ((line = reader.readLine()) != null) {
                    pciDevices.add(parseDeviceInfo(line));
                }

                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Returns all PCI devices as a list of strings
        public List<String> getDevices() {
            return pciDevices;
        }

        // Parses a single line of `lspci` output to extract readable PCI information
        private String parseDeviceInfo(String line) {
            // Example line: "00:1f.2 SATA controller [8086:2929] Intel Corporation 82801IR/IO Controller Hub (ICH9R)"
            String[] parts = line.split(" ");
            String busDevice = parts[0];

            // Extract vendor and product ID (in square brackets, e.g., "[8086:2929]")
            String vendorProductID = line.substring(line.indexOf("[") + 1, line.indexOf("]"));

            // Get the device description (everything after the bus device and ID)
            String description = line.substring(line.indexOf("]") + 1).trim();

            // Break down information into a more readable format
            return String.format(
                    "Bus/Device: %s\nVendor/Product ID: %s\nDescription: %s",
                    busDevice,
                    vendorProductID,
                    description
            );
        }
    }
}
