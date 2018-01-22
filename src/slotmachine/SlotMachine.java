
package slotmachine;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SlotMachine extends JFrame{

    public SlotMachine() {
        int imageNo = 6;
        int rows = 3;
        int columns = 5;
        int random = 5;
        int maxBet = 3;
        
        Reel[] symbol = new Reel[imageNo];
        for (int x = 0; x < imageNo; x++ ) {
            symbol[x] = new Reel();
        }
        symbol[0].image = "redseven";
        symbol[0].rewardMult = 7;
        
        symbol[1].image = "bell";
        symbol[1].rewardMult = 6;
        
        symbol[2].image = "watermelon";
        symbol[2].rewardMult = 5;
        
        symbol[3].image = "plum";
        symbol[3].rewardMult = 4;
        
        symbol[4].image = "lemon";
        symbol[4].rewardMult = 3;
        
        symbol[5].image = "cherry";
        symbol[5].rewardMult = 2;
        
        Stats addStats = new Stats();
        
        setLayout(new GridLayout (rows, columns, 5, 5));
        
        JLabel creditMessage = new JLabel ("Credits: ", SwingConstants.RIGHT);
        add(creditMessage);
        JLabel creditArea = new JLabel ("10");
        add(creditArea);
        JButton stats = new JButton ("Statistics");
        add(stats);
        JLabel betMessage = new JLabel ("Bet: ", SwingConstants.RIGHT);
        add(betMessage);
        JLabel betArea = new JLabel ("0");
        add(betArea);
        
        add(new JLabel(""));
        JButton LRM = new JButton (symbol[0].image);
        LRM.setIcon(new javax.swing.ImageIcon("Images\\" + symbol[0].image + ".png"));
        add(LRM);
        JButton MRM = new JButton (symbol[0].image);//.setIcon(new javax.swing.ImageIcon("H:\\0.png"));
        MRM.setIcon(new javax.swing.ImageIcon("Images\\" + symbol[0].image + ".png"));
        add(MRM);
        JButton RRM = new JButton (symbol[0].image);
        RRM.setIcon(new javax.swing.ImageIcon("Images\\" + symbol[0].image + ".png"));
        add(RRM);
        add(new JLabel(""));
        
        JButton reset = new JButton ("Reset");
        add(reset);
        JButton betOne = new JButton ("Bet One");
        add(betOne);
        JButton betMax = new JButton ("Bet Max");
        add(betMax);
        JButton addCoin = new JButton ("Add coin");
        add(addCoin);
        JButton spin = new JButton ("Spin");
        add(spin);
        
        javax.swing.Timer tmRL = new javax.swing.Timer(100, new ActionListener() {
            Random rand = new Random();
            String text;

            int nL = rand.nextInt(random);
            //int nL = 0;

            public void actionPerformed(ActionEvent evt) {
                    nL = rand.nextInt(random);
                    //int nL = 0;
                    text = symbol[nL].image;
                    LRM.setText(text);
                    LRM.setIcon(new javax.swing.ImageIcon("Images\\" + text + ".png"));
            }

        });
        javax.swing.Timer tmRM = new javax.swing.Timer(100, new ActionListener() {

            Random rand = new Random();
            String text;

            int nM = rand.nextInt(random);
            //int nM = 0;

            public void actionPerformed(ActionEvent evt) {
                    nM = rand.nextInt(random);
                    //int nL = 0;
                    text = symbol[nM].image;
                    MRM.setText(text);
                    MRM.setIcon(new javax.swing.ImageIcon("Images\\" + text + ".png"));
            }
        });
        javax.swing.Timer tmRR = new javax.swing.Timer(100, new ActionListener() {

            Random rand = new Random();
            String text;

            int nR = rand.nextInt(random);
            //int nR = 0;

            public void actionPerformed(ActionEvent evt) {
                    nR = rand.nextInt(random);
                    //int nL = 0;
                    text = symbol[nR].image;
                    RRM.setText(text);
                    RRM.setIcon(new javax.swing.ImageIcon("Images\\" + text + ".png"));
            }
        });
        
        stats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Object[] options1 = { "Save and close", "Close"};
                JPanel panel = new JPanel();
                String message = "Games played: " + addStats.games + "\nWins: " + addStats.wins + "\nLosses: " + addStats.losses + 
                        "\nGames played: " + addStats.games + "\nTotal earnings: " + addStats.earnings + "\nAverage earnings: " + addStats.average;
                panel.add(new JTextArea(message));
                
                addStats.average = addStats.earnings / addStats.games;
                int result = JOptionPane.showOptionDialog(null, panel, "Statistics", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options1, null);
                
                if (result == JOptionPane.YES_OPTION){
                    String outputFileName = "Slot.txt";
                    try (PrintWriter outToFile = new PrintWriter(outputFileName)) {
                        outToFile.println(message);
                        System.out.println("File saved.");
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(SlotMachine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int credits = Integer.parseInt(creditArea.getText());
                int bet = Integer.parseInt(betArea.getText());
                credits = credits + bet;
                creditArea.setText("" + credits);
                betArea.setText("0");
            }
        });
        
        betOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int credits = Integer.parseInt(creditArea.getText());
                int bet = Integer.parseInt(betArea.getText());
                
                if (bet < maxBet){
                    credits = credits - 1;
                    bet = bet + 1;
                    creditArea.setText("" + credits);
                    betArea.setText("" + bet);
                }
            }
        });
        
        betMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int credits = Integer.parseInt(creditArea.getText());
                int bet = Integer.parseInt(betArea.getText());
                
                if (bet < maxBet){
                    credits = credits - (maxBet - bet);
                    bet = bet + (maxBet - bet);
                    creditArea.setText("" + credits);
                    betArea.setText("" + bet);
                }
            }
        });
        
        addCoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int credits = Integer.parseInt(creditArea.getText());
                credits = credits + 1;
                creditArea.setText("" + credits);
            }
        });
        
        spin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int bet = Integer.parseInt(betArea.getText());
                
                if (bet > 0){
                    tmRL.start();
                    tmRM.start();
                    tmRR.start();
                }
            }
        });
        
        LRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmRL.stop();
                tmRM.stop();
                tmRR.stop();

                addStats.games = addStats.games + 1;

                int credits = Integer.parseInt(creditArea.getText());
                int bet = Integer.parseInt(betArea.getText());

                String imageLeft = LRM.getText();
                String imageMiddle = MRM.getText();
                String imageRight = RRM.getText();

                if (imageLeft == imageMiddle || imageLeft == imageRight || imageMiddle == imageRight){
                    if (imageLeft == symbol[0].image && imageMiddle == symbol[0].image){
                        bet = bet * symbol[0].rewardMult;
                    } else if (imageLeft == symbol[0].image && imageRight == symbol[0].image){
                        bet = bet * symbol[0].rewardMult;
                    } else if (imageMiddle == symbol[0].image && imageRight == symbol[0].image){
                        bet = bet * symbol[0].rewardMult;
                    } else if (imageLeft == symbol[1].image && imageMiddle == symbol[1].image){
                        bet = bet * symbol[1].rewardMult;
                    } else if (imageLeft == symbol[1].image && imageRight == symbol[1].image){
                        bet = bet * symbol[1].rewardMult;
                    } else if (imageMiddle == symbol[1].image && imageRight == symbol[1].image){
                        bet = bet * symbol[1].rewardMult;
                    } else if (imageLeft == symbol[2].image && imageMiddle == symbol[2].image){
                        bet = bet * symbol[2].rewardMult;
                    } else if (imageLeft == symbol[2].image && imageRight == symbol[2].image){
                        bet = bet * symbol[2].rewardMult;
                    } else if (imageMiddle == symbol[2].image && imageRight == symbol[2].image){
                        bet = bet * symbol[2].rewardMult;
                    } else if (imageLeft == symbol[3].image && imageMiddle == symbol[3].image){
                        bet = bet * symbol[3].rewardMult;
                    } else if (imageLeft == symbol[3].image && imageRight == symbol[3].image){
                        bet = bet * symbol[3].rewardMult;
                    } else if (imageMiddle == symbol[3].image && imageRight == symbol[3].image){
                        bet = bet * symbol[3].rewardMult;
                    } else if (imageLeft == symbol[4].image && imageMiddle == symbol[4].image){
                        bet = bet * symbol[4].rewardMult;
                    } else if (imageLeft == symbol[4].image && imageRight == symbol[4].image){
                        bet = bet * symbol[4].rewardMult;
                    } else if (imageMiddle == symbol[4].image && imageRight == symbol[4].image){
                        bet = bet * symbol[4].rewardMult;
                    } else if (imageLeft == symbol[5].image && imageMiddle == symbol[5].image){
                        bet = bet * symbol[5].rewardMult;
                    } else if (imageLeft == symbol[5].image && imageRight == symbol[5].image){
                        bet = bet * symbol[5].rewardMult;
                    } else if (imageMiddle == symbol[5].image && imageRight == symbol[5].image){
                        bet = bet * symbol[5].rewardMult;
                    }
                    credits = credits + bet;
                    creditArea.setText("" + credits);
                    JOptionPane.showMessageDialog(null, "You Win " + bet + " credtits");
                    addStats.wins = addStats.wins + 1;
                    addStats.earnings = addStats.earnings + bet;
                } else {
                    JOptionPane.showMessageDialog(null, "You Lose " + bet + " credtits");
                    addStats.losses = addStats.losses + 1;
                    addStats.earnings = addStats.earnings - bet;
                }
                betArea.setText("0");
            }
        });

        MRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmRL.stop();
                tmRM.stop();
                tmRR.stop();

                addStats.games = addStats.games + 1;

                int credits = Integer.parseInt(creditArea.getText());
                int bet = Integer.parseInt(betArea.getText());

                String imageLeft = LRM.getText();
                String imageMiddle = MRM.getText();
                String imageRight = RRM.getText();

                if (imageLeft == imageMiddle || imageLeft == imageRight || imageMiddle == imageRight){
                    if (imageLeft == symbol[0].image && imageMiddle == symbol[0].image){
                        bet = bet * symbol[0].rewardMult;
                    } else if (imageLeft == symbol[0].image && imageRight == symbol[0].image){
                        bet = bet * symbol[0].rewardMult;
                    } else if (imageMiddle == symbol[0].image && imageRight == symbol[0].image){
                        bet = bet * symbol[0].rewardMult;
                    } else if (imageLeft == symbol[1].image && imageMiddle == symbol[1].image){
                        bet = bet * symbol[1].rewardMult;
                    } else if (imageLeft == symbol[1].image && imageRight == symbol[1].image){
                        bet = bet * symbol[1].rewardMult;
                    } else if (imageMiddle == symbol[1].image && imageRight == symbol[1].image){
                        bet = bet * symbol[1].rewardMult;
                    } else if (imageLeft == symbol[2].image && imageMiddle == symbol[2].image){
                        bet = bet * symbol[2].rewardMult;
                    } else if (imageLeft == symbol[2].image && imageRight == symbol[2].image){
                        bet = bet * symbol[2].rewardMult;
                    } else if (imageMiddle == symbol[2].image && imageRight == symbol[2].image){
                        bet = bet * symbol[2].rewardMult;
                    } else if (imageLeft == symbol[3].image && imageMiddle == symbol[3].image){
                        bet = bet * symbol[3].rewardMult;
                    } else if (imageLeft == symbol[3].image && imageRight == symbol[3].image){
                        bet = bet * symbol[3].rewardMult;
                    } else if (imageMiddle == symbol[3].image && imageRight == symbol[3].image){
                        bet = bet * symbol[3].rewardMult;
                    } else if (imageLeft == symbol[4].image && imageMiddle == symbol[4].image){
                        bet = bet * symbol[4].rewardMult;
                    } else if (imageLeft == symbol[4].image && imageRight == symbol[4].image){
                        bet = bet * symbol[4].rewardMult;
                    } else if (imageMiddle == symbol[4].image && imageRight == symbol[4].image){
                        bet = bet * symbol[4].rewardMult;
                    } else if (imageLeft == symbol[5].image && imageMiddle == symbol[5].image){
                        bet = bet * symbol[5].rewardMult;
                    } else if (imageLeft == symbol[5].image && imageRight == symbol[5].image){
                        bet = bet * symbol[5].rewardMult;
                    } else if (imageMiddle == symbol[5].image && imageRight == symbol[5].image){
                        bet = bet * symbol[5].rewardMult;
                    }
                    credits = credits + bet;
                    creditArea.setText("" + credits);
                    JOptionPane.showMessageDialog(null, "You Win " + bet + " credtits");
                    addStats.wins = addStats.wins + 1;
                    addStats.earnings = addStats.earnings + bet;
                } else {
                    JOptionPane.showMessageDialog(null, "You Lose " + bet + " credtits");
                    addStats.losses = addStats.losses + 1;
                    addStats.earnings = addStats.earnings - bet;
                }
                betArea.setText("0");
            }
        });

        RRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tmRL.stop();
                tmRM.stop();
                tmRR.stop();

                addStats.games = addStats.games + 1;

                int credits = Integer.parseInt(creditArea.getText());
                int bet = Integer.parseInt(betArea.getText());

                String imageLeft = LRM.getText();
                String imageMiddle = MRM.getText();
                String imageRight = RRM.getText();

                if (imageLeft == imageMiddle || imageLeft == imageRight || imageMiddle == imageRight){
                    if (imageLeft == symbol[0].image && imageMiddle == symbol[0].image){
                        bet = bet * symbol[0].rewardMult;
                    } else if (imageLeft == symbol[0].image && imageRight == symbol[0].image){
                        bet = bet * symbol[0].rewardMult;
                    } else if (imageMiddle == symbol[0].image && imageRight == symbol[0].image){
                        bet = bet * symbol[0].rewardMult;
                    } else if (imageLeft == symbol[1].image && imageMiddle == symbol[1].image){
                        bet = bet * symbol[1].rewardMult;
                    } else if (imageLeft == symbol[1].image && imageRight == symbol[1].image){
                        bet = bet * symbol[1].rewardMult;
                    } else if (imageMiddle == symbol[1].image && imageRight == symbol[1].image){
                        bet = bet * symbol[1].rewardMult;
                    } else if (imageLeft == symbol[2].image && imageMiddle == symbol[2].image){
                        bet = bet * symbol[2].rewardMult;
                    } else if (imageLeft == symbol[2].image && imageRight == symbol[2].image){
                        bet = bet * symbol[2].rewardMult;
                    } else if (imageMiddle == symbol[2].image && imageRight == symbol[2].image){
                        bet = bet * symbol[2].rewardMult;
                    } else if (imageLeft == symbol[3].image && imageMiddle == symbol[3].image){
                        bet = bet * symbol[3].rewardMult;
                    } else if (imageLeft == symbol[3].image && imageRight == symbol[3].image){
                        bet = bet * symbol[3].rewardMult;
                    } else if (imageMiddle == symbol[3].image && imageRight == symbol[3].image){
                        bet = bet * symbol[3].rewardMult;
                    } else if (imageLeft == symbol[4].image && imageMiddle == symbol[4].image){
                        bet = bet * symbol[4].rewardMult;
                    } else if (imageLeft == symbol[4].image && imageRight == symbol[4].image){
                        bet = bet * symbol[4].rewardMult;
                    } else if (imageMiddle == symbol[4].image && imageRight == symbol[4].image){
                        bet = bet * symbol[4].rewardMult;
                    } else if (imageLeft == symbol[5].image && imageMiddle == symbol[5].image){
                        bet = bet * symbol[5].rewardMult;
                    } else if (imageLeft == symbol[5].image && imageRight == symbol[5].image){
                        bet = bet * symbol[5].rewardMult;
                    } else if (imageMiddle == symbol[5].image && imageRight == symbol[5].image){
                        bet = bet * symbol[5].rewardMult;
                    }
                    credits = credits + bet;
                    creditArea.setText("" + credits);
                    JOptionPane.showMessageDialog(null, "You Win " + bet + " credtits");
                    addStats.wins = addStats.wins + 1;
                    addStats.earnings = addStats.earnings + bet;
                } else {
                    JOptionPane.showMessageDialog(null, "You Lose " + bet + " credtits");
                    addStats.losses = addStats.losses + 1;
                    addStats.earnings = addStats.earnings - bet;
                }
                betArea.setText("0");
            }
        });
        
    }
    
    public static void main(String[] args) {
        SlotMachine frame = new SlotMachine();
        frame.setTitle("Slot Machine");
        frame.setSize (600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}