package cw2.datadisplay;

import java.awt.Color;

/**
 * Class that creates a palette of n colours that are distinct
 * from one another.
 */
public class Palette {

  private final float[] hues;
  
  public Palette(final int numberOfColours) {
    this.hues = new float[numberOfColours];
    final float[] local = new float[numberOfColours];
    // create a spectrum
    for( int i = 0; i < numberOfColours; i++) {
      local[i] = ((float)i) / numberOfColours;
    }
    // intersperse colours to avoid similar colours adjacent
    final int half = numberOfColours / 2;
    for (int i = 0, j = 0; i < numberOfColours; i += 2, j++) {
      this.hues[i] = local[j];
    }
    for (int i = 1, j = half; i < numberOfColours; i += 2, j++) {
      this.hues[i] = local[j];
    }
  }

  /**
   * Get a colour, specifying the saturation and brightness values.
   * The index determines the hue.
   */
  public Color get( final int index, final float saturation, final float brightness) {
    return new Color(Color.HSBtoRGB(this.hues[index], saturation, brightness));
  }
  
  /**
   * Get a colour whose hue is determined by the index value, and whose
   * saturation and brightness are both 1.00.
   */
  public Color get( final int index) {
    return get(index, 1f, 1f);
  }
}
