/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import org.apache.jasper.tagplugins.jstl.core.Url;
import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSObject;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDJpeg;
import org.apache.pdfbox.pdmodel.graphics.xobject.PDXObjectImage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

/**
 *
 * @author fish
 */
public class test {

    public static void main(String[] args) throws IOException, COSVisitorException {
//        PDDocument doc = PDDocument.load("/Users/fish/Documents/test.pdf");
//        List pages = doc.getDocumentCatalog().getAllPages();
//        for (int i = 0; i < pages.size(); i++) {
//            System.out.println("ready");
//            PDPage page = (PDPage) pages.get(i);
//            //PDPage page1 = (PDPage) pages.get(1);
//            BufferedImage image = page.convertToImage();
//            //BufferedImage imgae = page1.convertToImage();
//            Iterator iter = ImageIO.getImageWritersBySuffix("png");
//            ImageWriter writer = (ImageWriter) iter.next();
//            File outFile = new File("/Users/fish/test/" + i + ".png");
//            FileOutputStream out = new FileOutputStream(outFile);
//            ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
//            writer.setOutput(outImage);
//            writer.write(new IIOImage(image, null, null));
//        }
//        
//clear a page
//        PDDocument doc1 = PDDocument.load("/Users/fish/Documents/os3.pdf");
//        List pages1 = doc1.getDocumentCatalog().getAllPages();
//        for (int i = 0; i < pages1.size(); i++) {
//            System.out.println("ready");
//            PDPage page = (PDPage) pages1.get(i);
//            //PDPage page1 = (PDPage) pages.get(1);
//            BufferedImage image = page.convertToImage();
//            //BufferedImage imgae = page1.convertToImage();
//            Iterator iter = ImageIO.getImageWritersBySuffix("png");
//            ImageWriter writer = (ImageWriter) iter.next();
//            File outFile = new File(URLDecoder.decode(("/Users/fish/test/" + i + "??.png"),"UTF-8"));
//            FileOutputStream out = new FileOutputStream(outFile);
//            ImageOutputStream outImage = ImageIO.createImageOutputStream(out);
//            writer.setOutput(outImage);
//            writer.write(new IIOImage(image, null, null));
//            page.getContents().getStream().clear();
//        }
//        PDPage first = (PDPage) pages1.get(0);
//        PDRectangle r = new PDRectangle( first.getMediaBox().getWidth(),first.getMediaBox().getHeight());
//        PDPage newPage = new PDPage(r);
//        doc1.addPage(newPage);
//        doc1.save("/Users/fish/Documents/os3.pdf");
//        doc1.close();

        //restore
//        PDDocument doc = new PDDocument();
//
//        PDPage page = new PDPage();
//        doc.addPage(page);
//
//        PDXObjectImage ximage = null;
//        String image = "/Users/fish/test/1.png";
//        BufferedImage bi = ImageIO.read(new File(image));
//        ximage = new PDJpeg(doc, bi);
//        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
//        System.out.println(ximage.getHeight()+ " " + page.getMediaBox().getHeight());
//        contentStream.drawXObject(ximage, 0, 0,page.getMediaBox().getWidth(), page.getMediaBox().getHeight() );
//        contentStream.close();
//        page.getContents().
//        doc.save("/Users/fish/test/1.pdf");
        
        
        //outline -- bookmark
        PDDocument doc = PDDocument.load("/Users/fish/Downloads/test"
                + ".pdf");
        PDDocumentOutline root = doc.getDocumentCatalog().getDocumentOutline();
        PDOutlineItem item = root.getFirstChild();
        while (item != null) {
            System.out.println("Item:" + item.getTitle() + " page: " + doc.getDocumentCatalog().getAllPages().indexOf(item.findDestinationPage(doc)));
            PDOutlineItem child = item.getFirstChild();
            while (child != null) {
                System.out.println("    Child:" + child.getTitle());
                child = child.getNextSibling();
            }
            item = item.getNextSibling();
        }
    }
}
