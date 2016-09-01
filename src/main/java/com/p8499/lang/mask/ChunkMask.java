package com.p8499.lang.mask;

import com.p8499.mvc.database.Mask;

public class ChunkMask implements Mask
{	protected boolean cpid=false;
	protected boolean cplsid=false;
	protected boolean cpsi=false;
	protected boolean cptg=false;
	protected boolean cpft=false;
	protected boolean cpsort=false;

	public ChunkMask(boolean cpid,boolean cplsid,boolean cpsi,boolean cptg,boolean cpft,boolean cpsort)
	{	this.cpid=cpid;
		this.cplsid=cplsid;
		this.cpsi=cpsi;
		this.cptg=cptg;
		this.cpft=cpft;
		this.cpsort=cpsort;
	}
	public ChunkMask()
	{	
	}
	public boolean getCpid()
	{	return cpid;
	}
	public ChunkMask setCpid(boolean cpid)
	{	this.cpid=cpid;
		return this;
	}
	public boolean getCplsid()
	{	return cplsid;
	}
	public ChunkMask setCplsid(boolean cplsid)
	{	this.cplsid=cplsid;
		return this;
	}
	public boolean getCpsi()
	{	return cpsi;
	}
	public ChunkMask setCpsi(boolean cpsi)
	{	this.cpsi=cpsi;
		return this;
	}
	public boolean getCptg()
	{	return cptg;
	}
	public ChunkMask setCptg(boolean cptg)
	{	this.cptg=cptg;
		return this;
	}
	public boolean getCpft()
	{	return cpft;
	}
	public ChunkMask setCpft(boolean cpft)
	{	this.cpft=cpft;
		return this;
	}
	public boolean getCpsort()
	{	return cpsort;
	}
	public ChunkMask setCpsort(boolean cpsort)
	{	this.cpsort=cpsort;
		return this;
	}
}