package com.lee.iframe;

import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.lee.dao.ReadbookDao;
import com.lee.model.Read;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ReadInfoModiAndDelIFrame extends JInternalFrame {
    private JTextField UnoTxt;
    private JTextField BnoTxt;
    private JTextField RpageTxt;
    private int getBookStyleNumber(List<?> list){
        //tag等于1时，表示该图书类型编号存在
        int tag = 0;
        Object[] bookStyles = new Object[list.size()];
        for(int i=0;i<list.size();i++){
            Read bookStyle = (Read) list.get(i);
            bookStyles[i] = bookStyle.getBno();
            bookStyles[i] = bookStyle.getUno();
            if(bookStyles[i].equals(BnoTxt.getText())&&bookStyles[i].equals(UnoTxt)) {
                tag = 1;
            }
        }
        return tag;
    }

    /**
     * Create the frame.
     */
    public ReadInfoModiAndDelIFrame() {
        setTitle("阅读记录更新");
        setFrameIcon(new ImageIcon(ReadInfoAddIFrame.class.getResource("/icon/type_16px.png")));
        setIconifiable(true);
        setClosable(true);
        setBounds(100, 100, 500, 380);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(ReadInfoAddIFrame.class.getResource("/icon/readAdd.jpg")));
        panel.add(lblNewLabel);

        JPanel panel_Button = new JPanel();
        getContentPane().add(panel_Button, BorderLayout.SOUTH);

        JButton saveButton = new JButton("更新");
        saveButton.addActionListener(new SaveButtonActionListener());
        saveButton.setIcon(new ImageIcon(ReadInfoAddIFrame.class.getResource("/icon/save_button_16px.png")));
        panel_Button.add(saveButton);

        JButton closeButton = new JButton("关闭");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doDefaultCloseAction();
            }
        });
        closeButton.setIcon(new ImageIcon(ReadInfoAddIFrame.class.getResource("/icon/logoff_16px.png")));
        panel_Button.add(closeButton);

        JPanel panel_Info = new JPanel();
        getContentPane().add(panel_Info, BorderLayout.CENTER);

        JLabel UnoLabel = new JLabel("读者编号：");

        JLabel BnoLabel = new JLabel("图书编号：");

        JLabel RpageLabel = new JLabel("阅读页数：");


        UnoTxt = new JTextField();
        UnoTxt.setColumns(10);

        BnoTxt = new JTextField();
        BnoTxt.setColumns(10);

        RpageTxt = new JTextField();
        RpageTxt.setColumns(10);

        GroupLayout gl_panel_Info = new GroupLayout(panel_Info);
        gl_panel_Info.setHorizontalGroup(
                gl_panel_Info.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_Info.createSequentialGroup()
                                .addGap(77)
                                .addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING, false)
                                        .addGroup(gl_panel_Info.createSequentialGroup()
                                                .addComponent(RpageLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(RpageTxt, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel_Info.createSequentialGroup()
                                                .addComponent(BnoLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(BnoTxt))
                                        .addGroup(gl_panel_Info.createSequentialGroup()
                                                .addComponent(UnoLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(UnoTxt, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(68, Short.MAX_VALUE))
        );
        gl_panel_Info.setVerticalGroup(
                gl_panel_Info.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_Info.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(UnoLabel)
                                        .addComponent(UnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(26)
                                .addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(BnoLabel)
                                        .addComponent(BnoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(31)
                                .addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(RpageLabel)
                                        .addComponent(RpageTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(27)
                                .addContainerGap(48, Short.MAX_VALUE))
        );
        panel_Info.setLayout(gl_panel_Info);

        //设置窗体可见
        this.setVisible(true);
    }

    //保存按钮监听事件
    class SaveButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String Uno =  UnoTxt.getText();
            String Bno = BnoTxt.getText();
            String Rpage = RpageTxt.getText();
            if(Bno.length() == 0) {
                JOptionPane.showMessageDialog(null, "图书编号不能为空。");
                return;
            }
            if(Uno.length() == 0) {
                JOptionPane.showMessageDialog(null, "读者编号不能为空。");
                return;
            }
            if(Rpage.length() == 0) {
                JOptionPane.showMessageDialog(null, "页数不能为空。");
                return;
            }
            int i = ReadbookDao.updateReader(Uno,Bno,Integer.parseInt(RpageTxt.getText().trim()));
            //int i = ReaderInfoDao.insertReader(nameTxt.getText().trim(),NoTxt.getText().trim(),sex.trim(),Integer.parseInt(ageTxt.getText().trim()),Date.valueOf(regdateTxt.getText().trim()));
            if(i == 1) {
                JOptionPane.showMessageDialog(null, "更新成功。");
                UnoTxt.setText(null);
                BnoTxt.setText(null);
                RpageTxt.setText(null);
            }
        }
    }
}
