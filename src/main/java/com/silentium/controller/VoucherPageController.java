package com.silentium.controller;

import com.silentium.model.*;
import com.silentium.repository.*;
import com.silentium.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;


@Controller
public class VoucherPageController {
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private TourStructureRepository tourStructureRepository;
    @Autowired
    private StatusVoucherRepository statusVoucherRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private VoucherClientRepository voucherClientRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/user/voucherpage")
    public String tourpageUser(@RequestParam (name = "id") int select_id, Model model) {
        initModelVoucherPage(select_id, model);
        return "/user/voucherpage";
    }

    @GetMapping("/admin/voucherpage")
    public String tourpageAdmin(@RequestParam (name = "id") int select_id, Model model) {
        initModelVoucherPage(select_id, model);
        return "/admin/voucherpage";
    }

    @PostMapping("/admin/voucherpage/updateVoucher.do")
    public ModelAndView updateVoucherAdmin(@RequestParam(name = "status_voucher") int select_statusvoucher,
                                           @Valid @ModelAttribute("voucherSave") Voucher voucherSave,
                                           BindingResult res,
                                           Model model) {
        int select_id = voucherSave.getId();
        updateVoucher(select_id, select_statusvoucher, model);
        return new ModelAndView("redirect:/admin/home");
    }

    private void initModelVoucherPage(int select_id, Model model) {
        List<TourStructure> tourStructures= tourStructureRepository.findByTourId(select_id);
        model.addAttribute("voucher", voucherRepository.findById(select_id));
        model.addAttribute("statusvouchers", statusVoucherRepository.findAll());
        model.addAttribute("statusvouchers", statusVoucherRepository.findAll());
        model.addAttribute("voucherclients", voucherClientRepository.findByVoucherId(select_id));
        model.addAttribute("clients", personRepository.findAllpage());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account user = accountService.findUserByLogin(auth.getName());
        model.addAttribute("userName", user.getPerson().getName()) ;
        model.addAttribute("userSurName", user.getPerson().getSurname());
        model.addAttribute("userEmail", user.getLogin());
    }

    private void updateVoucher(int voucher_id, int status_id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account user = accountService.findUserByLogin(auth.getName());
        String role = accountRepository.findRoleByLogin(auth.getName());
        if (!role.equals("ADMIN")) {return;}
        Voucher voucher = voucherRepository.findById(voucher_id);
        if (voucher == null) {
            return;
        }
        if (voucher.getManager() != null) {
            if (voucher.getManager().getId() != user.getPerson().getId()) {
                return;
            }
        } else {
            voucher.setManager(user.getPerson());
        }
        if (voucher.getStatusVoucher().getId() != status_id) {
            StatusVoucher statusVoucher = statusVoucherRepository.findById(status_id);
            voucher.setStatusVoucher(statusVoucher);
        }
        voucherRepository.saveAndFlush(voucher);
    }

    public String selectUrl(String urlUser, String urlAdmin){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = accountRepository.findRoleByLogin(auth.getName());
        if (role.equals("ADMIN")) {
            return urlAdmin;
        }
        return urlUser;
    }
}


