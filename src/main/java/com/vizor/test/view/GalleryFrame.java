package com.vizor.test.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

public class GalleryFrame extends JFrame {

    private static final Color backgroundColor = new Color(204, 243, 255);
    private static final int ROWS = 4;
    private static final int COLUMNS = 5;
    private static GalleryFrame instance;

    public static GalleryFrame getInstance() {

        if (instance == null) {
            instance = new GalleryFrame();
        }
        return instance;
    }

    private GalleryFrame() {

        setLayout(new BorderLayout());
        JPanel utilPanel = new ToolPanel(backgroundColor);
        JPanel paginationPanel = new PaginationPanel(backgroundColor);
        ImagePanel imagePanel = new ImagePanel(backgroundColor, ROWS, COLUMNS);
        add(utilPanel, "North");
        add(paginationPanel, "South");
        add(imagePanel);
    }
}
