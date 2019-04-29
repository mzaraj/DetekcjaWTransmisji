package front;

/*
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
*/

import java.awt.Color;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Gui
{
    private JFrame frame;
    private JPanel panelMain;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JButton zakodujButton;
    private JButton wyslijButton;
    private JButton zaklocButton;
    private JSlider slider1;
    private JTextPane textPane4;
    private JTextPane textPane1;
    private JTextPane textPane2;
    private JTextPane textPane3;
    private JTextPane textPane5;

    private JLabel jLabel;

    private String textToSend = "";

    private StyledDocument doc;

    public Gui()
    {
        //openMainWindow();

//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                openMainWindow();
//            }
//        });
        jLabel = new JLabel("Hello World");

        zakodujButton.addActionListener(e -> handleZakodujButton());

        zaklocButton.addActionListener(e -> handleZaklocButton());
        wyslijButton.addActionListener(e -> handleWyslijButton());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);
        buttonGroup.add(radioButton5);
        buttonGroup.add(radioButton6);
        radioButton1.setSelected(true);
    }

    public void openMainWindow()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e)
        {
            JOptionPane.showMessageDialog(null, "Problem z ustawianiem wyglądu!");
        }

        frame = new JFrame("Mistrzowie");
        frame.setContentPane(new Gui().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }

    private void createUIComponents()
    {
        doc = (StyledDocument) new DefaultStyledDocument();
        textPane4 = new JTextPane(doc);
        textPane4.setText("Lorem ipsum dolor sit amet");
        javax.swing.text.Style style = textPane4.addStyle("Red", null);
        StyleConstants.setForeground(style, Color.RED);
        doc.setCharacterAttributes(1, 1, textPane4.getStyle("Red"), false);
        doc.setCharacterAttributes(5, 1, textPane4.getStyle("Red"), false);
        doc.setCharacterAttributes(9, 1, textPane4.getStyle("Red"), false);
        doc.setCharacterAttributes(16, 1, textPane4.getStyle("Red"), false);
        doc.setCharacterAttributes(22, 1, textPane4.getStyle("Red"), false);
    }

    private void handleZakodujButton()
    {
        textToSend = textPane1.getText();
        textPane2.setText(textToSend);
        textPane3.setText(textToSend);
    }

    private void handleZaklocButton()
    {
        doc.setCharacterAttributes(1, 1, textPane4.getStyle("Red"), false);

    }

    private void handleWyslijButton()
    {

    }
}


