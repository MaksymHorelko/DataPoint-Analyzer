package ua.horelkoMaksym.DataPointAnalyzer.comp1.table;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import ua.horelkoMaksym.DataPointAnalyzer.comp1.exceptions.DateParsingException;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.awt.event.ActionEvent;

public class DataSheetTable extends JPanel implements Serializable {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DataSheetTableModel tableModel;
	private JButton addButton;
	private JButton delButton;

	public DataSheetTable() {

		setLayout(new BorderLayout());

		tableModel = new DataSheetTableModel();

		table = new JTable();
		table.setModel(tableModel);
		tableModel.setDataSheet(new DataSheet());

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		add(scrollPane, BorderLayout.CENTER);

		addButton = new JButton("Add");
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JTextField dateField = new JTextField(10);
				JTextField xField = new JTextField(5);
				JTextField yField = new JTextField(5);

				JPanel panel = new JPanel(new GridLayout(0, 2));
				panel.add(new JLabel("Date (yyyy-MM-dd):"));
				panel.add(dateField);
				panel.add(new JLabel("X Value:"));
				panel.add(xField);
				panel.add(new JLabel("Y Value:"));
				panel.add(yField);

				int result = JOptionPane.showConfirmDialog(null, panel, "Add Data Point", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {

					try {

						String date = dateField.getText();
						double x = Double.parseDouble(xField.getText());
						double y = Double.parseDouble(yField.getText());

						tableModel.getDataSheet().addDataPoint(new DataPoint(x, y, date));
						tableModel.setRowCount(tableModel.getRowCount() + 1);

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid number format! Please enter a valid number.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} catch (DateParsingException ex) {
						JOptionPane.showMessageDialog(null, "Invalid date format! Please enter a valid date.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

					table.revalidate();
					tableModel.fireDataSheetChange();
				}

			}
		});

		delButton = new JButton("Delete");
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableModel.getRowCount() > 1) {
					tableModel.setRowCount(tableModel.getRowCount() - 1);
					tableModel.getDataSheet().removeDataPoint(tableModel.getDataSheet().size() - 1);
					table.revalidate();
					tableModel.fireDataSheetChange();
				} else {
					tableModel = new DataSheetTableModel();
					table.setModel(tableModel);
					tableModel.setDataSheet(new DataSheet());
					table.revalidate();
					tableModel.fireDataSheetChange();
				}
			}
		});

		JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 5));
		panelButtons.add(addButton);
		panelButtons.add(delButton);

		add(panelButtons, BorderLayout.SOUTH);

	}

	public DataSheetTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DataSheetTableModel tableModel) {
		this.tableModel = tableModel;
		revalidate();
	}

	public void revalidate() {
		if (table != null)
			table.revalidate();
	}
}
