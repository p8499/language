package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Chunkcriteria implements Bean
{	public static final String TABLE="public.F1021";
	public static final String NAME="Chunkcriteria";
	public static final String FIELD_CCID="CCID";
	public static final String FIELD_CCCPID="CCCPID";
	public static final String FIELD_CCSI="CCSI";
	public static final String FIELD_CCTK="CCTK";
	public static final String FIELD_CCTG="CCTG";
	protected Integer ccid=null;
	protected Integer cccpid=null;
	protected Integer ccsi=null;
	protected String cctk=null;
	protected String cctg=null;

	public Chunkcriteria(Integer ccid,Integer cccpid,Integer ccsi,String cctk,String cctg)
	{	this.ccid=ccid;
		this.cccpid=cccpid;
		this.ccsi=ccsi;
		this.cctk=cctk;
		this.cctg=cctg;
	}
	public Chunkcriteria()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Chunkcriteria clone()
	{
		return new Chunkcriteria(ccid,cccpid,ccsi,cctk,cctg);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getCcid()
	{	return ccid;
	}
	public Chunkcriteria setCcid(Integer ccid)
	{	this.ccid=ccid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getCccpid()
	{	return cccpid;
	}
	public Chunkcriteria setCccpid(Integer cccpid)
	{	this.cccpid=cccpid;
		return this;
	}
	
	@NotNull(groups={Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getCcsi()
	{	return ccsi;
	}
	public Chunkcriteria setCcsi(Integer ccsi)
	{	this.ccsi=ccsi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getCctk()
	{	return cctk;
	}
	public Chunkcriteria setCctk(String cctk)
	{	this.cctk=cctk;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getCctg()
	{	return cctg;
	}
	public Chunkcriteria setCctg(String cctg)
	{	this.cctg=cctg;
		return this;
	}
}