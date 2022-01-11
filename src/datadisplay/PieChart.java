package cw2.datadisplay;

import java.awt.Graphics;

/**
 * DataComponent that displays the data as a pie chart.
 */
public class PieChart extends AbstractChart {

  private static final long serialVersionUID = 1L;

  @Override
  protected void paintContent(final Graphics g, final int width, final int height) {
    if (this.norm.length > 0) {
      final int side = Math.min(width, height);
      final int xs = (width - side) / 2;
      final int ys = (height - side) / 2;
      int startAngle = 0;
      for (int i = 0; i < this.sumToOne.length; i++) {
        g.setColor(this.colors.get(i));
        final int angle = (int) Math.round(this.sumToOne[i] * 360);
        g.fillArc(xs,ys,side,side, startAngle, angle);
        startAngle += angle;
      }
    }
  }

}
