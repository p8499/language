package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class UserroleMask implements Mask
{	protected boolean urid=false;
	protected boolean urusid=false;
	protected boolean urrlid=false;

	public UserroleMask(boolean urid,boolean urusid,boolean urrlid)
	{	this.urid=urid;
		this.urusid=urusid;
		this.urrlid=urrlid;
	}
	public UserroleMask()
	{	
	}
	public boolean getUrid()
	{	return urid;
	}
	public UserroleMask setUrid(boolean urid)
	{	this.urid=urid;
		return this;
	}
	public boolean getUrusid()
	{	return urusid;
	}
	public UserroleMask setUrusid(boolean urusid)
	{	this.urusid=urusid;
		return this;
	}
	public boolean getUrrlid()
	{	return urrlid;
	}
	public UserroleMask setUrrlid(boolean urrlid)
	{	this.urrlid=urrlid;
		return this;
	}
}