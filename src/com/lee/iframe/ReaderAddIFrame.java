package com.lee.iframe;

import com.lee.dao.ReaderInfoDao;
import com.lee.model.ReadersInfo;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
//读者信息添加
@SuppressWarnings("serial")
public class ReaderAddIFrame extends JInternalFrame {

    private JTextField nameTxt;
    private JTextField ageTxt;
    private JTextField NoTxt;
    private JFormattedTextField regdateTxt;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton JRadioButton1;
    private JRadioButton JRadioButton2;
    private int getReaderNumber(List<?> list){
        //tag等于1时，表示读者编号存在
        int tag = 0;
        Object[] Reader = new Object[list.size()];
        for(int i=0;i<list.size();i++){
            ReadersInfo Readers = (ReadersInfo) list.get(i);
            Reader[i] =Readers.getUno();
            if(Reader[i].equals(NoTxt.getText())) {
                tag = 1;
            }
        }
        return tag;
    }

    /**
     * Create the frame.
     */
    public ReaderAddIFrame() {
        setTitle("读者添加");
        setFrameIcon(new ImageIcon(ReadInfoAddIFrame.class.getResource("/icon/type_16px.png")));
        setIconifiable(true);
        setClosable(true);
        setBounds(100, 100, 500, 500);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(ReadInfoAddIFrame.class.getResource("/icon/background.jpg")));
        panel.add(lblNewLabel);

        JPanel panel_Button = new JPanel();
        getContentPane().add(panel_Button, BorderLayout.SOUTH);

        JButton saveButton = new JButton("添加");
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

        JLabel BnoLabel = new JLabel("姓    名：");

        JLabel sexLabel = new JLabel("性     别：");

        JLabel BnameLabel = new JLabel("年    龄：");

        JLabel BpageLabel = new JLabel("学    号：");

        JLabel BtypeLabel = new JLabel("注 册 日 期：");

        nameTxt = new JTextField();
        nameTxt.setColumns(10);
        JPanel panel_radioButton1 = new JPanel();

        JRadioButton1 = new JRadioButton("男");
        buttonGroup.add(JRadioButton1);
        panel_radioButton1.add(JRadioButton1);
        JRadioButton2 = new JRadioButton("女");
        buttonGroup.add(JRadioButton2);
        panel_radioButton1.add(JRadioButton2);

        ageTxt = new JTextField();
        ageTxt.setColumns(10);

        NoTxt = new JTextField();
        NoTxt.setColumns(10);

        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");
        regdateTxt = new JFormattedTextField(myfmt.getDateInstance());
        regdateTxt.setColumns(20);
        GroupLayout gl_panel_Info = new GroupLayout(panel_Info);
        gl_panel_Info.setHorizontalGroup(
                gl_panel_Info.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_Info.createSequentialGroup()
                                .addGap(77)
                                .addGroup(gl_panel_Info.createParallelGroup(Alignment.LEADING, false)
                                        .addGroup(gl_panel_Info.createSequentialGroup()
                                                .addComponent(BtypeLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(regdateTxt, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel_Info.createSequentialGroup()
                                                .addComponent(BpageLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(NoTxt, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel_Info.createSequentialGroup()
                                                .addComponent(BnameLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(ageTxt))
                                        .addGroup(gl_panel_Info.createSequentialGroup()
                                                .addComponent(sexLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGap(18)
                                                .addComponent(panel_radioButton1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel_Info.createSequentialGroup()
                                                .addComponent(BnoLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(77, Short.MAX_VALUE))
        );
        gl_panel_Info.setVerticalGroup(
                gl_panel_Info.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_Info.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(BnoLabel)
                                        .addComponent(nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(26)
                                .addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(sexLabel)
                                        .addComponent(panel_radioButton1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
                                .addGap(26)
                                .addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(BnameLabel)
                                        .addComponent(ageTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(31)
                                .addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(BpageLabel)
                                        .addComponent(NoTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(27)
                                .addGroup(gl_panel_Info.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(BtypeLabel)
                                        .addComponent(regdateTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
            String Uname =  nameTxt.getText();
            String Uage = ageTxt.getText();
            String Uno = NoTxt.getText();
            String Udata = regdateTxt.getText();
            String sex = "女";
            if(JRadioButton1.isSelected()) {
                sex = "男";
            }
            if(getReaderNumber(ReaderInfoDao.selectReader()) == 1) {
                JOptionPane.showMessageDialog(null, "该读者编号已存在，请重新输入。");
                return;
            }
            if(Uname.length() == 0) {
                JOptionPane.showMessageDialog(null, "读者不能为空。");
                return;
            }
            if(Uage.length() == 0) {
                JOptionPane.showMessageDialog(null, "读者年龄不能为空。");
                return;
            }
            if(Uno.length() == 0) {
                JOptionPane.showMessageDialog(null, "学号不能为空。");
                return;
            }
            if(Udata.length() == 0) {
                JOptionPane.showMessageDialog(null, "日期不能为空。");
                return;
            }int i = ReaderInfoDao.insertReader(nameTxt.getText().trim(),NoTxt.getText().trim(),sex.trim(),Integer.parseInt(ageTxt.getText().trim()),Date.valueOf(regdateTxt.getText().trim()));
            if(i == 1) {
                JOptionPane.showMessageDialog(null, "添加成功。");
                nameTxt.setText(null);
                NoTxt.setText(null);
                ageTxt.setText(null);
            }
        }
    }
}
