package com.learn.spring.springinjection.autowire.mkyongtutorial.common;

import org.springframework.beans.factory.annotation.Autowired;

public class Customer {

//	@Autowired
	@Autowired(required=false)
//    @Qualifier("childPerson2")
//    if no Person exists then null will be set. no error occurs.
//    if we have 2 persons in xml, then we will have error. We have to use qualifier in
//    this case.
	private Person personB;
	private int type;

    @Autowired
    private SingleApprovalService singleApprovalService;

	private String action;

	public Person getPersonB() {
		return personB;
	}

	public void setPersonB(Person personB) {
		this.personB = personB;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "Customer [personB=" + personB + ", type=" + type + ", action="
				+ action + "]";
	}


    public SingleApprovalService getSingleApprovalService() {
        return singleApprovalService;
    }

    public void setSingleApprovalService(SingleApprovalService singleApprovalService) {
        this.singleApprovalService = singleApprovalService;
    }
}