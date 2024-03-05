package com.example.syyoo.members.controller;

import com.example.syyoo.members.service.BasicService;
import com.example.syyoo.members.vo.MemberVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 멤버 컨트롤러
 * @author syyoo
 * */
@Controller
@RequiredArgsConstructor
public class BasicController {

    /** 서비스를 private 으로 설정해야 작동 */
    private final BasicService basicService;
    private static final Logger logger = LoggerFactory.getLogger(BasicController.class);

    /** 메인 페이지 */
    @GetMapping("/")
    public String home(){
        return "index";
    }

    /** 본문 페이지 */
    @GetMapping("/generic")
    public String generic(){
        return "generic";
    }

    /** 상세 페이지 */
    @GetMapping("/elements")
    public String elements(){
        return "elements";
    }

    /** 멤버 전체 조회 페이지 */
    @GetMapping("/members")
    public String findMembers(Model model){
        List<MemberVo> members = basicService.findAll();
        model.addAttribute("members",members);
        return "members";
    }

    /** 멤버 상세 조회 페이지 */
    @GetMapping("/{memSeq}")
    public String findMemBySeq(@PathVariable("memSeq") long memSeq, Model model){
        MemberVo member = basicService.findByMemSeq(memSeq);
        model.addAttribute("member",member);
        return "member";
    }

    /** 등록 페이지 */
    @GetMapping("/add")
    public String addForm(){
        return "addForm";
    }

    /** 등록 기능 */
    @PostMapping("/add")
    public String add(@ModelAttribute MemberVo memberVo){
        System.out.println("저장시작");
        System.out.println(memberVo);

        basicService.save(memberVo);
        return "redirect:/members";
    }

    /** 수정 페이지 */
    @GetMapping("/{memSeq}/edit")
    public String editForm(@PathVariable("memSeq") long memSeq, Model model){
        MemberVo member = basicService.findByMemSeq(memSeq);
        model.addAttribute("member",member);
        return "editForm";
    }

    /** 수정 기능 */
    @PostMapping("/{memSeq}/edit")
    public String editBySeq(@PathVariable("memSeq") long memSeq, @RequestParam Map<String, Object> updateMemberMap, @ModelAttribute MemberVo memberVo, Model model){
        logger.info("업데이트 시작");
        MemberVo findMember = basicService.findByMemSeq(memSeq);
        updateMemberMap.put("memSeq", memSeq);
        updateMemberMap.put("memId", memberVo.getMemId());
        updateMemberMap.put("memPass", memberVo.getMemPass());
        updateMemberMap.put("memName", memberVo.getMemName());
        updateMemberMap.put("memAuth", memberVo.getMemAuth());
        updateMemberMap.put("useYn", memberVo.getUseYn());
        basicService.update(updateMemberMap);
        logger.info("업데이트 완료");
        model.addAttribute("member", updateMemberMap);
        return "redirect:/{memSeq}";
    }

    /** 삭제 기능 */
    @GetMapping("/{memSeq}/delete")
    public String deleteBySeq(@PathVariable("memSeq") long memSeq){
        basicService.delMemBySeq(memSeq);
        logger.info("삭제완료");
        return "redirect:/";
    }

    /** 주소 검색 */
    @GetMapping("/map")
    public String map(){
        return "map";
    }

    /** 로그인 */
    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    /** 로그인 기능 */
    @PostMapping("/login")
    public String login(@RequestParam Map<String, Object> loginInfoMap, @ModelAttribute MemberVo memberVo, HttpSession session, HttpServletRequest request, Model model){
        List<MemberVo> members = basicService.findAll();
        for(int i=0;i<members.size();i++){
            if(members.get(i).getMemId().equals(memberVo.getMemId())){
                if(members.get(i).getMemPass().equals(memberVo.getMemPass())){
                    logger.info("로그인 성공");
                    session.setAttribute("memSeq", members.get(i).getMemSeq());
                    session.setAttribute("memName", members.get(i).getMemName());
                    session.setAttribute("memId", members.get(i).getMemId());
                    session.setAttribute("memAuth", members.get(i).getMemAuth());

                    MemberVo member = basicService.findByMemSeq(members.get(i).getMemSeq());
                    model.addAttribute("member", member);
                    return "redirect:/";
                }else{
                    logger.info("비밀번호 불일치");
                    return "redirect:/error";
                }
            }else {
                logger.info("아이디 불일치");
                return "redirect:/error";
            }
        }
        return "login";
    }

    /** zoom */
    @GetMapping("/zoom")
    public String zoomPage(){
        return "zoom";
    }

}
