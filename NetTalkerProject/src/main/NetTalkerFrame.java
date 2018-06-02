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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.LayoutStyle.ComponentPlacement;


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
					MultiTalkServer MTS = new MultiTalkServer();
					MTS.start();
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
		
		JLabel lbljava = new JLabel("\u6B22\u8FCE\u4F7F\u7528Java\u804A\u5929\u5668\uFF0C\u8BF7\u9009\u62E9\u4F60\u60F3\u4F7F\u7528\u7684\u529F\u80FD");
		
		JButton button = new JButton("\u591A\u4EBA\u804A\u5929");
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TalkClient tc = new TalkClient();
				tc.start();
				ManyPeopleChatFrame m = new ManyPeopleChatFrame();
				m.setVisible(true);
			}
		});
		
		JButton button_1 = new JButton("\u4E00\u5BF9\u4E00\u804A\u5929");
		
		JButton button_2 = new JButton("\u6587\u4EF6\u4F20\u9001");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(button)
					.addPreferredGap(ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
					.addComponent(button_1)
					.addGap(57)
					.addComponent(button_2)
					.addGap(31))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(82)
					.addComponent(lbljava)
					.addContainerGap(102, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(53)
					.addComponent(lbljava)
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_2)
						.addComponent(button_1)
						.addComponent(button))
					.addContainerGap(108, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
