import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
 
public class StudentGradeCalculator extends JFrame {
    private JTextField studentNameField;
    private JPanel subjectsPanel;
    private JLabel percentageLabel, gradeLabel;
 
    private ArrayList<SubjectRow> subjects = new ArrayList<>();

    private static final Color BACKGROUND = new Color(245, 247, 251);
    private static final Color SURFACE = Color.WHITE;
    private static final Color NAVY = new Color(25, 40, 72);
    private static final Color MUTED = new Color(111, 124, 145);
    private static final Color ACCENT = new Color(44, 104, 214);
 
    public StudentGradeCalculator() {
 
        setTitle("Student Grade Calculator");
        setSize(820, 790);
        setMinimumSize(new Dimension(700, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
 
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(BACKGROUND);
        mainPanel.setBorder(new EmptyBorder(28, 34, 24, 34));

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));

        JLabel eyebrow = new JLabel("ACADEMIC TOOLS");
        eyebrow.setForeground(ACCENT);
        eyebrow.setFont(new Font("SansSerif", Font.BOLD, 12));

        JLabel title = new JLabel("Student Grade Calculator");
        title.setForeground(NAVY);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));

        JLabel subtitle = new JLabel("Enter subject marks to calculate the overall result.");
        subtitle.setForeground(MUTED);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));

        headerPanel.add(eyebrow);
        headerPanel.add(Box.createVerticalStrut(7));
        headerPanel.add(title);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(subtitle);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel namePanel = new JPanel(new BorderLayout(16, 0));
        namePanel.setBackground(SURFACE);
        namePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 231, 239)),
                new EmptyBorder(16, 18, 16, 18)));
        namePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 76));

        JLabel nameLabel = new JLabel("Student name");
        nameLabel.setForeground(NAVY);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        studentNameField = new JTextField();
        styleTextField(studentNameField);
        studentNameField.setToolTipText("Enter the student's full name");

        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(studentNameField, BorderLayout.CENTER);
        centerPanel.add(namePanel);
        centerPanel.add(Box.createVerticalStrut(18));

        JPanel subjectHeading = new JPanel(new BorderLayout());
        subjectHeading.setOpaque(false);
        JLabel subjectsTitle = new JLabel("Subjects");
        subjectsTitle.setForeground(NAVY);
        subjectsTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
        JLabel marksHint = new JLabel("Marks are out of 100");
        marksHint.setForeground(MUTED);
        marksHint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        subjectHeading.add(subjectsTitle, BorderLayout.WEST);
        subjectHeading.add(marksHint, BorderLayout.EAST);
        subjectHeading.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        centerPanel.add(subjectHeading);
        centerPanel.add(Box.createVerticalStrut(10));

        subjectsPanel = new JPanel();
        subjectsPanel.setBackground(BACKGROUND);
        subjectsPanel.setLayout(new BoxLayout(subjectsPanel, BoxLayout.Y_AXIS));
 
        addSubject("Python");
        addSubject("Java");
        addSubject("C++");
 
        JScrollPane scrollPane = new JScrollPane(subjectsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension(700, 250));
        scrollPane.setMinimumSize(new Dimension(400, 150));
        centerPanel.add(scrollPane);

        centerPanel.add(Box.createVerticalStrut(18));
        JPanel resultPanel = new JPanel(new BorderLayout(20, 0));
        resultPanel.setBackground(NAVY);
        resultPanel.setBorder(new EmptyBorder(18, 22, 18, 22));
        resultPanel.setPreferredSize(new Dimension(700, 125));
        resultPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 125));

        JPanel percentagePanel = new JPanel();
        percentagePanel.setOpaque(false);
        percentagePanel.setLayout(new BoxLayout(percentagePanel, BoxLayout.Y_AXIS));
        JLabel resultCaption = new JLabel("OVERALL PERCENTAGE");
        resultCaption.setForeground(new Color(184, 199, 222));
        resultCaption.setFont(new Font("SansSerif", Font.BOLD, 11));
        percentageLabel = new JLabel("0.00%");
        percentageLabel.setForeground(Color.WHITE);
        percentageLabel.setFont(new Font("SansSerif", Font.BOLD, 38));
        percentagePanel.add(Box.createVerticalGlue());
        percentagePanel.add(resultCaption);
        percentagePanel.add(Box.createVerticalStrut(3));
        percentagePanel.add(percentageLabel);
        percentagePanel.add(Box.createVerticalGlue());

        JPanel gradePanel = new JPanel();
        gradePanel.setOpaque(false);
        gradePanel.setLayout(new BoxLayout(gradePanel, BoxLayout.Y_AXIS));
        JLabel gradeCaption = new JLabel("FINAL GRADE");
        gradeCaption.setForeground(new Color(184, 199, 222));
        gradeCaption.setFont(new Font("SansSerif", Font.BOLD, 11));
        gradeLabel = new JLabel("N/A");
        gradeLabel.setForeground(Color.WHITE);
        gradeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        gradePanel.add(Box.createVerticalGlue());
        gradePanel.add(gradeCaption);
        gradePanel.add(Box.createVerticalStrut(9));
        gradePanel.add(gradeLabel);
        gradePanel.add(Box.createVerticalGlue());

        resultPanel.add(percentagePanel, BorderLayout.CENTER);
        resultPanel.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.WEST);
        resultPanel.add(gradePanel, BorderLayout.EAST);
        centerPanel.add(resultPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setOpaque(false);
 
        JButton addButton = new JButton("+ Add subject");
        JButton calculateButton = new JButton("Calculate");
        JButton resetButton = new JButton("Reset");
        JButton exportButton = new JButton("Export");
 
        styleButton(addButton, SURFACE, NAVY, new Color(218, 225, 236));
        styleButton(calculateButton, ACCENT, Color.WHITE, ACCENT);
        styleButton(resetButton, SURFACE, new Color(180, 67, 67), new Color(238, 216, 216));
        styleButton(exportButton, SURFACE, NAVY, new Color(218, 225, 236));
 
        addButton.addActionListener(e -> addSubject("Subject"));
        calculateButton.addActionListener(e -> calculateResult());
        resetButton.addActionListener(e -> resetCalculator());
        exportButton.addActionListener(e -> exportResult());
 
        buttonPanel.add(addButton);
        buttonPanel.add(calculateButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exportButton);
 
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
 
        add(mainPanel);
        setVisible(true);
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setForeground(NAVY);
        field.setBackground(new Color(250, 251, 253));
        field.setCaretColor(ACCENT);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(218, 225, 236)),
                new EmptyBorder(9, 11, 9, 11)));
    }
 
    // Add subject
    private void addSubject(String name) {
        SubjectRow row = new SubjectRow(name);
        subjects.add(row);
        subjectsPanel.add(row.panel);
        subjectsPanel.revalidate();
        subjectsPanel.repaint();
    }
 
    // Calculate result
    private void calculateResult() {
 
        if (studentNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the student name."
            );
            return;
        }
 
        int total = 0;
        boolean failed = false;
 
        for (SubjectRow subject : subjects) {
 
            String text = subject.marksField.getText().trim();
 
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter marks for "
                                + subject.nameField.getText()
                );
                return;
            }
 
            try {
                int marks = Integer.parseInt(text);
 
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Marks must be between 0 and 100."
                    );
                    return;
                }
 
                total += marks;
 
                // Less than 35 = Fail
                if (marks < 35) {
                    failed = true;
                }
 
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid numbers."
                );
                return;
            }
        }
 
        double percentage =
                (double) total / (subjects.size() * 100) * 100;
 
        percentageLabel.setText(
                String.format("%.2f%%", percentage)
        );
 
        String grade;
 
        if (failed)
            grade = "FAIL";
        else if (percentage >= 90)
            grade = "A+";
        else if (percentage >= 80)
            grade = "A";
        else if (percentage >= 70)
            grade = "B+";
        else if (percentage >= 60)
            grade = "B";
        else if (percentage >= 50)
            grade = "C";
        else
            grade = "D";
 
        gradeLabel.setText("Overall Grade: " + grade);
    }
 
    // Reset
    private void resetCalculator() {
 
        studentNameField.setText("");
 
        for (SubjectRow subject : subjects)
            subject.marksField.setText("0");
 
        percentageLabel.setText("0.00%");
        gradeLabel.setText("Overall Grade: N/A");
    }
 
    // Export
    private void exportResult() {
 
        String name = studentNameField.getText().trim();
 
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter student name first."
            );
            return;
        }
 
        try {
 
            FileWriter writer =
                    new FileWriter("StudentGradeResult.txt");
 
            writer.write("STUDENT GRADE REPORT\n");
            writer.write("====================\n\n");
            writer.write("Student Name: " + name + "\n\n");
 
            for (SubjectRow subject : subjects) {
                writer.write(
                        subject.nameField.getText()
                                + " : "
                                + subject.marksField.getText()
                                + " / 100\n"
                );
            }
 
            writer.write(
                    "\nPercentage: "
                            + percentageLabel.getText()
                            + "\n"
            );
 
            writer.write(
                    gradeLabel.getText()
                            + "\n"
            );
 
            writer.close();
 
            JOptionPane.showMessageDialog(
                    this,
                    "Result exported successfully!"
            );
 
        } catch (IOException e) {
 
            JOptionPane.showMessageDialog(
                    this,
                    "Error exporting result."
            );
        }
    }
 
    // Button style
    private void styleButton(JButton button, Color background, Color foreground, Color borderColor) {
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor),
                new EmptyBorder(10, 15, 10, 15)));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
 
    // Subject row
    class SubjectRow {
 
        JPanel panel;
        JTextField nameField;
        JTextField marksField;
 
        SubjectRow(String name) {
 
            panel = new JPanel(new BorderLayout(18, 0));
            panel.setBackground(SURFACE);
            panel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(226, 231, 239)),
                    new EmptyBorder(10, 14, 10, 14)));
            panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 66));

            nameField = new JTextField(name);
            styleTextField(nameField);

            JLabel maxLabel = new JLabel("MAX");
            maxLabel.setForeground(MUTED);
            maxLabel.setFont(new Font("SansSerif", Font.BOLD, 10));

            JTextField maxField = new JTextField("100", 4);
            styleTextField(maxField);
            maxField.setEditable(false);
            maxField.setHorizontalAlignment(JTextField.CENTER);

            JLabel gotLabel = new JLabel("MARKS");
            gotLabel.setForeground(MUTED);
            gotLabel.setFont(new Font("SansSerif", Font.BOLD, 10));

            marksField = new JTextField("0", 4);
            styleTextField(marksField);
            marksField.setHorizontalAlignment(JTextField.CENTER);

            JPanel fieldsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
            fieldsPanel.setOpaque(false);
            fieldsPanel.add(maxLabel);
            fieldsPanel.add(maxField);
            fieldsPanel.add(gotLabel);
            fieldsPanel.add(marksField);

            panel.add(nameField, BorderLayout.CENTER);
            panel.add(fieldsPanel, BorderLayout.EAST);
        }
    }
 
    // Main
    public static void main(String[] args) {
 
        SwingUtilities.invokeLater(
                StudentGradeCalculator::new
        );
    }
}
