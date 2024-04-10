package org.example.view.dialog;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDialog extends JDialog {


    public DeleteDialog() {

        JPanel contentPanel;
        contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        JLabel textes;
        JButton jButton = new JButton("OK");
        JTextField idfield = new JTextField(10);

        textes = new JLabel("ID :");
        contentPanel.add(textes);
        contentPanel.add(idfield);

        jButton.setSize(100,100);
        contentPanel.add(jButton);


        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ContactDao contactDao = new ContactDao();
                int count;
                try {
                    int id = Integer.parseInt(idfield.getText());
                    Contact contact = contactDao.getOneContact(id);
                    if(contact!=null){
                        count = contactDao.deleteContact(contact);
                    } else{
                        count=0;
                    }
                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, "Contact Removed Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Contact doesn't exist, you son of a Bitch !!!!");
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }



            }
        });


        setContentPane(contentPanel);
        setVisible(true);
        pack();

    }


}
