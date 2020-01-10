// Starter Template 
// import(s) at top of program b4 class line

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class memory extends JFrame implements ActionListener{ 
  //Variable and GUIObject Declaration area 
  JLabel tittle, score;
  JPanel panel1, panel2, panel3;
  JButton [] buttons;
  Icon []imgIcon;
  int  clicks=1, firstbutton=0, add=0;
  int[][] check=new int [14][5];
  int [][] deck= new int [52][2];
  int nxtcard=0, nxtsuit=0;
  Timer myTimer; 
  
  
  public static void main(String[ ] args) 
  {
    new memory();
  }  
  
  public memory (){
    //Create object and your code goes here
    this.setLayout(new BorderLayout());
    tittle= new JLabel("MEMORY");
    panel1=new JPanel();
    this.add(panel1, BorderLayout.NORTH);
    
    panel1.add(tittle);
    
    panel2=new JPanel();
    score=new JLabel("Your Score is");
    this.add(panel2, BorderLayout.SOUTH);
    panel2.add(score);
    

    buttons=new JButton[52];
    panel3=new JPanel();
    this.add(panel3);

    for (int a=0; a<52; a++)
    {
      do{
        nxtcard=(int)(Math.random()*13)+1;
        nxtsuit=(int)(Math.random()*4)+1;
      }  while(check[nxtcard][nxtsuit]==1);
      check[nxtcard][nxtsuit]=1;
      deck[a][0]=nxtcard;
      deck[a][1]=nxtsuit;
      System.out.println(deck[a][0]+","+deck[a][1]);

    }
 
    for (int  t=0,b=0; b<52; b++)
    {
      //buttons[b] = new JButton(imgIcon2);
      buttons[b] = new JButton(""+deck[b][0]);
      buttons[b].setBackground(Color.BLUE);
      buttons[b].setForeground(Color.YELLOW);
      panel3.add(buttons[b]);
      buttons[b].addActionListener(this);
//      System.out.println(numbuttons+"numbuttons");
    }
    panel3.setLayout(new GridLayout(4,13,3,3));
    
    myTimer = new Timer(1000, new TimerListener());
 
    this.setSize(700,400);
    this.setVisible(true);
  } 
  
  public void actionPerformed(ActionEvent e)
  {
    //Most of the 'action' codes will be attached to buttons
    for (int  t=0,b=0; b<52; b++)
    {
      if (e.getSource()==buttons[b])
      {
        buttons[b].setForeground(Color.RED);
        if (clicks==1)
        {
          clicks=2;
          firstbutton=b;
        }
        else
        {
          if (buttons[firstbutton].getText().equals(buttons[b].getText()))
          {
            System.out.println("Match");
            buttons[firstbutton].setForeground(Color.GREEN);
            buttons[b].setForeground(Color.GREEN);
            buttons[firstbutton].setEnabled(false);
            buttons[b].setEnabled(false);
            add=add+10;
            score.setText("Your score is  " +add);
          }
          else
          {
            System.out.println("No Match");
            buttons[firstbutton].setForeground(Color.YELLOW);
            buttons[b].setForeground(Color.YELLOW);
            add=add-5;
            score.setText("Your score is "+add);
            myTimer.start();
          
          clicks=1;
        }
      }
    }
  }
}
     private void TimerListener implements ActionListener {
       public void actionPerformed(ActionEvent e) {
            buttons[firstbutton].setForeground(Color.WHITE);
            buttons[b].setForeground(Color.WHITE);
            myTimer.stop();
       }
     }

