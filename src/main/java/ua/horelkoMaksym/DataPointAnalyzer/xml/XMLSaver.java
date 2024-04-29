package ua.horelkoMaksym.DataPointAnalyzer.xml;

import ua.horelkoMaksym.DataPointAnalyzer.comp1.table.DataSheet;
import ua.horelkoMaksym.DataPointAnalyzer.comp1.table.DataPoint;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.util.List;

public class XMLSaver {
	public static void saveToXml(DataSheet dataSheet, String fileName) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();

			Element rootElement = doc.createElement("datasheet");

			Element dataElement = doc.createElement("data");
			rootElement.appendChild(dataElement);

			doc.appendChild(rootElement);

			List<DataPoint> dataPoints = dataSheet.getDataList();
			for (DataPoint dataPoint : dataPoints) {
				Element pointElement = doc.createElement("point");
				dataElement.appendChild(pointElement);

				Element xElement = doc.createElement("x");
				xElement.appendChild(doc.createTextNode(String.valueOf(dataPoint.getX())));
				pointElement.appendChild(xElement);

				Element yElement = doc.createElement("y");
				yElement.appendChild(doc.createTextNode(String.valueOf(dataPoint.getY())));
				pointElement.appendChild(yElement);

				Element dateElement = doc.createElement("date");
				dateElement.appendChild(doc.createTextNode(dataPoint.getDate()));
				pointElement.appendChild(dateElement);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fileName));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
