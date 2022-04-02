package com.vizor.test.view;

import com.vizor.test.service.ImageService;
import com.vizor.test.service.ImageServiceImpl;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

public class GalleryFrame extends JFrame {

    private static final Color backgroundColor = new Color(204, 243, 255);
    private static final int ROWS = 4;
    private static final int COLUMNS = 5;

    private final ImagePanel imagePanel;
    private final PaginationPanel paginationPanel;
    private final ImageService imageService;

    public GalleryFrame(String title) {

        super(title);
        imageService = ImageServiceImpl.getInstance();
        setLayout(new BorderLayout());
        JPanel utilPanel = new ToolPanel(backgroundColor, this);
        paginationPanel = new PaginationPanel(this, backgroundColor);
        imagePanel = new ImagePanel(paginationPanel, backgroundColor, ROWS, COLUMNS);
        add(utilPanel, "North");
        add(paginationPanel, "South");
        add(imagePanel);
    }

    public void updateImages() {

        int amount = ROWS * COLUMNS;
        int page = paginationPanel.getPage();
        final List<ImageIcon> images = imageService.readPaginated(page, amount);
        imagePanel.uploadImages(images);
        if (page == 1) {
            paginationPanel.disablePreviousPageButton();
        }
        final boolean nextPageAvailable = imageService.checkNextPage(page + 1, amount);
        if (!nextPageAvailable) {
            paginationPanel.disableNextPageButton();
        }
    }
}
