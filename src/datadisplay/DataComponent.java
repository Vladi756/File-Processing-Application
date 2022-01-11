package cw2.datadisplay;

import java.awt.Dimension;

import javax.swing.JComponent;

/**
 * Abstract parent class for components that display data in some way.
 * Mainly this sets up the data values in various representations and 
 * provides a palette of colours with one colour per data item.
 */
public abstract class DataComponent extends JComponent {

  private static final long serialVersionUID = 1L;

  protected String[] labels = new String[0];
  protected double[] data = new double[0];
  protected double[] sumToOne = new double[0];
  protected double[] norm = new double[0];
  
  protected Palette colors;
  
  public DataComponent() {
    setPreferredSize(new Dimension(200,200));
  }
  
  public void setData( final String[] labels, final double[] data) {
    this.labels = labels;
    this.data = data;
    double total = 0;
    for (int i = 0; i < this.data.length; i++) {
      total += this.data[i];
    }
    
    double max = 0;
    this.sumToOne = new double[this.data.length];
    for (int i = 0; i < this.data.length; i++) {
      this.sumToOne[i] = this.data[i] / total;      
      if ( this.sumToOne[i] > max) { max = this.sumToOne[i]; }
    }
    
    this.norm = new double[this.data.length];
    for (int i = 0; i < this.data.length; i++) {
      this.norm[i] = this.sumToOne[i] / max;
    }
    this.colors = new Palette(data.length);
    repaint();
  }
  
}
