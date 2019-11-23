package DAO;

import Entity.UserHash;
import Hibernate.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.NoResultException;
import java.util.List;

import javax.persistence.NoResultException;

public class DAOLoginHash {

    private Session session;


    public void addHash(UserHash userHash){
        Session session = HibernateSessionFactoryUtil.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(userHash);
        transaction.commit();
        session.close();
    }

    public boolean hasHash (String login, String hash){
        boolean flag = false;
        Session session = HibernateSessionFactoryUtil.getSession();
        UserHash user = new UserHash(login, hash);
        UserHash userTmp;
        try {
            userTmp = session.createQuery("From UserHash Where login='" + login + "'", UserHash.class).getSingleResult();
        } catch (NoResultException ex) {
            return false;
        }
        if (userTmp != null){
            if (userTmp.getHash().equals(hash)){
                flag = true;
            }
        }
        session.close();
        return flag;
    }

    public boolean hashContainsInTable(String hash) {
        boolean contains = false;
        Session session = HibernateSessionFactoryUtil.getSession();
        List<UserHash> lhs = (List<UserHash>) session.createQuery("From UserHash").list();
        for (UserHash lh : lhs){
            if (lh.getHash().equals(hash)){
                contains = true;
            }
        }
        return contains;
    }
}
