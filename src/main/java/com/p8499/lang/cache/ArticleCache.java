package com.p8499.lang.cache;

import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;

import com.p8499.lang.bean.Article;
import com.p8499.lang.bean.Category;
import com.p8499.lang.bean.Segment;
import com.p8499.lang.bean.Segmentflow;
import com.p8499.lang.bean.Sentence;
import com.p8499.lang.listener.ArticleListener;
import com.p8499.lang.listener.CategoryListener;
import com.p8499.lang.listener.SegmentListener;
import com.p8499.lang.listener.SegmentflowListener;
import com.p8499.lang.listener.SentenceListener;
import com.p8499.lang.mapper.ArticleMapper;
import com.p8499.lang.mapper.CategoryMapper;
import com.p8499.lang.mapper.SegmentMapper;
import com.p8499.lang.mapper.SegmentflowMapper;
import com.p8499.lang.mapper.SentenceMapper;
import com.p8499.lang.mask.ArticleMask;
import com.p8499.lang.mask.CategoryMask;
import com.p8499.lang.mask.SegmentMask;
import com.p8499.lang.mask.SegmentflowMask;
import com.p8499.mvc.FilterConditionExpr;
import com.p8499.mvc.FilterExpr;
import com.p8499.mvc.FilterLogicExpr;
import com.p8499.mvc.FilterOperandExpr;
import com.p8499.mvc.database.Bean;
import com.p8499.mvc.database.BeanListener;
import com.p8499.mvc.database.Cache;
import com.p8499.mvc.database.Mask;

public class ArticleCache extends Cache
{	@Resource(name="categoryMapper")
	protected CategoryMapper categoryMapper;
	@Resource(name="articleMapper")
	protected ArticleMapper articleMapper;
	@Resource(name="sentenceMapper")
	protected SentenceMapper sentenceMapper;
	@Resource(name="segmentMapper")
	protected SegmentMapper segmentMapper;
	@Resource(name="segmentflowMapper")
	protected SegmentflowMapper segmentflowMapper;
	
	@Resource(name="categoryListener")
	protected CategoryListener categoryListener;
	@Resource(name="articleListener")
	protected ArticleListener articleListener;
	@Resource(name="sentenceListener")
	protected SentenceListener sentenceListener;
	@Resource(name="segmentListener")
	protected SegmentListener segmentListener;
	@Resource(name="segmentflowListener")
	protected SegmentflowListener segmentflowListener;
	
	public ArticleCache()
	{	categoryListener.register(BeanListener.ACTION_AFTERUPDATE,this);
		articleListener.register(BeanListener.ACTION_AFTERADD,this);
		sentenceListener.register(BeanListener.ACTION_AFTERADD,this);
		sentenceListener.register(BeanListener.ACTION_AFTERDELETE,this);
		segmentListener.register(BeanListener.ACTION_AFTERADD,this);
		segmentListener.register(BeanListener.ACTION_AFTERUPDATE,this);
		segmentListener.register(BeanListener.ACTION_AFTERDELETE,this);
		segmentflowListener.register(BeanListener.ACTION_AFTERADD,this);
		segmentflowListener.register(BeanListener.ACTION_AFTERUPDATE,this);
		segmentflowListener.register(BeanListener.ACTION_AFTERDELETE,this);
	}
	
	@Override
	@Scheduled(fixedRate=2000)
	public void start()
	{	super.start();
	}
	
