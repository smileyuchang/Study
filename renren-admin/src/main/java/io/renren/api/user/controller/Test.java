package io.renren.api.user.controller;

import cn.hutool.core.lang.Filter;
import cn.hutool.dfa.StopChar;
import cn.hutool.dfa.WordTree;

import java.util.List;

/**
 * @auth kolt.yu
 * @时间： 2023/2/7 3:06 PM
 */
public class Test {

    //关键词过滤
    public static void main(String[] args) {
        WordTree tree = new WordTree();
        tree.addWord("彩票");
        tree.addWord("发票");

        Filter<Character> charFilter = StopChar::isNotStopChar;
        tree.setCharFilter(charFilter);

        //正文
        String text = "我有一颗大土豆，刚出锅的彩.票发票";

        System.out.println(tree.isMatch(text));
        List<String> matchAll = tree.matchAll(text, -1, false, false);

        System.out.println(matchAll);
    }

}
