/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication19;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.CardLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Saurabh
 */
public class User extends javax.swing.JPanel {

    /**
     * Creates new form admin
     * 
     *
     */
    
        JPanel base_panel;
        issue_desc desc;
        Main_list_panel lpn ;
        Socket soc;
        String uname;
        
          public User() {  
        
        initComponents();
         }
            
    public User(JPanel base1,issue_desc base2,Main_list_panel lp) {
        base_panel = base1;
        desc = base2;
         lpn = lp;
        initComponents();
        
        view.setVisible(false);
      
        deleteb.setVisible(false);
        edit.setVisible(false);
        
    }
    void setsocket(Socket soc) throws IOException
    {
    this.soc = soc;
    }
    
    void setuname(String uname)
     {
     this.uname = uname;
     }
    
 public int getiid() throws SQLException
    {
        jdbc j=new jdbc();
        String s ="Select * from issue";
        ResultSet r = j.getaccess(s);    
        Random rand=new Random();
        int i,flag=0;
        while(true){
        flag=0;
        i=rand.nextInt(1000)+rand.nextInt(1000);
  
        while(r.next())
        {
            if(i==r.getInt(5))
            {
           // System.out.println("again");
            flag=1;
            break;
            }
        }
       // System.out.println("im out");
        if(flag==0)
        break;
        }
        return(i);
    }

    
     
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        view = new javax.swing.JButton();
        deleteb = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        create = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(221, 562));
        setLayout(null);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication19/15003918289975.png"))); // NOI18N
        add(jLabel4);
        jLabel4.setBounds(40, 90, 150, 60);

        view.setText("View Issue");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        add(view);
        view.setBounds(30, 330, 140, 30);

        deleteb.setText("Delete Issue");
        deleteb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebActionPerformed(evt);
            }
        });
        add(deleteb);
        deleteb.setBounds(30, 380, 140, 30);

        edit.setText("Edit Issue");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        add(edit);
        edit.setBounds(30, 430, 140, 30);

        create.setText("Create Issue");
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });
        add(create);
        create.setBounds(30, 280, 140, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/javaapplication19/panel.jpg"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 220, 560);
    }// </editor-fold>//GEN-END:initComponents

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        // TODO add your handling code here:
      
        
       CardLayout cardLayout = (CardLayout) base_panel.getLayout();
       cardLayout.show(base_panel,"card3");
      
        issue i= lpn.selected;
        Gson g = new Gson();
            String ar;
            try {
                 DataInputStream din=new DataInputStream(soc.getInputStream());
            DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
                dos.writeUTF("4");
                dos.flush();
                
                 ar = din.readUTF();
            System.out.println(ar);
            
            
             
            List<vote> vo;
           vo = g.fromJson(ar , new TypeToken<List<vote>>(){}.getType());
           
           vote v = new vote();
           
           v.IID = i.IID;
           v.UID = uname;
           
                //System.out.println("iid-"+v.IID+" uid-"+v.UID+" "+vo.contains(v));
          
               // System.out.println(vo.get(0).UID.equals(uname)+" "+vo.get(0).IID.equals(v.IID)); 
               boolean flag = true;
               for(int k=0;k<vo.size();k++)
            {
                if(vo.get(k).UID.equals(uname) && vo.get(k).IID.equals(v.IID))
                {
                flag=true;
                break;
                }
                else
                {
                flag=false;
                }
            }

               
           if(flag)
           {
           desc.up.setVisible(false);
           desc.down.setVisible(false);
          }
           else
           {
            desc.up.setVisible(true);
       desc.down.setVisible(true);
           }
           
        
        String t = i.IID;
        Integer r = i.rating;
       desc.iid.setText(t.toString());
       desc.subjectcombo.setVisible(false);
       desc.subject.setText(i.Subject);
       desc.title.setEditable(false);
       desc.title.setText(i.Title);
       desc.description.setEditable(false);
       desc.description.setText(i.Description);
       desc.rating.setText(r.toString());
       desc.date.setText(i.Date.toString());
       
       base_panel.setSize(842, 390);
       desc.comment_b.setBackground(null);
       desc.comment_b.setVisible(false);
       desc.comment_button.setVisible(false);
       desc.comment_l.setVisible(false);
      desc.make.setVisible(false);
       desc.status.setText(i.Status);
       desc.submit.setVisible(false);
       
       } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }//GEN-LAST:event_viewActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        // TODO add your handling code here:
              CardLayout cardLayout = (CardLayout) base_panel.getLayout();
       cardLayout.show(base_panel,"card3");
      
        
        
            try {
                Integer t;
                t = getiid();
                desc.iid.setText(t.toString());
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       
       
       
       desc.subject.setVisible(false);
     
       desc.subjectcombo.setVisible(true);
       desc.title.setEditable(true);
       desc.description.setEditable(true);
     
       desc.title.setText("");
       desc.description.setText("");
       desc.rating.setText("0");
       desc.up.setVisible(false);
       desc.down.setVisible(false);
       
       DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      Date dateobj = new Date();
            desc.date.setText(df.format(dateobj));

       desc.status.setText("Awaiting approval");
       base_panel.setSize(842, 390);
       desc.comment_b.setBackground(null);
       desc.comment_b.setVisible(false);
       desc.comment_button.setVisible(false);
       desc.comment_l.setVisible(false);
       desc.submit.setVisible(true);
       desc.make.setVisible(false);
       
       
    }//GEN-LAST:event_createActionPerformed

    private void deletebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebActionPerformed

       
        
        try {
                // TODO add your handling code here:
                //System.out.println(soc.getPort());
                String i = lpn.selected.IID;
                
                DataInputStream din=new DataInputStream(soc.getInputStream());
                DataOutputStream dos = new DataOutputStream(soc.getOutputStream());
                
                Gson g = new Gson();
                String ar;
                //System.out.println(lpn.issuelist.get(1).IID);
                dos.writeUTF("2");
                dos.writeUTF(i.toString());
                
                
               
                
                
//                try {
//            // TODO add your handling code here:
//            lpn.refresh_list();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(MainDisplayFrame.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(MainDisplayFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
                
                
            } catch (IOException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
            
               try {
                lpn.refresh_list();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
               
         
    }//GEN-LAST:event_deletebActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        // TODO add your handling code here:
               
       
        
        CardLayout cardLayout = (CardLayout) base_panel.getLayout();
       cardLayout.show(base_panel,"card3");
      
        issue i= lpn.selected;
        
       
            
            
           
           desc.up.setVisible(false);
       desc.down.setVisible(false);
           
        String t = i.IID;
        Integer r = i.rating;
       desc.iid.setText(t.toString());
       
       desc.subject.setVisible(false);
     
       desc.subjectcombo.setVisible(true);
       desc.title.setEditable(true);
       desc.description.setEditable(true);
     
       desc.title.setText(i.Title);
       desc.subjectcombo.setSelectedItem(i.Subject);
       desc.description.setText(i.Description);
       desc.rating.setText(r.toString());
       desc.date.setText(i.Date.toString());
       desc.make.setVisible(false);
       desc.status.setText(i.Status);
       base_panel.setSize(842, 390);
       desc.comment_b.setBackground(null);
       desc.comment_b.setVisible(false);
       desc.comment_button.setVisible(false);
       desc.comment_l.setVisible(false);
       desc.submit.setVisible(false);
       desc.make.setVisible(true);

       
     
            
           
    }//GEN-LAST:event_editActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton create;
    public javax.swing.JButton deleteb;
    public javax.swing.JButton edit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
}
