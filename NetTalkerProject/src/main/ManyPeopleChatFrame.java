package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

//import main.TalkClient.GetServerMessage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ManyPeopleChatFrame extends JFrame {

	private JPanel contentPane;
    //private TalkClient mtc;
    private static Socket socket;
	private Scanner sin;
	private static Scanner choice;
	private JScrollPane js;
	public static char ch;
	File file;
	JTextField textField;
	JTextArea textArea_1;
	JButton button_1;
	JLabel label;
	private JButton button;
	private String time;
	public void start() {

		try{
			GetServerMessage GSM = new GetServerMessage();
	        new Thread(GSM).start();
	        PrintWriter os=new PrintWriter(socket.getOutputStream(),true);
	        //String readline = null;
			//sin = new Scanner(System.in);
	        //	BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			//由Socket对象得到输出流，并构造PrintWriter对象
	        button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { 
						//若从标准输入读入的字符串为 "bye"则停止循环
							//将从系统标准输入读入的字符串输出到Server
						//	readline = sin.readLine();
							//readline = new String(textField.getText()); //从系统标准输入读入一字符串
							os.println(textField.getText());
							os.flush();//刷新输出流，使Server马上收到该字符串
							//在系统标准输出上打印读入的字符串
							//从Server读入一字符串，并打印到标准输出上		
							
						 //继续循环
				}
			});
		}catch(Exception e) {
			System.out.println("Error"+e); //出错，则打印出错信息
		}
	}
    private class GetServerMessage implements Runnable{
        public void run() {
            try {
                 /*通过这个内部类启动一个线程
                  * 不断读取服务器发来的消息
                  */
            	  BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                  String message = null;
                  while((message = is.readLine()) != null){
                	  time = new SimpleDateFormat("h:m:s").format(new Date());
                	  textArea_1.append(time+"\r\n");
                	  textArea_1.append(message+"\r\n");
                  }
               } catch (Exception e) {
                  e.printStackTrace();
           }
       }

}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManyPeopleChatFrame frame = new ManyPeopleChatFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public ManyPeopleChatFrame() {
		setTitle("Java\u804A\u5929\u5668-\u591A\u4EBA\u804A\u5929");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		label = new JLabel("\u804A\u5929\u5185\u5BB9\uFF1A");
		
		button_1 = new JButton("\u53D1\u8A00");
		
		textArea_1 = new JTextArea();
		js=new JScrollPane(textArea_1);
		js.setHorizontalScrollBarPolicy(
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setVerticalScrollBarPolicy(
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//设置滚动条
		textArea_1.setLineWrap(true);        //激活自动换行功能 
		textArea_1.setWrapStyleWord(true);  // 激活断行不断字功能
		textArea_1.append("客户端与服务端连接中，请等待...\n\r");
				//mtcf.lblNewLabel.setT
				//向本机的6666端口发出客户请求
				try {
					socket = new Socket("127.0.0.1",6666);
					
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					textArea_1.append("客户端与服务端连接成功!\n\r");
				}
				
		
		textField = new JTextField();
		textField.setColumns(10);
		
		button = new JButton("\u4F20\u9001\u6587\u4EF6");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SendFileFrame s = new SendFileFrame();
				s.setVisible(true);
			}
			
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(69)
								.addComponent(button_1)
								.addContainerGap()))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(58)
							.addComponent(button)
							.addContainerGap())))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button_1)
							.addGap(18)
							.addComponent(button)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
