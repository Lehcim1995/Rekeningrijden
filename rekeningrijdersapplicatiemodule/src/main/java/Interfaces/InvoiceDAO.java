package Interfaces;

import classes.Invoice;
import classes.Owner;

public interface InvoiceDAO {

    void create (Invoice invoice);

    void edit (Invoice invoice);

    void delete (Invoice invoice);

    Invoice findByOwner (Owner owner);
}
