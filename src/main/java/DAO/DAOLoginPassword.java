package DAO;

import Entity.UserPassword;
import Hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.util.List;

public class DAOLoginPassword {

    private Session session;

    public void addUser(UserPassword userPassword){
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(userPassword);
        transaction.commit();
        session.close();
    }

    public boolean hasUser(String login, String password){
        boolean flag = false;
        Session session = HibernateSessionFactoryUtil.getSession();
        UserPassword user = new UserPassword(login, password);
        UserPassword userTmp;
        try {
            userTmp = session.createQuery("From UserPassword Where login='" + login + "'", UserPassword.class).getSingleResult();
        } catch (NoResultException ex){
            return false;
        }
        if (userTmp != null) {
            if (userTmp.getPassword().equals(password))
                flag = true;
        }
        session.close();
        return flag;
    }
    public List toList() {
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction transaction = session.beginTransaction();
        List lps = session.createQuery("From UserPassword").getResultList();
        transaction.commit();
        session.close();
        return lps;
    }
}
