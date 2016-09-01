package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Languageparameter implements Bean
{	public static final String TABLE="public.F9410";
	public static final String NAME="Languageparameter";
	public static final String FIELD_LPLSID="LPLSID";
	public static final String FIELD_LPSGNG="LPSGNG";
	public static final String FIELD_LPSGCH="LPSGCH";
	public static final String FIELD_LPSGLF="LPSGLF";
	public static final String FIELD_LPSGCW="LPSGCW";
	public static final String FIELD_LPSGBW="LPSGBW";
	public static final String FIELD_LPSGNB="LPSGNB";
	public static final String FIELD_LPSGFT="LPSGFT";
	public static final String FIELD_LPSGFC="LPSGFC";
	public static final String FIELD_LPPONG="LPPONG";
	public static final String FIELD_LPPOCH="LPPOCH";
	public static final String FIELD_LPPOLF="LPPOLF";
	public static final String FIELD_LPPOFT="LPPOFT";
	public static final String FIELD_LPPOFC="LPPOFC";
	protected String lplsid=null;
	protected Integer lpsgng=null;
	protected Integer lpsgch=null;
	protected Double lpsglf=null;
	protected Double lpsgcw=null;
	protected Double lpsgbw=null;
	protected Integer lpsgnb=null;
	protected String lpsgft=null;
	protected String lpsgfc=null;
	protected Integer lppong=null;
	protected Integer lppoch=null;
	protected Double lppolf=null;
	protected String lppoft=null;
	protected String lppofc=null;

	public Languageparameter(String lplsid,Integer lpsgng,Integer lpsgch,Double lpsglf,Double lpsgcw,Double lpsgbw,Integer lpsgnb,String lpsgft,String lpsgfc,Integer lppong,Integer lppoch,Double lppolf,String lppoft,String lppofc)
	{	this.lplsid=lplsid;
		this.lpsgng=lpsgng;
		this.lpsgch=lpsgch;
		this.lpsglf=lpsglf;
		this.lpsgcw=lpsgcw;
		this.lpsgbw=lpsgbw;
		this.lpsgnb=lpsgnb;
		this.lpsgft=lpsgft;
		this.lpsgfc=lpsgfc;
		this.lppong=lppong;
		this.lppoch=lppoch;
		this.lppolf=lppolf;
		this.lppoft=lppoft;
		this.lppofc=lppofc;
	}
	public Languageparameter()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Languageparameter clone()
	{
		return new Languageparameter(lplsid,lpsgng,lpsgch,lpsglf,lpsgcw,lpsgbw,lpsgnb,lpsgft,lpsgfc,lppong,lppoch,lppolf,lppoft,lppofc);
	}
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getLplsid()
	{	return lplsid;
	}
	public Languageparameter setLplsid(String lplsid)
	{	this.lplsid=lplsid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getLpsgng()
	{	return lpsgng;
	}
	public Languageparameter setLpsgng(Integer lpsgng)
	{	this.lpsgng=lpsgng;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getLpsgch()
	{	return lpsgch;
	}
	public Languageparameter setLpsgch(Integer lpsgch)
	{	this.lpsgch=lpsgch;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Double getLpsglf()
	{	return lpsglf;
	}
	public Languageparameter setLpsglf(Double lpsglf)
	{	this.lpsglf=lpsglf;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Double getLpsgcw()
	{	return lpsgcw;
	}
	public Languageparameter setLpsgcw(Double lpsgcw)
	{	this.lpsgcw=lpsgcw;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Double getLpsgbw()
	{	return lpsgbw;
	}
	public Languageparameter setLpsgbw(Double lpsgbw)
	{	this.lpsgbw=lpsgbw;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getLpsgnb()
	{	return lpsgnb;
	}
	public Languageparameter setLpsgnb(Integer lpsgnb)
	{	this.lpsgnb=lpsgnb;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getLpsgft()
	{	return lpsgft;
	}
	public Languageparameter setLpsgft(String lpsgft)
	{	this.lpsgft=lpsgft;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getLpsgfc()
	{	return lpsgfc;
	}
	public Languageparameter setLpsgfc(String lpsgfc)
	{	this.lpsgfc=lpsgfc;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getLppong()
	{	return lppong;
	}
	public Languageparameter setLppong(Integer lppong)
	{	this.lppong=lppong;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getLppoch()
	{	return lppoch;
	}
	public Languageparameter setLppoch(Integer lppoch)
	{	this.lppoch=lppoch;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Double getLppolf()
	{	return lppolf;
	}
	public Languageparameter setLppolf(Double lppolf)
	{	this.lppolf=lppolf;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getLppoft()
	{	return lppoft;
	}
	public Languageparameter setLppoft(String lppoft)
	{	this.lppoft=lppoft;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=32)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getLppofc()
	{	return lppofc;
	}
	public Languageparameter setLppofc(String lppofc)
	{	this.lppofc=lppofc;
		return this;
	}
}