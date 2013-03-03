/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;

/**
 *
 * @author fish
 */
public class Bookmark {

    protected String title;
    protected int page;
    protected List<Bookmark> children;

    public Bookmark() {
        this.children = new ArrayList();
    }

    public void scan(PDDocument doc, PDOutlineItem item) throws IOException {
        title = item.getTitle();
        page = doc.getDocumentCatalog().getAllPages().indexOf(item.findDestinationPage(doc));

        PDOutlineItem child = item.getFirstChild();

        while (child != null) {
            Bookmark childBookmark = new Bookmark();
            childBookmark.scan(doc, child);
            children.add(childBookmark);
            child = child.getNextSibling();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Bookmark> getChildren() {
        return children;
    }

    public void setChildren(List<Bookmark> children) {
        this.children = children;
    }
}
