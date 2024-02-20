package com.cis.batch33.library.service;

import com.cis.batch33.library.model.Member;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class MemberService {

    private Map<Long, Member> memberMap = new HashMap<>();

    public Member createMember(Member member){
        Long memberId = generateMemberId();
        member.setMemberId(memberId);
        memberMap.put(memberId, member);
        return member;
    }

    public Member getMember(Long memberId) {
        return memberMap.get(memberId);
    }

    public Member updateMember(Long memberId, Member member) {
        if (memberMap.containsKey(memberId)) {
            member.setMemberId(memberId);
            memberMap.put(memberId, member);
            return member;
        } else {
            return null; // or throw an exception indicating member not found
        }
    }

    public void deleteMember(Long memberId) {
        memberMap.remove(memberId);
    }

    private Long generateMemberId() {
        // Simulating generation of unique member IDs
        return new Random().nextLong();
    }
}
