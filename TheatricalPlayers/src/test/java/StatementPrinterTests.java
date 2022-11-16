import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {

        List<Play> plays = new ArrayList<>();
        plays.add(new Tragedie("Hamlet"));
        plays.add(new Comedie("As You Like It"));
        plays.add(new Tragedie("Othello"));

        ArrayList<Performance> temp = new ArrayList<>();
        temp.add(new Performance(plays.get(0), 55));
        temp.add(new Performance(plays.get(1), 35));
        temp.add(new Performance(plays.get(2), 40));

        Acheteur acheteur = new Acheteur("BigCo");
        Facture facture = new Facture(acheteur, temp);

        var result = facture.printToText();
        facture.printToHTML();
        verify(result);

    }
}