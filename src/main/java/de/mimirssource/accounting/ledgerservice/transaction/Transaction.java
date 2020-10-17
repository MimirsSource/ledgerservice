package de.mimirssource.accounting.ledgerservice.transaction;

import de.mimirssource.accounting.ledgerservice.ledger.Ledger;
import lombok.Data;

@Data
public class Transaction {

    private String source;

    private String target;

    private Double amount;

}
