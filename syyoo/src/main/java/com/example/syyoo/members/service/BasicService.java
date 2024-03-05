package com.example.syyoo.members.service;

import com.example.syyoo.members.mapper.MemberMapper;
import com.example.syyoo.members.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BasicService {

    private final MemberMapper memberMapper;

    public List<MemberVo> findAll(){
        return memberMapper.findAll();
    }
    public MemberVo findByMemSeq(long memSeq) {
        return memberMapper.findByMemSeq(memSeq);
    }

    public void update(Map<String, Object> updateMemberMap) {
        memberMapper.update(updateMemberMap);
    }

    public void delMemBySeq(long memSeq) {
        memberMapper.delMemBySeq(memSeq);
    }

    public void save(MemberVo memberVo) {
        memberMapper.save(memberVo);
    }
}
