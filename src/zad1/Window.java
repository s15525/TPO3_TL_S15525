package zad1;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Window extends JPanel {
    static Map<String, Integer> mapServer = HeadServer.mapServer;
    Font font = new Font("AvantGarde", Font.ITALIC, 30);
    private static String result;

    public static String intialize() {

        JFrame jFrame = new JFrame();
        JPanel p = new JPanel(new BorderLayout(5, 5));
        JPanel labels = new JPanel(new GridLayout(0, 1, 2, 2));
        labels.add(new JLabel("Jezyk", SwingConstants.RIGHT));
        labels.add(new JLabel("Slowo", SwingConstants.RIGHT));
        p.add(labels, BorderLayout.WEST);

        JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
        JTextField slowo = new JTextField();
        JComboBox comboBox = new JComboBox();

        for (String arg :
                mapServer.keySet()) {
            comboBox.addItem(arg);
        }
        controls.add(comboBox);
        controls.add(slowo);
        p.add(controls, BorderLayout.CENTER);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JOptionPane.showMessageDialog(
                jFrame, p, "Gui Slownik", JOptionPane.QUESTION_MESSAGE);

        result = comboBox.getSelectedItem() + ";" + slowo.getText();

        return result;
    }
}
