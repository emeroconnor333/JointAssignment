import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class HorizontalBarChart extends JPanel {
    private final int[] values;
    private final String[] categories;

    public HorizontalBarChart(int[] values, String[] categories) {
        this.values = values;
        this.categories = categories;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.PINK);
        int barHeight = 30;
        int maxBarWidth = this.getWidth() - 100;
        int maxValue = this.getMaxValue(this.values);

        for(int i = 0; i < this.values.length; ++i) {
            int barWidth = (int)((double)this.values[i] / (double)maxValue * (double)maxBarWidth);
            g.fillRect(50, 50 + i * (barHeight + 10), barWidth, barHeight);
            g.setColor(Color.BLACK);
            g.drawString(this.categories[i], 10, 70 + i * (barHeight + 10));
            g.setColor(Color.PINK);
        }

    }

    private int getMaxValue(int[] values) {
        int max = Integer.MIN_VALUE;
        int[] var3 = values;
        int var4 = values.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            int value = var3[var5];
            if (value > max) {
                max = value;
            }
        }

        return max;
    }
}