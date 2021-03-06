package edu.neu.csye.clubuml2012.compareAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Class for handling the report
 * @author Shoeb Shaikh
 *
 */
public class Report {

	private String reportFile = "";	// Absolute file name for report
	OutputStream file;		// Stream for writing results
	Document document;		// associated pdf document

	public Report(String report) {
		// initialize/open pdf code
		try {
			// Create Directory
			new File(new File(report).getParent().toString()).mkdirs();
			// Create file for generating report
			file = new FileOutputStream(new File(report));
			document = new Document();
			// instance for pdf writer
			PdfWriter.getInstance(document, file);
			document.open();
			// Writing headers
			document.add(new Paragraph("REPORT"));
			document.add(new Paragraph(new Date().toString()));
			document.add(new Paragraph("\n"));
		} catch (DocumentException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// To add which routine is been called
	public void startRoutine(String routine) {
		addToReport("Performing comparison of " + routine + "!");
	}
	
	// Marking end of routine
	public void terminateRoutine(String routine) {
		addToReport(routine + " comparison complete!");
	}
	
	// Adding the results to pdf file
	public void addToReport(String report) {
		try {
			document.add(new Paragraph(report + "\n\n"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}

	}

	// Closing the file
	public void finalize() {
		document.close();
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
