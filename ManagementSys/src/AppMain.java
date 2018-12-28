import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AppMain extends JFrame {


	
	ProductDAO pdao;
	Container contentPane = getContentPane(); // ����Ʈ ���� �˾Ƴ���.
	JTextArea ta = new JTextArea();
	ArrayList<Product> datas;

	Vector<Integer> prcode = new Vector<Integer>();
	JComboBox<String> combo = new JComboBox(prcode);
	int index;

	Product product;
	JTextField productField = new JTextField(15);
	JTextField priceField = new JTextField(15);
	JTextField manufactureField = new JTextField(15);
	JLabel ml = new JLabel("#### �޼��� ��º� ####");
	boolean editmode;

	
	class myPanel1 extends JPanel
	{

		  myPanel1() {

		         this.setLayout(new GridLayout(4, 2, 5, 5));
		         this.add(new JLabel("������ȣ"));
		         this.add(combo);
		         this.add(new JLabel("��ǰ��"));
		         this.add(productField);
		         this.add(new JLabel("�� ��"));
		         this.add(priceField);
		         this.add(new JLabel("������"));
		         this.add(manufactureField);   
		         Product p = new Product();
		         
		         
		         combo.addActionListener(new ActionListener() {

		 			@Override
		 			public void actionPerformed(ActionEvent e) {
		 				System.out.println("�׼� ������");
		 				System.out.println(p.getPrice());

		 				JComboBox jcb = (JComboBox) e.getSource();
		 				index = jcb.getSelectedIndex();
		 				productField.setText("");
		 				priceField.setText(p.getPrice() + "");
		 				manufactureField.setText(p.getManufacture());
		 				index = index + 1;
		 				System.out.println("index : " + index);
		 				// pdao.getProduct(index);

		 			}

		 		});
		         
		      }

	
	}


	public class myPanel2 extends JPanel {

		JButton registerBtn;
		JButton selectBtn;
		JButton deleteBtn;
		
		myPanel2() {
			registerBtn = new JButton("���");
			selectBtn = new JButton("��ȸ");
			deleteBtn = new JButton("����");
			this.add(registerBtn);
			this.add(selectBtn);
			this.add(deleteBtn);
			
			registerBtn.addActionListener(new registerListener());
			selectBtn.addActionListener(new selectListener());
			deleteBtn.addActionListener(new deleteListener());
			
		}
	}
	class registerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          /*
        	product.setPrname(productField.getText());
           product.setPrice(Integer.parseInt(priceField.getText()));
           product.setManufacture(manufactureField.getText());
*/
           if (editmode == true) {
        	   product.setPrcode(Integer.parseInt((String) combo.getSelectedItem()));
              if (pdao.updateProduct(product)) {
                 ml.setText( "��ǰ�� �����߽��ϴ�!!");
                 clearField();
                 editmode = false;
              } else
                 ml.setText( "��ǰ ������ �����߽��ϴ�.");
           } else {
              if (pdao.newProduct(product))
                 ml.setText( "��ǰ�� ����߽��ϴ�!!");
              else
                 ml.setText( "��ǰ ������ �����߽��ϴ�.");
           }
           refreshData();
        }
     }

     class selectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	System.out.println("select clicked!");
           String s = (String) combo.getSelectedItem();
          
           if (!s.equals("��ü")) {  
              product = pdao.getProduct(Integer.parseInt(s));
              if (product != null) {
            	  ml.setText("��ǰ������ �����Դ�");
                 productField.setText(product.getPrname());
                 priceField.setText(String.valueOf(product.getPrice()));
                 manufactureField.setText(product.getManufacture());

                 editmode = true;
              } 
                 //ml.setText(msg + "��ǰ�� �˻����� �ʾҽ��ϴ�!!");
           }
        }
     }

     class deleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           String s = (String) combo.getSelectedItem();
           if (s.equals("��ü"))
              ml.setText( "��ü������ ���� �ʽ��ϴ�!!");
           else {
              if (pdao.delProduct(Integer.parseInt(s)))
                 ml.setText( "��ǰ�� �����Ǿ����ϴ�!!");
              else
                 ml.setText( "��ǰ�� �������� �ʾҽ��ϴ�!!");
           }
           refreshData();
        }
     }
  

	public void refreshData() {
		ta.setText("");
		clearField();
		editmode = false;

		ta.append("��ǰ ��ȣ " +"\t" +"��ǰ �̸�"+"\t" +"��ǰ ����"+"\t" +"������"+"\n");
		datas = pdao.getAll();

		combo.setModel(new DefaultComboBoxModel(pdao.getItems()));

		if (datas != null) {

			for (Product p : datas) {
				StringBuffer sb = new StringBuffer();
				sb.append(p.getPrcode()+"\t");
				sb.append(p.getPrname()+"\t");
				sb.append(p.getPrice()+"\t");
				sb.append(p.getManufacture()+"\n");
				ta.append(sb.toString());

			}
		} else {
			ta.append("��ϵ� ��ǰ ����");
		}

	}
	public void clearField() {
		ta.setText("");
	}
	
	public AppMain() {
		setTitle("View"); // ������ Ÿ��Ʋ �ޱ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ �����츦 ������ ���α׷��� �����ϵ��� ����
		contentPane.setLayout(new BorderLayout(10, 10));

		contentPane.add(ml, BorderLayout.NORTH);
		contentPane.add(combo);
		contentPane.add(new JScrollPane(ta), BorderLayout.CENTER);
		contentPane.add(new myPanel1(), BorderLayout.WEST);
		contentPane.add(new myPanel2(), BorderLayout.SOUTH);

		pdao = new ProductDAO();
		setSize(800, 400); // ������ ũ�� 300x150 ����
		setVisible(true); // ȭ�鿡 ������ ���
		refreshData();

		// pdao.getAll();
	}

	
	public static void main(String args[]) {

		new AppMain();

	}

}
