/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

/**
 *
 * @author fish
 */
public class RootBookmark extends Bookmark {

    public RootBookmark() {
        title = null;
        page = 0;
        children = new ArrayList();
    }

    public RootBookmark(PDDocument doc) {
        title = null;
        page = 0;
        children = new ArrayList();

        PDDocumentOutline root = null;
        root = doc.getDocumentCatalog().getDocumentOutline();
        if(root == null){
            return;
        }
        PDOutlineItem item = root.getFirstChild();
        try {
            while (item != null) {
                Bookmark childBookmark = new Bookmark();
                childBookmark.scan(doc, item);
                children.add(childBookmark);
                item = item.getNextSibling();
            }
        } catch (IOException ex) {
            Logger.getLogger(RootBookmark.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean addBookmark(List<Integer> arch, String title, int page){
        Bookmark node = this;
        for(int i = 0; i < arch.size(); i++){
            node = node.getChildren().get(arch.get(i));
            if(node == null){
                return false;
            }
        }
        Bookmark newBookmark = new Bookmark();
        newBookmark.setPage(page);
        newBookmark.setTitle(title);
        
        if(node.getChildren().size() == 0){
            return false;
        }
        
        for(int i = 0; i < node.getChildren().size(); i++){
            if(node.getChildren().get(i).getPage() > page){
                node.getChildren().add(i, newBookmark);
                return true;
            }
        }
        node.getChildren().add(newBookmark);
        return true;
    }
}
