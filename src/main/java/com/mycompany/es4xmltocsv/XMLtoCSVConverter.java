package com.mycompany.es4xmltocsv;

import java.io.File;
import java.io.FileWriter;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLtoCSVConverter {

    public static void main(String[] args) {
        try {
            // Parse XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            Document doc = factory.newDocumentBuilder().parse(new File("EmployeeData.xml"));

            // Create SortedSet to store and sort data
            SortedSet<EmployeeData> sortedEmployeeData = new TreeSet<>((e1, e2) -> Integer.compare(e2.salary, e1.salary));

            // Extract data from XML and add to SortedSet
            NodeList dataList = doc.getElementsByTagName("Data");
            for (int i = 0; i < dataList.getLength(); i++) {
                Node node = dataList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element dataElement = (Element) node;
                    int id = Integer.parseInt(dataElement.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = dataElement.getElementsByTagName("FirstName").item(0).getTextContent();
                    String lastName = dataElement.getElementsByTagName("LastName").item(0).getTextContent();
                    String position = dataElement.getElementsByTagName("Position").item(0).getTextContent();
                    int salary = Integer.parseInt(dataElement.getElementsByTagName("Salary").item(0).getTextContent());

                    EmployeeData employeeData = new EmployeeData(id, firstName, lastName, position, salary);
                    sortedEmployeeData.add(employeeData);
                }
            }

            // Write sorted data to CSV
            FileWriter csvWriter = new FileWriter("EmployeeData.csv");
            csvWriter.append("ID,FirstName,LastName,Position,Salary\n");
            for (EmployeeData employeeData : sortedEmployeeData) {
                csvWriter.append(employeeData.toCSVString()).append("\n");
            }
            csvWriter.close();

            System.out.println("CSV file created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}