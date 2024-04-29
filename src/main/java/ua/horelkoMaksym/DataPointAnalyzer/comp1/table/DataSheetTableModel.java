package ua.horelkoMaksym.DataPointAnalyzer.comp1.table;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.table.AbstractTableModel;

public class DataSheetTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private final int columnCount = 3;

	private int rowCount = 1;

	private DataSheet dataSheet;

	private final String[] columnNames = { "Date", "X Value", "Y Value" };

	private ArrayList<DataSheetChangeListener> listenerList = new ArrayList<DataSheetChangeListener>();

	private DataSheetChangeEvent event = new DataSheetChangeEvent(this);

	public DataSheet getDataSheet() {
		return dataSheet;
	}

	public void setDataSheet(DataSheet dataSheet) {
		this.dataSheet = dataSheet;
		rowCount = this.dataSheet.size();
		fireDataSheetChange();
	}

	public void addDataSheetChangeListener(DataSheetChangeListener l) {
		listenerList.add(l);
	}

	public void removeDataSheetChangeListener(DataSheetChangeListener l) {
		listenerList.remove(l);
	}

	protected void fireDataSheetChange() {
		Iterator<DataSheetChangeListener> i = listenerList.iterator();
		while (i.hasNext())
			(i.next()).dataChanged(event);
	}

	@Override
	public int getColumnCount() {
		return columnCount;
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex >= 0;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		try {
			double d;
			if (dataSheet != null) {

				if (columnIndex == 0) {
					dataSheet.getDataPoint(rowIndex).setDate((String) value);
				}

				else if (columnIndex == 1) {
					d = Double.parseDouble((String) value);
					dataSheet.getDataPoint(rowIndex).setX(d);
				}

				else if (columnIndex == 2) {
					d = Double.parseDouble((String) value);
					dataSheet.getDataPoint(rowIndex).setY(d);
				}

			}

			fireDataSheetChange();

		} catch (Exception ex) {
			//TODO throw ex
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (dataSheet != null) {

			if (columnIndex == 0) {
				return dataSheet.getDataPoint(rowIndex).getDate();
			}

			else if (columnIndex == 1) {
				return dataSheet.getDataPoint(rowIndex).getX();
			}

			else if (columnIndex == 2) {
				return dataSheet.getDataPoint(rowIndex).getY();
			}

		}

		return null;
	}

	public void setRowCount(int rowCount) {
		if (rowCount > 0)
			this.rowCount = rowCount;
	}

}
