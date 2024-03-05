package com.example.syyoo.members.mapper;

import com.example.syyoo.members.vo.MemberVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface MemberMapper {

    List<MemberVo> findAll();

    MemberVo findByMemSeq(long memSeq);

    void update(Map<String, Object> updateMemberMap);

    void delMemBySeq(long memSeq);

    void save(MemberVo memberVo);
}
