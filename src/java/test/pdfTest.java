/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDPixelMap;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;

/**
 *
 * @author fish
 */
public class pdfTest {

    static public void main(String[] args) throws IOException, COSVisitorException {
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);
        BufferedImage awtImage = ImageIO.read(new File("/Users/fish/test.png"));
        PDXObjectImage ximage = new PDPixelMap(doc, awtImage);
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        contentStream.drawImage(ximage, 0, 0);

        contentStream.close();
        doc.save("/Users/fish/test.pdf");
        page.getContents().getStream().clear();
        doc.save("/Users/fish/test.pdf");
        ximage = new PDPixelMap(doc, awtImage);
        contentStream = new PDPageContentStream(doc, page);

        contentStream.drawImage(ximage, 0, 0);

        contentStream.close();
        doc.save("/Users/fish/test.pdf");
    }
}
