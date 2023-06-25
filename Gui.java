import java.io.*;
import javax.swing.*;
import java.awt.*;
class Gui {
	static String user = "";
	static String response = "";
	public static String getResponse(String q) throws Exception {
	    String command = " python request.py \""+q+"\" ", res="";
	    Process child = Runtime.getRuntime().exec(command);

	    InputStream in = child.getInputStream();
	    int c;
	    while ((c = in.read()) != -1) {
	      res += (char)c;
	    }
	    in.close();
	    return res;
    }
    public static void main(String args[]) {

        // Frame
        JFrame frame = new JFrame("GPT Client w Java (for sunrin AImath assignment)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setSize(600, 600);

        // components 추가용 메뉴 바
        /*
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);
        JMenuItem m11 = new JMenuItem("Open");
        JMenuItem m22 = new JMenuItem("Save as");
        m1.add(m11);
        m1.add(m22);
		*/
		
        // Panel
        JPanel panel = new JPanel(); // 출력시엔 안보임
        JLabel label = new JLabel("Question: ");
        JTextField tf = new JTextField(32);
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        panel.add(label);
        panel.add(tf);
        panel.add(send);
        panel.add(reset);

        // TextArea
        JTextArea textArea = new JTextArea(1,1);
         textArea.setLineWrap(true);
        textArea.setEditable(false);
		JScrollPane scroll = new JScrollPane (textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.add(scroll);

		Font font = new Font("Calibri", Font.BOLD, 18);
        textArea.setFont(font);
		// Function of send
		send.addActionListener(e ->
        {
            user = tf.getText();
            textArea.append("\nYou: "+user);
            try{
            	response = getResponse(user);
            	textArea.append("\nGPT: "+response.trim());
            }catch(Exception exception){
            	System.exit(-1);
            }
			tf.setText("");
        });

        reset.addActionListener(e -> 
        {
        	textArea.setText("");
        });
        

        // Frame에 components 추가
        frame.getContentPane().add(java.awt.BorderLayout.SOUTH, panel);
        frame.getContentPane().add(java.awt.BorderLayout.CENTER, textArea);
        frame.setVisible(true);
    }
}
