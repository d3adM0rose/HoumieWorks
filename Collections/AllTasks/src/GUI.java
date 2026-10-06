import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Список студентов");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Поле ввода
        JTextField textField = new JTextField();

        // Кнопка
        JButton button = new JButton("Добавить");

        // Модель списка
        DefaultListModel<String> listModel = new DefaultListModel<>();

        // JList
        JList<String> list = new JList<>(listModel);

        // Верхняя панель
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(textField, BorderLayout.CENTER);
        topPanel.add(button, BorderLayout.EAST);

        // Добавляем элементы в окно
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(list), BorderLayout.CENTER);

        // Действие кнопки
        button.addActionListener(e -> {
            String text = textField.getText();

            if (!text.isEmpty()) {
                listModel.addElement(text);
                textField.setText("");
            }
        });

        // Показываем окно
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
