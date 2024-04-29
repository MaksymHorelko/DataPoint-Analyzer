package ua.horelkoMaksym.DataPointAnalyzer.comp1.main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ua.horelkoMaksym.DataPointAnalyzer.comp1.table.DataSheetTable;

public class TableMain {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			DataSheetTable dataSheetTable = new DataSheetTable();
			JFrame frame = new JFrame("Table");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().add(dataSheetTable);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}

}
