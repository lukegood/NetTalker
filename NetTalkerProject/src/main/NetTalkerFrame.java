package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NetTalkerFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NetTalkerFrame frame = new NetTalkerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		MultiTalkServer MTS = new MultiTalkServer();
		MTS.start();
	}

	/**
	 * Create the frame.
	 */
	public NetTalkerFrame() {
		setTitle("Java\u804A\u5929\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lbljava = new JLabel("\u6B22\u8FCE\u4F7F\u7528Java\u804A\u5929\u5668\uFF0C\u8BF7\u9009\u62E9\u4F60\u60F3\u8981\u4F7F\u7528\u7684\u529F\u80FD");
		
		JButton btnNewButton = new JButton("\u591A\u4EBA\u804A\u5929");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				ManyPeopleChatFrame m = new ManyPeopleChatFrame();
				m.setVisible(true);
		    	m.start();
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u4E00\u5BF9\u4E00\u804A\u5929");
		
		JButton btnNewButton_2 = new JButton("\u4F20\u9001\u6587\u4EF6");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SendFileFrame sff = new SendFileFrame();
				sff.setVisible(true);
				//sff.start();
			}
		});
		
		JButton btnNewButton_3 = new JButton("\u4E86\u89E3\u66F4\u591A\u4FE1\u606F");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "作者：\n王希\n王李光 \nwlg1996@webmail.hzau.edu.cn\nJava聊天器", "了解更多信息",  JOptionPane.INFORMATION_MESSAGE); 
			}
		});
		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(84)
					.addComponent(lbljava)
					.addContainerGap(88, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(107, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_2))
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(111))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(lbljava)
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_2)
						.addComponent(btnNewButton_3))
					.addGap(71))
		);
		contentPane.setLayout(groupLayout);
	}
}
