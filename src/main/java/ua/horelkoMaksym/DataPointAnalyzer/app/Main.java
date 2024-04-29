package ua.horelkoMaksym.DataPointAnalyzer.app;

import javax.swing.*;

import ua.horelkoMaksym.DataPointAnalyzer.comp1.table.DataSheet;
import ua.horelkoMaksym.DataPointAnalyzer.comp1.table.DataSheetTable;
import ua.horelkoMaksym.DataPointAnalyzer.comp2.DataSheetGraph;
import ua.horelkoMaksym.DataPointAnalyzer.xml.SAXReader;
import ua.horelkoMaksym.DataPointAnalyzer.xml.XMLSaver;

import java.awt.*;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private DataSheetGraph dataSheetGraph;
	private DataSheetTable dataSheetTable;
	private static DataSheet dataSheet;

	public Main() {
		setTitle("Data Point Analyzer");
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		dataSheet = new DataSheet();

		dataSheetGraph = new DataSheetGraph();
		dataSheetGraph.setDataSheet(dataSheet);

		dataSheetTable = new DataSheetTable();
		dataSheetTable.getTableModel().setDataSheet(dataSheet);
		dataSheetTable.getTableModel().addDataSheetChangeListener(e -> {
			dataSheetGraph.revalidate();
			dataSheetGraph.repaint();
		});

		getContentPane().add(dataSheetGraph, BorderLayout.CENTER);
		getContentPane().add(dataSheetTable, BorderLayout.WEST);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File("."));

		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(e -> {
			dataSheet = new DataSheet();

			dataSheetTable.getTableModel().setDataSheet(dataSheet);
			dataSheetTable.revalidate();
			dataSheetGraph.setDataSheet(dataSheet);
		});
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(e -> {
			if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
				String fileName = fileChooser.getSelectedFile().getPath();
                XMLSaver.saveToXml(dataSheet, fileName);
				JOptionPane.showMessageDialog(null, "File " + fileName.trim() + " saved!", "Results Saved",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		JButton readButton = new JButton("Read");
		readButton.addActionListener(e -> {
			if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
				String fileName = fileChooser.getSelectedFile().getPath();
				dataSheet = new DataSheet(SAXReader.parseXML(fileName));
				dataSheetTable.getTableModel().setDataSheet(dataSheet);
				dataSheetTable.revalidate();
				dataSheetGraph.setDataSheet(dataSheet);
			}
		});

		JPanel filePanel = new JPanel();
		filePanel.add(clearButton);
		filePanel.add(saveButton);
		filePanel.add(readButton);
		getContentPane().add(filePanel, BorderLayout.SOUTH);
		JButton exitButton = new JButton("Exit");
		filePanel.add(exitButton);
		exitButton.addActionListener(e -> dispose());

		setSize(new Dimension(1000, 600));
		setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			Main test = new Main();
			test.setVisible(true);
		});
	}
}