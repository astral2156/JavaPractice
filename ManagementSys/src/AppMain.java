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
	Container contentPane = getContentPane(); // 컨텐트 팬을 알아낸다.
	JTextArea ta = new JTextArea();
	ArrayList<Product> datas;

	Vector<Integer> prcode = new Vector<Integer>();
	JComboBox<String> combo = new JComboBox(prcode);
	int index;

	Product product;
	JTextField productField = new JTextField(15);
	JTextField priceField = new JTextField(15);
	JTextField manufactureField = new JTextField(15);
	JLabel ml = new JLabel("#### 메세지 출력부 ####");
	boolean editmode;

	
	class myPanel1 extends JPanel
	{

		  myPanel1() {

		         this.setLayout(new GridLayout(4, 2, 5, 5));
		         this.add(new JLabel("관리번호"));
		         this.add(combo);
		         this.add(new JLabel("상품명"));
		         this.add(productField);
		         this.add(new JLabel("단 가"));
		         this.add(priceField);
		         this.add(new JLabel("제조사"));
		         this.add(manufactureField);   
		         Product p = new Product();
		         
		         
		         combo.addActionListener(new ActionListener() {

		 			@Override
		 			public void actionPerformed(ActionEvent e) {
		 				System.out.println("액션 퍼폼드");
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
			registerBtn = new JButton("등록");
			selectBtn = new JButton("조회");
			deleteBtn = new JButton("삭제");
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
                 ml.setText( "상품을 수정했습니다!!");
                 clearField();
                 editmode = false;
              } else
                 ml.setText( "상품 수정이 실패했습니다.");
           } else {
              if (pdao.newProduct(product))
                 ml.setText( "상품을 등록했습니다!!");
              else
                 ml.setText( "상품 수정이 실패했습니다.");
           }
           refreshData();
        }
     }

     class selectListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	System.out.println("select clicked!");
           String s = (String) combo.getSelectedItem();
          
           if (!s.equals("전체")) {  
              product = pdao.getProduct(Integer.parseInt(s));
              if (product != null) {
            	  ml.setText("상품정보를 가져왔다");
                 productField.setText(product.getPrname());
                 priceField.setText(String.valueOf(product.getPrice()));
                 manufactureField.setText(product.getManufacture());

                 editmode = true;
              } 
                 //ml.setText(msg + "상품이 검색되지 않았습니다!!");
           }
        }
     }

     class deleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           String s = (String) combo.getSelectedItem();
           if (s.equals("전체"))
              ml.setText( "전체삭제는 되지 않습니다!!");
           else {
              if (pdao.delProduct(Integer.parseInt(s)))
                 ml.setText( "상품이 삭제되었습니다!!");
              else
                 ml.setText( "상품이 삭지되지 않았습니다!!");
           }
           refreshData();
        }
     }
  

	public void refreshData() {
		ta.setText("");
		clearField();
		editmode = false;

		ta.append("상품 번호 " +"\t" +"상품 이름"+"\t" +"상품 가격"+"\t" +"만든사람"+"\n");
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
			ta.append("등록된 상품 없음");
		}

	}
	public void clearField() {
		ta.setText("");
	}
	
	public AppMain() {
		setTitle("View"); // 프레임 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 윈도우를 닫으면 프로그램을 종료하도록 설정
		contentPane.setLayout(new BorderLayout(10, 10));

		contentPane.add(ml, BorderLayout.NORTH);
		contentPane.add(combo);
		contentPane.add(new JScrollPane(ta), BorderLayout.CENTER);
		contentPane.add(new myPanel1(), BorderLayout.WEST);
		contentPane.add(new myPanel2(), BorderLayout.SOUTH);

		pdao = new ProductDAO();
		setSize(800, 400); // 프레임 크기 300x150 설정
		setVisible(true); // 화면에 프레임 출력
		refreshData();

		// pdao.getAll();
	}

	
	public static void main(String args[]) {

		new AppMain();

	}

}
