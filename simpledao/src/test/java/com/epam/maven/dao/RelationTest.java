package com.epam.maven.dao;

import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.epam.maven.domain.Group;
import com.epam.maven.domain.Student;
import com.epam.maven.mysql.MySqlDaoFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class RelationTest {
    private static final DaoFactory<Connection> factory = new MySqlDaoFactory();

    private Connection connection;

    @Before
    public void setUp() throws PersistException, SQLException {
        connection = factory.getContext();
        connection.setAutoCommit(false);
    }

    @After
    public void tearDown() throws SQLException {
        connection.rollback();
        connection.close();
    }

    @Test
    public void testCreate() throws PersistException {
        Student student = (Student) factory.getDao(connection, Student.class).create();
        Assert.assertNull("Group is not null.", student.getGroup());

        Group group = new Group();
        student.setGroup(group);
        Assert.assertNotNull("Group is null.", student.getGroup());
    }

    @Test
    public void testPersist() throws PersistException {
        Student student = new Student();
        Group group = (Group) factory.getDao(connection, Group.class).create();
        student.setGroup(group);
        group.setNumber(1234);
        student = (Student) factory.getDao(connection, Student.class).persist(student);
        Assert.assertNotNull("Group is null.", student.getGroup());
        Assert.assertEquals("Wrong group number.", 1234, student.getGroup().getNumber());
    }

    @Test
    public void testPersistAll() throws PersistException {
        Student student = new Student();
        student.setGroup(new Group());
        student = (Student) factory.getDao(connection, Student.class).persist(student);
        Assert.assertNotNull("Group is null.", student.getGroup());
        Assert.assertNotNull("Group.id is null.", student.getGroup().getId());
    }

    @Test
    public void testUpdate() throws PersistException {
        Student student = (Student) factory.getDao(connection, Student.class).create();
        student.setGroup(new Group());
        factory.getDao(connection, Student.class).update(student);
        Assert.assertNotNull("Group is null.", student.getGroup());
        Assert.assertNotNull("Group.id is null.", student.getGroup().getId());
    }

    @Test
    public void testUpdateAll() throws PersistException {
        Student student = (Student) factory.getDao(connection, Student.class).create();
        Group group = (Group) factory.getDao(connection, Group.class).create();
        group.setNumber(1234);
        student.setGroup(group);
        factory.getDao(connection, Student.class).update(student);
        Assert.assertNotNull("Group is null.", student.getGroup());
        Assert.assertEquals("Wrong group number.", 1234, student.getGroup().getNumber());
    }

    @Test
    public void testRead() throws PersistException {
        Student student = (Student) factory.getDao(connection, Student.class).create();
        student.setGroup(new Group());
        factory.getDao(connection, Student.class).update(student);

        student = (Student) factory.getDao(connection, Student.class).getByPK(student.getId());
        Assert.assertNotNull("Student is null.", student);
        Assert.assertNotNull("Group is null.", student.getGroup());
    }

    @Test
    public void testDelete() throws PersistException {
        Student student = (Student) factory.getDao(connection, Student.class).create();
        student.setGroup(new Group());
        factory.getDao(connection, Student.class).update(student);

        Group group = student.getGroup();

        factory.getDao(connection, Student.class).delete(student);
        group = (Group) factory.getDao(connection, Group.class).getByPK(group.getId());
        Assert.assertNotNull("Group not found.", group);
    }
}
