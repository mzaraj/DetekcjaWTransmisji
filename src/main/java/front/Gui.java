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

import back.*;
import back.Ascii.ASCIIConverter;
import back.errorService.ErrorGenerator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JTextPane textPane6;
    private JTextPane textPane5;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;

    private ButtonGroup buttonGroup;

    private JLabel jLabel;

    private String textToSend = "";

    private StyledDocument doc;
    private StyledDocument doc2;
    private StyledDocument doc3;
    private StyledDocument doc4;

    private int methodType;

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

        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        radioButton1.setActionCommand("1");
        buttonGroup.add(radioButton2);
        radioButton2.setActionCommand("2");
        buttonGroup.add(radioButton3);
        radioButton3.setActionCommand("3");
        buttonGroup.add(radioButton4);
        radioButton4.setActionCommand("4");
        buttonGroup.add(radioButton5);
        radioButton5.setActionCommand("5");
        buttonGroup.add(radioButton6);
        radioButton6.setActionCommand("6");
        radioButton1.setSelected(true);

//        radioButton1.addActionListener(e -> System.out.println("Action Command is: " + e.getActionCommand()));
        radioButton1.addActionListener(this::clearTextPanes);
        radioButton2.addActionListener(this::clearTextPanes);
        radioButton3.addActionListener(this::clearTextPanes);
        radioButton4.addActionListener(this::clearTextPanes);
        radioButton5.addActionListener(this::clearTextPanes);
        radioButton6.addActionListener(this::clearTextPanes);

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
        doc = new DefaultStyledDocument();
        doc2 = new DefaultStyledDocument();
        doc3 = new DefaultStyledDocument();
        doc4 = new DefaultStyledDocument();
        textPane2 = new JTextPane(doc);
        textPane3 = new JTextPane(doc2);
        textPane6 = new JTextPane(doc3);
        textPane4 = new JTextPane(doc4);
        javax.swing.text.Style style = textPane2.addStyle("Blue", null);
        javax.swing.text.Style style6 = textPane6.addStyle("Blue6", null);
        javax.swing.text.Style style4 = textPane4.addStyle("Blue4", null);
        javax.swing.text.Style styleRed = textPane3.addStyle("Red", null);
        StyleConstants.setForeground(style, new Color(0, 120, 255));
        StyleConstants.setForeground(style6, new Color(240, 0, 0));
        StyleConstants.setForeground(style4, new Color(0, 200, 0));
        StyleConstants.setForeground(styleRed, new Color(240, 0, 0));
    }

    private void clearTextPanes(ActionEvent e)
    {
        //if()
        System.out.println("Action Command is: " + e.getActionCommand());
        textPane2.setText("");
        textPane3.setText("");
        textPane4.setText("");
        textPane5.setText("");
        textPane6.setText("");
        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");
    }

    private void handleZakodujButton()
    {
        ErrorCount.setCorrectedErrorsIndexes(new ArrayList<>());
        ErrorCount.setDetectedErrorsIndexes(new ArrayList<>());
        ErrorCount.setAllErrors(0);
        ErrorCount.setAllErrorsIndexes(new ArrayList<>());

        label1.setText("");
        label2.setText("");
        label3.setText("");
        label4.setText("");
        label5.setText("");

        textToSend = textPane1.getText();
        String asciiText = new ASCIIConverter().converterToASCI(textToSend);
        System.out.println("ASCII text: " + asciiText);
        methodType = Integer.parseInt(buttonGroup.getSelection().getActionCommand());
        String convertedText = "";
        switch(methodType)
        {
            case 1:
                convertedText = new CRC().send(asciiText, CRC.CRC16);
                break;
            case 2:
                convertedText = new CRC().send(asciiText, CRC.CRC16REVERSE);
                break;
            case 3:
                convertedText = new CRC().send(asciiText, CRC.SDLC);
                break;
            case 4:
                convertedText = new CRC().send(asciiText, CRC.SDLCREVERSE);
                break;
            case 5:
                convertedText = Hamming.send(asciiText);
                break;
            case 6:
                convertedText = Parity.send(asciiText);
                break;
            default:
                System.out.println("Niepoprawny tryb wykryty!");
                break;
        }
        textPane2.setText(convertedText);
        textPane3.setText(convertedText);

        switch(methodType)
        {
            case 1:
            case 2:
            case 3:
            case 4:
                for(int i =0 ;i<convertedText.length();i+=24)
                {
                    doc.setCharacterAttributes(i+8, 16, textPane2.getStyle("Blue"), false);
                }
                break;
            case 5:
                for(int i =1 ;i<convertedText.length();i*=2)
                {
                    doc.setCharacterAttributes(convertedText.length()-(i), 1, textPane2.getStyle("Blue"), false);
                }
                break;
            case 6:
                for(int i =0 ;i<convertedText.length();i+=9)
                {
                    doc.setCharacterAttributes(i+8, 1, textPane2.getStyle("Blue"), false);
                }
                break;
        }
        label4.setText("" + textPane2.getText().length());
        label5.setText("" + (textPane2.getText().length() - asciiText.length()));

    }

    private void handleZaklocButton()
    {
        textPane3.setText(new ErrorGenerator().generateError(slider1.getValue(), textPane2.getText()));

        ArrayList<Integer> listaBledow = ErrorCount.getAllErrorsIndexes();

        for(int i: listaBledow)
        {
            doc2.setCharacterAttributes(i, 1, textPane3.getStyle("Red"), false);
        }

//        int[] tablica = {1,4,6,9,11,15};
//
//        for(int i : tablica)
//        {
//            doc.setCharacterAttributes(i, 1, textPane5.getStyle("Red"), false);
//        }

        //doc.setCharacterAttributes(1, 1, textPane4.getStyle("Red"), false);

    }

    private void handleWyslijButton()
    {
        //ostateczne zliczanie błędów i kolorowanie
        ErrorCount.countErrors(textPane2.getText(), textPane3.getText());
        ArrayList<Integer> listaBledow = ErrorCount.getAllErrorsIndexes();
        for(int i: listaBledow)
        {
            doc2.setCharacterAttributes(i, 1, textPane3.getStyle("Red"), false);
        }

        String textRepaired = "";
        if(methodType == 5) //method does correct errors
        {
            String[] results;
            results = Hamming.receive(textPane3.getText());

            textPane4.setText(results[0]);
            textPane5.setText(results[1]);
            textPane6.setText(new ASCIIConverter().convertedToText(results[1]));


        }
        else //method does NOT correct errors
        {
            switch(methodType)
            {
                case 1:
                    textRepaired = new CRC().receive(textPane3.getText(), CRC.CRC16);
                    break;
                case 2:
                    textRepaired = new CRC().receive(textPane3.getText(), CRC.CRC16REVERSE);
                    break;
                case 3:
                    textRepaired = new CRC().receive(textPane3.getText(), CRC.SDLC);
                    break;
                case 4:
                    textRepaired = new CRC().receive(textPane3.getText(), CRC.SDLCREVERSE);
                    break;
                case 6:
                    textRepaired = Parity.receive(textPane3.getText());
                    break;
                default:
                    System.out.println("Niepoprawny tryb wykryty!");
                    break;
            }
            textPane4.setText(textPane3.getText());
            textPane5.setText(textRepaired);
            textPane6.setText(new ASCIIConverter().convertedToText(textRepaired));

        }
        if(methodType==5)
        {
            ArrayList<Integer> correctedIndexes = ErrorCount.getCorrectedErrorsIndexes();
            System.out.println("cos");

            for(int i: correctedIndexes)
            {
                System.out.println(i);
                doc4.setCharacterAttributes(i, 1, textPane4.getStyle("Blue4"), false);
            }
        }

        ArrayList<Integer> detectedIndexes = ErrorCount.getDetectedErrorsIndexes();
        System.out.println("cos2");

        for(int i: detectedIndexes)
        {
            System.out.println(i);
            doc3.setCharacterAttributes(i, 1, textPane6.getStyle("Blue6"), false);
        }
        label1.setText("" + ErrorCount.getAllErrors());
        label2.setText("" + ErrorCount.getDetectedErrors());
        label3.setText("" + ErrorCount.getUndetectedErrors());
    }
}