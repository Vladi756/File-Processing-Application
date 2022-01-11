package cw2;

import java.util.HashMap;
import java.util.Map;

import cw2.datadisplay.BarChart;
import cw2.datadisplay.DataComponent;
import cw2.datadisplay.LineGraph;
import cw2.datadisplay.PieChart;
import cw2.datadisplay.SquareChart;

public class ChartFactory {
	public enum ChartType { BAR, PIE, LINE, SQUARE}
	
	private Map<ChartType, DataComponent> map = new HashMap<>();
	
	public DataComponent createDataComponent(ChartType cT) {
		DataComponent dc = this.map.get(cT);
		if(dc == null) {
			switch(cT) {
			case BAR:
				dc = new BarChart();
				break;
			case PIE:
				dc = new PieChart();
				break;
			case LINE:
				dc = new LineGraph();
				break;
			case SQUARE:
				dc = new SquareChart();
				break;
			}
			this.map.put(cT, dc);
		}
		return dc;
	}
	
}

