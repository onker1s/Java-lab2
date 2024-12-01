import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static java.lang.Math.*;


@SuppressWarnings("serial")

public class Main extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldC;
    private JTextField textFieldResult;
    private JTextField textFieldMCX;
    private JTextField textFieldMCY;
    private JTextField textFieldMCZ;
    private ButtonGroup radioButtonsfor = new ButtonGroup();
    private ButtonGroup radioButtonsvar = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxVariableSelect = Box.createHorizontalBox();
    private int formulaId = 1;
    private int varId = 1;
    Double mem1 =0.0; Double mem2 = 0.0; Double mem3 = 0.0;
    Double result;
    JLabel labelFormula1 = new JLabel();

    public Double formula1(Double x, Double y, Double z)  {
        return pow(pow(log(1+x),2)+cos(PI*pow(z,3)),sin(y))+pow((exp(pow(x,2))+ cos(exp(z))+sqrt(1/y)),1/x);
    }

    public Double formula2(Double x, Double y, Double z) {
        return pow(cos(PI*pow(x,3))+ pow(log(1+y),2),1/4)*(exp(pow(z,2))+sqrt(1/x)+cos(exp(y)));
    }

    private void addRadioButton(String buttonName, final int formulaId) throws IOException {
        JRadioButton button = new JRadioButton(buttonName);

        ImageIcon imagef1;
        Image image = ImageIO.read(new File("src\\f1.bmp"));
        Image newimg = image.getScaledInstance(500, 50,  java.awt.Image.SCALE_SMOOTH);
        imagef1 = new ImageIcon(newimg);
        labelFormula1.setIcon(imagef1);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Main.this.formulaId = formulaId;

                if (formulaId==1){
                    ImageIcon imagef1 ;
                    Image image = null;
                    try {
                        image = ImageIO.read(new File("src\\f1.bmp"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Image newimg = image.getScaledInstance(500, 60,  java.awt.Image.SCALE_SMOOTH);
                    imagef1 = new ImageIcon(newimg);
                    labelFormula1.setIcon(imagef1);}
                else {
                    ImageIcon imagef2;
                    Image image = null;
                    try {
                        image = ImageIO.read(new File("src\\f2.bmp"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ;
                    Image newimg = image.getScaledInstance(500, 60,  java.awt.Image.SCALE_SMOOTH);
                    imagef2 = new ImageIcon(newimg);
                    labelFormula1.setIcon(imagef2);}
            }
        });
        radioButtonsfor.add(button);
        hboxFormulaType.add(button);
    }

    private void addRadioButton_var(String buttonName, final int varId) {
        JRadioButton button = new JRadioButton(buttonName);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Main.this.varId = varId;}
        });
        radioButtonsvar.add(button);
        hboxVariableSelect.add(button);
    }

    public Main() throws IOException {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);

        hboxVariableSelect.add(Box.createHorizontalGlue());
        addRadioButton_var("Переменная 1", 1);
        addRadioButton_var("Переменная 2", 2);
        addRadioButton_var("Переменная 3", 3);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);

        radioButtonsvar.setSelected(
                radioButtonsvar.getElements().nextElement().getModel(), true);
        hboxVariableSelect.add(Box.createHorizontalGlue());


        radioButtonsfor.setSelected(
                radioButtonsfor.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());

        JLabel labelForA = new JLabel("x:");
        textFieldA = new JTextField(10);
        textFieldA.setMaximumSize(textFieldA.getPreferredSize());
        JLabel labelForB = new JLabel("y:");
        textFieldB = new JTextField(10);
        textFieldB.setMaximumSize(textFieldB.getPreferredSize());
        JLabel labelForC= new JLabel("z:");
        textFieldC = new JTextField("0", 10);
        textFieldC.setMaximumSize(textFieldC.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForA);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldA);
        hboxVariables.add(Box.createHorizontalStrut(45));
        hboxVariables.add(labelForB);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldB);
        hboxVariables.add(Box.createHorizontalStrut(45));
        hboxVariables.add(labelForC);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldC);
        hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Результат:");

        textFieldResult = new JTextField("0", 40);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());

        JButton buttonMp = new JButton("M+");
        buttonMp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try { if (varId==1)
                { mem1+=result;
                    String resS = mem1.toString();
                    textFieldMCX.setText(mem1.toString());
                    }
                    if (varId==2)
                    { mem2+=result;
                        String resS = mem2.toString();
                        textFieldMCY.setText(mem2.toString());
                        }
                    if (varId==3)
                    { mem3+=result;
                        String resS = mem3.toString();
                        textFieldMCZ.setText(mem3.toString());
                        }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try { if (varId==1)
                {mem1 =0.0; ; textFieldMCX.setText("0");}
                    if (varId==2)
                    {mem2=0.0; ;textFieldMCY.setText("0");}
                    if (varId==3)
                    { mem3=0.0; ;textFieldMCZ.setText("0");}
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        Box hboxMButtons = Box.createHorizontalBox();
        hboxMButtons.add(Box.createHorizontalGlue());
        hboxMButtons.add(buttonMp);
        hboxMButtons.add(Box.createHorizontalStrut(10));
        hboxMButtons.add(buttonMC);
        hboxMButtons.add(Box.createHorizontalGlue());

        textFieldMCX = new JTextField("0", 10);
        textFieldMCX.setMaximumSize(textFieldMCX.getPreferredSize());
        textFieldMCY = new JTextField("0", 10);
        textFieldMCY.setMaximumSize(textFieldMCY.getPreferredSize());
        textFieldMCZ = new JTextField("0", 10);
        textFieldMCZ.setMaximumSize(textFieldMCZ.getPreferredSize());

        Box hboxMFields = Box.createHorizontalBox();
        hboxMFields.add(Box.createHorizontalGlue());
        hboxMFields.add(textFieldMCX);
        hboxMFields.add(Box.createHorizontalStrut(10));
        hboxMFields.add(textFieldMCY);
        hboxMFields.add(Box.createHorizontalStrut(10));
        hboxMFields.add(textFieldMCZ);
        hboxMFields.add(Box.createHorizontalGlue());

        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double A = Double.parseDouble(textFieldA.getText());
                    Double B = Double.parseDouble(textFieldB.getText());
                    Double C = Double.parseDouble(textFieldC.getText());

                    if (formulaId==1)  result  = formula1(A, B, C);
                    else result = formula2(A, B, C);
                    String resS = result.toString();
                    textFieldResult.setText(resS);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldA.setText("0");
                textFieldB.setText("0");
                textFieldC.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        
        buttonCalc.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonReset.setAlignmentX(Component.RIGHT_ALIGNMENT);
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonReset);


        Box hboxFormulaImage1  = Box.createHorizontalBox();
        hboxFormulaImage1.add(Box.createHorizontalGlue());
        hboxFormulaImage1.add(labelFormula1);
        hboxFormulaImage1.add(Box.createHorizontalGlue());

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxVariableSelect);
        contentBox.add(hboxMFields);
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxMButtons);
        contentBox.add(hboxFormulaImage1);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);

    }

    public static void main(String[] args) throws IOException {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}