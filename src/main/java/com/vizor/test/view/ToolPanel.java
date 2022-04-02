package com.vizor.test.view;

import javax.swing.Box;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridLayout;

public class ToolPanel extends JPanel {

    private static final int ROWS_COUNT = 1;
    private static final int COLUMNS_COUNT = 3;
    private static final int HGAP = 0;
    private static final int VGAP = 0;

    public ToolPanel(Color backgroundColor) {

        setLayout(new GridLayout(ROWS_COUNT, COLUMNS_COUNT, HGAP, VGAP));
        setBackground(backgroundColor);
        final UploadPanel uploadPanel = new UploadPanel(backgroundColor);
        final SearchPanel searchPanel = new SearchPanel(backgroundColor);

        add(uploadPanel);
        add(searchPanel);
        add(Box.createHorizontalStrut(0));
    }
}
