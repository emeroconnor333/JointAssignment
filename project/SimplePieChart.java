import javax.swing.*;
import java.awt.*;

class SimplePieChart extends JPanel {
    private final long[] values;
    private final Color[] colors;

    public SimplePieChart(long[] values, Color[] colors) {
        this.values = values;
        this.colors = colors;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate total of all values
        long total = 0;
        for (long value : values) {
            total += value;
        }

        // Draw each pie section
        int startAngle = 0;
        for (int i = 0; i < values.length; i++) {
            int arcAngle = (int) Math.round((double) values[i] / total * 360);
            g.setColor(colors[i]);
            g.fillArc(50, 50, 200, 200, startAngle, arcAngle);
            startAngle += arcAngle;
        }
    }
}