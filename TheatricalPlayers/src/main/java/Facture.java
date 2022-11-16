
import java.io.FileWriter;
import java.text.NumberFormat;
import java.util.*;
import java.io.IOException;

public class Facture {

  public Acheteur acheteur;
  public ArrayList<Performance> performances;

  public Facture(Acheteur customer, ArrayList<Performance> performances) {
    this.acheteur = customer;
    this.performances = performances;
  }

  public String printToText() {
    Double totalAmount = 0.0;
    Double volumeCredits = 0.0;
    StringBuilder builder = new StringBuilder().append(String.format("Declaration pour %s\n", this.acheteur.getName()));
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : this.performances) {
      totalAmount += perf.getAmount(acheteur);

      volumeCredits += perf.getCredit();
      acheteur.updateCredits(perf);

      // print line for this order
      builder.append(perf);
    }

    builder.append(String.format("Le montant du est %s\n", format.format(totalAmount)));
    builder.append(String.format("Vous avez gagne %s credits\n", volumeCredits.intValue()));

    writeToFile("output/Facture.txt", builder.toString());

    return builder.toString();
  }

  public void printToHTML(){
    Double totalAmount = 0.0;
    Double volumeCredits = 0.0;
    StringBuilder builder = new StringBuilder().append(String.format("<!DOCTYPE HTML>\n<html>\n<body>\n<h1>Facture</h1>\n<ul><li><p><b>Client : </b>%s</p></li></ul>", this.acheteur.getName()));
    NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

    builder.append(String.format("<table style=\"border: 1px solid black\"><thead><tr><th style=\"border: 1px solid black\"><b>Piece</b></th><th style=\"border: 1px solid black\"><b>Sièges vendus</b></th><th style=\"border: 1px solid black\"><b>Prix</b></th></tr></thead><tbody>"));
    for (Performance perf : this.performances) {
      totalAmount += perf.getAmount(acheteur);

      volumeCredits += perf.getCredit();
      acheteur.updateCredits(perf);

      builder.append(String.format("<tr><td style=\"border: 1px solid black\">%s</td><td style=\"border: 1px solid black\">%s</td><td style=\"border: 1px solid black\">%s</td></tr>", perf.play.getName(), perf.audience.toString(), format.format(perf.getAmount(acheteur))));
      if (perf.getReduction()) {
        builder.append(String.format("<tr><td align=\"right\" colspan=\"2\"><b>Remise: (already applied)</b></td><td>%s</td></tr>", format.format((-15))));
      }
    }
    builder.append(String.format("<tr><td align=\"right\" style=\"border: 1px solid black\" colspan=\"2\"><b>Total du:</b></td><td style=\"border: 1px solid black\">%s</td></tr><td align=\"right\" style=\"border: 1px solid black\" colspan=\"2\"><b>Points de fidélite gagnes:</b></td><td style=\"border: 1px solid black\">%s</td><tr></tr></tbody></table>", format.format(totalAmount), volumeCredits.toString()));
    builder.append("<p><i>Le paiement est exige sous 30 jours.</i></p></body></html>");

    writeToFile("output/Facture.html", builder.toString());
  }

  public void writeToFile(String fileName, String output) {
    try{
      FileWriter writer = new FileWriter(fileName);
      writer.write(output);
      writer.close();
    }catch(IOException e){
      System.err.println(e.getMessage());
    }
  }
}
