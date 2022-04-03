package com.vizor.test.view;

import com.vizor.test.service.ImageService;
import com.vizor.test.service.ImageServiceImpl;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ButtonsPanel extends JPanel {

    private static final int BUTTON_WIDTH = 40;
    private static final int BUTTON_HEIGHT = 40;
    private static final int BUTTON_BORDER_THICKNESS = 4;
    private static final String UPLOAD_ICON_PATH = "src/main/resources/icons/uploadIcon.png";
    private static final String DELETE_ICON_PATH = "src/main/resources/icons/bin.png";

    private final JButton uploadButton;
    private final JButton deleteButton;
    private final GalleryFrame galleryFrame;
    private final LineBorder defaultButtonBorder;

    public ButtonsPanel(Color backgroundColor, GalleryFrame galleryFrame) {

        this.galleryFrame = galleryFrame;
        defaultButtonBorder = new LineBorder(backgroundColor, BUTTON_BORDER_THICKNESS);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(backgroundColor);
        uploadButton = new JButton();
        deleteButton = new JButton();
        configUploadButton(backgroundColor);
        configDeleteButton(backgroundColor);
        add(uploadButton);
        add(deleteButton);
    }

    private void configUploadButton(Color backgroundColor) {

        ImageIcon icon = new ImageIcon(UPLOAD_ICON_PATH);
        uploadButton.setIcon(icon);
        uploadButton.setBackground(backgroundColor);
        uploadButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        uploadButton.setBorder(defaultButtonBorder);
        uploadButton.addActionListener(new UploadListener());
    }

    private void configDeleteButton(Color backgroundColor) {

        ImageIcon icon = new ImageIcon(DELETE_ICON_PATH);
        deleteButton.setIcon(icon);
        deleteButton.setBackground(backgroundColor);
        deleteButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        deleteButton.setBorder(defaultButtonBorder);
        deleteButton.addActionListener(new DeleteListener());
    }

    private class UploadListener implements ActionListener {

        private final ImageService imageService = ImageServiceImpl.getInstance();

        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setMultiSelectionEnabled(true);
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.setDialogTitle("Select images");
            jfc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("images", "png", "jpg");
            jfc.addChoosableFileFilter(filter);
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File[] selectedFiles = jfc.getSelectedFiles();
            uploadFile(selectedFiles);
        }

        private void uploadFile(File[] files) {

            Thread uploadThread = new Thread(() -> {
                try {
                    for (File file : files) {
                        String filePath = file.getAbsolutePath();
                        imageService.save(filePath);
                        galleryFrame.updateImages();
                    }
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(new JFrame(), "Image upload error. Try again", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
            uploadThread.start();
        }
    }

    private class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            boolean deleteModeEnabled = galleryFrame.isDeleteModeEnabled();
            if (deleteModeEnabled) {
                deleteButton.setBorder(defaultButtonBorder);
            } else {
                LineBorder deleteModeBorder = new LineBorder(Color.red, BUTTON_BORDER_THICKNESS);
                deleteButton.setBorder(deleteModeBorder);
            }
            galleryFrame.setDeleteModeEnabled(!deleteModeEnabled);
        }
    }
}
