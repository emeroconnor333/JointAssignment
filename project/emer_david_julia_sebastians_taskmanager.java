import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class emer_david_julia_sebastians_taskmanager {

    public static void main(String[] args) {
        // Create the main window
        JFrame frame = new JFrame("Emer, David, Julia & Sebastian's Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Load the background image
        ImageIcon backgroundImage = new ImageIcon("\"C:\\Users\\5090d\\OneDrive\\Desktop\\digital-technology-binary-code-blue-background-matrix-cyber-technology-security-abstract-circuit-tech-secure-internet-network-connection-binary-zero-one-number-ai-big-data-illustration-vector.jpg\"");

        // Create a custom JPanel with the image as background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new FlowLayout());
        // Create buttons for different system information with custom colors
        JButton button1 = new JButton("CPU Information");
        button1.setPreferredSize(new Dimension(300, 200));
        button1.setBackground(new Color(100, 149, 237));  // Cornflower blue background
        button1.setForeground(Color.WHITE);               // White text

        JButton button2 = new JButton("PCI Information");
        button2.setPreferredSize(new Dimension(300, 200));
        button2.setBackground(new Color(255, 165, 0));   // Medium turquoise background
        button2.setForeground(Color.WHITE);               // White text

        JButton button3 = new JButton("USB Information");
        button3.setPreferredSize(new Dimension(300, 200));
        button3.setBackground(new Color(255, 182, 193));  // Light pink background
        button3.setForeground(Color.DARK_GRAY);           // Dark gray text

        JButton button4 = new JButton("Disk Information");
        button4.setPreferredSize(new Dimension(300, 200));
        button4.setBackground(new Color(60, 179, 113));   // Medium sea green background
        button4.setForeground(Color.WHITE);               // White text

        // Add the buttons to the main frame
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);

        // Attach action listeners to each button
        button1.addActionListener(e -> showCPUInfoScreen());
        button2.addActionListener(e -> showPCIInfoScreen());
        button3.addActionListener(e -> showUSBInfoScreen());
        button4.addActionListener(e -> showDISCInfoScreen());

        // Configure and display the main frame
        frame.setSize(1500, 300);
        frame.setVisible(true);
    }

    public static void showCPUInfoScreen() {
        JFrame cpuInfoFrame = new JFrame("CPU Information");
        cpuInfoFrame.setSize(700, 500);
        cpuInfoFrame.setLayout(new FlowLayout());
        cpuInfoFrame.getContentPane().setBackground(Color.LIGHT_GRAY);  // Set background color
        JLabel cpuInfoLabel = new JLabel("Displaying CPU Information...");
        cpuInfoFrame.add(cpuInfoLabel);
        cpuInfoFrame.setVisible(true);
    }

    public static void showPCIInfoScreen() {
        JFrame pciInfoFrame = new JFrame("PCI Information");
        pciInfoFrame.setSize(800, 600);
        pciInfoFrame.setLayout(new BorderLayout());
        pciInfoFrame.getContentPane().setBackground(Color.LIGHT_GRAY);  // Set background color

        pciInfo pci = new pciInfo();
        pci.read();

        // Create a text area to display formatted PCI information
        JTextArea pciTextArea = new JTextArea();
        pciTextArea.setEditable(false);
        pciTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        pciTextArea.setBackground(new Color(245, 245, 245));  // Very light gray background

        // Add a scroll pane for the text area
        JScrollPane scrollPane = new JScrollPane(pciTextArea);
        pciInfoFrame.add(scrollPane, BorderLayout.CENTER);

        // Populate the text area with PCI information
        StringBuilder displayText = new StringBuilder("Detected PCI Devices:\n\n");
        for (String deviceInfo : pci.getDevices()) {
            displayText.append(deviceInfo).append("\n\n");
        }
        pciTextArea.setText(displayText.toString());

        // Make the PCI info window visible
        pciInfoFrame.setVisible(true);
    }

    public static void showUSBInfoScreen() {
        JFrame usbInfoFrame = new JFrame("USB Information");
        usbInfoFrame.setSize(700, 500);
        usbInfoFrame.setLayout(new FlowLayout());
        usbInfoFrame.getContentPane().setBackground(Color.LIGHT_GRAY);  // Set background color
        JLabel usbInfoLabel = new JLabel("Displaying USB Information...");
        usbInfoFrame.add(usbInfoLabel);
        usbInfoFrame.setVisible(true);
    }

    public static void showDISCInfoScreen() {
        JFrame discInfoFrame = new JFrame("Disk Information");
        discInfoFrame.setSize(700, 500);
        discInfoFrame.setLayout(new FlowLayout());
        discInfoFrame.getContentPane().setBackground(Color.LIGHT_GRAY);  // Set background color
        JLabel discInfoLabel = new JLabel("Displaying Disk Information...");
        discInfoFrame.add(discInfoLabel);
        discInfoFrame.setVisible(true);
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
            String[] parts = line.split(" ");
            String busDevice = parts[0];
            String vendorProductID = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
            String description = line.substring(line.indexOf("]") + 1).trim();

            // Format the information for better readability
            return String.format(
                    "Bus/Device: %s\nVendor/Product ID: %s\nDescription: %s",
                    busDevice,
                    vendorProductID,
                    description
            );
        }
    }
}
