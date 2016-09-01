package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class RoleMask implements Mask
{	protected boolean rlid=false;
	protected boolean rlname=false;

	public RoleMask(boolean rlid,boolean rlname)
	{	this.rlid=rlid;
		this.rlname=rlname;
	}
	public RoleMask()
	{	
	}
	public boolean getRlid()
	{	return rlid;
	}
	public RoleMask setRlid(boolean rlid)
	{	this.rlid=rlid;
		return this;
	}
	public boolean getRlname()
	{	return rlname;
	}
	public RoleMask setRlname(boolean rlname)
	{	this.rlname=rlname;
		return this;
	}
}