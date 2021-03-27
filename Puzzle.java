
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Puzzle  extends JFrame implements ActionListener{   //Puzzle Ŭ���� ����� ��ư��ü�� event�� ����� ������ ActionListener�� �����ϵ��� �����ڸ� ��
	
	public static void main(String[] args) {
		new Puzzle();
	}
	
	private JButton btn[];
	  
	Puzzle() {  
		 setTitle ("Puzzle");   //Ÿ��Ʋ
		 setSize (400,300);     //������
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 makeUI();
		 setVisible(true);
	}
	
private void makeUI () {
            //��ư 16�� ������ �ϴϱ�
	     btn = new JButton[16];
	     setLayout( new GridLayout(4,4)); 
	     for (int i=0 ; i < 16 ; i++  ) {
	    	add (  btn[i] =  new JButton(String.valueOf(i)));   //�������� String������ ��ȯ
 btn[i].setEnabled(true);  //Ȱ��ȭ
 btn[i].addActionListener(this);  //�����ڵ� ����, �����ڵ� �ڱ��ڽ��� �ǵ���
              }
	    	btn[15].setText(""); //���� ������ ��ư�� 15��°�� ȭ�鿡 �ȳ�������  ���� �����
	    	btn[15].setEnabled(false); // ���� ������ ��ư����Ȱ��ȭ�ǵ��� �սô�.
	     }
	     		  
private int[] nb = new int[4]; //int ������ �迭����, C�� ������ �� �ٸ���? nb�� Neighbor�� ��Ÿ���ٰ� ���ø� �ǰڽ��ϴ�.
private void findNeighbor (int id) { //id�� ������ �ڱ� �̿��� ���ϴ� �迭 nb�� ��,�Ʒ�,��,�� ������ �޴� �Լ�
   //nb �迭�� �ִµ� nb[0]���� up�� ���� nb[1]�� down��.. 
  //up  
  nb[0] = id -4;
  
  //down
  nb[1] = id + 4;
  if (nb[1] >= 16 )
        nb[1] = -1; // ����� 16�̻��̶�� ����� ���ٴ� �ǹ̷� �Ϻη� ������ ����

  //left
  nb[2] = id - 1;
  if ((nb[2]) < 0  || nb[2] %4 == 3)
      nb[2] = -1;

  //right
  nb[3] = id + 1;
  if ((nb[3]) % 4 == 0 )
     nb[3] = -1; //-�� ���� ������ neighbor�� �̿��� ���ٴ� ���� �ǰڴ�.
} 


 public void actionPerformed(ActionEvent e) {
	JButton b = (JButton)e.getSource();  // e�� ��ư�� �������� � � ��ư�� ���ȴ���..actionevent�� �߻���Ų �ҽ��� �������ϴ� �ǵ�.
                                              //e�� object type(��ü Ÿ��)�̴�. 16�� ��ư �߿� ���� ���ȳ��ϴ� ���� �˾ƺ��� �̴ϴ�.
                                              // b�� ���� ��ư�̰�, ���� ��ư�� ���������� �ڵ带 �ۼ�
         int id;
         for ( id= 0 ; id<16; id++)     // b��� ��ư�� ���� ������? id�� ���� �ſ���. id�� 0���� 15���� � ���� ���µ�, 5�� ������ id�� 5�� ��.
               if (b == btn[id])
                  break;

                                      // 5�� �������� 5�� �̿��� ã�ƾ�...
            findNeighbor(id) ;         // �Լ� ȣ��, id���� ������ id�� �̿����� nb�迭�� ���� ��.

            //�״����� �̿��߿��� Ȱ��ȭ���� ���� ���� ���� �ִ°�? Ŭ���� id�� Ȱ��ȭ���� ���� �Ͱ� �ٲپ��.

             for (int i=0; i<4; i++) {  //up, down, left, right ����ؾ�
             if ( nb[i] >= 0 && !btn[nb[i]].isEnabled())  { //�̿��� ��ư 4���� ������� �̿��� ���°��̹Ƿ� �����ؾ�, �� �̿��� ��ư 4���� ����̸鼭 �̿� 4�� ��ư �� ��Ȱ��ȭ�Ȱ��� ������ for�� ������. id ��ư�� �Űܶ�
                 JButton act, inact;
                  act = btn[id];  //���� ��ư�� id�̰�
                 inact = btn[nb[i]];  //��Ȱ��ȭ�Ȱ� nb��ư�̴ϱ�
             inact.setText( act.getText() );  //���� �¹ٲٰ�
	     act.setText("");            
	     inact.setEnabled(true);         //Ȱ��ȭ�Ȱ��� �� �ٲٰ�
	     act.setEnabled(false);
             break;  //�̿��� ����� ����Ȱ��ȭ�Ǿ� �����״ϱ� �ϳ��� Ȱ��ȭ�Ȱ��� �߰ߵǸ� for������ �� ���ʿ�� ����. ��Ȱ��ȭ�Ȱ� �߰ߵǸ� ������ ���������� �ȴ�.
             }


         /* JButton act,inact;
	if(  b1.isEnabled () ){
		 act = b1;
		 inact =  b2;
	  } 
	  else {
		  act  =  b2;
		  inact  =  b1;
	  }
         */
	 /*inact.setText( act.getText() );
	 act.setText("");
	 inact.setEnabled(true);
	 act.setEnabled(false);
        */
 }
}}
