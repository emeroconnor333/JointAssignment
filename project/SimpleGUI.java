import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

public class SimpleGUI {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Emer, David, Julia & Sebastian's Task Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for full panel coverage

        // Create a custom background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("background.png");
        backgroundPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Keep original layout

        JButton button1 = new JButton("CPU Information");
        button1.setPreferredSize(new Dimension(300, 200));
        button1.setBackground(new Color(100, 149, 237));  // Cornflower blue background
        button1.setForeground(Color.WHITE);               // White text

        JButton button2 = new JButton("PCI Information");
        button2.setPreferredSize(new Dimension(300, 200));
        button2.setBackground(new Color(255, 165, 0));   // Orange background
        button2.setForeground(Color.WHITE);               // White text

        JButton button3 = new JButton("USB Information");
        button3.setPreferredSize(new Dimension(300, 200));
        button3.setBackground(new Color(255, 182, 193));  // Light pink background
        button3.setForeground(Color.WHITE);               // Dark gray text

        JButton button4 = new JButton("CPU Efficiency");
        button4.setPreferredSize(new Dimension(300, 200));
        button4.setBackground(new Color(255, 0, 0));  // Red background
        button4.setForeground(Color.WHITE);           // Dark gray text

        JButton button5 = new JButton("Disk Information");
        button5.setPreferredSize(new Dimension(300, 200));
        button5.setBackground(new Color(60, 179, 113));   // Medium sea green background
        button5.setForeground(Color.WHITE);               // White text

        JButton button6 = new JButton("Memory Information");
        button6.setPreferredSize(new Dimension(300, 200));
        button6.setBackground(new Color(116, 38, 213, 255));  // Purple background
        button6.setForeground(Color.WHITE);           // Dark gray text

        backgroundPanel.add(button1);
        backgroundPanel.add(button2);
        backgroundPanel.add(button3);
        backgroundPanel.add(button4);
        backgroundPanel.add(button5);
        backgroundPanel.add(button6);

        frame.add(backgroundPanel, BorderLayout.CENTER); // Add background panel to frame

        button1.addActionListener(e -> showCPUInfoScreen());
        button2.addActionListener(e -> showPCIInfoScreen());

        button3.addActionListener(e -> {
            System.out.println("USB Information button clicked");  // Log the button click
            showUSBInfoScreen();
        });

        button4.addActionListener(e -> showCPUEfficiencyScreen());
        button5.addActionListener(e -> showDiskInfoScreen());
        button6.addActionListener(e -> showMemoryInfoScreen());

        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Custom JPanel to scale the background image
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            URL imageURL = SimpleGUI.class.getResource("/resources/" + imagePath);
            if (imageURL != null) {
                backgroundImage = new ImageIcon(imageURL).getImage();
            } else {
                System.err.println("Image not found: " + imagePath);
            }

            // Repaint when the panel is resized
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); // Scale image
            }
        }
    }

    public static void showCPUInfoScreen() {
        JFrame cpuInfoFrame = new JFrame("CPU Times");
        cpuInfoFrame.setSize(600, 600);
        cpuInfoFrame.setLayout(new BorderLayout());
        cpuTimes cpuPanel = new cpuTimes();
        cpuInfoFrame.add(cpuPanel, BorderLayout.CENTER);
        cpuInfoFrame.setLocationRelativeTo(null);
        cpuInfoFrame.setVisible(true);
    }

    public static void showPCIInfoScreen() {
        JFrame pciInfoFrame = new JFrame("PCI Information");
        pciInfoFrame.setSize(800, 600);
        pciInfoFrame.setLayout(new BorderLayout());

        PCIInfo1 pci = new PCIInfo1();
        pci.read();

        if (pci.getDevices().isEmpty()) {
            System.out.println("No PCI devices found or command did not execute properly.");
        } else {
            System.out.println("PCI devices found:");
            for (String device : pci.getDevices()) {
                System.out.println(device);
            }
        }

        JTextArea pciTextArea = new JTextArea();
        pciTextArea.setEditable(false);
        pciTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(pciTextArea);
        pciInfoFrame.add(scrollPane, BorderLayout.CENTER);

        StringBuilder displayText = new StringBuilder("Detected PCI Devices:\n\n");
        for (String deviceInfo : pci.getDevices()) {
            displayText.append(deviceInfo).append("\n\n");
        }

        pciTextArea.setText(displayText.toString());
        pciInfoFrame.setVisible(true);
    }

    public static void showUSBInfoScreen() {
        System.out.println("Opening USB Information screen...");
        JFrame usbInfoFrame = new JFrame("USB Information");
        usbInfoFrame.setSize(500, 300);
        usbInfoFrame.setLayout(new BorderLayout());

        String usbInfo = USBInfo1.showUSB();
        System.out.println(usbInfo);

        JTextArea usbInfoArea = new JTextArea(usbInfo);
        usbInfoArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        usbInfoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(usbInfoArea);
        usbInfoFrame.add(scrollPane, BorderLayout.CENTER);

        usbInfoFrame.setLocationRelativeTo(null);
        usbInfoFrame.setVisible(true);
    }

    public static void showCPUEfficiencyScreen() {

        JFrame cpuEfficiencyFrame = new JFrame("CPU Efficiency");
        cpuEfficiencyFrame.setSize(500, 300);
        cpuEfficiencyFrame.setLayout(new BorderLayout());

        cpuEfficiency cpuPanel = new cpuEfficiency();
        cpuEfficiencyFrame.add(cpuPanel, BorderLayout.CENTER);

        cpuEfficiencyFrame.setLocationRelativeTo(null);
        cpuEfficiencyFrame.setVisible(true);

    }

    public static void showDiskInfoScreen() {
        JFrame diskInfoFrame = new JFrame("Disk Information");
        diskInfoFrame.setSize(500, 300);
        diskInfoFrame.setLayout(new FlowLayout());
        JLabel diskInfoLabel = new JLabel("Displaying Disk Information...");
        diskInfoFrame.add(diskInfoLabel);
        diskInfoFrame.setVisible(true);
    }

    public static void showMemoryInfoScreen() {
        try {
            System.loadLibrary("sysinfo");
            sysInfo info = new sysInfo();
            memInfo mem = new memInfo();
            mem.read();

            int usedMemory = mem.getUsed() / (1024 * 1024);
            int totalMemory = mem.getTotal() / (1024 * 1024);

            SwingUtilities.invokeLater(() -> {
                Memory memoryFrame = new Memory(totalMemory, usedMemory);
                memoryFrame.setVisible(true);
            });
        } catch (UnsatisfiedLinkError e) {
            JOptionPane.showMessageDialog(null, "Memory library could not be loaded. Please check your configuration.");
        }
    }
}