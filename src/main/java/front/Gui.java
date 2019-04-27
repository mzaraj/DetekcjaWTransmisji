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

import javax.swing.*;

public class Gui
{
    private JFrame frame;
    private JPanel panelMain;
    private JTextField textField1;
    private JRadioButton CRC16RadioButton;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JTextField textField2;
    private JButton zakodujButton;
    private JButton wyślijButton;
    private JButton zakłóćButton;
    private JSlider slider1;
    private JTextPane textPane1;
    private JTextField textField3;

    private JLabel jLabel;

    public Gui()
    {
        //openMainWindow();

//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                openMainWindow();
//            }
//        });
        jLabel = new JLabel("Hello World");

    }

    public void openMainWindow()
    {
        System.out.println("DEFINE TRUE FALSE");

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
}
