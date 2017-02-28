package ua.mykytenko.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ua.mykytenko.dao.ServiceStationDAO;
import ua.mykytenko.model.ServiceStation;
import ua.mykytenko.util.HibernateUtil;

import javax.persistence.EntityNotFoundException;

public class ServiceStationDAOImpl implements ServiceStationDAO {

    public ServiceStation addServiceStation(ServiceStation serviceStation) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(serviceStation);
            session.getTransaction().commit();
        } catch (HibernateException e) {
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
        return serviceStation;
    }

    public ServiceStation updateServiceStation(ServiceStation serviceStation) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(serviceStation);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(session != null && session.isOpen()){
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
        return serviceStation;
    }

    public ServiceStation getServiceStationById(int id) {
        Session session = null;
        ServiceStation ss = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            ss = session.get(ServiceStation.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(session != null && session.isOpen()){
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
        return ss;
    }

    public boolean deleteServiceStationById(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            ServiceStation serviceStation = session.getReference(ServiceStation.class, id);
            session.delete(serviceStation);
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
