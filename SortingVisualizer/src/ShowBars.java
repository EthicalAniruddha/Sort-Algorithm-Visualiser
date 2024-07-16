import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ShowBars extends JPanel {
    private int[] data;

    public ShowBars(int[] data) {
        this.data = data;
        setPreferredSize(new Dimension(1660, 895));
        setBackground(new Color(0, 0, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / data.length;

        // Draw bars
        for (int i = 0; i < data.length; i++) {
            int barHeight = (int) (data[i] * (height / (double) getMaxData()));

            // Customize bar color and appearance here
            g2d.setColor(new Color(0, 255, 0));

            Rectangle2D bar = new Rectangle2D.Double(i * barWidth, height - barHeight, barWidth, barHeight);
            g2d.fill(bar);
            g2d.setColor(Color.BLACK); // Bar outline
            g2d.draw(bar);
        }

        g2d.dispose();
    }

    private int getMaxData() {
        int max = Integer.MIN_VALUE;
        for (int value : data) {
            max = Math.max(max, value);
        }
        return max;
    }

    public void setData(int[] newData) {
        this.data = newData;
    }
}


