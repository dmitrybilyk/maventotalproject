import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.apache.log4j.spi.LoggerFactory;


import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 17.02.13
 * Time: 10:52
 * To change this template use File | Settings | File Templates.
 */
public class TranslateForm extends JFrame implements ClipboardOwner{
    private static final long serialVersionUID = -5654961743273574141L;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

//    private JButton copyToClipboardButton;
//    private JButton translateButton;
    private JTextArea translatedTextTextArea;
    private JTextPane originalTextTextPane;
//    private JButton pasteFromClipboardButton;
//    private JButton pasteAndTranslateButton;
//    private JComboBox originalLanguageComboBox;
//    private JComboBox translatedLanguageComboBox;
//    private JButton swapButton;
    private JPanel contentPane;
    private JLabel textToTranslateLabel;
    private JLabel translatedTextLabel;

    private SaveToPocketBookXML saveToPocketBookXML = new SaveToPocketBookXML();

    private TrayIcon trayIcon;
    private boolean trayIconActionEnabled = true;

    public TranslateForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setContentPane(contentPane);

//        pasteFromClipboardButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                pasteFromClipboardActionPerformed();
//            }
//        });

       // Associating labels with components as this cannot be done in the UI editor
        textToTranslateLabel.setLabelFor(originalTextTextPane);
        translatedTextLabel.setLabelFor(translatedTextTextArea);

        // Hide form on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hideFrame();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        addUndoRedoAbility(originalTextTextPane);
        addUndoRedoAbility(translatedTextTextArea);
        this.setSize(170, 270);

        originalTextTextPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String result = "";
                try {
                    String clipboardContent = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                    originalTextTextPane.setText(clipboardContent);
                    translatedTextTextArea.setText("");
                    translatedTextLabel.setText("Translation");
                } catch (UnsupportedFlavorException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

        translatedTextLabel.setText("Translation");
        translatedTextTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String result = "";
                try {
                    String clipboardContent = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
                    translatedTextLabel.setText("Translation");
                    saveToPocketBookXML.savePairToXML(originalTextTextPane.getText(), clipboardContent);
                    translatedTextTextArea.setText(clipboardContent);
                    translatedTextLabel.setText("Completed");

                } catch (UnsupportedFlavorException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                } catch (IOException e1) {
                    e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        });

