package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Pronounce implements Bean
{	public static final String TABLE="public.F1011";
	public static final String VIEW="public.F1011";
	public static final String NAME="Pronounce";
	public static final String FIELD_PNID="PNID";
	public static final String FIELD_PNLSID="PNLSID";
	public static final String FIELD_PNCT="PNCT";
	public static final String FIELD_PNPI="PNPI";
	public static final String FIELD_PNTN="PNTN";
	public static final String FIELD_PNCO="PNCO";
	public static final String FIELD_PNVO="PNVO";
	public static final String FIELD_PNCL="PNCL";
	public static final String FIELD_PNRM="PNRM";
	protected Integer pnid=null;
	protected String pnlsid=null;
	protected String pnct=null;
	protected String pnpi=null;
	protected Integer pntn=null;
	protected String pnco=null;
	protected String pnvo=null;
	protected String pncl=null;
	protected String pnrm=null;

	public Pronounce(Integer pnid,String pnlsid,String pnct,String pnpi,Integer pntn,String pnco,String pnvo,String pncl,String pnrm)
	{	this.pnid=pnid;
		this.pnlsid=pnlsid;
		this.pnct=pnct;
		this.pnpi=pnpi;
		this.pntn=pntn;
		this.pnco=pnco;
		this.pnvo=pnvo;
		this.pncl=pncl;
		this.pnrm=pnrm;
	}
	public Pronounce()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Pronounce clone()
	{
		return new Pronounce(pnid,pnlsid,pnct,pnpi,pntn,pnco,pnvo,pncl,pnrm);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getPnid()
	{	return pnid;
	}
	public Pronounce setPnid(Integer pnid)
	{	this.pnid=pnid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getPnlsid()
	{	return pnlsid;
	}
	public Pronounce setPnlsid(String pnlsid)
	{	this.pnlsid=pnlsid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=1)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getPnct()
	{	return pnct;
	}
	public Pronounce setPnct(String pnct)
	{	this.pnct=pnct;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getPnpi()
	{	return pnpi;
	}
	public Pronounce setPnpi(String pnpi)
	{	this.pnpi=pnpi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getPntn()
	{	return pntn;
	}
	public Pronounce setPntn(Integer pntn)
	{	this.pntn=pntn;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getPnco()
	{	return pnco;
	}
	public Pronounce setPnco(String pnco)
	{	this.pnco=pnco;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getPnvo()
	{	return pnvo;
	}
	public Pronounce setPnvo(String pnvo)
	{	this.pnvo=pnvo;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=4)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getPncl()
	{	return pncl;
	}
	public Pronounce setPncl(String pncl)
	{	this.pncl=pncl;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=4)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getPnrm()
	{	return pnrm;
	}
	public Pronounce setPnrm(String pnrm)
	{	this.pnrm=pnrm;
		return this;
	}
}