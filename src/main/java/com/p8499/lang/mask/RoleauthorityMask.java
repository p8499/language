package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class RoleauthorityMask implements Mask
{	protected boolean raid=false;
	protected boolean rarlid=false;
	protected boolean raauid=false;

	public RoleauthorityMask(boolean raid,boolean rarlid,boolean raauid)
	{	this.raid=raid;
		this.rarlid=rarlid;
		this.raauid=raauid;
	}
	public RoleauthorityMask()
	{	
	}
	public boolean getRaid()
	{	return raid;
	}
	public RoleauthorityMask setRaid(boolean raid)
	{	this.raid=raid;
		return this;
	}
	public boolean getRarlid()
	{	return rarlid;
	}
	public RoleauthorityMask setRarlid(boolean rarlid)
	{	this.rarlid=rarlid;
		return this;
	}
	public boolean getRaauid()
	{	return raauid;
	}
	public RoleauthorityMask setRaauid(boolean raauid)
	{	this.raauid=raauid;
		return this;
	}
}