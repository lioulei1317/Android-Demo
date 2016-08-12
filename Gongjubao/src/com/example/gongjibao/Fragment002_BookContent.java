package com.example.gongjibao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fragment002_BookContent {
	// 定义一个内部类，作为系统的业务对象
	public static class Book {
		public Integer id;
		public String title;
		public String desc;

		public Book(Integer id, String title, String desc) {
			this.id = id;
			this.title = title;
			this.desc = desc;
		}

		@Override
		public String toString() {

			return title;
		}
	}
	// 使用List集合记录系统所包含的Book对象
	public static List<Book> ITEMS = new ArrayList<Fragment002_BookContent.Book>();
	// 使用Map集合记录系统所包含的Book对象
	public static Map<Integer, Book> ITEM_MAP = new HashMap<Integer, Fragment002_BookContent.Book>();

	private static void additem(Book book) {
		ITEMS.add(book);
		ITEM_MAP.put(book.id, book);

	}

	static {
		// 使用静态初始化代码，将Book对象添加到List集合、Map集合中
		additem(new Book(
				1,
				"三国演义",
				"介绍：综合民间传说和戏曲、话本，结合陈寿的《三国志》、范晔《后汉书》、元代《三国志平话》、和裴松之注的史料，以及作者个人对社会人生的体悟写成。现所见刊本以明嘉靖本最早，分24卷，240则。清初毛宗岗父子又做了一些修改，并成为现在最常见的120回本。《三国演义》是中国第一部长篇章回体小说。"));
		additem(new Book(
				2,
				"水浒传",
				"介绍：《水浒传》是由作者在《宣和遗事》及相关话本、故事的基础上创作而成。全书以描写农民战争为主要题材，塑造了宋江、吴用、李逵、武松、林冲、鲁智深等梁山英雄，揭示了当时的社会矛盾。故事曲折、语言生动、人物性格鲜明，具有很高的艺术成就。"));
		additem(new Book(
				3,
				"红楼梦",
				"介绍：红楼梦写于十八世纪中叶的清乾隆时代，内容“大旨谈情”。隋唐以前中华文化受尚古尚朴潜规则影响，没有专门研究、开示、描画心性的作品传世，而佛教却特别重视研究、开示、描写心性，有很高成就，禅宗更以“不立文字、教外别传、直指人心、见性成佛”为立宗宗旨。隋唐政权能够成立与佛教在中国迅速发展密切相关，隋唐之后，佛教对社会风气、政治生态影响仍然举足轻重。红楼梦是中国唯一一部明确以“谈情”为主旨，并且取得极高成就的小说。红楼梦所谈的情与先天之性有关，与后天学力有关，与伪相反。《易·系辞上》：“圣人立象以尽意，设卦以尽情伪。”《左传·僖公二十八年》：“晋侯在外十九年矣……民之情伪，尽知之矣。”《后汉书·耿国传》：“夷狄情伪难知，不可许。”宋·陈亮《丙午秋答朱元晦秘书书》：“天下之情伪，岂一人之智虑所能尽防哉！”清·纪昀《阅微草堂笔记·姑妄听之三》：“儒家释家，情伪日增，门径各别，可勿与辩也。”红楼梦的分旨是：类似于史记功能的《石头记》；类似于世情小说的《风月宝鉴》；类似于传奇的《金陵十二钗》；类似于见闻录的《情僧录》。多个旨意融于一书，一击两鸣，一笔多用，一言两味或者多味，在红楼梦里比比皆是。难度极高，成就极高、极大。"));
		additem(new Book(
				4,
				"西游记",
				"介绍：西游记以民间传说的唐僧取经的故事和有关话本及杂剧（元末明初杨讷作）基础上创作而成。西游记前七回叙述孙悟空出世，有大闹天宫等故事。此后写孙悟空随唐僧西天取经，沿途除妖降魔、战胜困难的故事。书中唐僧、孙悟空、猪八戒、沙僧等形象刻画生动，规模宏大，结构完整，是中国古典小说中伟大的浪漫主义文学作品。"));

	}
}
