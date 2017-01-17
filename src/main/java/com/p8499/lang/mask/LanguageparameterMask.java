package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class LanguageparameterMask implements Mask
{	protected boolean lplsid=false;
	protected boolean lpsgng=false;
	protected boolean lpsgch=false;
	protected boolean lpsglf=false;
	protected boolean lpsgcw=false;
	protected boolean lpsgbw=false;
	protected boolean lpsgnb=false;
	protected boolean lpsgft=false;
	protected boolean lpsgfc=false;
	protected boolean lppong=false;
	protected boolean lppoch=false;
	protected boolean lppolf=false;
	protected boolean lppoft=false;
	protected boolean lppofc=false;

	public LanguageparameterMask(boolean lplsid,boolean lpsgng,boolean lpsgch,boolean lpsglf,boolean lpsgcw,boolean lpsgbw,boolean lpsgnb,boolean lpsgft,boolean lpsgfc,boolean lppong,boolean lppoch,boolean lppolf,boolean lppoft,boolean lppofc)
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
	public LanguageparameterMask()
	{	
	}
	@Override
	public LanguageparameterMask all(boolean b)
	{	this.lplsid=b;
		this.lpsgng=b;
		this.lpsgch=b;
		this.lpsglf=b;
		this.lpsgcw=b;
		this.lpsgbw=b;
		this.lpsgnb=b;
		this.lpsgft=b;
		this.lpsgfc=b;
		this.lppong=b;
		this.lppoch=b;
		this.lppolf=b;
		this.lppoft=b;
		this.lppofc=b;
	return this;
	}
	public boolean getLplsid()
	{	return lplsid;
	}
	public LanguageparameterMask setLplsid(boolean lplsid)
	{	this.lplsid=lplsid;
		return this;
	}
	public boolean getLpsgng()
	{	return lpsgng;
	}
	public LanguageparameterMask setLpsgng(boolean lpsgng)
	{	this.lpsgng=lpsgng;
		return this;
	}
	public boolean getLpsgch()
	{	return lpsgch;
	}
	public LanguageparameterMask setLpsgch(boolean lpsgch)
	{	this.lpsgch=lpsgch;
		return this;
	}
	public boolean getLpsglf()
	{	return lpsglf;
	}
	public LanguageparameterMask setLpsglf(boolean lpsglf)
	{	this.lpsglf=lpsglf;
		return this;
	}
	public boolean getLpsgcw()
	{	return lpsgcw;
	}
	public LanguageparameterMask setLpsgcw(boolean lpsgcw)
	{	this.lpsgcw=lpsgcw;
		return this;
	}
	public boolean getLpsgbw()
	{	return lpsgbw;
	}
	public LanguageparameterMask setLpsgbw(boolean lpsgbw)
	{	this.lpsgbw=lpsgbw;
		return this;
	}
	public boolean getLpsgnb()
	{	return lpsgnb;
	}
	public LanguageparameterMask setLpsgnb(boolean lpsgnb)
	{	this.lpsgnb=lpsgnb;
		return this;
	}
	public boolean getLpsgft()
	{	return lpsgft;
	}
	public LanguageparameterMask setLpsgft(boolean lpsgft)
	{	this.lpsgft=lpsgft;
		return this;
	}
	public boolean getLpsgfc()
	{	return lpsgfc;
	}
	public LanguageparameterMask setLpsgfc(boolean lpsgfc)
	{	this.lpsgfc=lpsgfc;
		return this;
	}
	public boolean getLppong()
	{	return lppong;
	}
	public LanguageparameterMask setLppong(boolean lppong)
	{	this.lppong=lppong;
		return this;
	}
	public boolean getLppoch()
	{	return lppoch;
	}
	public LanguageparameterMask setLppoch(boolean lppoch)
	{	this.lppoch=lppoch;
		return this;
	}
	public boolean getLppolf()
	{	return lppolf;
	}
	public LanguageparameterMask setLppolf(boolean lppolf)
	{	this.lppolf=lppolf;
		return this;
	}
	public boolean getLppoft()
	{	return lppoft;
	}
	public LanguageparameterMask setLppoft(boolean lppoft)
	{	this.lppoft=lppoft;
		return this;
	}
	public boolean getLppofc()
	{	return lppofc;
	}
	public LanguageparameterMask setLppofc(boolean lppofc)
	{	this.lppofc=lppofc;
		return this;
	}
}