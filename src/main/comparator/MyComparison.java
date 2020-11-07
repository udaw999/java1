package comparator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class MyComparison extends JFrame {
    private JFrame frame;
    private JPanel panel;//создаенм панель
    private JPanel panel1;//создаенм панель
    private JPanel panel2;//создаенм панель
    private JButton compare;
    private JButton btnPatch;
    private JButton btnaAddBlockToFileOne;
    private JButton btnAddBlockToFileTwo;
    private JLabel lbSelectFile_1;
    private JLabel lbSelectFile_2;
    private JScrollPane scrollRight;
    private JScrollPane scrollLeft;
    private CompareTwoFiles compareTwoFiless;
    private File selectedFile_1 = null;
    private File selectedFile_2 = null;
    String fileName_1;// = "C:\\Users\\admin\\IdeaProjects\\fileCompare\\file_com_1.txt";
    String fileName_2;// = "C:\\Users\\admin\\IdeaProjects\\fileCompare\\file_com_2.txt";
    private int numBlok = 0;
    private ActionListener compareTwoFiles = new CompareActionListener();
    private JList<Object> listLeft;
    private JList<Object> listRight;
    private JList<Object> listPatch;
    private DefaultListModel<Object> listModelLeft;
    private DefaultListModel<Object> listModelRight;
    private DefaultBoundedRangeModel scrModelDef;
    private DefaultListModel<Object> modelPatch;

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
        btnPatch = new JButton("Патч");
        btnaAddBlockToFileOne = new JButton("Добавить блок в файл 1");
        btnAddBlockToFileTwo = new JButton("Добавить блок в файл 2");
        compare = new JButton("Сравнить");
        lbSelectFile_1 = new JLabel("Файл не выбран",JLabel.CENTER);
        lbSelectFile_2 = new JLabel("Файл не выбран",JLabel.CENTER);
        listModelLeft = new DefaultListModel<>();
        listModelRight = new DefaultListModel<>();
        modelPatch = new DefaultListModel<>();
        listLeft = new JList<Object>(listModelLeft);
        listRight = new JList<Object>(listModelRight);

        scrollLeft = new JScrollPane(listLeft,
                VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollRight = new JScrollPane(listRight,
                VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);




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
        btnPatch.setEnabled(false);


        //виполняем действие для соответствующей кнопке
        btnSelectFile_1.addActionListener(selectFile_1);
        btnSelectFile_2.addActionListener(selectFile_2);
        compare.addActionListener(compareTwoFiles);
        btnPatch.addActionListener(new Patch());
        btnaAddBlockToFileOne.addActionListener(addBlockToFileOne);
        btnAddBlockToFileTwo.addActionListener(addBlockToFileTwo);



        // добавляем элементы на панель
        panel.add(btnaAddBlockToFileOne);
        panel.add(btnSelectFile_1);
        panel.add(compare);
        panel.add(btnPatch);
        panel.add(btnSelectFile_2);
        panel.add(btnAddBlockToFileTwo);

        panel1.add("North",lbSelectFile_1);

        panel2.add("North",lbSelectFile_2);



        //объединяем полосы прокрутки
        // scrollLeft.getHorizontalScrollBar().setModel(scrollRight.getHorizontalScrollBar().getModel());
        // scrollLeft.getVerticalScrollBar().setModel(scrollRight.getVerticalScrollBar().getModel());

        panel2.add("Center",scrollRight);//а потом скрол на панель
        panel1.add("Center",scrollLeft);//а потом скрол на панель

        //добавить панель на форму

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel);
        frame.revalidate();
//        frame.repaint();//обновить вид
    }



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

    private ActionListener addBlockToFileOne = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent one) {
            compareTwoFiless.addBlockToFileOne(numBlok);
            CompareActionListener compTwo = new CompareActionListener();
            compTwo.actionPerformed(one);
        }

    };
    private ActionListener addBlockToFileTwo = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent two) {
            compareTwoFiless.addBlockToFileTwo(numBlok);
            CompareActionListener compTwo = new CompareActionListener();
            compTwo.actionPerformed(two);
        }
    };

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

                listFail(fileName_1, listModelLeft);//считываем файл
                if (fileName_1 != null && fileName_2 != null)
                    compare.setEnabled(true);//делаем кнопку активной
            }

        }

    };


    //выбор файла 2 после клика на кнопке
    private ActionListener selectFile_2 = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser fc = new JFileChooser(); // открываем файл выбора.
            int returnValue = fc.showOpenDialog(null);//для проверки открытия окна
            if (returnValue == JFileChooser.APPROVE_OPTION) // проверяем открыто ли диалог окно
            {
                selectedFile_2 = fc.getSelectedFile(); // Ставим указатель на файл который выбрали
                lbSelectFile_2.setText(selectedFile_2.getAbsolutePath()); // Получаем Path файла который выбрали
                fileName_2 = selectedFile_2.getAbsolutePath();

                listFail(fileName_2,listModelRight);
                if (fileName_1 != null && fileName_2 != null)
                    compare.setEnabled(true);//делаем кнопку активной
            } else {
                System.out.println("No selection");
            }
        }
    };

    private void listFail(String file, DefaultListModel<Object> list) {
        if (scrModelDef != null)//возвращаем независимый скрол первому файлу
            scrollLeft.getVerticalScrollBar().setModel(scrModelDef);
        ReadFile readFiles = new ReadFile(file);
        readFiles.readFile();
        list.removeAllElements();
        for (String str : readFiles.getList()){
            list.addElement(str);
        }

    }

    //сравнивание после клика по кнопке Сравнить
    //private final ActionListener compareTwoFiles = new ActionListener() {
    public class CompareActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent z) {
            if (fileName_1 != null && fileName_2 != null) {
                compareTwoFiless = new CompareTwoFiles(fileName_1, fileName_2);
                compareTwoFiless.compareTwoFiles();//сравниваем

                listModelLeft.removeAllElements();
                listModelRight.removeAllElements();
                modelPatch.removeAllElements();
                for (StringTwoFiles str : compareTwoFiless.getArrayAllLines()){
                    listModelLeft.addElement(str);
                    listModelRight.addElement(str);
                    modelPatch.addElement(str);
                }

                deriveComparisonLeft();//выводим сравнение на панели 1(файл 1)
                deriveComparisonRight();//выводим сравнение на панели 2(файл 2)

                //запоминаем настройки скрола первого файла перед объединением
                scrModelDef = (DefaultBoundedRangeModel) scrollLeft.getVerticalScrollBar().getModel();
                //объединяем полосы прокрутки
                //scrollLeft.getHorizontalScrollBar().setModel(scrollRight.getHorizontalScrollBar().getModel());
                scrollLeft.getVerticalScrollBar().setModel(scrollRight.getVerticalScrollBar().getModel());

                compare.setEnabled(false);//делаем кнопку не активной
                btnPatch.setEnabled(true);
//                frame.revalidate();
//                frame.repaint();//обновить вид
            } else {
                System.err.println("Выберите для сравнения файлы");
            }

        }

        //выводим сравнение на панели 1
        private void deriveComparisonLeft() {
            listLeft.setCellRenderer(new DefaultListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof StringTwoFiles) {
                        StringTwoFiles nextStringTwoFiles = (StringTwoFiles) value;

                        if (!nextStringTwoFiles.isBelongsFile_1() && nextStringTwoFiles.isBelongsFile_2()) {
                            setText(" ");
                            setBackground(Color.LIGHT_GRAY);
                            //setBorder(BorderFactory.createLineBorder(Color.gray));
                        } else if (nextStringTwoFiles.isBelongsFile_1() && !nextStringTwoFiles.isBelongsFile_2()){
                            setText(" " + nextStringTwoFiles.getStr() );
                            setBackground(Color.ORANGE);
                        } else {
                            setText(" " + nextStringTwoFiles.getStr() );
                            setBackground(Color.white);
                        }
                        if (isSelected) {
                            setBackground(getBackground().darker());
                            numBlok = nextStringTwoFiles.getNumBlok();
                            enableButtonAddBlok(numBlok, compareTwoFiless);
                        }
                    }
                    return c;
                }

            });
        }

        //выводим сравнение на панели 2
        private void deriveComparisonRight() {
            listRight.setCellRenderer(new DefaultListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof StringTwoFiles) {
                        StringTwoFiles nextStringTwoFiles = (StringTwoFiles) value;

                        if (!nextStringTwoFiles.isBelongsFile_1() && nextStringTwoFiles.isBelongsFile_2()) {
                            setText(" " + nextStringTwoFiles.getStr() );
                            setBackground(Color.ORANGE);
                        } else if (nextStringTwoFiles.isBelongsFile_1() && !nextStringTwoFiles.isBelongsFile_2()){
                            setText(" ");
                            setBackground(Color.LIGHT_GRAY);
                        } else {
                            setText(" " + nextStringTwoFiles.getStr() );
                            setBackground(Color.white);
                        }
                        if (isSelected) {
                            setBackground(getBackground().darker());
                            numBlok = nextStringTwoFiles.getNumBlok();
                            enableButtonAddBlok(numBlok, compareTwoFiless);

                        }
                    }
                    return c;
                }

            });
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

    //окно патча
    class Patch implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame frame2 = new JFrame();

            //frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// чтоб программа закрылась по клику на х
            frame2.setTitle("Патч файлов");//заголовок
            frame2.setResizable(false);//размер окна без изменений false
            frame2.setPreferredSize(new Dimension(1200,700));

            frame2.setVisible(true);//видимость формы

            frame2.pack();//для размера формы чтоб все поместилось
            frame2.setLocationRelativeTo(null);//окошко по центру
            //frame2.setExtendedState(MAXIMIZED_BOTH);//на всю ширину экрана

            listPatch = new JList<Object>(modelPatch);
            JScrollPane scrollPatch = new JScrollPane(listPatch,
                    VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

            frame2.add(scrollPatch);


            listPatch.setCellRenderer(new DefaultListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(JList list, Object value, int index,
                                                              boolean isSelected, boolean cellHasFocus) {
                    Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof StringTwoFiles) {
                        StringTwoFiles nextStringTwoFiles = (StringTwoFiles) value;

                        if (!nextStringTwoFiles.isBelongsFile_1() && nextStringTwoFiles.isBelongsFile_2()) {
                            setText(" + " + nextStringTwoFiles.getStr());
                            setBackground(Color.GREEN);
                            //setBorder(BorderFactory.createLineBorder(Color.gray));
                        } else if (nextStringTwoFiles.isBelongsFile_1() && !nextStringTwoFiles.isBelongsFile_2()){
                            setText(" - " + nextStringTwoFiles.getStr() );
                            setBackground(Color.PINK);
                        } else {
                            setText("   " + nextStringTwoFiles.getStr() );
                            setBackground(Color.white);
                        }
                        if (isSelected) {
                            setBackground(getBackground().darker());
//                            numBlok = nextStringTwoFiles.getNumBlok();
//                            enableButtonAddBlok(numBlok, compareTwoFiless);
                        }
                    }
                    return c;
                }

            });
        }
    }
}