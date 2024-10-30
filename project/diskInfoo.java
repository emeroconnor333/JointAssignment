import javax.swing.*;
import java.awt.*;

public class diskInfoo {

    public static long totSpace(long[] spaceArray){
        long spaceTotal = 0;
        for (long i : spaceArray) {
            spaceTotal += i;
        }
        return spaceTotal;
    }

    public static void main(String[] args) {
        // Assuming diskInfo is an external class with methods that are instance methods, we create an instance
        diskInfo diskInfoInstance = new diskInfo();
        diskInfoInstance.read(); // Assuming this reads disk information

        // Access instance methods using diskInfoInstance
        int diskCounts = diskInfoInstance.diskCount();
        long[] totalDiskSpace = new long[diskCounts];
        long[] usedDiskSpace = new long[diskCounts];

        for (int i = 0; i < diskCounts; i++) {
            totalDiskSpace[i] = diskInfoInstance.getTotal(i);
            usedDiskSpace[i] = diskInfoInstance.getUsed(i);
        }

        long usedSpace = totSpace(usedDiskSpace);
        long totalSpace = totSpace(totalDiskSpace);
        long availableSpace = totalSpace - usedSpace;
        System.out.println("There is " + totalSpace + " bytes on this laptop in total");
        System.out.println(usedSpace + " bytes have been used");
        System.out.println("This means there is " + availableSpace + " bytes left");

        // Sample values and colors for the pie chart
        long[] values = {usedSpace, availableSpace};
        Color[] colors = {Color.PINK, Color.BLUE};

        // Create the panel and frame
        SimplePieChart pieChart = new SimplePieChart(values, colors);
        JFrame frame = new JFrame("Disk Space");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.add(pieChart);
        frame.setVisible(true);
    }
}