package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
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
					//MultiTalkServer MTS = new MultiTalkServer();
					//MTS.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
			}
		});
		
		JButton btnNewButton_1 = new JButton("\u4E00\u5BF9\u4E00\u804A\u5929");
		
		JButton btnNewButton_2 = new JButton("\u4F20\u9001\u6587\u4EF6");
		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(84)
					.addComponent(lbljava)
					.addContainerGap(88, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addComponent(btnNewButton_1)
					.addGap(56)
					.addComponent(btnNewButton_2)
					.addGap(24))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(lbljava)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(61)
							.addComponent(btnNewButton_2))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(58)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(59)
							.addComponent(btnNewButton_1)))
					.addContainerGap(118, Short.MAX_VALUE))
		);
		contentPane.setLayout(groupLayout);
	}
}
