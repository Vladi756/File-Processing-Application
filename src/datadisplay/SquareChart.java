package cw2.datadisplay;

import java.awt.Graphics;
import java.util.Map;
import java.util.TreeMap;

/**
 * DataComponent that displays the data as a set of nested squares,
 * where the area of each square represents the data value.
 */
public class SquareChart extends AbstractChart {

  private static final long serialVersionUID = 1L;

  private int[] paletteMap;
  
  @Override
  public void setData( final String[] labels, final double[] data) {
    // data MUST be sorted into descending order to be rendered correctly in this chart
    TreeMap<Number,Integer> sorter = new TreeMap<>();
    for (int i = 0; i < data.length; i++) {
      sorter.put(data[i], i);
    }
    String[] newLabels = new String[data.length];
    double[] newData = new double[data.length];
    this.paletteMap = new int[data.length];
    int i = data.length - 1;
    for(Map.Entry<Number,Integer> me : sorter.entrySet()) {
      newData[i] = Math.sqrt(me.getKey().doubleValue()); // take square root to get area relative squares
      newLabels[i] = labels[me.getValue()];
      this.paletteMap[i] = me.getValue();
      i--;
    }
    super.setData(newLabels, newData);
  }
  
  @Override
  protected void paintContent(final Graphics g, final int width, final int height) {
    if ( this.norm.length > 0) {
      int side = Math.min(width, height);
      for (int i = 0; i < this.norm.length; i++) {
        g.setColor(this.colors.get(this.paletteMap[i]));
        g.fillRect(0, 0, (int)(this.norm[i] * side), (int)(this.norm[i] * side));
      }
    }
  }

}
