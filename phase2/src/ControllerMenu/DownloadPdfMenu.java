package ControllerMenu;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import Controller.ControllerSystem;
import java.io.File;
import java.io.IOException;

/**
 * Loading the schedule as a pdf.
 *
 * @author Minhui, Shang Wu
 * @version %I%, %G%
 */

public class DownloadPdfMenu {

    /**
     * Get schedule for user and created pdf.
     *
     * @param userid the id of the user who want to print his or her schedule.
     * @throws IOException
     */

    public void pdfCreated(String userid, ControllerSystem cs) throws IOException {

        int numEvents = cs.userSystem().getEventTotal(userid);
        int numPage;
        if (numEvents == 0) {
            numPage = 1;
        }else {
            numPage = (int) Math.ceil((double) numEvents / 6);
        }

        PDDocument doc = new PDDocument();
        try {
            for (int i=0; i<numPage; i++) {
                PDPage blankPage = new PDPage();
                doc.addPage( blankPage );
            }
            doc.save("my_schedule.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
            for (int i = 0; i < numPage; i++) {
                PDPage page = doc.getPage(i);
                PDPageContentStream contentStream = new PDPageContentStream(doc, page);
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
                contentStream.setLeading(14.5f);
                contentStream.newLineAtOffset(25, 725);

                String text = cs.userSystem().getSchedule(userid);
                String[] piece = text.split("\n");
                for (int j = 48 * (i); j < Math.min(48 * (i + 1), piece.length) ; j++) {
                    contentStream.showText(piece[j]);
                    contentStream.newLine();
                }
                contentStream.endText();
                System.out.println("The pdf is available now. It is stored under the folder of this project.\n");
                contentStream.close();

            }
        doc.save(new File("my_schedule.pdf"));
        doc.close();
    }
}

