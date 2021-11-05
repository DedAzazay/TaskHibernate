package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS `mydbtest`.`users` " +
                    "(`id` BIGINT NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(45) NOT NULL," +
                    " `lastName` VARCHAR(45) NOT NULL," +
                    " `age` TINYINT NOT NULL," +
                    " PRIMARY KEY (`id`))";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();


        }
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception ex) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();

        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "DELETE FROM User WHERE id = :Id";
            session.createQuery(hql).setParameter("Id", id).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "FROM User";
            list = session.createQuery(hql).list();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if(session.getTransaction() != null) {
                ex.printStackTrace();
                session.getTransaction().rollback();
            }
        } finally {
            session.close();

        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            String hql = "DELETE FROM User";
            session.createQuery(hql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
        } finally {
            session.close();

        }
    }
}
