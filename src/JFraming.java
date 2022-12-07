import components.SpringUtilities;

import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class JFraming {
            public static void createAndShowGUI() {
            final String[] labels = {"Input: "};
            int labelsLength = labels.length;
            JTextField[] texts = new JTextField[labelsLength];
            //Create and populate the panel.
            JPanel p = new JPanel(new SpringLayout());
            for (int i = 0; i < labelsLength; i++) {
                JLabel l = new JLabel(labels[i], JLabel.TRAILING);
                p.add(l);
                JTextField textField = new JTextField(10);
                texts[i] = textField;
                l.setLabelFor(textField);
                p.add(textField);
            }
            JButton button = new JButton("Submit");
            p.add(new JLabel());
            p.add(button);

            //Lay out the panel.

            //Create and set up the window.
            JFrame frame = new JFrame("SpringForm");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                SpringUtilities.makeCompactGrid(p,
                        labelsLength + 1, 2, //rows, cols
                        7, 7,        //initX, initY
                        7, 7);       //xPad, yPad

                button.addActionListener(e -> {

                    //Execute when button is pressed
                    for (JTextField text:texts) {
                        System.out.println(text.getText());
                        Main.input = text.getText();
                    }
                    frame.dispose();
                });

            //Set up the content pane.
            p.setOpaque(true);  //content panes must be opaque
            frame.setContentPane(p);

            //Display the window.
            frame.pack();
            frame.setVisible(true);
        }

//        public static void main(String[] args) {
//            //Schedule a job for the event-dispatching thread:
//            //creating and showing this application's GUI.
//            javax.swing.SwingUtilities.invokeLater(new Runnable() {
//                public void run() {
//                    createAndShowGUI();
//                }
//            });
//        }

}
