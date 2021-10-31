package com.example.controller;

import com.example.SpringMvcApplication;
import com.example.facade.BookingFacade;
import com.example.model.Ticket;
import com.example.model.User;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/ticket")
public class TicketController {
    private final BookingFacade bookingFacade;

    public TicketController(BookingFacade bookingFacade) {
        this.bookingFacade = bookingFacade;
    }

    @GetMapping()
    public String getTicketInfo(@RequestParam long eventId, Model model, HttpSession session) {
        log.info("Ticket get mapping on the page ticket info");
        Ticket ticketInfo = bookingFacade.getDetailedTicketInfo(eventId);
        model.addAttribute("ticket", ticketInfo);
        System.out.println("USERNAME: " + session.getAttribute("username"));
        return "ticketInfo";
    }

    @GetMapping("/my")
    public String getUserTickets(HttpSession session,
                                 Model model,
                                 @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        User currentUser = (User) session.getAttribute("user");
        Page<Ticket> page = bookingFacade.getBookedTickets(currentUser, pageable);
        model.addAttribute("page", page);
        return "ticketsList";
    }

    /**
     * This piece of s... code took more than 3 hours. I don`t have any emotional power to refactor this.
     */
    @GetMapping("/my/downloadPdf")
    public ResponseEntity<byte[]> downloadPdf(HttpSession session, Model model) throws IOException, TemplateException {
        User currentUser = (User) session.getAttribute("user");
        Page<Ticket> page = bookingFacade.getBookedTickets(currentUser, Pageable.ofSize(3));
        model.addAttribute("page", page);

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassForTemplateLoading(SpringMvcApplication.class, "/templates");
        cfg.setIncompatibleImprovements(new Version(2, 3, 31));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<>();

        input.put("page", page);

        Template template = cfg.getTemplate("ticketsList.ftlh");
        File file = new File("output.pdf");
        Writer fileWriter = new FileWriter(file);
        System.out.println(template);
        template.process(input, fileWriter);

        String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        ByteArrayOutputStream target = new ByteArrayOutputStream();

        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        HtmlConverter.convertToPdf(content, target, converterProperties);

        byte[] bytes = target.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

    @PostMapping("/purchase")
    public String buyTicket(@RequestParam long eventId, Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        Ticket ticket = bookingFacade.buyTicket(username, eventId);
        model.addAttribute("ticket", ticket);
        return "success";
    }
}
