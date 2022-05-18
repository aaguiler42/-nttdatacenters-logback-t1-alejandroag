package com.nttdata.nttdatacenters_logback_t1_alejandroag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.util.Scanner;

/**
 * PDF pyramid generator
 * 
 * @author Alejandro Aguilera García
 * @version 0.1-SNAPSHOT
 */
public class App {
	private static final Logger Log = LoggerFactory.getLogger(App.class);

	/**
	 * Obtain the height of the pyramid from the user
	 * 
	 * @return the height of the pyramid
	 */
	private static int readParameters() {
		Log.info("Leyendo parametros");
		Scanner s = new Scanner(System.in);

		System.out.print("Introduzca la altura de la piramide: ");
		int h = s.nextInt();

		s.close();
		
		Log.info("Parametros leidos");

		return h;
	}

	/**
	 * Generate and save the PDF with the pyramid
	 * 
	 * @param h the height of the pyramid
	 * @throws IOException if an I/O error occurs
	 */
	public static void generatePdfPiramid(int h) throws IOException {
		Log.info("Generando PDF");
		PDDocument doc = new PDDocument();
		PDPage firstPage = new PDPage();

		String text;
		int spaces = h - 1;
		int print = 1;

		doc.addPage(firstPage);
		PDPageContentStream contentStream = new PDPageContentStream(doc, firstPage);
		Log.info("Primera página aniadida");

		contentStream.beginText();
		contentStream.setFont(PDType1Font.TIMES_BOLD_ITALIC, 14);
		contentStream.setLeading(14.5f);
		contentStream.newLineAtOffset(25, 700);
		Log.info("Valores por defecto establecidos");

		for (int i = 1; i <= h; i++) {
			Log.info("Pintando línea {}", i);
			text = "";

			for (int j = 1; j <= spaces; j++) {
				text = text + " ";
			}

			for (int k = 1; k <= print; k++) {
				text = text + "*";
			}
			print++;
			spaces--;
			contentStream.showText(text);
			contentStream.newLine();
			Log.info("Línea {} pintada", i);
		}

		contentStream.endText();
		contentStream.close();

		doc.save("pdfs/newPDF");
		Log.info("PDF guardado");
		doc.close();
		Log.info("Documento cerrado");
		Log.info("PDF generado");
	}

	/**
	 * @param args the command line arguments (not used)
	 */
	public static void main(String[] args) throws IOException {
		Log.info("Inicio de programa");
		int h = readParameters();
		generatePdfPiramid(h);
		Log.info("Programa finalizado");
	}
}
