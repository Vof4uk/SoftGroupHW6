package ua.mykytenko.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ua.mykytenko.dao.CarDAO;
import ua.mykytenko.model.Car;
import ua.mykytenko.util.HibernateUtil;

import javax.persistence.EntityNotFoundException;

public class CarDAOImpl implements CarDAO{
    public Car addCar(Car car) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(car);
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
        return car;
    }

    public Car updateCar(Car car) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(car);
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
        return car;
    }

    public Car getCarById(int id) {
        Session session = null;
        Car car = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            car = session.get(Car.class, id);
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            if(session != null){
                try {
                    session.close();
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
        return car;
    }

    public boolean deleteCarById(int id) {
        Session session = null;
        int count = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Car car = session.getReference(Car.class, id);
            session.delete(car);
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
