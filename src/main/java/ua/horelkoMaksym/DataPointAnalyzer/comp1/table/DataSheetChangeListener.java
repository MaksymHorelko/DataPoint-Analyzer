package ua.horelkoMaksym.DataPointAnalyzer.comp1.table;

import java.util.EventListener;

public interface DataSheetChangeListener extends EventListener {
	public void dataChanged(DataSheetChangeEvent e);

}
