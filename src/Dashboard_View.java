package cw2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import cw2.ChartFactory.ChartType;
import cw2.datadisplay.DataComponent;
import cw2.datadisplay.DataTable;

// Displays data components to the user.

public class Dashboard_View extends JFrame {

	private static final long serialVersionUID = 1L;

	private Dashboard_Controller controller;
	private ControlPanel controlPanel;
	private final JPanel dataComponentPanel;

	private final TreeMap<String, Number> map = new TreeMap<String, Number>();
	private final Map<ChartType, DataComponent> dataComponentObjects = new HashMap<>();
	private final LinkedHashSet<DataComponent> dataComponents = new LinkedHashSet<DataComponent>();

	public JPanel getDataComponentPanel() {return this.dataComponentPanel;}
	
	public LinkedHashSet<DataComponent> getDataComponents() {return this.dataComponents;}

	public Map<ChartType, DataComponent> getDataComponentObjects() {return this.dataComponentObjects;}

	public TreeMap<String, Number> getMap() {return this.map;}

	public ControlPanel getControlPanel() {return this.controlPanel;}

	public void updateFolder(File f) {this.controller.setFolder(f);}

	public Dashboard_View(Dashboard_Controller c) {
		this.controller = c;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.controlPanel = new ControlPanel(this);

		final JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(this.controlPanel, BorderLayout.NORTH);

		// add a menu to the window
		final JMenuBar menuBar = new JMenuBar();
		final JMenu chartMenu = new JMenu("Charts");
		for (final ChartType type : ChartType.values()) {
			final JMenuItem item = new JMenuItem(type.toString());
			item.addActionListener((ev) -> doMenu(type));
			chartMenu.add(item);
		}
		menuBar.add(chartMenu);
		setJMenuBar(menuBar);

		this.dataComponentPanel = new JPanel(new GridLayout(0, 1));
		DataTable dataTable = new DataTable();
		this.dataComponents.add(dataTable);

		final JSplitPane horizontalPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		mainPanel.add(horizontalPanel, BorderLayout.CENTER);

		horizontalPanel.add(dataTable);
		horizontalPanel.add(this.dataComponentPanel);

		ChartFactory cF = new ChartFactory();
		for (ChartType t : ChartType.values()) {
			DataComponent dc = cF.createDataComponent(t);
			this.dataComponentObjects.put(t, dc);
		}

		mainPanel.add(horizontalPanel, BorderLayout.CENTER);
		add(mainPanel);
		pack();
		setVisible(true);
	}

	public void doMenu(ChartType cT) {
		this.controller.setMenu(cT);
	}

	public void updateData() {
		Collection<Map.Entry<String, Number>> sorted = null;
		if (this.controlPanel.sortedOnLabels()) {
			sorted = this.map.entrySet();
		} else {
			sorted = new ArrayList<Map.Entry<String, Number>>(this.map.entrySet());
			((List<Map.Entry<String, Number>>) sorted).sort(new Comparator<Map.Entry<String, Number>>() {
				@Override
				public int compare(final Entry<String, Number> o1, final Entry<String, Number> o2) {
					return Double.compare(o1.getValue().doubleValue(), o2.getValue().doubleValue());
				}
			});
		}

		final String[] labels = new String[sorted.size()];
		final double[] values = new double[sorted.size()];
		int i = 0;
		for (final Map.Entry<String, Number> me : sorted) {
			labels[i] = me.getKey();
			values[i++] = me.getValue().doubleValue();
		}

		for (final DataComponent dc : this.dataComponents) {
			dc.setData(labels, values);
		}
	}

}
