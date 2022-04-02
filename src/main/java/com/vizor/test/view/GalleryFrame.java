package com.vizor.test.view;

import com.vizor.test.service.ImageService;
import com.vizor.test.service.ImageServiceImpl;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;

public class GalleryFrame extends JFrame {

    private static final Color backgroundColor = new Color(204, 243, 255);
    private static final int ROWS = 4;
    private static final int COLUMNS = 5;

    private final ImagePanel imagePanel;
    private final PaginationPanel paginationPanel;
    private final ToolPanel toolPanel;
    private final ImageService imageService;

    public GalleryFrame(String title) {

        super(title);
        imageService = ImageServiceImpl.getInstance();
        setLayout(new BorderLayout());
        toolPanel = new ToolPanel(backgroundColor, this);
        paginationPanel = new PaginationPanel(this, backgroundColor);
        imagePanel = new ImagePanel(backgroundColor, ROWS, COLUMNS);
        add(toolPanel, "North");
        add(paginationPanel, "South");
        add(imagePanel);
    }

    public void updateImages() {

        int amount = ROWS * COLUMNS;
        String searchText = toolPanel.getSearchText();
        int page = paginationPanel.getPage();
        List<ImageIcon> images;
        images = imageService.readPaginated(page, amount, searchText);
        imagePanel.uploadImages(images);
        if (page == 1) {
            paginationPanel.disablePreviousPageButton();
        }
        boolean nextPageAvailable = imageService.checkNextPage(page + 1, amount, searchText);
        if (nextPageAvailable) {
            paginationPanel.enableNextPageButton();
        } else {
            paginationPanel.disableNextPageButton();
        }
    }

    public void resetPages() {

        paginationPanel.setPage(1);
    }
}
