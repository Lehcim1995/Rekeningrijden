package dao;

import classes.Invoice;
import classes.MonthEnum;
import classes.Owner;
import classes.PaymentEnum;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;
import java.util.ArrayList;
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
    public boolean changePaymentStatusById(int invoiceId, String paymentStatus) {
        if(!paymentStatus.equals("Open"))
        {
            try {
                Invoice newInvoice = getInvoiceByInvoiceId(invoiceId);
                newInvoice.setPaymentStatus(PaymentEnum.valueOf(paymentStatus));
                em.merge(newInvoice);
                return true;
            }
            catch (IllegalArgumentException | TransactionRequiredException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    @Override
    public Invoice getInvoiceByInvoiceId(int invoiceId) {
        try {
            return em.createQuery("SELECT invoice FROM Invoice invoice WHERE invoice.invoiceId = :invoiceId", Invoice.class)
                    .setParameter("invoiceId", invoiceId)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        try {
            return em.createQuery("SELECT invoice FROM Invoice invoice", Invoice.class).getResultList();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Invoice getInvoiceByTrackerId(String trackerId) {
        try {
            return em.createQuery("SELECT invoice FROM Invoice invoice WHERE invoice.vehicleTrackerId = :trackerId", Invoice.class)
                    .setParameter("trackerId", trackerId)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Invoice> getInvoicesByOwner(Owner owner) {
        try {
            return em.createQuery("SELECT invoice FROM Invoice invoice WHERE invoice.owner = :owner", Invoice.class)
                    .setParameter("owner", owner)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Invoice> getInvoicesByPaymentStatus(PaymentEnum paymentEnum) {
        try {
            return em.createQuery("SELECT invoice FROM Invoice invoice WHERE invoice.paymentStatus = :paymentEnum", Invoice.class)
                    .setParameter("paymentEnum", paymentEnum)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Invoice> getInvoicesByTrackerIdAndMonth(String trackerId, MonthEnum monthEnum) {
        try {
            return em.createQuery("SELECT invoice FROM Invoice invoice WHERE invoice.vehicleTrackerId = :trackerId " +
                    "AND invoice.date = :monthEnum", Invoice.class)
                    .setParameter("trackerId", trackerId)
                    .setParameter("monthEnum", monthEnum)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Invoice> getInvoicesByOwnerAndMonth(Owner owner, MonthEnum monthEnum) {
        try {
            return em.createQuery("SELECT invoice FROM Invoice invoice WHERE invoice.owner = :owner " +
                    "AND invoice.date = :monthEnum", Invoice.class)
                    .setParameter("owner", owner)
                    .setParameter("monthEnum", monthEnum)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public List<Invoice> getInvoicesByPaymentStatusAndMonth(PaymentEnum paymentEnum, MonthEnum monthEnum) {
        try {
            return em.createQuery("SELECT invoice FROM Invoice invoice WHERE invoice.paymentStatus = :paymentEnum " +
                    "AND invoice.date = :monthEnum", Invoice.class)
                    .setParameter("paymentEnum", paymentEnum)
                    .setParameter("monthEnum", monthEnum)
                    .getResultList();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Owner findOwnerById(int citizenId) {
        try {
            return em.createQuery("SELECT owner FROM Owner owner WHERE owner.citizenId = :citizenId", Owner.class)
                    .setParameter("citizenId", citizenId)
                    .getSingleResult();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }
}
