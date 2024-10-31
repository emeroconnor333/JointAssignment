/*
Takes how much time CPU has been in user, idle and system modes
and displays this in a pie chart.
*/
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class cpuTimes extends JPanel {
    // Colors for the pie slices
    private Color[] colors = {new Color(67, 56, 120), new Color(126, 96, 191), new Color(228, 177, 240), new Color(67, 56, 140), new Color(67, 96, 120), new Color(17, 56, 120), new Color(67, 56, 10), new Color(67, 6, 120), new Color(67, 56, 50)};

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            drawPieChart(g);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to draw the pie chart
    private void drawPieChart(Graphics g) throws IOException {
        String filePath = "/proc/stat";
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        String line = br.readLine();
        String valuesString = "";
        if (line.contains("cpu ")) {
            valuesString = line.substring(5);
        }

        String[] valuesStringArray = valuesString.split(" ");
        int[] valuesArray = new int[valuesStringArray.length];
        for (int i = 0; i < valuesStringArray.length; i++) {
            valuesArray[i] = Integer.parseInt(valuesStringArray[i]);
        }

        int totalCpuTime = 0;
        for (int value : valuesArray) {
            totalCpuTime += value;
        }
        totalCpuTime -= valuesArray[3]; //idle time drowns out all other values

        double userPercent = (double) valuesArray[0] / totalCpuTime * 100;
        double nicePercent = (double) valuesArray[1] / totalCpuTime * 100;
        double systemPercent = (double) valuesArray[2] / totalCpuTime * 100;
        double iowaitPercent = (double) valuesArray[4] / totalCpuTime * 100;
        double irqPercent = (double) valuesArray[5] / totalCpuTime * 100;
        double softirqPercent = (double) valuesArray[6] / totalCpuTime * 100;
        double stealPercent = (double) valuesArray[7] / totalCpuTime * 100;
        double guestPercent = (double) valuesArray[8] / totalCpuTime * 100;
        double guestNicePercent = (double) valuesArray[9] / totalCpuTime * 100;

        double[] values = {userPercent, nicePercent, systemPercent, iowaitPercent,
                irqPercent, softirqPercent, stealPercent, guestPercent, guestNicePercent};

        String[] valuesNames = {"user", "nice", "system", "IO wait",
                "IRQ", "soft IRQ", "steal", "guest", "guest nice"};

        Graphics2D g2d = (Graphics2D) g;

        // Get dimensions for the chart
        int width = getWidth();
        int height = getHeight();

        // Coordinates and size for the pie chart
        int centerX = width / 16;
        int centerY = height / 16;
        int pieWidth = 200;
        int pieHeight = 200;

        // Start at the top for the first pie slice
        int startAngle = 90;

        // Loops through the values and draws each pie slice
        for (int i = 0; i < values.length; i++) {
            // Calculate the angle for each slice
            int arcAngle = (int) Math.round(values[i] * 360 / 100);
            g2d.setColor(colors[i]);
            g2d.fillArc(centerX, centerY, pieWidth, pieHeight, startAngle, arcAngle); //actually draws the slice

            // Moves the start angle forward by the current slice's angle
            startAngle += arcAngle;
        }

        //prints CPU times underneath
        g.setFont(new Font("SansSerif", Font.BOLD, 18));
        g.setColor(Color.BLACK);
        g.drawString("CPU time spent in:", 50, 255);
        for (int i = 0; i < 9; i++) {
            g.drawString(valuesNames[i] + " mode:  " + String.format("%.2f", values[i]) + "%", 50, 280 + i * 20);
        }

        //finds most used mode
        String modeMostUsed = "";
        double maxTime = (double) 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > maxTime) {
                maxTime = values[i];
                modeMostUsed = valuesNames[i];
            }
        }

        //chooses message based on mode most used
        String[] modeMostUsedMessage = new String[3];
        if (modeMostUsed == "system") {
            modeMostUsedMessage[0] = "-managing memory";
            modeMostUsedMessage[1] = "-handling file access";
            modeMostUsedMessage[2] = "-scheduling tasks";
        }
        else if (modeMostUsed == "user") {
            modeMostUsedMessage[0] = "-processing user commands";
            modeMostUsedMessage[1] = "-running apps & software";
            modeMostUsedMessage[2] = "-requesting system services";
        }
        else if (modeMostUsed == "soft IRQ") {
            modeMostUsedMessage[0] = "-networking";
            modeMostUsedMessage[1] = "-disk I/O operations";
            modeMostUsedMessage[2] = "-task scheduling";
        }

        //prints most used mode and what that means
        if (modeMostUsed.equals("system") || modeMostUsed.equals("user") || modeMostUsed.equals("soft IRQ")) {
            g.drawString("The mode most used is " + modeMostUsed, 300, 255);
            g.drawString("which includes tasks like:", 300, 275);
            g.drawString(modeMostUsedMessage[0], 300, 305);
            g.drawString(modeMostUsedMessage[1], 300, 325);
            g.drawString(modeMostUsedMessage[2], 300, 345);
        }
    }


    public static void main(String[] args) throws IOException {
        // Creates our JFrame window
        JFrame frame = new JFrame("CPU Times");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //so you can x out of it
        frame.setSize(600, 600);

        frame.add(new cpuTimes()); //adding in our pie chart

        frame.setVisible(true); //so you can see the window

    }
}