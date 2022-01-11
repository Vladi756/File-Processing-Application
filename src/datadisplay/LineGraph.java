package cw2.datadisplay;

import java.awt.Color;
import java.awt.Graphics;

/**
 * DataComponent that displays the data as a line graph.
 */
public class LineGraph extends AbstractChart {

  private static final long serialVersionUID = 1L;

  @Override
  protected void paintContent(final Graphics g, final int width, final int height) {
    if ( this.norm.length > 0) {
      final int barWidth = width / this.norm.length;
      final int effectiveHeight = (int) (height * 0.8);
      final int heightShift = (height - effectiveHeight) / 2;
      int lx = 0;
      int ly = 0;
      g.setColor(Color.BLACK);
      for (int i = 0; i < this.norm.length; i++) {
        final int px = (i * barWidth) + (barWidth / 2);
        final int py = (effectiveHeight-(int)(this.norm[i] * effectiveHeight)) + heightShift;
        if ( i > 0) {
          g.drawLine(lx,ly,px,py);
        }
        lx = px; ly = py;
      }
      for (int i = 0; i < this.norm.length; i++) {
        g.setColor(this.colors.get(i));     
        final int px = (i * barWidth) + (barWidth / 2);
        final int py = (effectiveHeight-(int)(this.norm[i] * effectiveHeight)) + heightShift;          
        g.fillRect(px - 2, py - 2, 4, 4);
      }
    }
  }

}
