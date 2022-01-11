package cw2.datadisplay;

import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;

public abstract class AbstractChart extends DataComponent {

  private static final long serialVersionUID = 1L;

  public AbstractChart() {
    setBorder(BorderFactory.createTitledBorder(getClass().getSimpleName()));
  }
  
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g = g.create();
    final Insets insets = getInsets();   
    final int width = getWidth() - (insets.left + insets.right);
    final int height = getHeight() - (insets.top + insets.bottom);
    g.translate(insets.left, insets.top);
    g.clipRect(0, 0, width, height);
    paintContent(g, width, height);
  }
  
  protected abstract void paintContent(Graphics g, int width, int height);

}
