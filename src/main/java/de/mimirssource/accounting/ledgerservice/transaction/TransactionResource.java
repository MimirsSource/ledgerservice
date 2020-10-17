package de.mimirssource.accounting.ledgerservice.transaction;

import de.mimirssource.accounting.ledgerservice.ledger.Ledger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/transaction")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource {

    @Inject
    TransactionService transactionService;

    @GET
    public List<Transaction> list() {
        return transactionService.list();
    }

    @POST
    public List<Transaction> add(final Transaction transaction) {
        transactionService.add(transaction);
        return list();
    }

}
