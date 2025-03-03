package com.zw.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClueController {
    // 不做权限控制
    @RequestMapping("/api/clue/index")
    public String index(){
        return "clueIndex";
    }

    // 权限标识符
    @PreAuthorize("hasAuthority('clue:list')")
    @RequestMapping("/api/clue/list")
    public String clueList(){
        return "clueList";
    }

    @PreAuthorize("hasAuthority('clue:add')")
    @RequestMapping("/api/clue/input")
    public String clueInput(){
        return "clueInput";
    }

    @PreAuthorize("hasAuthority('clue:del')")
    @RequestMapping("/api/clue/del")
    public String clueDel(){
        return "clueDel";
    }
}
