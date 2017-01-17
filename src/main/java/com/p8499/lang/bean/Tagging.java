package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Tagging implements Bean
{	public static final String TABLE="public.F1140";
	public static final String VIEW="public.F1140";
	public static final String NAME="Tagging";
	public static final String FIELD_TGASID="TGASID";
	public static final String FIELD_TGCONT="TGCONT";
	public static final String FIELD_TGST="TGST";
	public static final String FIELD_TGUSID="TGUSID";
	public static final String FIELD_TGUPDD="TGUPDD";
	public static final String FIELD_TGUPDT="TGUPDT";
	public static final Integer TGST_DISABLED=-1;
	public static final Integer TGST_ENABLED=0;
	protected Integer tgasid=null;
	protected String tgcont=null;
	protected Integer tgst=null;
	protected String tgusid=null;
	protected String tgupdd=null;
	protected String tgupdt=null;

	public Tagging(Integer tgasid,String tgcont,Integer tgst,String tgusid,String tgupdd,String tgupdt)
	{	this.tgasid=tgasid;
		this.tgcont=tgcont;
		this.tgst=tgst;
		this.tgusid=tgusid;
		this.tgupdd=tgupdd;
		this.tgupdt=tgupdt;
	}
	public Tagging()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Tagging clone()
	{
		return new Tagging(tgasid,tgcont,tgst,tgusid,tgupdd,tgupdt);
	}
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getTgasid()
	{	return tgasid;
	}
	public Tagging setTgasid(Integer tgasid)
	{	this.tgasid=tgasid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=128)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTgcont()
	{	return tgcont;
	}
	public Tagging setTgcont(String tgcont)
	{	this.tgcont=tgcont;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getTgst()
	{	return tgst;
	}
	public Tagging setTgst(Integer tgst)
	{	this.tgst=tgst;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTgusid()
	{	return tgusid;
	}
	public Tagging setTgusid(String tgusid)
	{	this.tgusid=tgusid;
		return this;
	}
	
	@Size(max=10)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTgupdd()
	{	return tgupdd;
	}
	public Tagging setTgupdd(String tgupdd)
	{	this.tgupdd=tgupdd;
		return this;
	}
	
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getTgupdt()
	{	return tgupdt;
	}
	public Tagging setTgupdt(String tgupdt)
	{	this.tgupdt=tgupdt;
		return this;
	}
}