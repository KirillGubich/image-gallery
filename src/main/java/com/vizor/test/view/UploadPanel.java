package com.vizor.test.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class UploadPanel extends JPanel {

    private static final int UPLOAD_BUTTON_WIDTH = 40;
    private static final int UPLOAD_BUTTON_HEIGHT = 40;
    private static final int BUTTON_BORDER_THICKNESS = 4;
    private static final String UPLOAD_ICON_PATH = "src/main/resources/icons/uploadIcon.png";

    private final JButton uploadButton;

    public UploadPanel(Color backgroundColor) {

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(backgroundColor);
        uploadButton = new JButton();
        configUploadButton(backgroundColor);
        add(uploadButton);
    }

    private void configUploadButton(Color backgroundColor) {

        ImageIcon uploadIcon = new ImageIcon(UPLOAD_ICON_PATH);
        LineBorder buttonBorder = new LineBorder(backgroundColor, BUTTON_BORDER_THICKNESS);
        uploadButton.setIcon(uploadIcon);
        uploadButton.setBackground(backgroundColor);
        uploadButton.setPreferredSize(new Dimension(UPLOAD_BUTTON_WIDTH, UPLOAD_BUTTON_HEIGHT));
        uploadButton.setBorder(buttonBorder);
    }
}
