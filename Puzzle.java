
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Puzzle  extends JFrame implements ActionListener{   //Puzzle 클래스 만들고 버튼자체가 event를 만들기 때문에 ActionListener를 수신하도록 수신자를 둠
	
	public static void main(String[] args) {
		new Puzzle();
	}
	
	private JButton btn[];
	  
	Puzzle() {  
		 setTitle ("Puzzle");   //타이틀
		 setSize (400,300);     //사이즈
		 setDefaultCloseOperation(EXIT_ON_CLOSE);
		 makeUI();
		 setVisible(true);
	}
	
private void makeUI () {
            //버튼 16개 만들어야 하니까
	     btn = new JButton[16];
	     setLayout( new GridLayout(4,4)); 
	     for (int i=0 ; i < 16 ; i++  ) {
	    	add (  btn[i] =  new JButton(String.valueOf(i)));   //정수값을 String값으로 변환
 btn[i].setEnabled(true);  //활성화
 btn[i].addActionListener(this);  //수신자도 붙임, 수신자도 자기자신이 되도록
              }
	    	btn[15].setText(""); //제일 마지막 버튼인 15번째는 화면에 안나오도록  라벨이 없어야
	    	btn[15].setEnabled(false); // 제일 마지막 버튼은비활성화되도록 합시다.
	     }
	     		  
private int[] nb = new int[4]; //int 정수형 배열선언, C와 구문이 좀 다르죠? nb는 Neighbor를 나타낸다고 보시면 되겠습니다.
private void findNeighbor (int id) { //id를 줬을때 자기 이웃을 구하는 배열 nb에 위,아래,좌,우 정보를 받는 함수
   //nb 배열이 있는데 nb[0]에는 up이 들어가게 nb[1]은 down이.. 
  //up  
  nb[0] = id -4;
  
  //down
  nb[1] = id + 4;
  if (nb[1] >= 16 )
        nb[1] = -1; // 결과가 16이상이라면 결과가 없다는 의미로 일부러 음수를 넣음

  //left
  nb[2] = id - 1;
  if ((nb[2]) < 0  || nb[2] %4 == 3)
      nb[2] = -1;

  //right
  nb[3] = id + 1;
  if ((nb[3]) % 4 == 0 )
     nb[3] = -1; //-를 넣은 이유는 neighbor에 이웃이 없다는 뜻이 되겠다.
} 


 public void actionPerformed(ActionEvent e) {
	JButton b = (JButton)e.getSource();  // e는 버튼을 눌렀을때 어떤 어떤 버튼이 눌렸는지..actionevent를 발생시킨 소스가 누구냐하는 건데.
                                              //e는 object type(객체 타입)이다. 16개 버튼 중에 누가 눌렸나하는 것을 알아보는 겁니다.
                                              // b는 눌린 버튼이고, 누가 버튼을 눌렀는지는 코드를 작성
         int id;
         for ( id= 0 ; id<16; id++)     // b라는 버튼을 누가 눌렀나? id가 누른 거예요. id에 0부터 15까지 어떤 값이 들어가는데, 5를 누르면 id에 5가 들어감.
               if (b == btn[id])
                  break;

                                      // 5를 눌렀으면 5의 이웃을 찾아야...
            findNeighbor(id) ;         // 함수 호출, id값을 넣으면 id의 이웃값이 nb배열에 들어가게 됨.

            //그다음은 이웃중에서 활성화되지 않은 것이 뭐가 있는가? 클릭한 id가 활성화되지 않은 것과 바꾸어야.

             for (int i=0; i<4; i++) {  //up, down, left, right 고려해야
             if ( nb[i] >= 0 && !btn[nb[i]].isEnabled())  { //이웃의 버튼 4개가 음수라면 이웃이 없는것이므로 생략해야, 즉 이웃의 버튼 4개가 양수이면서 이웃 4개 버튼 중 비활성화된것이 있으면 for를 돌린다. id 버튼을 옮겨라
                 JButton act, inact;
                  act = btn[id];  //현재 버튼이 id이고
                 inact = btn[nb[i]];  //비활성화된게 nb버튼이니까
             inact.setText( act.getText() );  //라벨을 맞바꾸고
	     act.setText("");            
	     inact.setEnabled(true);         //활성화된것을 맞 바꾸고
	     act.setEnabled(false);
             break;  //이웃이 어느만 나비활성화되어 있을테니까 하나라도 활성화된것이 발견되면 for루프를 더 돌필요는 없다. 비활성화된게 발견되면 루프를 빠져나오면 된다.
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
