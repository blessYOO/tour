package com.example.syyoo.members.vo;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberVo {
    private long memSeq;
    private String memId;
    private String memPass;
    private String memName;
    private int memAuth;
    private char useYn;

    public MemberVo() {
    }

    public MemberVo(String memId, String memPass, String memName, int memAuth, char useYn) {
        this.memId = memId;
        this.memPass = memPass;
        this.memName = memName;
        this.memAuth = memAuth;
        this.useYn = useYn;
    }

    public MemberVo(long memSeq, String memId, String memPass, String memName, int memAuth, char useYn) {
        this.memSeq = memSeq;
        this.memId = memId;
        this.memPass = memPass;
        this.memName = memName;
        this.memAuth = memAuth;
        this.useYn = useYn;
    }

}
