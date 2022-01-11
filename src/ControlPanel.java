package cw2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ControlPanel extends JPanel {

  private static final long serialVersionUID = 1L;
  
  private final JButton browseButton = new JButton("Choose folder");
  private final JTextField folderField = new JTextField( 60);
  private final JRadioButton byLabelRadioButton = new JRadioButton("by name");
  private final JRadioButton byValueRadioButton = new JRadioButton("by value");
  
  private final JFileChooser chooser = new JFileChooser();
  
  public ControlPanel(Dashboard_View dashboard) {
    super( new BorderLayout());
    
    final JPanel folderPanel = new JPanel( new BorderLayout());
    folderPanel.setBorder(BorderFactory.createTitledBorder("Folder"));
    folderPanel.add(this.folderField, BorderLayout.CENTER);
    folderPanel.add(this.browseButton, BorderLayout.EAST);
    
    final JPanel sortPanel = new JPanel( new GridLayout( 0, 2));
    sortPanel.setBorder(BorderFactory.createTitledBorder("Sort"));
    sortPanel.add(this.byLabelRadioButton);
    sortPanel.add(this.byValueRadioButton);
    
    final ButtonGroup buttonGroup = new ButtonGroup();
    buttonGroup.add(this.byLabelRadioButton);
    buttonGroup.add(this.byValueRadioButton);
    
    add( folderPanel, BorderLayout.CENTER);
    add( sortPanel, BorderLayout.EAST);
    
    this.chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    this.chooser.setMultiSelectionEnabled(false);
    
    this.browseButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(final ActionEvent e) {
        final int choice = ControlPanel.this.chooser.showOpenDialog(ControlPanel.this);
        if ( choice == JFileChooser.APPROVE_OPTION) {
          dashboard.updateFolder(ControlPanel.this.chooser.getSelectedFile());
        }
      }});
    
    this.byLabelRadioButton.setSelected(true);
    this.byLabelRadioButton.addActionListener((ev) -> dashboard.updateData());
    this.byValueRadioButton.addActionListener((ev) -> dashboard.updateData());
    this.folderField.addActionListener((ev) -> dashboard.updateFolder(new File( this.folderField.getText())));
  }
  
  /** Returns true if the "by name" radio button is selected. */
  public boolean sortedOnLabels() {
    return this.byLabelRadioButton.isSelected();
  }
  
  /** Updates the text in the folderField when a new folder has been selected in the chooser. */
  public void setFolder( final File folder) {
    this.folderField.setText( folder.getPath());
  }
    
}
