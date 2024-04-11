package org.example.dao;

import org.example.model.Role;
import org.example.model.Salarie;
import org.example.utils.ConnexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalarieDAO {
    Connection con;

    private PreparedStatement ps;

    public Salarie getOneSalarie(int id) {
        con = ConnexionDB.getConnection();
        try {
            ps = con.prepareStatement("SELECT * FROM `salarie` WHERE `id`= ? ");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                Salarie salarie = new Salarie(resultSet.getInt("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        (Role) resultSet.getObject("role"));
                return salarie;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Salarie> getSalarie() {
        con = ConnexionDB.getConnection();
        List<Salarie> salarieList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM `salarie`");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Salarie salarie = new Salarie();
                salarie.setId(resultSet.getInt("id"));
                salarie.setFirstname(resultSet.getString("firstname"));
                salarie.setLastname(resultSet.getString("lastname"));
                salarie.setRole((Role) resultSet.getObject("role"));
                salarieList.add(salarie);
            }

            return salarieList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public int addSalarie(Salarie salarie) {
        con = ConnexionDB.getConnection();
        try {
            ps = con.prepareStatement("INSERT INTO `salarie`(`firstname`,`lastname`,`role`,`departement`)values(?,?,?)");
            ps.setString(1, salarie.getFirstname());
            ps.setString(2, salarie.getLastname());
            ps.setObject(3, salarie.getRole());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateSalarie(Salarie salarie) {
        con = ConnexionDB.getConnection();
        try {
            ps = con.prepareStatement("UPDATE `salarie` SET `firstname`= ?,`lastname`= ?, `role`= ? WHERE id = ?");
            ps.setString(1, salarie.getFirstname());
            ps.setString(2, salarie.getLastname());
            ps.setObject(3, salarie.getRole());
            ps.setInt(4, salarie.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteSalarie(Salarie salarie) {
        con = ConnexionDB.getConnection();
        try {
            ps = con.prepareStatement("DELETE FROM `salarie` WHERE id = ?");
            ps.setInt(1, salarie.getId());
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
