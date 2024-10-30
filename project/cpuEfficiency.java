import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
public class cpuEfficiency extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawString("CPU Efficiency Grades", 10, 20);
    }

    public static int cpuCores(Integer cpuCores) {
        Integer cpuCoresGrade = null;
        cpuInfo cpu = new cpuInfo();
        if (cpuCores >= 12) {
            cpuCoresGrade = 30;
        } else if (cpuCores >= 8) {
            cpuCoresGrade = 45;
        } else if (cpuCores >= 6) {
            cpuCoresGrade = 70;
        } else if (cpuCores >= 4) {
            cpuCoresGrade = 85;
        } else if (cpuCores == 2) {
            cpuCoresGrade = 60;
        } else if (cpuCores < 2) {
            cpuCoresGrade = 30;
        }
        return cpuCoresGrade;

    }


    public static int l1AndInstructionCache(int cacheSize) {
        Integer l1AndInstructionCacheGrade = null;
        if (cacheSize >= 64) {
            l1AndInstructionCacheGrade = 70;
        } else if (cacheSize >= 32) {
            l1AndInstructionCacheGrade = 85;
        } else if (cacheSize >= 16) {
            l1AndInstructionCacheGrade = 60;
        } else if (cacheSize >= 8) {
            l1AndInstructionCacheGrade = 45;
        } else {
            l1AndInstructionCacheGrade = 30;
        }
        return l1AndInstructionCacheGrade;
    }

    public static int l2DataCache(Integer l2Cache) {
        Integer l2CacheGrade = null;
        cpuInfo cpu = new cpuInfo();
        if (l2Cache >= 512) {
            l2CacheGrade = 85;
        } else if (l2Cache >= 256) {
            l2CacheGrade = 70;
        } else if (l2Cache >= 128) {
            l2CacheGrade = 60;
        } else if (l2Cache >= 64) {
            l2CacheGrade = 45;
        } else {
            l2CacheGrade = 30;
        }
        return l2CacheGrade;
    }

    public static int l3DataCache(Integer l3Cache) {
        Integer l3CacheGrade = null;
        cpuInfo cpu = new cpuInfo();
        if (l3Cache >= 8) {
            l3CacheGrade = 85;
        } else if (l3Cache >= 6) {
            l3CacheGrade = 70;
        } else if (l3Cache >= 4) {
            l3CacheGrade = 60;
        } else if (l3Cache >= 2) {
            l3CacheGrade = 45;
        } else {
            l3CacheGrade = 30;
        }
        return l3CacheGrade;

    }

    public static int gradeAverage(ArrayList<Integer> gradeList) {
        int lengthList = gradeList.size();
        if (lengthList == 0) {
            return 0;
        } else {
            int sum = 0;
            for (int num : gradeList) {
                sum += num;
            }
            int average = sum / lengthList;
            return average;

        }
    }


    public static ArrayList<String> gradeAssignment(ArrayList<Integer> gradeList) {
        ArrayList<String> letterGradeList = new ArrayList<>();
        for (int num : gradeList) {
            if (num >= 85) {
                letterGradeList.add("A");
            } else if (num >= 70) {
                letterGradeList.add("B");
            } else if (num >= 60) {
                letterGradeList.add("C");
            } else if (num >= 45) {
                letterGradeList.add("D");
            } else if (num >= 30) {
                letterGradeList.add("F");
            }
        }
        return letterGradeList;
    }

    public static int[] gradeCount(ArrayList<String> letterGradeList) {
        int[] gradeCountList = {0, 0, 0, 0, 0};
        for (String letter : letterGradeList) {
            if (letter.equals("A")) {
                gradeCountList[0] += 1;
            } else if (letter.equals("B")) {
                gradeCountList[1] += 1;
            } else if (letter.equals("C")) {
                gradeCountList[2] += 1;
            } else if (letter.equals("D")) {
                gradeCountList[3] += 1;
            } else if (letter.equals("F")) {
                gradeCountList[4] += 1;
            }

        }
        return gradeCountList;


    }


    public static void main(String[] args) {
        cpuEfficiency cpuInstance = new cpuEfficiency();

        System.loadLibrary("sysinfo");
        cpuInfo cpu = new cpuInfo();
        int grade = 0;
        ArrayList<Integer> grades = new ArrayList<Integer>();
        int numberCpuCores=cpu.coresPerSocket();
        //int numberCpuCores = 4;
        grades.add(cpuCores(numberCpuCores));
        int l1CacheSize=cpu.l1dCacheSize();
        //int l1CacheSize = 16;
        grades.add(l1AndInstructionCache(l1CacheSize));
        int instructionCacheSize=cpu.l1iCacheSize();
        //int instructionCacheSize = 128;
        grades.add(l1AndInstructionCache(instructionCacheSize));
        int l2CacheSize=cpu.l2CacheSize();
        //int l2CacheSize = 300;
        grades.add(l2DataCache(l2CacheSize));
        int l3CacheSize=cpu.l3CacheSize();
        //int l3CacheSize = 6;
        grades.add(l3DataCache(l3CacheSize));
        int averageGrade = gradeAverage(grades);
        grades.add(averageGrade);
        ArrayList<String> gradeLetters = gradeAssignment(grades);
        int[] gradeCounts = gradeCount(gradeLetters);
        ArrayList<String> gradeLetterGraph = new ArrayList<String>();
        gradeLetterGraph.add("A");
        gradeLetterGraph.add("B");
        gradeLetterGraph.add("C");
        gradeLetterGraph.add("D");
        gradeLetterGraph.add("F");
        JFrame frame1 = new JFrame("Number of each Grade Achieved");
        HorizontalBarChart chart = new HorizontalBarChart(gradeCounts, gradeLetterGraph.toArray(new String[0]));
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(chart);
        frame1.setSize(600, 400);
        frame1.setVisible(true);


        String[] gradesTable = gradeLetters.toArray(new String[0]);

        JFrame frame = new JFrame("CPU Efficiency");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Create the cpuEfficiency panel and add it to the center of the frame
        cpuEfficiency cpuPanel = new cpuEfficiency();
        frame.add(cpuPanel, BorderLayout.CENTER);

        // Create table data
        String[][] data = {
                {"CPU Cores", String.valueOf(numberCpuCores), gradeLetters.get(0)},
                {"L1 Cache Size", String.valueOf(l1CacheSize) + " KB", gradeLetters.get(1)},
                {"L2 Cache Size", String.valueOf(l2CacheSize) + " KB", gradeLetters.get(2)},
                {"L3 Cache Size", String.valueOf(l3CacheSize) + " MB", gradeLetters.get(3)},
                {"Overall Grade", String.valueOf(averageGrade), gradeLetters.get(4)}
        };
        String[] columnNames = {"Component", "Value", "Grade"};

        // Create the JTable and add it to a JScrollPane
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scrollPane (table) to the bottom (SOUTH) of the frame
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Set the frame to be visible
        frame.setVisible(true);
    }
}