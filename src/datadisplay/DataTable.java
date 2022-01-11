package cw2.datadisplay;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * DataComponent that displays the data as a table of values.
 */
public class DataTable extends DataComponent {

  private static final long serialVersionUID = 1L;

  private final JScrollPane sp = new JScrollPane();
  
  public DataTable() {
    setLayout(new BorderLayout());
    add(this.sp);
  }

  @Override
  public void setData( final String[] labels, final double[] data) {
    super.setData(labels, data);
    final JTable table = new JTable(data.length,2);
    this.sp.setViewportView(table);
    final DefaultTableModel tableModel = new DefaultTableModel(labels.length, 3) {
      private static final long serialVersionUID = 1L;
      @Override
      public Class<?> getColumnClass(final int column) {
          return getValueAt(0, column).getClass();
      }
      @Override
      public boolean isCellEditable(final int row, final int column) {
        return false;
      }
    };
    
    tableModel.setColumnIdentifiers(new String[] { "Key", "Item", "Value" });
    for (int i = 0; i < data.length; i++) {
      final BufferedImage image = new BufferedImage(64,16,BufferedImage.TYPE_INT_RGB);
      final Graphics g = image.getGraphics();
      g.setColor(this.colors.get(i));
      g.fillRect(0, 0, 64, 16);
      tableModel.setValueAt(new ImageIcon(image), i, 0);
      tableModel.setValueAt(labels[i], i, 1);
      tableModel.setValueAt(data[i], i, 2);
    }
    table.setModel(tableModel);
  }
  
}
