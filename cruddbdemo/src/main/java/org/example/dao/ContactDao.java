package org.example.dao;

import org.example.connexion.ConnectionUtil;
import org.example.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {


    Connection con;

    private PreparedStatement ps;

    public Contact getOneContact(int id) {
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("SELECT * FROM `contact` WHERE `id`= ? ");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                Contact contact = new Contact(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("number"));
                return contact;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Contact> getContact() {
        con = ConnectionUtil.getConnection();
        List<Contact> contactList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `contact`");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setNumber(resultSet.getString("number"));
                contactList.add(contact);
            }

            return contactList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int addContact(Contact contact) {
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("INSERT INTO `contact`(`name`,`number`)values(?,?)");
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getNumber());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateContact(Contact contact) {
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("UPDATE `contact` SET `name`= ?, `number` = ? WHERE id = ?");
            ps.setString(1, contact.getName());
            ps.setString(2, contact.getNumber());
            ps.setInt(3, contact.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int deleteContact(Contact contact) {
        con = ConnectionUtil.getConnection();
        try {
            ps = con.prepareStatement("DELETE FROM `contact` WHERE id = ?");
            ps.setInt(1, contact.getId());
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
