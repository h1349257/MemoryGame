/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryGame;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import javax.swing.Timer;

/**
 *
 * @author Phuc
 */
public final class GameClass extends JFrame implements ActionListener{
    JLabel lblTilesTurned, labelHighScore, lblScore;
    JToggleButton toggleMusic;
    JToggleButton toggleSound;
    Timer timer;
    JProgressBar bar;
    private int n ;
    int timeClick = 0, score = 0, numClick = 0;
    Boolean tile = false;
    JButton[] b;
  
    public int getNumTile(){
        return n;
    }
    public void setNumTile(int value){
        if(value < 0)
            n = 16;
        else
            n = value;
    }
    public GameClass(String tile, int numberTile){
        super(tile);
        getContentPane().setLayout(null);
        this.setNumTile(numberTile);
        loadGame(getContentPane(), numberTile);
        addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		    	 new main_Frame().setVisible(true);
		      }
		    });
        load();
    }

    GameClass() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    private void loadGame(Container c, int Num){
        b = new JButton[Num];
        int k = 0, j = 0;
        for (int i = 0; i < Num; i++){
            b[i] = new JButton();
            b[i].setName("");
            b[i].setSize(100, 100);
            //b[i].setEnabled(false);
            //b[i].setBackground(Color.BLUE);
            b[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoryGame/resource/images/Default_.jpg")));
            //b[i].setIcon(new javax.swing.ImageIcon("E:\\New folder 2\\_Learning\\Chuyen de Java\\Workspace\\BaiTapCDJava\\src\\GiaoDienWin\\Image\\(" + 1 + ").ico"));
            //b[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoryGame/resource/images/Default_.jpg")));
            //b[i].setIcon(new javax.swing.ImageIcon("/memoryGame/resource/images/Default_.jpg"));
            b[i].setLocation(10 + k * 105, 10 + j * 105);
            //b[i].setText(String.valueOf(k) + "-" + String.valueOf(b[i].getLocation().x) + ", " +String.valueOf(b[i].getLocation().y) );
            //b[i].enable(true);
            b[i].addActionListener(this);
            c.add(b[i]);
            k++;
            if(n == 16){
                if (k == 4){
                    k = 0; j++;
                }
            }
            else if(n == 24){
                if(k == 6){
                    k = 0;
                    j++;
                }
            }
            else if(n == 36){
                if(k == 6){
                    k = 0;
                    j++;
                }
            }
            else if(n == 48){
                if(k == 8){
                    k = 0;
                    j++;
                }
                
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int k=0; k<n; k++){
            if (e.getSource()== b[k]){
                labelHighScore.setText(b[k].getText());
                b[k].setName(Integer.toString(k));
                lblScore.setText(b[k].getName());
                b[k].setBackground(Color.GRAY);
                b[k].setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoryGame/resource/images/(" + b[k].getText() + ").png")));
                numClick++;
                lblTilesTurned.setText(Integer.toString(numClick));
               // b[k].setEnabled(true);
                
                
            }
            //if( numClick % 2 != 0) {
            //} else {
                try {
                    checkPair();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            //}
        }
        throw new UnsupportedOperationException("Not supported yet.");
        
    }
       public void random(){
        //String num = Integer.toString((int)(Math.random()*24));
        
        //Nửa mảng button cho random các giá trị từ 0 đến 9
        //ArrayList list = new ArrayList();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < b.length / 2; i++){
            int j =  (int) (Math.random()*22);
            b[i].setText(Integer.toString(j));
            list.add(j);
         }
         for (int i = b.length / 2; i < b.length; i++){
            int x = (int) (Math.random()*(list.size() - 1)); // Lấy ngẫu nhiên 1 index trong list.
            b[i].setText(Integer.toString(list.get(x)));//random phần tử có index x trong list.
            list.remove(x);//Xóa phần tử đã được random trong list.
        }
    }
    
     public void checkPair() throws InterruptedException{
        for (int i = 0; i < b.length - 1; i++){
            for (int j = i + 1; j < b.length; j++){
                if (!"".equals(b[i].getName()) && !"".equals(b[j].getName())){
                    //Nếu 2 button đều đã mở thì kiểm tra tiếp xem tag của 2 button có bằng nhau không, nếu bằng nhau thì xóa button.
                    if (b[i].getText().equals(b[j].getText())){
                        //--------------------correctSound();
                        //Thread.sleep(500);
                        //b[i].enable(true);
                        //b[j].enable(true);
                        b[i].setVisible(false);
                        b[j].setVisible(false);
                        score = score + 10;
                        //lblYScore.Text = Score.ToString();
                        //dem++;
                    }
                    else{
                        //wrongSound();
                        //Thread.sleep(500);//-------------------- chu y chu y
                        //b[i].enable(true);
                        //b[j].enable(true);
                        if (score > 0)
                            score = score - 5;
                        //lblYScore.Text = Score.ToString();
                        b[i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoryGame/resource/images/Default_.jpg")));
                        b[j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoryGame/resource/images/Default_.jpg")));
                        b[j].setName("");
                        b[i].setName("");
                    } 
                   
                }

            }
        }
    
    } 
    
    private void load(){
        JLabel labelHS = new JLabel("HIGH SCORE: ");
	labelHS.setLocation(700,90);
        labelHS.setSize(150,30);
	getContentPane().add(labelHS);
        
        labelHighScore = new JLabel("????????");
	labelHighScore.setLocation(800,90);
        labelHighScore.setSize(150,30);
	getContentPane().add(labelHighScore);
        //Score
   	JLabel labelS = new JLabel("SCORE: ");
	labelS.setLocation(700,120);
        labelS.setSize(150,30);
	getContentPane().add(labelS);
        
        lblScore = new JLabel("????????");
        lblScore.setLocation(700,160);
        lblScore.setSize(150,30);
	getContentPane().add(lblScore);
        
        //Tiles turned
        JLabel labelT = new JLabel("TILES TURNED: ");
	labelT.setLocation(700,190);
        labelT.setSize(150,30);
	getContentPane().add(labelT);
        
        lblTilesTurned = new JLabel("????????");
        lblTilesTurned.setLocation(800,220);
        lblTilesTurned.setSize(150,30);
	getContentPane().add(lblTilesTurned);
        
        //Time
        JLabel lblTi = new JLabel("TIME:");
        lblTi.setLocation(700,60);
        lblTi.setSize(150,30);
	getContentPane().add(lblTi);
        
        final JLabel lblTime = new JLabel("????????????");
        lblTime.setLocation(800,60);
        lblTime.setSize(150,30);
	getContentPane().add(lblTime);
        
        //Play
        JButton btnPlay = new JButton("PLAY");
        btnPlay.setLocation(700, 250);
        btnPlay.setSize(100, 30);
        getContentPane().add(btnPlay);
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                timer.start();
                random();
            }
        });
        
        //Pause
        JButton btnPause = new JButton("PAUSE");
        btnPause.setLocation(700, 280);
        btnPause.setSize(100, 30);
        getContentPane().add(btnPause);
        btnPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                timer.stop();
            }
        });
             
        //Setting
        JButton btnSetting = new JButton("SETTING");
        btnSetting.setLocation(700, 310);
        btnSetting.setSize(100, 30);
        getContentPane().add(btnSetting);
        btnSetting.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                settingFrame setting = new settingFrame();
                setting.setVisible(true);
                setting.setSize(700, 400);
                setting.setLocationRelativeTo(null);
            }
        });
		 
        
        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setLocation(700, 340);
        btnCancel.setSize(100, 30);
        getContentPane().add(btnCancel);
        
        toggleMusic = new JToggleButton("MUSIC");
        toggleMusic.setLocation(700, 400);
        toggleMusic.setSize(100, 30);
        getContentPane().add(toggleMusic);
        
        toggleSound = new JToggleButton("SOUND");
        toggleSound.setLocation(700, 430);
        toggleSound.setSize(100, 30);
        getContentPane().add(toggleSound);
        
        bar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
        bar.setSize(200, 30);
        bar.setLocation(700, 500);
        getContentPane().add(bar);
        
        timer = new Timer(1000, new ActionListener() {
        private int counter = 1;
        @Override
        public void actionPerformed(ActionEvent ae) {
            lblTime.setText(String.valueOf(counter));
            bar.setValue(++counter);
            if (counter > 100) {
                timer.stop();
            }
        }
        
    });
    }
    
 
}