        this.setAlwaysOnTop(true);
        this.setVisible(true);


    }

    private void appendFiles(JTextPane textPane, DropTargetDropEvent evt, File[] files) {
        StringBuilder errorMessage = new StringBuilder();
        for (File file : files) {

            // If Ctrl + Shift
            if (evt.getDropAction() == DnDConstants.ACTION_LINK) {
                // Put the file path in the "text to translate"
                textPane.replaceSelection(file.getName());
            } else if (file.canRead()) {
                try {
                    // If Ctrl
                    if (evt.getDropAction() == DnDConstants.ACTION_COPY) {
                        // Put a file component in the "text to translate" - this will allow to check if a specific
                        // FileProcessor exists for this type of file
                    } else { // No modifiers

                        // Read the file content into the "text to translate"
                        BufferedReader reader = null;
                        try {
                            reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
                            char[] buf = new char[1024];
                            int numRead;
                            while ((numRead = reader.read(buf)) != -1) {
                                textPane.replaceSelection(new String(buf, 0, numRead));
                            }
                            textPane.replaceSelection(LINE_SEPARATOR);
                        } catch (IOException e) {
                            errorMessage.append("Cannot read file '").append(file.getAbsolutePath()).append("' due to ").append(e).append('\n');
                        } finally {
                            if (reader != null) {
                                try {
                                    reader.close();
                                } catch (IOException e) {
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    errorMessage.append("File '").append(file.getAbsolutePath()).append("' cannot be added (").append(e).append(")");
                }
            } else {
                errorMessage.append("File '").append(file.getAbsolutePath()).append("' cannot be read (missing privileges?)").append('\n');
            }
        }

    }

    private void hideFrame() {
            setState(Frame.ICONIFIED);
    }

    private void addUndoRedoAbility(JTextComponent textComponent) {
        final UndoManager undo = new UndoManager();
        Document doc = textComponent.getDocument();

        // Listen for undo and redo events
        doc.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent evt) {
                undo.addEdit(evt.getEdit());
            }
        });

        // Create an undo action and add it to the text component
        textComponent.getActionMap().put("Undo",
                new AbstractAction("Undo") {
                    private static final long serialVersionUID = -8332492751611604661L;

                    public void actionPerformed(ActionEvent evt) {
                        try {
                            if (undo.canUndo()) {
                                undo.undo();
                            }
                        } catch (CannotUndoException e) {
                        }
                    }
                });

        // Bind the undo action to ctl-Z
        textComponent.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Undo");

        // Create a redo action and add it to the text component
        textComponent.getActionMap().put("Redo",
                new AbstractAction("Redo") {
                    private static final long serialVersionUID = -1112019671582152769L;

                    public void actionPerformed(ActionEvent evt) {
                        try {
                            if (undo.canRedo()) {
                                undo.redo();
                            }
                        } catch (CannotRedoException e) {
                        }
                    }
                });

        // Bind the redo action to ctl-Y
        textComponent.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Redo");
    }


    private void applyLanguageComboBoxModel(JComboBox comboBox, DefaultComboBoxModel comboBoxModel) {
        comboBox.setModel(comboBoxModel);
        if (comboBoxModel.getSelectedItem() != null) {
            // Manually selecting the item in the combo so the action listeners will be notified
            comboBox.setSelectedItem(comboBoxModel.getSelectedItem());
        }
    }




   private boolean pasteFromClipboardActionPerformed() {
        boolean success = false;
        try {
            String clipboardText = "clipboardManager.getClipboardContents()";
            Component[] components = originalTextTextPane.getComponents();
            for (Component component : components) {
                originalTextTextPane.remove(component);
            }
            originalTextTextPane.setText(clipboardText);
            success = true;
        } catch (Exception e) {
//            SwingUtils.showError(this, e, "errorMessage.cannotPasteFromClipboard");
        }
        return success;
    }


    private void removeTrayIcon() {
        if (trayIcon != null) {
            SystemTray.getSystemTray().remove(trayIcon);
        }
    }

    private TrayIcon createTrayIcon(Image icon) throws AWTException {
        PopupMenu popupMenu = new PopupMenu();

        final TrayIcon trayIcon = new TrayIcon(icon, "TRAY ICON", popupMenu);
        trayIcon.setImageAutoSize(true);
        SystemTray tray = SystemTray.getSystemTray();
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // When the user clicks on the displayMessage an action is performed - but an action is also performed
                // when the user double clicks on the system tray icon - because the displayMessage is only used when
                // the application loads it is safe to say that if a user clicked the icon, we can ignore the action
                trayIconActionEnabled = false;

                // On double click
                if (e.getClickCount() == 2) {
                    // toggle form visibility
                    setVisible(!isVisible());
                }
            }
        });



        tray.add(trayIcon);

        return trayIcon;
    }



    public void displayTrayIconMessage(String caption, String text, TrayIcon.MessageType messageType) {
        trayIcon.displayMessage(caption, text, messageType);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(1, 1, new Insets(5, 5, 5, 5), -1, -1));
        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null));
        final JSplitPane splitPane1 = new JSplitPane();
        splitPane1.setDividerLocation(150);
        splitPane1.setOrientation(0);
        contentPane.add(splitPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 1, new Insets(5, 5, 5, 5), -1, -1));
        splitPane1.setRightComponent(panel1);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        translatedTextLabel = new JLabel("Translated text");
