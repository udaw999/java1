package comparator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

public class MyComparison extends JFrame {
    private JFrame frame;
    private JPanel panel;//создаенм панель
    private JPanel panel1;//создаенм панель
    private JPanel panel2;//создаенм панель
    private JButton compare;
    private JButton btnaAddBlockToFileOne;
    private JButton btnAddBlockToFileTwo;
    private JLabel lbSelectFile_1;
    private JLabel lbSelectFile_2;
    private JScrollPane scrollRight;
    private JScrollPane scrollLeft;
    private List list1;
    private List list2;
    private CompareTwoFiles compareTwoFiless;
    private File selectedFile_1 = null;
    private File selectedFile_2 = null;
    String fileName_1;// = "C:\\Users\\admin\\IdeaProjects\\fileCompare\\file_com_1.txt";
    String fileName_2;// = "C:\\Users\\admin\\IdeaProjects\\fileCompare\\file_com_2.txt";
    private int numBlok = 0;
    private ActionListener compareTwoFiles = new CompareActionListener();
    private JList<StringTwoFiles> listLeft;
    private JList<StringTwoFiles> listRight;

    public static void main(String[] args) {
        new MyComparison();
    }

    private MyComparison(){

        initFrame();
        initPanel();
    }

    //панель
    private void initPanel() {
        panel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        JButton btnSelectFile_1 = new JButton("Выбрать файл 1");
        JButton btnSelectFile_2 = new JButton("Выбрать файл 2");
        btnaAddBlockToFileOne = new JButton("Добавить блок в файл 1");
        btnAddBlockToFileTwo = new JButton("Добавить блок в файл 2");
        compare = new JButton("Сравнить");
        lbSelectFile_1 = new JLabel("Файл не выбран",JLabel.CENTER);
        lbSelectFile_2 = new JLabel("Файл не выбран",JLabel.CENTER);
        list1 = new List();
        list2 = new List();





        //меняем стандартные свойства на свои
        panel.setBorder(BorderFactory.createLineBorder(Color.gray,1));
        panel.setBounds(0,0,frame.getWidth(),40);

        panel1.setLayout( new BorderLayout() );//способ размещения содержимого
        panel1.setBounds(4,40,frame.getWidth()/2-16,frame.getHeight()-84);
        panel1.setBorder(BorderFactory.createLineBorder(Color.gray,1));

        panel2.setLayout( new BorderLayout() );//способ размещения содержимого
        panel2.setBounds(frame.getWidth()/2-4,40,frame.getWidth()/2-16,frame.getHeight()-84);
        panel2.setBorder(BorderFactory.createLineBorder(Color.gray,1));

        lbSelectFile_1.setForeground(Color.BLUE);//цвет
        lbSelectFile_1.setPreferredSize(new Dimension(panel1.getWidth(), 40));//размер
        lbSelectFile_2.setForeground(Color.BLUE);//цвет
        lbSelectFile_2.setPreferredSize(new Dimension(panel2.getWidth(), 40));//размер



        compare.setEnabled(false);//делаем кнопку неактивной
        btnaAddBlockToFileOne.setEnabled(false);
        btnAddBlockToFileTwo.setEnabled(false);



        //виполняем действие для соответствующей кнопке
        btnSelectFile_1.addActionListener(selectFile_1);
        btnSelectFile_2.addActionListener(selectFile_2);
        compare.addActionListener(compareTwoFiles);
        btnaAddBlockToFileOne.addActionListener(addBlockToFileOne);
        btnAddBlockToFileTwo.addActionListener(addBlockToFileTwo);



        // добавляем элементы на панель
        panel.add(btnaAddBlockToFileOne);
        panel.add(btnSelectFile_1);
        panel.add(compare);
        panel.add(btnSelectFile_2);
        panel.add(btnAddBlockToFileTwo);

        panel1.add("North",lbSelectFile_1);
        panel1.add("Center",list1);

        panel2.add("North",lbSelectFile_2);
        panel2.add("Center",list2);


        //добавить панель на форму

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel);
    }
    private ActionListener addBlockToFileOne = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent one) {
            compareTwoFiless.addBlockToFileOne(numBlok);
            CompareActionListener compTwo = new CompareActionListener();
            compTwo.actionPerformed(one);
            panel1.remove(scrollLeft);
            frame.revalidate();
            frame.repaint();//обновить вид
        }

    };
    private ActionListener addBlockToFileTwo = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent two) {
            compareTwoFiless.addBlockToFileTwo(numBlok);
            CompareActionListener compTwo = new CompareActionListener();
            compTwo.actionPerformed(two);
            panel2.remove(scrollRight);
            frame.revalidate();
            frame.repaint();//обновить вид
        }
    };
//setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.LIGHT_GRAY));

    //фрейм
    private void initFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// чтоб программа закрылась по клику на х
        frame.setTitle("Мой сравниватель файлов");//заголовок
        frame.setResizable(false);//размер окна без изменений false
        frame.setPreferredSize(new Dimension(1200,700));

        frame.setVisible(true);//видимость формы

        frame.pack();//для размера формы чтоб все поместилось
        frame.setExtendedState(MAXIMIZED_BOTH);//на всю ширину экрана
        //frame.setLocationRelativeTo(null);//окошко по центру
        //setIconImage(getImage("icon"));// иконка для программы


    }

    //выбор файла 1 после клика на кнопке
    private ActionListener selectFile_1 = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent arg0) {

            JFileChooser fc = new JFileChooser(); // открываем файл выбора.
            int returnValue = fc.showOpenDialog(null);//для проверки открытия окна
            if (returnValue == JFileChooser.APPROVE_OPTION) // проверяем открыто ли диалог окно
            {
                selectedFile_1 = fc.getSelectedFile(); // Ставим указатель на файл который выбрали
                lbSelectFile_1.setText(selectedFile_1.getAbsolutePath()); // Получаем Path файла который выбрали
                fileName_1 = selectedFile_1.getAbsolutePath();
                //panel1.add("Center",list1);//добавляем на панель List()
                //System.err.println("tttt-" +list1.isDisplayable());
                listFail(fileName_1, list1,scrollLeft,panel1);//считываем файл
                if (fileName_1 != null && fileName_2 != null)
                    compare.setEnabled(true);//делаем кнопку активной
            }

        }

    };


    //выбор файла 2 после клика на кнопке
    private ActionListener selectFile_2 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            if (scrollRight != null){//проверка есть ли уже результат сравнения на панели если есть
//                list2.removeAll();
//                panel2.remove(scrollRight);//удаляем его
//                panel2.add("Center",list2);//добавляем на панель List()
//            }
            JFileChooser fc = new JFileChooser(); // открываем файл выбора.
            int returnValue = fc.showOpenDialog(null);//для проверки открытия окна
            if (returnValue == JFileChooser.APPROVE_OPTION) // проверяем открыто ли диалог окно
            {
                selectedFile_2 = fc.getSelectedFile(); // Ставим указатель на файл который выбрали
                lbSelectFile_2.setText(selectedFile_2.getAbsolutePath()); // Получаем Path файла который выбрали
                fileName_2 = selectedFile_2.getAbsolutePath();

                listFail(fileName_2,list2,scrollRight,panel2 );
                if (fileName_1 != null && fileName_2 != null)
                    compare.setEnabled(true);//делаем кнопку активной
            } else {
                System.out.println("No selection");
            }
        }
    };

    private void listFail(String file, List list, JScrollPane scroll, JPanel paneled) {
        if (scroll != null && scroll.isDisplayable()){//проверка есть ли уже результат сравнения на панели если есть
            list.removeAll();
            paneled.remove(scroll);//удаляем его
            paneled.add("Center",list);//добавляем на панель List()
        }
        ReadFile readFiles = new ReadFile(file);
        readFiles.readFile();
        list.removeAll();
        for (String str : readFiles.getList()){
            list.add(str);
        }

    }

    //сравнивание после клика по кнопке Сравнить
    //private final ActionListener compareTwoFiles = new ActionListener() {
    public class CompareActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent z) {
            System.err.println(fileName_2);
            System.err.println(fileName_1);
            if (fileName_1 != null && fileName_2 != null) {
                compareTwoFiless = new CompareTwoFiles(fileName_1, fileName_2);
                compareTwoFiless.compareTwoFiles();//сравниваем
                System.err.println("после компаре");
                deriveComparisonLeft(compareTwoFiless);//выводим сравнение на панели 1(файл 1)
                deriveComparisonRight(compareTwoFiless);//выводим сравнение на панели 2(файл 2)

                //объединяем полосы прокрутки
                scrollLeft.getHorizontalScrollBar().setModel(scrollRight.getHorizontalScrollBar().getModel());
                scrollLeft.getVerticalScrollBar().setModel(scrollRight.getVerticalScrollBar().getModel());
//                fileName_1 = null;
//                fileName_2 = null;
                compare.setEnabled(false);//делаем кнопку не активной
                frame.revalidate();
                frame.repaint();//обновить вид
            } else {
                System.err.println("Выберите для сравнения файлы");
            }

        }

        //выводим сравнение на панели 1
        private void deriveComparisonLeft(CompareTwoFiles compareTwoFiless) {
            panel1.remove(list1);//удаляем с панели
            listLeft = new JList<>(new Vector<StringTwoFiles>() {
                {

                    for (StringTwoFiles line : compareTwoFiless.getArrayAllLines()) {
                        add(line);
                    }
                }
            });

            listLeft.setCellRenderer(new DefaultListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof StringTwoFiles) {
                        StringTwoFiles nextStringTwoFiles = (StringTwoFiles) value;

                        if (!nextStringTwoFiles.isBelongsFile_1() && nextStringTwoFiles.isBelongsFile_2()) {
                            setText(" ");
                            setBackground(Color.PINK);
                            //setBorder(BorderFactory.createLineBorder(Color.gray));
                        } else if (nextStringTwoFiles.isBelongsFile_1() && !nextStringTwoFiles.isBelongsFile_2()){
                            setText(" " + nextStringTwoFiles.getStr() );
                            setBackground(Color.PINK);
                        } else {
                            setText(" " + nextStringTwoFiles.getStr() );
                            setBackground(Color.white);
                        }
                        if (isSelected) {
                            setBackground(getBackground().darker());
                            numBlok = nextStringTwoFiles.getNumBlok();
                            enableButtonAddBlok(numBlok, compareTwoFiless);
                        }
                    } else {
                        setText("whodat?");
                    }
                    return c;
                }

            });


            scrollLeft = new JScrollPane(listLeft,
                    JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            //scrollLeft = new JScrollPane(listLeft);//добавляем лист
            //scrollLeft.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            panel1.add("Center",scrollLeft);//а потом скрол на панель
        }

        //выводим сравнение на панели 2
        private void deriveComparisonRight(CompareTwoFiles compareTwoFiless) {
            panel2.remove(list2);//удаляем с панели
            listRight = new JList<>(new Vector<StringTwoFiles>() {
                {

                    for (StringTwoFiles line : compareTwoFiless.getArrayAllLines()) {
                        add(line);
                        System.err.println(line);
                    }
                }
            });

            listRight.setCellRenderer(new DefaultListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof StringTwoFiles) {
                        StringTwoFiles nextStringTwoFiles = (StringTwoFiles) value;

                        if (!nextStringTwoFiles.isBelongsFile_1() && nextStringTwoFiles.isBelongsFile_2()) {
                            setText(" " + nextStringTwoFiles.getStr() );
                            setBackground(Color.PINK);
                        } else if (nextStringTwoFiles.isBelongsFile_1() && !nextStringTwoFiles.isBelongsFile_2()){
                            setText(" ");
                            setBackground(Color.PINK);
                        } else {
                            setText(" " + nextStringTwoFiles.getStr() );
                            setBackground(Color.white);
                        }
                        if (isSelected) {
                            setBackground(getBackground().darker());
                            numBlok = nextStringTwoFiles.getNumBlok();
                            //System.err.println("tttt-" +numBlok);
                            enableButtonAddBlok(numBlok, compareTwoFiless);
                        }
                    } else {
                        setText("whodat?");
                    }
                    return c;
                }

            });
            scrollRight = new JScrollPane(listRight,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            //scrollRight = new JScrollPane(listRight);//добавляем лист
            //scrollRight.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
            panel2.add("Center",scrollRight);//а потом скрол на панель
        }
    }

    private void enableButtonAddBlok(int numBlok, CompareTwoFiles compareTwoFiless){

        for (StringTwoFiles num : compareTwoFiless.getArrayAllLines()){
            if (num.getNumBlok() == numBlok && !num.isBelongsFile_1()){
                btnaAddBlockToFileOne.setEnabled(true);//кнопки активны
                btnAddBlockToFileTwo.setEnabled(false);
            }
            if (num.getNumBlok() == numBlok && !num.isBelongsFile_2()){
                btnAddBlockToFileTwo.setEnabled(true);
                btnaAddBlockToFileOne.setEnabled(false);
            }
            if(numBlok == 0){
                btnaAddBlockToFileOne.setEnabled(false);
                btnAddBlockToFileTwo.setEnabled(false);
            }

        }


    }
}

