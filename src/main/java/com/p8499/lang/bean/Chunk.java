package com.p8499.lang.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.p8499.mvc.database.Add;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@JsonInclude((JsonInclude.Include.NON_NULL))
public class Chunk implements Bean
{	public static final String TABLE="public.F1020";
	public static final String VIEW="public.F1020";
	public static final String NAME="Chunk";
	public static final String FIELD_CPID="CPID";
	public static final String FIELD_CPLSID="CPLSID";
	public static final String FIELD_CPSI="CPSI";
	public static final String FIELD_CPTG="CPTG";
	public static final String FIELD_CPFT="CPFT";
	public static final String FIELD_CPSORT="CPSORT";
	protected Integer cpid=null;
	protected String cplsid=null;
	protected String cpsi=null;
	protected String cptg=null;
	protected String cpft=null;
	protected Integer cpsort=null;

	public Chunk(Integer cpid,String cplsid,String cpsi,String cptg,String cpft,Integer cpsort)
	{	this.cpid=cpid;
		this.cplsid=cplsid;
		this.cpsi=cpsi;
		this.cptg=cptg;
		this.cpft=cpft;
		this.cpsort=cpsort;
	}
	public Chunk()
	{	
	}
	@Override
	public String name()
	{	return NAME;
	}
	@Override
	public Chunk clone()
	{
		return new Chunk(cpid,cplsid,cpsi,cptg,cpft,cpsort);
	}
	@Null(groups={Add.class})
	@NotNull(groups={Update.class})
	public Integer getCpid()
	{	return cpid;
	}
	public Chunk setCpid(Integer cpid)
	{	this.cpid=cpid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getCplsid()
	{	return cplsid;
	}
	public Chunk setCplsid(String cplsid)
	{	this.cplsid=cplsid;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getCpsi()
	{	return cpsi;
	}
	public Chunk setCpsi(String cpsi)
	{	this.cpsi=cpsi;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getCptg()
	{	return cptg;
	}
	public Chunk setCptg(String cptg)
	{	this.cptg=cptg;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	@Size(max=16)
	//@SomeConstraint(groups={Add.class,Update.class})
	public String getCpft()
	{	return cpft;
	}
	public Chunk setCpft(String cpft)
	{	this.cpft=cpft;
		return this;
	}
	
	@NotNull(groups={Add.class,Update.class})
	//@SomeConstraint(groups={Add.class,Update.class})
	public Integer getCpsort()
	{	return cpsort;
	}
	public Chunk setCpsort(Integer cpsort)
	{	this.cpsort=cpsort;
		return this;
	}
}