	@Override
	public void put(String name,Bean bean,int action)//for add
	{	if(bean instanceof Article)
		{	Article article=(Article)bean;
			put(filterArticle(article.getAtid()).toString(),Article.FIELD_ATCGNAME,Article.FIELD_ATCSA,Article.FIELD_ATCSB,Article.FIELD_ATCSC,Article.FIELD_ATCSD,Article.FIELD_ATCSE,Article.FIELD_ATCSF);
		}
		else if(bean instanceof Sentence)
		{	Sentence sentence=(Sentence)bean;
			put(filterArticle(sentence.getAsatid()).toString(),Article.FIELD_ATCSA,Article.FIELD_ATCSB,Article.FIELD_ATCSC,Article.FIELD_ATCSD,Article.FIELD_ATCSE,Article.FIELD_ATCSF);
		}
		else if(bean instanceof Segment)
		{	Segment segment=(Segment)bean;
			Sentence sentence=sentenceMapper.get(segment.getTrasid());
			put(filterArticle(sentence.getAsatid()).toString(),Article.FIELD_ATCSA,Article.FIELD_ATCSB,Article.FIELD_ATCSC,Article.FIELD_ATCSD,Article.FIELD_ATCSE,Article.FIELD_ATCSF);
		}
		else if(bean instanceof Segmentflow)
		{	Segmentflow segmentflow=(Segmentflow)bean;
			Sentence sentence=sentenceMapper.get(segmentflow.getTaasid());
			put(filterArticle(sentence.getAsatid()).toString(),Article.FIELD_ATCSA,Article.FIELD_ATCSB,Article.FIELD_ATCSC,Article.FIELD_ATCSD,Article.FIELD_ATCSE,Article.FIELD_ATCSF);
		}
	}
	@Override
	public void put(String name,Bean bean,int action,Mask mask)//for update
	{	if(bean instanceof Category)
		{	CategoryMask categoryMask=(CategoryMask)mask;
			if(categoryMask.getCgname())
			{	Category category=(Category)bean;
				put(filterArticle2(category.getCgid()).toString(),Article.FIELD_ATCGNAME);
			}
		}
		else if(bean instanceof Segment)
		{	SegmentMask segmentMask=(SegmentMask)mask;
			if(segmentMask.getTrasid()||segmentMask.getTrst())
			{	Segment segment=(Segment)bean;
				Sentence sentence=sentenceMapper.get(segment.getTrasid());
				put(filterArticle(sentence.getAsatid()).toString(),Article.FIELD_ATCSA,Article.FIELD_ATCSB,Article.FIELD_ATCSC,Article.FIELD_ATCSD,Article.FIELD_ATCSE,Article.FIELD_ATCSF);
			}
		}
		else if(bean instanceof Segmentflow)
		{	SegmentflowMask segmentflowMask=(SegmentflowMask)mask;
			if(segmentflowMask.getTaasid()||segmentflowMask.getTast())
			{	Segmentflow segment=(Segmentflow)bean;
				Sentence sentence=sentenceMapper.get(segment.getTaasid());
				put(filterArticle(sentence.getAsatid()).toString(),Article.FIELD_ATCSA,Article.FIELD_ATCSB,Article.FIELD_ATCSC,Article.FIELD_ATCSD,Article.FIELD_ATCSE,Article.FIELD_ATCSF);
			}
		}
	}
	@Override
	public void put(String name,String key,int action)//for delete
	{	if(name.equals(Sentence.NAME))
		{	Sentence sentence=sentenceMapper.get(Integer.valueOf(key));
			put(filterArticle(sentence.getAsatid()).toString(),Article.FIELD_ATCSA,Article.FIELD_ATCSB,Article.FIELD_ATCSC,Article.FIELD_ATCSD,Article.FIELD_ATCSE,Article.FIELD_ATCSF);
		}
		else if(name.equals(Segment.NAME))
		{	Segment segment=segmentMapper.get(Integer.valueOf(key));
			Sentence sentence=sentenceMapper.get(segment.getTrasid());
			put(filterArticle(sentence.getAsatid()).toString(),Article.FIELD_ATCSA,Article.FIELD_ATCSB,Article.FIELD_ATCSC,Article.FIELD_ATCSD,Article.FIELD_ATCSE,Article.FIELD_ATCSF);
		}
		else if(name.equals(Segmentflow.NAME))
		{	Segmentflow segment=segmentflowMapper.get(Integer.valueOf(key));
			Sentence sentence=sentenceMapper.get(segment.getTaasid());
			put(filterArticle(sentence.getAsatid()).toString(),Article.FIELD_ATCSA,Article.FIELD_ATCSB,Article.FIELD_ATCSC,Article.FIELD_ATCSD,Article.FIELD_ATCSE,Article.FIELD_ATCSF);
		}
	}
	
