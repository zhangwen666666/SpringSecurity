package com.zw.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClueController {
    @PreAuthorize("hasRole('ROLE_saler')")
//    @PreAuthorize("hasAuthority('saler')")
    @RequestMapping("/api/clue/menu")
    public String clueMenu(){
        return "clueMenu";
    }

    @PreAuthorize("hasRole('ROLE_saler')")
//    @PreAuthorize("hasAuthority('saler')")
    @RequestMapping("/api/clue/menu/child")
    public String clueMenuChild(){
        return "clueMenuChild";
    }

    @PreAuthorize("hasRole('ROLE_saler')")
//    @PreAuthorize("hasAuthority('saler')")
    @RequestMapping("/api/clue/list")
    public String clueList(){
        return "clueList";
    }

    @PreAuthorize("hasRole('ROLE_saler')")
//    @PreAuthorize("hasAuthority('saler')")
    @RequestMapping("/api/clue/input")
    public String clueInput(){
        return "clueInput";
    }

    @PreAuthorize("hasRole('ROLE_saler')")
//    @PreAuthorize("hasAuthority('saler')")
    @RequestMapping("/api/clue/edit")
    public String clueEdit(){
        return "clueEdit";
    }

    @PreAuthorize("hasRole('ROLE_saler')")
//    @PreAuthorize("hasAuthority('saler')")
    @RequestMapping("/api/clue/view")
    public String clueView(){
        return "clueView";
    }

    @PreAuthorize("hasRole('ROLE_admin')")
//    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping("/api/clue/del")
    public String clueDel(){
        return "clueDel";
    }

    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_manager')")
//    @PreAuthorize("hasAnyAuthority('admin', 'manager')")
    @RequestMapping("/api/clue/export")
    public String clueExport(){
        return "clueExport";
    }

    @RequestMapping("/api/clue/index")
    public String index(){
        return "clueIndex";
    }
}
