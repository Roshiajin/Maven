package com.epam.maven;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main
{

    private String url;
    private String dbUsername = "";
    private String dbPassword = "";

    static class Person {
        private String name;
        private String info;


        public Person (String name, String info) {
            this.name = name;
            this.info = info;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public void initH2() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        this.url = "jdbc:h2:mem:test";
    }

    public void initOracle() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.OracleDriver");
        this.url = "jdbc:oracle:thin:@localhost:1521:orcl";
        this.dbUsername = "jdbcuser";
        this.dbPassword = "jdbcuser";
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.url, dbUsername, dbPassword);
    }

    public static void main( String[] args ) throws Exception
    {
        Main mainJdbc = new Main();

//        mainJdbc.initH2();
//        mainJdbc.createTableH2();
//        mainJdbc.updateQuery(5, "Bruce Banner", "Hulk");
//
//        mainJdbc.selectQueryById(5);
//
//        mainJdbc.deleteQuery(3);

        mainJdbc.initOracle();

        mainJdbc.selectQuery();
        mainJdbc.insertQuery("Tony", "Iron Man");

        List<Person> heroList = new ArrayList<>();
        heroList.add(new Person("Steve", "Captain America"));
        heroList.add(new Person("Emma", "Emma Frost"));
        heroList.add(new Person("Natasha", "Black Widow"));
        heroList.add(new Person("Bruce", "Hulk"));
        heroList.add(new Person("Peter", "Spider-Man"));

        mainJdbc.batchInsertQuery(heroList);

        mainJdbc.selectQuery();

        mainJdbc.updateQueryByName("Peter", "Peter Parker", "Spider-Man");
        mainJdbc.callInsertProcedure("Jessica","Jessica Jones");

        mainJdbc.selectQuery();

        mainJdbc.deleteAllQuery();

    }

    public void createTableH2() throws SQLException {

        String query = "create table TEST (id int auto_increment primary key, name varchar2(100), info varchar2(2000))";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("createDatabase exception: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public long insertQuery(String name, String info) throws SQLException{

        String query = "insert into TEST (name, info) values (?,?)";
        long generatedId = 0L;
        String generatedColumns[] = {"ID"};

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query, generatedColumns)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, info);
            preparedStatement.executeUpdate();

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                while (rs.next()) {
                    generatedId = rs.getLong(1);

                    System.out.println("generated id for "+ name +": " + generatedId);
                }
            }

            return generatedId;

        } catch (SQLException e) {
            System.out.println("insertQuery exception: " + e.getMessage());
            return generatedId;
        } catch (Exception e) {
            e.printStackTrace();
            return generatedId;
        }
    }

    public void updateQuery (int id, String name, String info) throws SQLException {

        String query = "UPDATE TEST set name = ?, info = ? where id = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)){

            preparedStatement.setInt(3, id);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, info);
            preparedStatement.executeUpdate();

            System.out.println(id + " id updated!");

        } catch (SQLException e) {
            System.out.println("update query: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateQueryByName (String oldName, String newName, String info) throws SQLException {

        String query = "UPDATE TEST set name = ?, info = ? where name = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)){

            preparedStatement.setString(3, oldName);
            preparedStatement.setString(1, newName);
            preparedStatement.setString(2, info);
            preparedStatement.executeUpdate();

            System.out.println(oldName + " updated!");

        } catch (SQLException e) {
            System.out.println("update query: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteQuery(int id) throws SQLException {
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

    public void deleteAllQuery() throws SQLException {
        String query = "delete from TEST";
        int result;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)){

            result = preparedStatement.executeUpdate();

            System.out.println(result + " rows deleted");

        } catch (SQLException e) {
            System.out.println("delete query: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectQuery() throws SQLException {

        String query = "select * from TEST";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()){

            while (rs.next()) {
                System.out.println("{Id: " + rs.getInt("id") + "}, {Name: " + rs.getString("name") + "}, {info: " + rs.getString("info")+"}");
            }
            System.out.println("----------------");

        } catch (SQLException e) {
            System.out.println("select query: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void selectQueryById(int id) throws SQLException {
        String query = "select * from TEST where id = ?";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                while (rs.next()) {
                    System.out.println("{Id: " + rs.getInt("id") + "}, {Name: " + rs.getString("name") + "}, {info: " + rs.getString("info")+"}");
                }
                System.out.println("----------------");
            }

        } catch (SQLException e) {
            System.out.println("select query by id: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void batchInsertQuery(List<Person> rows) throws SQLException {

        String query = "insert into TEST (name, info) values (?,?)";
        int [] batch;

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {

            for (Person person : rows) {
                preparedStatement.setString(1, person.getName());
                preparedStatement.setString(2, person.getInfo());
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

    public void callInsertProcedure(String name, String info) throws SQLException{

        String callMethod = "{call testpkg.insert_test(:p_name, :p_info, :p_id)}";

        CallableStatement callableStatement = getConnection().prepareCall(callMethod);

        callableStatement.setString("p_name", name);
        callableStatement.setString("p_info", info);
        callableStatement.registerOutParameter("p_id", Types.INTEGER);
        callableStatement.execute();

        System.out.println("generated id for "+ name +": " +callableStatement.getLong("p_id"));
    }

}