	@Override
	protected void run(String filter,Set<String> fieldSet)
	{	List<Article> articleList=articleMapper.queryWithMask(filter,null,0,Long.MAX_VALUE,new ArticleMask().setAtid(true).setAtcgid(true));
		for(Article article:articleList)
		{	ArticleMask articleMask=new ArticleMask();
			if(fieldSet.contains(Article.FIELD_ATCGNAME))
			{	article.setAtcgname(calcAtcgname(article.getAtcgid()));
				articleMask.setAtcgname(true);
			}
			if(fieldSet.contains(Article.FIELD_ATCSA))
			{	article.setAtcsa(calcAtcsa(article.getAtid()));
				articleMask.setAtcsa(true);
			}
			if(fieldSet.contains(Article.FIELD_ATCSB))
			{	article.setAtcsb(calcAtcsb(article.getAtid()));
				articleMask.setAtcsb(true);
			}
			if(fieldSet.contains(Article.FIELD_ATCSC))
			{	article.setAtcsc(calcAtcsc(article.getAtid()));
				articleMask.setAtcsc(true);
			}
			if(fieldSet.contains(Article.FIELD_ATCSD))
			{	article.setAtcsd(calcAtcsd(article.getAtid()));
				articleMask.setAtcsd(true);
			}
			if(fieldSet.contains(Article.FIELD_ATCSE))
			{	article.setAtcse(calcAtcse(article.getAtid()));
				articleMask.setAtcse(true);
			}
			if(fieldSet.contains(Article.FIELD_ATCSF))
			{	article.setAtcsf(calcAtcsf(article.getAtid()));
				articleMask.setAtcsf(true);
			}
			articleMapper.updateWithMask(article,articleMask);
		}
	}
	
