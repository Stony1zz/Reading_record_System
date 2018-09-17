package com.lee.iframe;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Reader;
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

import com.lee.dao.ReadbookDao;
import com.lee.dao.ReaderInfoDao;
import com.lee.model.Read;
import com.lee.model.ReadersInfo;
//阅读点击次数查询
@SuppressWarnings("serial")
public class ReadDataIFrame extends JInternalFrame {

    private JTextField queryConditionTxt;
    private JTable table_allResult, table_searchResult;
    private JTable table_allResult2, table_searchResult2;
    @SuppressWarnings("rawtypes")
    private JComboBox ComboBox;
    private JScrollPane scrollPane_allBook, scrollPane_result;
    private JScrollPane scrollPane_HotBook, scrollPane_result1;
    private String columnNames[] = {  "图书", "阅读热度" };
    private String columnNames1[] = { "读者", "阅读页数" };

    @SuppressWarnings("rawtypes")
    private Object[][] getselect2(List list) {
        Object[][]results=new Object[list.size()][columnNames.length];
        for(int i = 0; i < list.size(); i++) {
            Read Read = (Read) list.get(i);
            results[i][0] = Read.getUname();
            results[i][1] = Read.getRpiont();
        }
        return results;
    }
    private Object[][] getselect(List list) {
        Object[][]results=new Object[list.size()][columnNames.length];
        for(int i = 0; i < list.size(); i++) {
            Read Read = (Read) list.get(i);
            results[i][0] = Read.getBname();
            results[i][1] = Read.getRpiont();
        }
        return results;
    }
    /**
     * Create the frame.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ReadDataIFrame() {
        super();
        setFrameIcon(new ImageIcon(BookSearchIFrame.class.getResource("/icon/Book_Search_16px.png")));
        setIconifiable(true);
        setClosable(true);
        setTitle("阅读记录查询");
        setBounds(100, 100, 500, 400);

        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(0, 50));
        getContentPane().add(tabbedPane);

        final JPanel HotBookInfoPanel = new JPanel();
        tabbedPane.addTab("图书点击信息", null, HotBookInfoPanel, null);

        scrollPane_HotBook = new JScrollPane();
        scrollPane_HotBook.setPreferredSize(new Dimension(450, 250));
        HotBookInfoPanel.add(scrollPane_HotBook);

        Object[][] results=getselect(ReadbookDao.Readhot());
        table_allResult2 = new JTable(results,columnNames);

        JScrollPane scrollPane = new JScrollPane(table_allResult2);
        scrollPane.getViewport().setBackground(Color.black);

        table_allResult2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane_HotBook.setViewportView(table_allResult2);

        setVisible(true);

        final JPanel allBookInfoPanel = new JPanel();
        tabbedPane.addTab("读者阅读信息", null, allBookInfoPanel, null);

        scrollPane_allBook = new JScrollPane();
        scrollPane_allBook.setPreferredSize(new Dimension(450, 250));
        allBookInfoPanel.add(scrollPane_allBook);

        Object[][] results1=getselect2(ReadbookDao.Readerhard());
        table_allResult = new JTable(results1,columnNames1);

        JScrollPane scrollPane1 = new JScrollPane(table_allResult);
        scrollPane1.getViewport().setBackground(Color.black);

        table_allResult.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        scrollPane_allBook.setViewportView(table_allResult);

        //设置窗体可见
        setVisible(true);
    }
}