package de.mimirssource.accounting.ledgerservice.ledger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/ledger")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LedgerResource {

    @Inject
    LedgerService ledgerService;

    @GET
    public List<Ledger> list() {
        return ledgerService.list();
    }

    @POST
    public List<Ledger> add(final Ledger ledger) {
        ledgerService.add(ledger);
        return list();
    }
}
