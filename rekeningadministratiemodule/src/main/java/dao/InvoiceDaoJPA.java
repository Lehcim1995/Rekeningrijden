package dao;

import classes.Invoice;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class InvoiceDaoJPA implements InvoiceDao {

    @PersistenceContext(unitName = "accountAdministrationPU")
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public Invoice create(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice is null");
        }
        em.persist(invoice);
        return invoice;
    }

    @Override
    public Invoice update(Invoice invoice) {
        if (invoice == null) {
            throw new IllegalArgumentException("Invoice is null");
        }
        em.merge(invoice);
        return invoice;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        try {
            return em.createQuery("SELECT invoice FROM Invoice invoice", Invoice.class).getResultList();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public Invoice getInvoiceByTrackerId(String trackerId) {
        try {
            return em.createQuery("SELECT invoice FROM Invoice invoice WHERE invoice.vehicleTrackerId = :trackerId", Invoice.class).setParameter("trackerId", trackerId).getSingleResult();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }
}
