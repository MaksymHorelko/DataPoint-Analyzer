package ua.horelkoMaksym.DataPointAnalyzer.comp1.table;

import java.util.ArrayList;
import java.util.List;

public class DataSheet {
	private List<DataPoint> dataSheet;

	public DataSheet() {
		dataSheet = new ArrayList<>();
	}

	public DataSheet(List<DataPoint> dataPoints) {
		this.dataSheet = dataPoints;
	}

	public void addDataPoint(DataPoint dataPoint) {
		dataSheet.add(dataPoint);
	}

	public void setDataPoint(DataPoint point, int index) {
		if (index >= 0 && index < size()) {
			dataSheet.set(index, point);
		}
	}

	public void removeDataPoint(int index) {
		dataSheet.remove(index);
	}

	public DataPoint getDataPoint(int index) {
		return dataSheet.get(index);
	}

	public List<DataPoint> getDataList() {
		return dataSheet;
	}

	public void setDataList(List<DataPoint> dataPoints) {
		this.dataSheet = dataPoints;
	}

	public int size() {
		return dataSheet.size();
	}
}
