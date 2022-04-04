package com.vizor.test.view;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginationPanel extends JPanel {

    private static final String NEXT_PAGE_ICON_PATH = "src/main/resources/icons/nextPage.png";
    private static final String PREVIOUS_PAGE_ICON_PATH = "src/main/resources/icons/prevPage.png";
    private static final int SEPARATOR_WIDTH = 15;
    private static final int BUTTON_BORDER_THICKNESS = 3;

    private final JButton previousPageButton;
    private final JButton nextPageButton;
    private final GalleryFrame galleryFrame;
    private int page = 1;

    public PaginationPanel(Color backgroundColor, GalleryFrame galleryFrame) {

        this.galleryFrame = galleryFrame;
        previousPageButton = new JButton();
        nextPageButton = new JButton();
        LineBorder buttonBorder = new LineBorder(backgroundColor, BUTTON_BORDER_THICKNESS);
        setBackground(backgroundColor);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        initPreviousPageButton(backgroundColor, buttonBorder);
        initNextPageButton(backgroundColor, buttonBorder);
        add(previousPageButton);
        add(nextPageButton);
        add(Box.createHorizontalStrut(SEPARATOR_WIDTH));
    }

    private void initNextPageButton(Color backgroundColor, LineBorder buttonBorder) {

        ImageIcon icon = new ImageIcon(NEXT_PAGE_ICON_PATH);
        nextPageButton.setIcon(icon);
        nextPageButton.setBackground(backgroundColor);
        nextPageButton.setBorder(buttonBorder);
        nextPageButton.addActionListener(new NextPageListener());
    }

    private void initPreviousPageButton(Color backgroundColor, LineBorder buttonBorder) {

        ImageIcon icon = new ImageIcon(PREVIOUS_PAGE_ICON_PATH);
        previousPageButton.setIcon(icon);
        previousPageButton.setBackground(backgroundColor);
        previousPageButton.setBorder(buttonBorder);
        previousPageButton.setEnabled(false);
        previousPageButton.addActionListener(new PreviousPageListener());
    }

    public void setNextPageButtonEnabled(boolean enabled) {

        nextPageButton.setEnabled(enabled);
    }

    public void setPreviousPageButtonEnabled(boolean enabled) {

        previousPageButton.setEnabled(enabled);
    }

    public int getPage() {

        return page;
    }

    public void setPage(int page) {

        this.page = page;
    }

    private class NextPageListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            page++;
            Thread updateThread = new Thread(galleryFrame::updateImages);
            updateThread.start();
        }
    }

    private class PreviousPageListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (page == 1) {
                return;
            }
            page--;
            Thread updateThread = new Thread(galleryFrame::updateImages);
            updateThread.start();
        }
    }
}
