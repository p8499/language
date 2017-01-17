package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Usercreation implements Bean
{	public static final String TABLE="public.F0309";
	public static final String VIEW="public.F0309";
	public static final String NAME="Usercreation";
	public static final String FIELD_UCID="UCID";
	public static final String FIELD_UCCRDD="UCCRDD";
	public static final String FIELD_UCCRDT="UCCRDT";
	public static final String FIELD_UCUSID="UCUSID";
	public static final String FIELD_UCPV="UCPV";
	public static final String FIELD_UCMV="UCMV";
	public static final String FIELD_UCST="UCST";
	public static final Integer UCST_INVALID=-1;
	public static final Integer UCST_INITIAL=0;
	public static final Integer UCST_WAITING=1;
	public static final Integer UCST_SENT=2;
	public static final Integer UCST_FINALIZED=3;
	protected Integer ucid=null;
	protected String uccrdd=null;
	protected String uccrdt=null;
	protected String ucusid=null;
	protected String ucpv=null;
	protected String ucmv=null;
	protected Integer ucst=null;

	public Usercreation(Integer ucid,String uccrdd,String uccrdt,String ucusid,String ucpv,String ucmv,Integer ucst)
	{	this.ucid=ucid;
		this.uccrdd=uccrdd;
		this.uccrdt=uccrdt;
		this.ucusid=ucusid;
		this.ucpv=ucpv;
		this.ucmv=ucmv;
		this.ucst=ucst;
	}
	public Usercreation()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Usercreation clone()
	{
		return new Usercreation(ucid,uccrdd,uccrdt,ucusid,ucpv,ucmv,ucst);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getUcid()
	{	return ucid;
	}
	public Usercreation setUcid(Integer ucid)
	{	this.ucid=ucid;
		return this;
	}
	
	@Size(max=10)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getUccrdd()
	{	return uccrdd;
	}
	public Usercreation setUccrdd(String uccrdd)
	{	this.uccrdd=uccrdd;
		return this;
	}
	
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getUccrdt()
	{	return uccrdt;
	}
	public Usercreation setUccrdt(String uccrdt)
	{	this.uccrdt=uccrdt;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getUcusid()
	{	return ucusid;
	}
	public Usercreation setUcusid(String ucusid)
	{	this.ucusid=ucusid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=6)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getUcpv()
	{	return ucpv;
	}
	public Usercreation setUcpv(String ucpv)
	{	this.ucpv=ucpv;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=6)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getUcmv()
	{	return ucmv;
	}
	public Usercreation setUcmv(String ucmv)
	{	this.ucmv=ucmv;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getUcst()
	{	return ucst;
	}
	public Usercreation setUcst(Integer ucst)
	{	this.ucst=ucst;
		return this;
	}
}