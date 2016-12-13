package com.epam.maven;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main
{

    private static int id = 0;

    static class Person {
        private String name;
        private String desc;

        public Person (String name, String desc) {
            this.name = name;
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

    public static int getNextId() {
        return ++id;
    }

    public static void init() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:test");
    }

    public static void main( String[] args ) throws Exception
    {
        init();
        createDatabase();

        selectQuery();
        insertQuery("Tony", "Iron Man");

        List<Person> heroList = new ArrayList<>();
        heroList.add(new Person("Steve", "Captain America"));
        heroList.add(new Person("Emma", "Emma Frost"));
        heroList.add(new Person("Natasha", "Black Widow"));
        heroList.add(new Person("Bruce", "Hulk"));
        heroList.add(new Person("Peter", "Spider-Man"));

        batchInsertQuery(heroList);

        selectQuery();

        updateQuery(5, "Bruce Banner", "Hulk");

        selectQueryById(5);

        deleteQuery(3);

        selectQuery();

    }

    public static void createDatabase() throws SQLException {

        String query = "create table TEST (id int primary key, name varchar2(100), desc varchar2(2000))";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("createDatabase exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int insertQuery(String name, String desc) throws SQLException{

        String query = "insert into TEST values (?,?,?)";
        int generatedId;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            generatedId = getNextId();

            preparedStatement.setInt(1, generatedId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, desc);
            preparedStatement.executeUpdate();

            System.out.println("generated id for "+ name +": " + generatedId);

            return generatedId;

        } catch (SQLException e) {
            System.out.println("createDatabase exception: " + e.getMessage());
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void updateQuery (int id, String name, String desc) throws SQLException {

        String query = "UPDATE TEST set name = ?, desc = ? where id = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)){

            preparedStatement.setInt(3, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, desc);
            preparedStatement.executeUpdate();

            System.out.println(id + " id updated!");

        } catch (SQLException e) {
            System.out.println("update query: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteQuery(int id) throws SQLException {
        String query = "delete from TEST where id = ?";
        int result;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)){

            preparedStatement.setInt(1, id);
            result = preparedStatement.executeUpdate();

            if (result!=0)
                System.out.println("id " + id + " deleted");

        } catch (SQLException e) {
            System.out.println("delete query: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectQuery() throws SQLException {

        String query = "select * from TEST";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()){

            while (rs.next()) {
                System.out.println("Id: " + rs.getInt("id") + " Name: " + rs.getString("name") + " desc: " + rs.getString("desc"));
            }
            System.out.println("----------------");

        } catch (SQLException e) {
            System.out.println("select query: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void selectQueryById(int id) throws SQLException {
        String query = "select * from TEST where id = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    System.out.println("Id: " + rs.getInt("id") + " Name: " + rs.getString("name") + " desc: " + rs.getString("desc"));
                }
                System.out.println("----------------");
            }

        } catch (SQLException e) {
            System.out.println("select query by id: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void batchInsertQuery(List<Person> rows) throws SQLException {

        String query = "insert into TEST values (?,?,?)";
        int [] batch;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {

            for (Person person : rows) {
                preparedStatement.setInt(1, getNextId());
                preparedStatement.setString(2, person.getName());
                preparedStatement.setString(3, person.getDesc());
                preparedStatement.addBatch();

            }

            batch = preparedStatement.executeBatch();

            System.out.println("batch = " + batch.length);

        } catch (SQLException e) {
            System.out.println("select query by id: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
