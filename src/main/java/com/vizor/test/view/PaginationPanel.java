package com.vizor.test.view;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;

public class PaginationPanel extends JPanel {

    private static final String NEXT_PAGE_ICON_PATH = "src/main/resources/icons/nextPage.png";
    private static final String PREVIOUS_PAGE_ICON_PATH = "src/main/resources/icons/prevPage.png";
    private static final int SEPARATOR_WIDTH = 15;
    private static final int BUTTON_BORDER_THICKNESS = 3;

    private final JButton previousPageButton;
    private final JButton nextPageButton;

    public PaginationPanel(Color backgroundColor) {

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
    }

    private void initPreviousPageButton(Color backgroundColor, LineBorder buttonBorder) {

        ImageIcon icon = new ImageIcon(PREVIOUS_PAGE_ICON_PATH);
        previousPageButton.setIcon(icon);
        previousPageButton.setBackground(backgroundColor);
        previousPageButton.setBorder(buttonBorder);
    }


}
