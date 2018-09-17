package com.lee.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.lee.dao.ReaderInfoDao;
import com.lee.model.ReadersInfo;

//读者信息搜索
public class ReaderSearchIFrame extends JInternalFrame {

    private JTextField queryConditionTxt;
    private JTable table_allResult, table_searchResult;
    private JComboBox ComboBox;
    private JScrollPane scrollPane_allBook, scrollPane_result;
    private String columnNames[] = { "读者姓名", "学号", "性别", "年龄", "注册日期" };

    private Object[][] getselect(List list) {
        Object[][]results=new Object[list.size()][columnNames.length];
        for(int i = 0; i < list.size(); i++) {
            ReadersInfo Reader = (ReadersInfo) list.get(i);
            results[i][0] = Reader.getUname();
            results[i][1] = Reader.getUno();
            results[i][2] = Reader.getUsex();
            results[i][3] = Reader.getUage();
            results[i][4] = Reader.getRegdate();
        }
        return results;
    }
    /**
     * Create the frame.
     */
    public ReaderSearchIFrame() {
        super();
        setFrameIcon(new ImageIcon(BookSearchIFrame.class.getResource("/icon/Book_Search_16px.png")));
        setIconifiable(true);
        setClosable(true);
        setTitle("读者查询");
        setBounds(100, 100, 500, 400);

        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(0, 50));
        getContentPane().add(tabbedPane);

        final JPanel conditionQueryPanel = new JPanel();
        conditionQueryPanel.setLayout(new BorderLayout());
        tabbedPane.addTab("条件查询", null, conditionQueryPanel, null);

        final JPanel selectQueryPanel = new JPanel();
        selectQueryPanel.setBorder(new TitledBorder(null, "请选择查询项目", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        selectQueryPanel.setPreferredSize(new Dimension(0, 50));
        conditionQueryPanel.add(selectQueryPanel, BorderLayout.NORTH);

        ComboBox=new JComboBox();
        String[] array={"读者姓名","读者学号"};
        for(int i=0;i<array.length;i++){
            ComboBox.addItem(array[i]);
        }
        selectQueryPanel.add(ComboBox);
        queryConditionTxt = new JTextField();
        queryConditionTxt.setColumns(20);
        selectQueryPanel.add(queryConditionTxt);

        final JPanel searchResultPanel = new JPanel();
        searchResultPanel.setBorder(new TitledBorder(null, "查询结果显示", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        conditionQueryPanel.add(searchResultPanel);


        scrollPane_result = new JScrollPane();
        scrollPane_result.setPreferredSize(new Dimension(400, 200));
        searchResultPanel.add(scrollPane_result);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(0, 50));
        conditionQueryPanel.add(buttonPanel, BorderLayout.SOUTH);

        final JButton searchButton = new JButton();
        searchButton.setIcon(new ImageIcon(BookSearchIFrame.class.getResource("/icon/Book_Search_16px.png")));
        searchButton.setText("查询");
        buttonPanel.add(searchButton);

        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                String name=(String)ComboBox.getSelectedItem();
                if(name.equals("读者姓名")){
                    Object[][] results=getselect(ReaderInfoDao.selectName(queryConditionTxt.getText().trim()));
                    table_searchResult = new JTable(results,columnNames);
                    scrollPane_result.setViewportView(table_searchResult);
                }
                else {
                    Object[][] results=getselect(ReaderInfoDao.selectNo(queryConditionTxt.getText().trim()));

                    table_searchResult = new JTable(results,columnNames);
                    scrollPane_result.setViewportView(table_searchResult);
                }
            }
        });

        final JButton exitButton = new JButton();
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doDefaultCloseAction();
            }
        });
        exitButton.setIcon(new ImageIcon(BookSearchIFrame.class.getResource("/icon/logoff_16px.png")));
        exitButton.setText("退出");
        buttonPanel.add(exitButton);

        setVisible(true);

        final JPanel allBookInfoPanel = new JPanel();
        tabbedPane.addTab("显示读者全部信息", null, allBookInfoPanel, null);

        scrollPane_allBook = new JScrollPane();
        scrollPane_allBook.setPreferredSize(new Dimension(450, 250));
        allBookInfoPanel.add(scrollPane_allBook);

        Object[][] results=getselect(ReaderInfoDao.selectReader());
        table_allResult = new JTable(results,columnNames);
        table_allResult.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane_allBook.setViewportView(table_allResult);

        //设置窗体可见
        setVisible(true);
    }
}