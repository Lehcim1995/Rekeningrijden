package rest;

import domain.Invoice;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;

@Startup
@Singleton
public class Startuplol
{
    @Inject
    CollectInvoicesREst rest;

    @PostConstruct
    public void init()
    {
        System.out.println("collecting invoices");
        //List<Invoice> invoice = rest.collectInvoices();

        //System.out.println(invoice.size());
        System.out.println("Works lol");
    }

}
