package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class TaggingMask implements Mask
{	protected boolean tgasid=false;
	protected boolean tgcont=false;
	protected boolean tgst=false;
	protected boolean tgusid=false;
	protected boolean tgupdd=false;
	protected boolean tgupdt=false;

	public TaggingMask(boolean tgasid,boolean tgcont,boolean tgst,boolean tgusid,boolean tgupdd,boolean tgupdt)
	{	this.tgasid=tgasid;
		this.tgcont=tgcont;
		this.tgst=tgst;
		this.tgusid=tgusid;
		this.tgupdd=tgupdd;
		this.tgupdt=tgupdt;
	}
	public TaggingMask()
	{	
	}
	public boolean getTgasid()
	{	return tgasid;
	}
	public TaggingMask setTgasid(boolean tgasid)
	{	this.tgasid=tgasid;
		return this;
	}
	public boolean getTgcont()
	{	return tgcont;
	}
	public TaggingMask setTgcont(boolean tgcont)
	{	this.tgcont=tgcont;
		return this;
	}
	public boolean getTgst()
	{	return tgst;
	}
	public TaggingMask setTgst(boolean tgst)
	{	this.tgst=tgst;
		return this;
	}
	public boolean getTgusid()
	{	return tgusid;
	}
	public TaggingMask setTgusid(boolean tgusid)
	{	this.tgusid=tgusid;
		return this;
	}
	public boolean getTgupdd()
	{	return tgupdd;
	}
	public TaggingMask setTgupdd(boolean tgupdd)
	{	this.tgupdd=tgupdd;
		return this;
	}
	public boolean getTgupdt()
	{	return tgupdt;
	}
	public TaggingMask setTgupdt(boolean tgupdt)
	{	this.tgupdt=tgupdt;
		return this;
	}
}