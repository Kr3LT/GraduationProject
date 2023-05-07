package grad.Binh.AppointmentManage.utils;

import grad.Binh.AppointmentManage.entity.Invoice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;
@Component
public class PdfGeneratorUtils {
    private final SpringTemplateEngine templateEngine;
    private final String baseUrl;

    public PdfGeneratorUtils(SpringTemplateEngine templateEngine, @Value("${base.url}") String baseUrl){
        this.templateEngine = templateEngine;
        this.baseUrl = baseUrl;
    }
    public File generatePdfFromInvoice(Invoice invoice){
        Context context = new Context();
        context.setVariable("invoice", invoice);
        String processedHtml = templateEngine.process("email/pdf/invoice", context);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(processedHtml, baseUrl);
        renderer.layout();

        String fileName = UUID.randomUUID().toString();
        File outputFile = null;
        FileOutputStream outputStream = null;
        try {
            outputFile = File.createTempFile(fileName, ".pdf");
            outputStream = new FileOutputStream(outputFile);
            renderer.createPDF(outputStream, false);
            renderer.finishPDF();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(outputStream != null)
                try {
                    outputStream.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return outputFile;
    }
}
