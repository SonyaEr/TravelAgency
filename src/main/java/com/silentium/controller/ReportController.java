package com.silentium.controller;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import com.silentium.model.*;
import com.silentium.repository.*;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.math.BigDecimal;
import java.text.Format;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class ReportController {

    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private VoucherClientRepository voucherClientRepository;
    @Autowired
    private TourStructureRepository tourStructureRepository;
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private TourDateRepository tourDateRepository;
    @Autowired
    private ExcursionStructureRepository excursionStructureRepository;
    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    private static BaseFont baseFont = loadBaseFont("files/fonts/arial.ttf");
    private List listField;
    private List listValue;

    @PostMapping(value = {"/admin/home/Contract","/home/Contract"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReportContract(@RequestParam(name = "voucher_id") int select_id, Model model) {

        String templatePath = "files/templates/contract.pdf";
        String newPDFPath = "files/generated/contract_" + select_id + ".pdf";

        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos = null;
        PdfStamper stamper;

        Voucher voucher = voucherRepository.findById(select_id);
        List<Person> persons = voucherClientRepository.findByVoucherId(select_id);
        Person client = voucher.getOrder().getClient();

        try {
            out = new FileOutputStream(newPDFPath);// поток вывода
            reader = new PdfReader(templatePath);// читаем pdf шаблон
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            listField = new ArrayList<>();
            listValue = new ArrayList<>();

            addField("vaucher_id", Integer.toString(select_id));

            LocalDate date = convertToLocalDateViaSqlDate(voucher.getVoucherDate());
            addField("date", date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(new Locale("uk"))));
            addField("tour_id", Integer.toString(voucher.getTourDate().getTour().getId()));
            addField("tour_date", date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(new Locale("uk"))));
            int t = 1;
            String lastValue = "";
            for (Person person : persons) {
                String value = person.getFIO() + " / " + person.getDocumentType() + " " + person.getDocument();
                if (t <= 4) {
                    addField("tourist" + t, value);
                    lastValue = value;
                } else {
                    lastValue = lastValue + "\n" + value;
                    addField("tourist4", lastValue);
                }
                t++;
            }
            if (persons.isEmpty()) {
                String value = client.getFIO() + " / " + client.getDocumentType() + " " + client.getDocument();
                addField("tourist1", value);
            }

            LocalDate dateArrival = convertToLocalDateViaSqlDate(voucher.getTourDate().getDateArrival());
            LocalDate dateDeparture = convertToLocalDateViaSqlDate(voucher.getTourDate().getDateDeparture());
            addField("date_arrival", dateArrival.format(DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(new Locale("uk")))
                    + " - " + dateDeparture.format(DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(new Locale("uk"))));

            sendToForm(form);

            stamper.setFormFlattening(true);// Если значение равно false, сгенерированный файл PDF все еще можно редактировать, для него должно быть установлено значение true
            stamper.close();
            reader.close();

            Document doc = new Document();
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            doc.close();
        } catch (IOException e) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, e);
        } catch (DocumentException e) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        headers.setContentDisposition(ContentDisposition.parse("attachment; filename=contract_" + select_id + ".pdf"));

        ResponseEntity<byte[]> response = new ResponseEntity<>(bos.toByteArray(), headers, HttpStatus.OK);
        return response;
    }

    @PostMapping(value = {"/admin/home/Invoice","/home/Invoice"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReportInvoice(@RequestParam(name = "order_id") int select_id, Model model) {

        String templatePath = "files/templates/invoice.pdf";
        String newPDFPath = "files/generated/invoice_" + select_id + ".pdf";

        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos = null;
        PdfStamper stamper;

        Order order = orderRepository.findById(select_id);
        TourDate tourDate = order.getTourDate();
        Tour tour = tourDate.getTour();


        try {
            out = new FileOutputStream(newPDFPath);// поток вывода
            reader = new PdfReader(templatePath);// читаем pdf шаблон
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            listField = new ArrayList<>();
            listValue = new ArrayList<>();

            addField("inv_number", Integer.toString(select_id));


            LocalDate date = convertToLocalDateViaSqlDate(order.getOrderDate());
            addField("inv_date", date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy").withLocale(new Locale("uk"))));
            addField("inv_FIO", order.getClient().getFIO());
            addField("inv_tour_id", Integer.toString(tour.getId()) +
                    "/" + Integer.toString(order.getTourDate().getId()));
            LocalDate dateArrival = convertToLocalDateViaSqlDate(tourDate.getDateArrival());
            addField("inv_tour_name", tour.getName() + " " +
                    dateArrival.format(DateTimeFormatter.ofPattern("(dd.MM.yyyy)")));
            int count =order.getCount();
            float rate = currencyRateRepository.findByDate(order.getOrderDate()).getRate();
            float price = tourDate.getPrice()*rate;
            price = BigDecimal.valueOf(price)
                    .setScale(2, BigDecimal.ROUND_HALF_UP)
                    .floatValue();
            float sum = count*price;
            addField("inv_tour_count", Integer.toString(count));
            addField("inv_tour_unit", "шт.");
            addField("inv_tour_price", Float.toString(price));
            addField("inv_tour_sum", Float.toString(sum));
            addField("inv_sumall", Float.toString(sum));
            RuleBasedNumberFormat nf = new RuleBasedNumberFormat(Locale.forLanguageTag("uk"),
                    RuleBasedNumberFormat.SPELLOUT);
            int sum_uah = (int) sum;
            int sum_kop = (int) (sum-sum_uah)*100;
            addField("inv_sumchar", nf.format((int)sum)+" грн. " + Integer.toString(sum_kop) + " коп.");
            addField("inv_manager", "Шевченко П.О.");

            sendToForm(form);

            stamper.setFormFlattening(true);// Если значение равно false, сгенерированный файл PDF все еще можно редактировать, для него должно быть установлено значение true
            stamper.close();
            reader.close();

//            Document doc = new Document();
//            PdfCopy copy = new PdfCopy(doc, out);
//            doc.open();
//            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()),1);
//            copy.addPage(importPage);
//            doc.close();
        } catch (IOException e) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, e);
        } catch (DocumentException e) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//        headers.setContentDisposition(ContentDisposition.parse("attachment; filename=contract_" + select_id + ".pdf"));

        ResponseEntity<byte[]> response = new ResponseEntity<>(bos.toByteArray(), headers, HttpStatus.OK);
        return response;
    }

    @PostMapping(value = "/home/memo", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getReportMemo(@RequestParam(name = "tour_id") int select_id, Model model) {
        String path = "files/";
        String ftlName = "memo.html";
        String imageDiskPath = path + "imgs/";
        String outputFile = "files/generated/memo_" + select_id + ".pdf";
        ByteArrayOutputStream baos = null;

        Map<String, Object> data = getDataForMeno(select_id);
        try {
            Template tpl = generateTemplate(path + "ftl/", ftlName);
            StringWriter writer = new StringWriter();
            tpl.process(data, writer);
            writer.flush();
            String html = writer.toString();
            ITextRenderer render = generateITextRenderer(path);
            OutputStream out = new FileOutputStream(outputFile);
            render.setDocumentFromString(html);
            if (imageDiskPath != null && !"".equals(imageDiskPath)) {
                render.getSharedContext().setBaseURL("file:/" + imageDiskPath);
            }
            render.layout();
            render.createPDF(out);
            render.finishPDF();
            render = null;
            out.close();
            ;

            PdfReader reader = new PdfReader(outputFile);
            baos = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(reader, baos);
            stamper.setPageAction(PdfWriter.PAGE_OPEN, new PdfAction(PdfAction.PRINTDIALOG), 1);
            stamper.close();
            reader.close();

        } catch (IOException e) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, e);
        } catch (DocumentException e) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, e);
        } catch (TemplateException e) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, e);
        } catch (com.lowagie.text.DocumentException e) {
            throw new RuntimeException(e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
        return response;
    }

    private Map<String, Object> getDataForMeno(int select_id) {
        Map<String, Object> data = new HashMap<String, Object>();

        Tour tour = tourRepository.findById(select_id);
        List<TourStructure> tourStructures = tourStructureRepository.findByTourId(select_id);

        data.put("tour_name", tour.getName());
        data.put("tour_night", Integer.toString(tour.getQuantityNight()));
        data.put("tour_type", tour.getTypeTour().getName());
        data.put("tour_transport", tour.getTypeTransport().getName());
        data.put("tour_operator", tour.getTourOperator().getName());
        data.put("tour_food", tour.getTypeFood().getName());
        data.put("tour_description", tour.getDescription());

        String tour_cities = "";
        String tour_counties = "";

        List<Object> ListStructures = new ArrayList<Object>();
        int s = 1;
        for (TourStructure tourStructure : tourStructures) {
            Hotel hotel = tourStructure.getHotel();
            City city = tourStructure.getCity();
            List<Excursion> excusions = excursionStructureRepository.findByTourStructureId(tourStructure.getId());

            tour_cities = tour_cities + " - " + city.getName();
            tour_counties = tour_counties + " - " + city.getCountryName();

            Map<String, Object> tour_Structure = new HashMap<String, Object>();
            tour_Structure.put("counter", Integer.toString(s));
            tour_Structure.put("name", tourStructure.getName());
            tour_Structure.put("city", city.getName());
            tour_Structure.put("country", city.getCountryName());
            tour_Structure.put("duration", tourStructure.getDurationDay());
            tour_Structure.put("description", tourStructure.getDescription());
            if (hotel != null) {
                tour_Structure.put("hotel", hotel.getName());
                tour_Structure.put("hotel_stars", Integer.toString(hotel.getStars()));
                tour_Structure.put("hotel_address", hotel.getAddress());
                tour_Structure.put("hotel_city", hotel.getCity().getName());
            } else {
                tour_Structure.put("hotel", "");
                tour_Structure.put("hotel_stars", "");
                tour_Structure.put("hotel_address", "");
                tour_Structure.put("hotel_city", "");
            }


            List<Object> listExcursions = new ArrayList<Object>();
            for (Excursion excursion : excusions) {
                Map<String, Object> mapExcursion = new HashMap<String, Object>();
                mapExcursion.put("name", excursion.getName());
                mapExcursion.put("city", excursion.getCity().getName());
                mapExcursion.put("price", Float.toString(excursion.getPrice()));
                mapExcursion.put("description", excursion.getDescription());
                mapExcursion.put("duration", excursion.getTime().format(DateTimeFormatter.ofPattern("hh-mm")));
                listExcursions.add(mapExcursion);
            }
            tour_Structure.put("excursions", listExcursions);
            ListStructures.add(tour_Structure);
            s++;
        }
        data.put("tour_cities", tour_cities.substring(2));
        data.put("tour_counties", tour_counties.substring(2));
        data.put("tour_Structures", ListStructures);

        return data;
    }

    /**
     * Получить объект шаблона freemarker
     *
     * @param ftlPath Путь к шаблону FTL
     * @param ftlName Название шаблона FTL
     * @return
     * @throws IOException
     */
    private Template generateTemplate(String ftlPath, String ftlName) throws IOException {
        Configuration config = new Configuration(new Version("2.3.22"));
        config.setDirectoryForTemplateLoading(new File(ftlPath));
        config.setEncoding(new Locale("uk"), "UTF-8");

        Template template = config.getTemplate(ftlName);
        return template;
    }

    /**
     * Получить рендерер ITextRenderer
     *
     * @param path Корневой путь
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    private ITextRenderer generateITextRenderer(String path) throws DocumentException, IOException, com.lowagie.text.DocumentException {
        ITextRenderer render = new ITextRenderer();
        // Добавляем шрифты
        render.getFontResolver().addFont(path + "fonts/lucida.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        return render;
    }

    private void addField(String fieldName, String value) {
        listField.add(fieldName);
        listValue.add(value);
    }

    private void sendToForm(AcroFields form) throws DocumentException, IOException {
        java.util.Iterator<String> it = form.getFields().keySet().iterator();
        while (it.hasNext()) {
            String name = it.next();
            int id = listField.indexOf(name);
            if (id >= 0) {
                form.setFieldProperty(name, "textfont", baseFont, null);
                form.setField(name, listValue.get(id).toString());
            }
        }
    }

    private LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    /**
     * Загружаем шрифт из .ttf файла
     *
     * @param fontName Путь к файлу
     * @return
     */
    private static BaseFont loadBaseFont(String fontName) {
        BaseFont baseFont = null;
        try {
            baseFont = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseFont;
    }


}