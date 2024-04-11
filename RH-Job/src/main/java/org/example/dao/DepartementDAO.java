package org.example.dao;

import org.example.model.Departement;
import org.example.utils.ConnexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartementDAO {

    Connection con;

    private PreparedStatement ps;

    public Departement getOneDepartement(int id) {
        con = ConnexionDB.getConnection();
        try {
            ps = con.prepareStatement("SELECT * FROM `departement` WHERE `id` = ? ");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                Departement departement = new Departement(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getInt("salaries_number"));
                return departement;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Departement> getDepartement() {
        con = ConnexionDB.getConnection();
        List<Departement> departementList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `departement`");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Departement departement = new Departement();
                departement.setId(resultSet.getInt("id"));
                departement.setName(resultSet.getString("name"));
                departement.setSalaries_number(resultSet.getInt("salaries_number"));
                departementList.add(departement);
            }

            return departementList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int addDepartement(Departement departement) {
        con = ConnexionDB.getConnection();
        try {
            ps = con.prepareStatement("INSERT INTO `departement`(`id`,`name`,`salaries_number`) values (?,?,?)");
            ps.setInt(1, departement.getId());
            ps.setString(2, departement.getName());
            ps.setInt(3, departement.getSalaries_number());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateDepartement(Departement departement) {
        con = ConnexionDB.getConnection();
        try {
            ps = con.prepareStatement("UPDATE `departement` SET `name`= ?, `salaries_number`= ? WHERE id = ?");
            ps.setString(1, departement.getName());
            ps.setInt(2, departement.getSalaries_number());
            ps.setInt(3, departement.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int deleteDepartement(Departement departement) {
        con = ConnexionDB.getConnection();
        try {
            ps = con.prepareStatement("DELETE FROM `departement` WHERE id = ?");
            ps.setInt(1, departement.getId());
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
