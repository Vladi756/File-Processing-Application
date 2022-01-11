package cw2.datadisplay;

import java.awt.Graphics;

/**
 * DataComponent that displays the data as a bar chart.
 */
public class BarChart extends AbstractChart {

  private static final long serialVersionUID = 1L;

  @Override
  protected void paintContent(final Graphics g, final int width, final int height) {
    if ( this.norm.length > 0) {
      final int barWidth = width / this.norm.length;
      for (int i = 0; i < this.norm.length; i++) {
        g.setColor(this.colors.get(i));
        g.fillRect(i * barWidth, height-(int)(this.norm[i] * height), barWidth, height);
      }
    }
  }

}
