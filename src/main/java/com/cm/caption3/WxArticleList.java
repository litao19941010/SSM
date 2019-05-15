package com.cm.caption3;

import java.util.List;

/**
 * @author litao
 * 文章数组类，用于生成json数据
 */
public class WxArticleList {
    private List<WxMpArticle>  articles;
    public WxArticleList(){}

    public WxArticleList(List<WxMpArticle> articles) {
        super();
        this.articles = articles;
    }

    public List<WxMpArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<WxMpArticle> articles) {
        this.articles = articles;
    }
}
