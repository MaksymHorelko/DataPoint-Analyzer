package ua.horelkoMaksym.DataPointAnalyzer.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.horelkoMaksym.DataPointAnalyzer.comp1.table.DataPoint;

public class SAXReader {
	public static List<DataPoint> parseXML(String fileName) {
		List<DataPoint> dataPoints = new ArrayList<>();
		try {
			File file = new File(fileName);
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler() {
				private String currentElement;
				private double x;
				private double y;
				private String date;

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					currentElement = qName;
				}

				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					String value = new String(ch, start, length).trim();
					if (currentElement.equals("x")) {
						x = Double.parseDouble(value);
					} else if (currentElement.equals("y")) {
						y = Double.parseDouble(value);
					} else if (currentElement.equals("date")) {
						date = value;
					}
				}

				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (qName.equals("point")) {
						dataPoints.add(new DataPoint(x, y, date));
					}
				}
			};

			saxParser.parse(file, handler);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return dataPoints;
	}
}