	/* count Sentence(no Segment,no active Segmentflow)
	 */
	private Integer calcAtcsa(Integer atid)
	{	return (int)sentenceMapper.count(filterSentence(atid,false,null,false,null).toString());
	}
	/* count Sentence(no Segment,has active Segmentflow)
	 */
	private Integer calcAtcsb(Integer atid)
	{	return (int)sentenceMapper.count(filterSentence(atid,false,null,true,Segmentflow.TAST_VOTING).toString());
	}
	/* count Sentence(has inactive Segment,no active Segmentflow)
	 */
	private Integer calcAtcsd(Integer atid)
	{	return (int)sentenceMapper.count(filterSentence(atid,true,Segment.TRST_DISABLED,false,null).toString());
	}
	/* count Sentence(has inactive Segment,has active Segmentflow)
	 */
	private Integer calcAtcsc(Integer atid)
	{	return (int)sentenceMapper.count(filterSentence(atid,true,Segment.TRST_DISABLED,true,Segmentflow.TAST_VOTING).toString());
	}
	/* count Sentence(has active Segment,no active Segmentflow)
	 */
	private Integer calcAtcse(Integer atid)
	{	return (int)sentenceMapper.count(filterSentence(atid,true,Segment.TRST_ENABLED,false,null).toString());
	}
	/* count Sentence(has active Segment,has active Segmentflow)
	 */
	private Integer calcAtcsf(Integer atid)
	{	return (int)sentenceMapper.count(filterSentence(atid,true,Segment.TRST_ENABLED,true,Segmentflow.TAST_VOTING).toString());
	}
	private String calcAtcgname(Integer cgid)
	{	return categoryMapper.getWithMask(cgid,new CategoryMask().setCgname(true)).getCgname();
	}
	/* Article where atid=?
	 */
	private FilterExpr filterArticle(Integer atid)
	{	FilterOperandExpr operand11=new FilterOperandExpr(Article.FIELD_ATID,FilterOperandExpr.OP_STRING,true);
		FilterOperandExpr operand12=new FilterOperandExpr(atid.toString(),FilterOperandExpr.OP_NUMBER,false);
		FilterConditionExpr condition1=new FilterConditionExpr(FilterConditionExpr.OP_EQUAL,operand11,operand12);
		FilterLogicExpr logic=new FilterLogicExpr(FilterLogicExpr.OP_AND,condition1);
		return logic;
	}
	/* Article where atcgid=?
	 */
	private FilterExpr filterArticle2(Integer atcgid)
	{	FilterOperandExpr operand11=new FilterOperandExpr(Article.FIELD_ATCGID,FilterOperandExpr.OP_STRING,true);
		FilterOperandExpr operand12=new FilterOperandExpr(atcgid.toString(),FilterOperandExpr.OP_NUMBER,false);
		FilterConditionExpr condition1=new FilterConditionExpr(FilterConditionExpr.OP_EQUAL,operand11,operand12);
		FilterLogicExpr logic=new FilterLogicExpr(FilterLogicExpr.OP_AND,condition1);
		return logic;
	}
	/* Sentence where (exists/not exists) Segment and (exists/not exists) Segmentflow
	 */
	private FilterExpr filterSentence(Integer asatid,boolean segmentYN,Integer segmentTRST,boolean segmentflowYN,Integer segmentflowTAST)
	{	FilterOperandExpr operand11=new FilterOperandExpr(Sentence.FIELD_ASATID,FilterOperandExpr.OP_STRING,true);
		FilterOperandExpr operand12=new FilterOperandExpr(String.valueOf(asatid),FilterOperandExpr.OP_NUMBER,false);
		FilterConditionExpr condition1=new FilterConditionExpr(FilterConditionExpr.OP_EQUAL,operand11,operand12);
		FilterLogicExpr logic=new FilterLogicExpr(FilterLogicExpr.OP_AND,condition1,filterSegment(segmentYN,segmentTRST),filterSegmentflow(segmentflowYN,segmentflowTAST));
		return logic;
	}
	/* (exists/not exists) Segment where trasid=asid and trst=?
	 */
	private FilterExpr filterSegment(boolean exists,Integer trst)
	{	FilterOperandExpr operand11=new FilterOperandExpr(Segment.FIELD_TRASID,FilterOperandExpr.OP_STRING,true);
		FilterOperandExpr operand12=new FilterOperandExpr(Sentence.FIELD_ASID,FilterOperandExpr.OP_STRING,true);
		FilterConditionExpr condition1=new FilterConditionExpr(FilterConditionExpr.OP_EQUAL,operand11,operand12);
		FilterLogicExpr logicF1130;
		if(trst!=null)
		{	FilterOperandExpr operand21=new FilterOperandExpr(Segment.FIELD_TRST,FilterOperandExpr.OP_STRING,true);
			FilterOperandExpr operand22=new FilterOperandExpr(String.valueOf(trst),FilterOperandExpr.OP_NUMBER,false);
			FilterConditionExpr condition2=new FilterConditionExpr(FilterConditionExpr.OP_EQUAL,operand21,operand22);
			logicF1130=new FilterLogicExpr(FilterLogicExpr.OP_AND,condition1,condition2);
		}
		else
		{	logicF1130=new FilterLogicExpr(FilterLogicExpr.OP_AND,condition1);
		}
		FilterOperandExpr operandF1130=new FilterOperandExpr(Segment.TABLE,FilterOperandExpr.OP_STRING,true);
		FilterConditionExpr condition=new FilterConditionExpr(FilterConditionExpr.OP_EXISTS,operandF1130,logicF1130);
		if(exists)
			return condition;
		else
			return new FilterLogicExpr(FilterLogicExpr.OP_NOT,condition);
	}
	/* (exists/not exists) Segmentflow where taasid=asid and tast=?
	 */
	private FilterExpr filterSegmentflow(boolean exists,Integer tast)
	{	FilterOperandExpr operand11=new FilterOperandExpr(Segmentflow.FIELD_TAASID,FilterOperandExpr.OP_STRING,true);
		FilterOperandExpr operand12=new FilterOperandExpr(Sentence.FIELD_ASID,FilterOperandExpr.OP_STRING,true);
		FilterConditionExpr condition1=new FilterConditionExpr(FilterConditionExpr.OP_EQUAL,operand11,operand12);
		FilterLogicExpr logicF1131;
		if(tast!=null)
		{	FilterOperandExpr operand21=new FilterOperandExpr(Segmentflow.FIELD_TAST,FilterOperandExpr.OP_STRING,true);
			FilterOperandExpr operand22=new FilterOperandExpr(String.valueOf(tast),FilterOperandExpr.OP_NUMBER,false);
			FilterConditionExpr condition2=new FilterConditionExpr(FilterConditionExpr.OP_EQUAL,operand21,operand22);
			logicF1131=new FilterLogicExpr(FilterLogicExpr.OP_AND,condition1,condition2);
		}
		else
		{	logicF1131=new FilterLogicExpr(FilterLogicExpr.OP_AND,condition1);
		}
		FilterOperandExpr operandF1131=new FilterOperandExpr(Segmentflow.TABLE,FilterOperandExpr.OP_STRING,true);
		FilterConditionExpr condition=new FilterConditionExpr(FilterConditionExpr.OP_EXISTS,operandF1131,logicF1131);
		if(exists)
			return condition;
		else
			return new FilterLogicExpr(FilterLogicExpr.OP_NOT,condition);
	}
}
