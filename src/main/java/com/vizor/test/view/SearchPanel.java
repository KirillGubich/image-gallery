package com.vizor.test.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

public class SearchPanel extends JPanel {

    private static final int SEARCH_TEXT_FILED_WIDTH = 250;
    private static final int SEARCH_TEXT_FILED_HEIGHT = 30;
    private static final int SEARCH_BUTTON_WIDTH = 40;
    private static final int SEARCH_BUTTON_HEIGHT = 40;
    private static final int BUTTON_BORDER_THICKNESS = 4;
    private static final String SEARCH_ICON_PATH = "src/main/resources/icons/searchIcon.png";

    private final JButton searchButton;
    private final JTextField searchTextField;

    public SearchPanel(Color backgroundColor) {

        searchButton = new JButton();
        searchTextField = new JTextField();
        setBackground(backgroundColor);
        configSearchButton(backgroundColor);
        initSearchTextFiled();
        add(searchTextField);
        add(searchButton);
    }

    private void initSearchTextFiled() {

        final Dimension preferredSize = new Dimension(SEARCH_TEXT_FILED_WIDTH, SEARCH_TEXT_FILED_HEIGHT);
        searchTextField.setPreferredSize(preferredSize);
    }

    private void configSearchButton(Color backgroundColor) {

        ImageIcon searchIcon = new ImageIcon(SEARCH_ICON_PATH);
        LineBorder buttonBorder = new LineBorder(backgroundColor, BUTTON_BORDER_THICKNESS);
        searchButton.setIcon(searchIcon);
        searchButton.setBackground(backgroundColor);
        searchButton.setPreferredSize(new Dimension(SEARCH_BUTTON_WIDTH, SEARCH_BUTTON_HEIGHT));
        searchButton.setBorder(buttonBorder);
    }
}
