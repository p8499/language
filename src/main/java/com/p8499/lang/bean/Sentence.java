package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Sentence implements Bean
{	public static final String TABLE="public.F1120";
	public static final String VIEW="public.V1120";
	public static final String NAME="Sentence";
	public static final String FIELD_ASID="ASID";
	public static final String FIELD_ASATID="ASATID";
	public static final String FIELD_ASSI="ASSI";
	public static final String FIELD_ASCONT="ASCONT";
	public static final String FIELD_ASST="ASST";
	public static final String FIELD_ASUSID="ASUSID";
	public static final String FIELD_ASUPDD="ASUPDD";
	public static final String FIELD_ASUPDT="ASUPDT";
	public static final String FIELD_ASCS="ASCS";
	public static final Integer ASST_DISABLED=-1;
	public static final Integer ASST_ENABLED=0;
	protected Integer asid=null;
	protected Integer asatid=null;
	protected Integer assi=null;
	protected String ascont=null;
	protected Integer asst=null;
	protected String asusid=null;
	protected String asupdd=null;
	protected String asupdt=null;
	protected String ascs=null;

	public Sentence(Integer asid,Integer asatid,Integer assi,String ascont,Integer asst,String asusid,String asupdd,String asupdt,String ascs)
	{	this.asid=asid;
		this.asatid=asatid;
		this.assi=assi;
		this.ascont=ascont;
		this.asst=asst;
		this.asusid=asusid;
		this.asupdd=asupdd;
		this.asupdt=asupdt;
		this.ascs=ascs;
	}
	public Sentence()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Sentence clone()
	{
		return new Sentence(asid,asatid,assi,ascont,asst,asusid,asupdd,asupdt,ascs);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getAsid()
	{	return asid;
	}
	public Sentence setAsid(Integer asid)
	{	this.asid=asid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAsatid()
	{	return asatid;
	}
	public Sentence setAsatid(Integer asatid)
	{	this.asatid=asatid;
		return this;
	}
	
	@NotNull(groups={Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAssi()
	{	return assi;
	}
	public Sentence setAssi(Integer assi)
	{	this.assi=assi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=128)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAscont()
	{	return ascont;
	}
	public Sentence setAscont(String ascont)
	{	this.ascont=ascont;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getAsst()
	{	return asst;
	}
	public Sentence setAsst(Integer asst)
	{	this.asst=asst;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAsusid()
	{	return asusid;
	}
	public Sentence setAsusid(String asusid)
	{	this.asusid=asusid;
		return this;
	}
	
	@Size(max=10)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAsupdd()
	{	return asupdd;
	}
	public Sentence setAsupdd(String asupdd)
	{	this.asupdd=asupdd;
		return this;
	}
	
	@Size(max=8)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAsupdt()
	{	return asupdt;
	}
	public Sentence setAsupdt(String asupdt)
	{	this.asupdt=asupdt;
		return this;
	}
	
	@Size(max=1)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getAscs()
	{	return ascs;
	}
	public Sentence setAscs(String ascs)
	{	this.ascs=ascs;
		return this;
	}
}