package classes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.Matrix;

import java.io.IOException;

public class PdfCreator
{

    public void createFacature(Invoice invoice)
    {
        try (PDDocument document = new PDDocument())
        {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

// Create a new font object selecting one of the PDF base fonts
            PDFont font = PDType1Font.HELVETICA_BOLD;

// Start a new content stream which will "hold" the to be created content
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.transform(Matrix.getTranslateInstance(100, 700));
            contentStream.showText(invoice.getOwner()
                                          .getFirstName() + " " + invoice.getOwner()
                                                                         .getLastName());
            contentStream.endText();


// Make sure that the content stream is closed:
            contentStream.close();

// Save the results and ensure that the document is properly closed:

            String filename = invoice.getOwner()
                                     .getCitizenId() + "";
            filename += "_";
            filename += invoice.getOwner()
                               .getFirstName();
            filename += "_";
            filename += invoice.getOwner()
                               .getLastName();
            filename += "_";
            filename += invoice.getDate();
            filename += ".pdf";
            document.save(filename);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void createPdf(String text)
    {
        try (PDDocument document = new PDDocument())
        {
            PDPage page = new PDPage();
            document.addPage(page);

// Create a new font object selecting one of the PDF base fonts
            PDFont font = PDType1Font.HELVETICA_BOLD;

// Start a new content stream which will "hold" the to be created content
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.moveTextPositionByAmount(100, 700);
            contentStream.drawString(text);
            contentStream.endText();

// Make sure that the content stream is closed:
            contentStream.close();

// Save the results and ensure that the document is properly closed:
            document.save("Hello World.pdf");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
