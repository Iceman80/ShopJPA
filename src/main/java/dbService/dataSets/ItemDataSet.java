package dbService.dataSets;

import dbService.data.Items;
import dbService.EM;
import dbService.dao.ItemDAO;
import org.hibernate.*;
import java.util.List;


public class ItemDataSet {
    ItemDAO itemDAO;

    public long addItem(String quantity, String description, int unit, double price, String measurement) throws DBException {
        try {
            if (itemContains(quantity)) {
                System.out.println("Товар " + quantity + " уже есть");
                return 0;
            } else {
                Session session = (Session) EM.em().getDelegate();
                Transaction transaction = session.beginTransaction();
                itemDAO = new ItemDAO(session);
                long id = (Long) itemDAO.insertItems(quantity, description, unit, price, measurement);
                transaction.commit();
                return id;
            }
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public boolean itemContains(String quantity) throws DBException {
        try {
            Session session = (Session) EM.em().getDelegate();
            itemDAO = new ItemDAO(session);
            boolean temp = itemDAO.containItemQuantity(quantity);
            return temp;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public List<Items> readAllItems() throws DBException {
        try {
            Session session = (Session) EM.em().getDelegate();
            itemDAO = new ItemDAO(session);
            List<Items> temp = itemDAO.getAllItems();
            return temp;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public Items getItemForQuanity(String quanity) throws DBException {
        try {
            Session session = (Session) EM.em().getDelegate();
            itemDAO = new ItemDAO(session);
            Items temp = itemDAO.getItemQuantity(quanity);
            return temp;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public boolean itemDel(String quanity) throws DBException {
        try {
            Session session = (Session) EM.em().getDelegate();
            Items tmp = getItemForQuanity(quanity);
            if (tmp != null) {
                Transaction transaction1 = session.beginTransaction();
                session.delete(tmp);
                transaction1.commit();
                System.out.println("Delete Item OK");
                return true;
            } else return false;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void updateItem(Items items) {
        Session session = (Session) EM.em().getDelegate();
        Transaction transaction = session.beginTransaction();
        session.update(items);
        transaction.commit();
        System.out.println("Update Item OK");
    }

}
