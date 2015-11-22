/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoryGame;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Phuc
 */
public class GameFrame extends JFrame{
    public GameFrame(String title){
        super(title);
        getContentPane().setLayout(null);
	JLabel labelS = new JLabel("SCORE: ");
	labelS.setLocation(12,12);
	labelS.setSize(150,30);
        labelS.getSize();
	getContentPane().add(labelS);
        
//        JTextField newItemField = new JTextField();
//	newItemField.setLocation(12,12);
//	newItemField.setSize(150,30);
//	getContentPane().add(newItemField);
    }

    public static void main(String args[]){
        GameFrame gf= new GameFrame("Test");
        gf.setVisible(true);
        gf.setSize(800, 400);
    }
}
