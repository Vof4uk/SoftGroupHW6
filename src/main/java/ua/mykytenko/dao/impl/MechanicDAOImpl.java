package ua.mykytenko.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ua.mykytenko.dao.MechanicDAO;
import ua.mykytenko.model.Mechanic;
import ua.mykytenko.util.HibernateUtil;

import javax.persistence.EntityNotFoundException;

public class MechanicDAOImpl implements MechanicDAO{

    public Mechanic addMechanic(Mechanic mechanic) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(mechanic);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
        return mechanic;
    }

    public Mechanic updateMechanic(Mechanic mechanic) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(mechanic);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
        return mechanic;
    }

    public Mechanic getMechanicById(int id) {
        Session session = null;
        Mechanic mechanic = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            mechanic = session.get(Mechanic.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
        return mechanic;
    }

    public boolean deleteMechanicById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Mechanic mechanic = session.getReference(Mechanic.class, id);
            session.delete(mechanic);
            session.getTransaction().commit();
        }catch (EntityNotFoundException ex){
            ex.printStackTrace();
            return false;
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (session != null && session.isOpen()) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

}
