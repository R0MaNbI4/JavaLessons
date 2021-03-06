import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame {
    private final ScriptEngineManager mgr = new ScriptEngineManager();
    private final ScriptEngine engine = mgr.getEngineByName("Nashorn");
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WIDTH = 300;
    private final int HEIGHT = 400;
    private final int X_CENTER = (int) (dimension.getWidth() / 2) - (WIDTH / 2);
    private final int Y_CENTER = (int) (dimension.getHeight() / 2) - (HEIGHT / 2);

    public CalculatorFrame() {
        setTitle("Calculator");
        setBounds(X_CENTER, Y_CENTER, WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setLayout(new BorderLayout());

        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        JTextField inputField = new JTextField();
        inputField.setEditable(false);
        top.add(inputField, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(5, 3));

        JPanel right = new JPanel();
        right.setLayout(new GridLayout(5, 1));

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);

        DigitButtonListener digitButtonListener = new DigitButtonListener(inputField);

        for (int i = 9; i >= 0; i--) {
            JButton button = new JButton(String.valueOf(i));
            button.addActionListener(digitButtonListener);
            bottom.add(button);
        }

        JButton add = new JButton("+");
        right.add(add);
        add.addActionListener(digitButtonListener);

        JButton subtract = new JButton("-");
        right.add(subtract);
        subtract.addActionListener(digitButtonListener);

        JButton multiply = new JButton("*");
        right.add(multiply);
        multiply.addActionListener(digitButtonListener);

        JButton divide = new JButton("/");
        right.add(divide);
        divide.addActionListener(digitButtonListener);

        JButton clear = new JButton("C");
        bottom.add(clear);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                inputField.setText("");
            }
        });

        JButton result = new JButton("=");
        bottom.add(result);
        result.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();
                int result = 0;
                try {
                    result = (int) engine.eval(inputField.getText());
                } catch (ScriptException scriptException) {
                    inputField.setText("Error");
                    return;
                }
                inputField.setText(Integer.toString(result));
            }
        });

        JButton leftBracket = new JButton("(");
        bottom.add(leftBracket);
        leftBracket.addActionListener(digitButtonListener);

        JButton rightBracket = new JButton(")");
        bottom.add(rightBracket);
        rightBracket.addActionListener(digitButtonListener);

        setVisible(true);
    }
}
