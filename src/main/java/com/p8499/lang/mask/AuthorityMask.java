package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class AuthorityMask implements Mask
{	protected boolean auid=false;
	protected boolean augrp=false;
	protected boolean auname=false;

	public AuthorityMask(boolean auid,boolean augrp,boolean auname)
	{	this.auid=auid;
		this.augrp=augrp;
		this.auname=auname;
	}
	public AuthorityMask()
	{	
	}
	public boolean getAuid()
	{	return auid;
	}
	public AuthorityMask setAuid(boolean auid)
	{	this.auid=auid;
		return this;
	}
	public boolean getAugrp()
	{	return augrp;
	}
	public AuthorityMask setAugrp(boolean augrp)
	{	this.augrp=augrp;
		return this;
	}
	public boolean getAuname()
	{	return auname;
	}
	public AuthorityMask setAuname(boolean auname)
	{	this.auname=auname;
		return this;
	}
}