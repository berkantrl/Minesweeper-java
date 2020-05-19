

package minesweeper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

  
public class Minesweeper implements ActionListener{
    JFrame frame = new JFrame("Minesweeper");
    JButton ybaslat = new JButton("Yeniden başlat");
    JButton[][] buton = new JButton[20][20];
    int[][] counts = new int[20][20];
    Container grid = new Container();
    final int may�n = 10;
    public Minesweeper(){
        frame.setSize(1000,700);
        frame.setLayout(new BorderLayout());
        frame.add(ybaslat,BorderLayout.NORTH);
        ybaslat.addActionListener(this);
        grid.setLayout(new GridLayout(20,20));
        for (int i = 0; i < buton.length; i++) {
            for (int j = 0; j < buton[0].length; j++) {
                buton[i][j] = new JButton();
                buton[i][j].addActionListener(this);
                grid.add(buton[i][j]);
                
                
            }
            
        }
        frame.add(grid,BorderLayout.CENTER);
        mayınolustur();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
 
    public static void main(String[] args) {
    new Minesweeper();
    
    }
    public void may�nolustur(){
        ArrayList<Integer> liste = new ArrayList();
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[0].length; j++) {
                liste.add(i*100+j);
               
            }
            
        }
        counts = new int [20][20];
        for (int i = 0; i < 30; i++) {
           int secim = (int)(Math.random() * liste.size());
           counts[liste.get(secim)/100][liste.get(secim) % 100] = may�n;
            liste.remove(secim);
        }
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[0].length; j++) {
                if(counts[i][j] != may�n){
               int komsu = 0;
               if(i > 0 && j > 0 && counts[i-1][j-1] == may�n){
                   komsu++;
               }
               if(j > 0 && counts[i][j-1] == may�n){
                   komsu++;
               }
               if(i > counts.length - 1 && j < counts[0].length - 1 && counts[i+1][j+1]==may�n){
                   komsu++;
                   
               }
               counts[i][j] = komsu;
                }
            }
            
        }
    }
    public void oyunkaybet(){
        for (int i = 0; i < buton.length; i++) {
            for (int j = 0; j < buton[0].length; j++) {
               if(buton[i][j].isEnabled()){
                   if(counts[i][j] != may�n){
                       buton[i][j].setText(counts[i][j]+"");
                       buton[i][j].setEnabled(false);
                       
                   }else{
                       buton[i][j].setText("x");
                      buton[i][j].setEnabled(false);
                   }
               } 
                
            }
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource().equals(ybaslat)){
            for (int i = 0; i < buton.length; i++) {
                for (int j = 0; j < buton.length; j++) {
                    buton[i][j].setEnabled(true); 
                    buton[i][j].setText("");
                }
            }
            may�nolustur();
        }else{
            
            for (int i = 0; i < buton.length; i++) {
                for (int j = 0; j < buton.length; j++) {
                    if(event.getSource().equals(buton[i][j])){
                        if(counts[i][j]==may�n){
                           buton[i][j].setForeground(Color.red);
                           buton[i][j].setText("x");
                           oyunkaybet();

                        }else{
                        buton[i][j].setText(counts[i][j]+"");
                        buton[i][j].setEnabled(false);
                    }
                    
                }
                
            }
        }
    }
    
}
}
