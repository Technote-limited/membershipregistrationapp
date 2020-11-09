package com.example.dhl;



import com.example.dhl.model.Members;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.lang.reflect.Member;
import java.util.List;

public class MemberResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("members")
    @Expose
    private List<Members> members;

    @SerializedName("member")
    @Expose
    private Members member;


    /**
     * No args constructor for use in serialization
     *
     */
    public MemberResponse() {
    }

    /**
     *
     * @param members
     * @param error
     */
    public MemberResponse(Boolean error, List<Members> members) {
        super();
        this.error = error;
        this.members = members;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<Members> getMembers() {
        return members;
    }

    public void setMembers(List<Members> members) {
        this.members = members;
    }

    public Members getMember() {
        return member;
    }

    public void setMember(Members member) {
        this.member = member;
    }


}