//        this.$$$loadLabelText$$$(translatedTextLabel, ResourceBundle.getBundle("org/google/translate/desktop/resources/messages").getString("translateForm.translatedText"));
        panel2.add(translatedTextLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
//        copyToClipboardButton = new JButton();
//        this.$$$loadButtonText$$$(copyToClipboardButton, ResourceBundle.getBundle("org/google/translate/desktop/resources/messages").getString("translateForm.copyToClipboard"));
//        panel2.add(copyToClipboardButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel2.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        translatedTextTextArea = new JTextArea();
        translatedTextTextArea.setFont(new Font(translatedTextTextArea.getFont().getName(), translatedTextTextArea.getFont().getStyle(), 14));
        scrollPane1.setViewportView(translatedTextTextArea);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(3, 1, new Insets(5, 5, 5, 5), -1, -1));
        splitPane1.setLeftComponent(panel3);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        textToTranslateLabel = new JLabel();
//        this.$$$loadLabelText$$$(textToTranslateLabel, ResourceBundle.getBundle("org/google/translate/desktop/resources/messages").getString("translateForm.textToTranslate"));
        panel4.add(textToTranslateLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
//        pasteFromClipboardButton = new JButton();
//        this.$$$loadButtonText$$$(pasteFromClipboardButton, ResourceBundle.getBundle("org/google/translate/desktop/resources/messages").getString("translateForm.pasteFromClipboard"));
//        panel4.add(pasteFromClipboardButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel4.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
//        pasteAndTranslateButton = new JButton();
//        this.$$$loadButtonText$$$(pasteAndTranslateButton, ResourceBundle.getBundle("org/google/translate/desktop/resources/messages").getString("translateForm.pasteAndTranslate"));
//        panel4.add(pasteAndTranslateButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel3.add(scrollPane2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        originalTextTextPane = new JTextPane();
        originalTextTextPane.setSize(50, 150);
        originalTextTextPane.setFont(new Font(originalTextTextPane.getFont().getName(), originalTextTextPane.getFont().getStyle(), 14));
        scrollPane2.setViewportView(originalTextTextPane);
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 7, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel5, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
//        originalLanguageComboBox = new JComboBox();
//        panel5.add(originalLanguageComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
//        this.$$$loadLabelText$$$(label1, ResourceBundle.getBundle("org/google/translate/desktop/resources/messages").getString("translateForm.to"));
        panel5.add(label1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
//        translatedLanguageComboBox = new JComboBox();
//        panel5.add(translatedLanguageComboBox, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
//        swapButton = new JButton();
//        this.$$$loadButtonText$$$(swapButton, ResourceBundle.getBundle("org/google/translate/desktop/resources/messages").getString("translateForm.swap"));
//        panel5.add(swapButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel5.add(spacer3, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
//        translateButton = new JButton();
//        this.$$$loadButtonText$$$(translateButton, ResourceBundle.getBundle("org/google/translate/desktop/resources/messages").getString("translateForm.translate"));
//        panel5.add(translateButton, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
//        this.$$$loadLabelText$$$(label2, ResourceBundle.getBundle("org/google/translate/desktop/resources/messages").getString("translateForm.from"));
        panel5.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
//        label1.setLabelFor(translatedLanguageComboBox);
//        label2.setLabelFor(originalLanguageComboBox);
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadLabelText$$$(JLabel component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setDisplayedMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    private void $$$loadButtonText$$$(AbstractButton component, String text) {
        StringBuffer result = new StringBuffer();
        boolean haveMnemonic = false;
        char mnemonic = '\0';
        int mnemonicIndex = -1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '&') {
                i++;
                if (i == text.length()) break;
                if (!haveMnemonic && text.charAt(i) != '&') {
                    haveMnemonic = true;
                    mnemonic = text.charAt(i);
                    mnemonicIndex = result.length();
                }
            }
            result.append(text.charAt(i));
        }
        component.setText(result.toString());
        if (haveMnemonic) {
            component.setMnemonic(mnemonic);
            component.setDisplayedMnemonicIndex(mnemonicIndex);
        }
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

    @Override
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
