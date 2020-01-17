package zbin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import zbin.G3_RedBlackTree.Entry;

public class G3_TreeJpanel extends JPanel {
	private static final long serialVersionUID = -8983985863229007323L;

	private static final int diameter = 20;
	private static final int distanceX = 11;
	private static final int distanceY = 30;

	private int width = 1000;
	private int height = 400;

	private Entry root = null;
	private List<String> process = null;
	private List<Graph> list = new ArrayList<>();
	private boolean  isDirectRight = true;
	private int  initCount = 0;

	public G3_TreeJpanel(Entry root, List<String> process,int initCount ,boolean isDirectRight) {
		setPreferredSize(new Dimension(width, height));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(null);
		this.root = root;
		this.process = process;
		this.isDirectRight = isDirectRight;
		this.initCount = initCount;
	}

	private int countOffset(int depth) {
		return (int) (distanceX * (depth));
	}

	public void depthCount(Entry node, Entry nodeP, boolean isLeft) {
		if (node == null) {
			return;
		}

		boolean is = isLeft;
		for (Entry n = node; n.parent != null; n = n.parent) {
			if (is != (n == n.parent.left)) {
				if (is) {
					n.parent.depthR = n.parent.depthR + 1;
				} else {
					n.parent.depthL = n.parent.depthL + 1;
				}
			} else {
			}
			is = n == n.parent.left;
		}
		depthCount(node.left, node, true);
		depthCount(node.right, node, false);
	}

	public void depthClear(Entry node) {
		if (node == null) {
			return;
		}
		node.depthL = (node.left != null ? 1 : 0);
		node.depthR = (node.right != null ? 1 : 0);

		depthClear(node.left);
		depthClear(node.right);
	}

	public void collectNode(Entry node, int px, int py, boolean isLeft) {
		int x, y, offset;
		if (node == root) {
//			x = width / 2 - diameter / 2;
//			y = 20;
			if(initCount > 51){
				if(isDirectRight){  // 顺着   中心往左
					x = width / 3  + 20;    // 353
				}else {  // 逆着   中心往右
					x = width - width/4 ;  // 820
				}

			}else{
				if(isDirectRight){  // 顺着   中心往左
					x = width / 3  + 20;    // 353
				}else {  // 逆着   中心往右
					x = width - width/3 ;  // 820
				}

			}

//			System.out.println("x = "+ x );
			y = 40;
		} else {
			y = py + distanceY;
			if (node == null) {
				return;
			} else {
				offset = countOffset(isLeft ? node.parent.depthL : node.parent.depthR);
				x = isLeft ? (px - offset) : (px + offset);
			}
		}

		int ax = x + diameter / 2;
		int ay = y + diameter / 2;
		int lax, lay, rax, ray;
		if (node.right != null) {
			rax = x + countOffset(node.depthR) + diameter / 2;
			ray = y + distanceY + diameter / 2;
			list.add(new Graph(0, Color.BLUE, ax, ay, rax, ray));
		}
		if (node.left != null) {
			lax = x - countOffset(node.depthL) + diameter / 2;
			lay = y + distanceY + diameter / 2;
			list.add(new Graph(0, Color.BLUE, ax, ay, lax, lay));
		}

		list.add(new Graph(1, node.color ? Color.BLACK : Color.RED, x, y, diameter, diameter));



		// 写字
		list.add(new Graph(3, Color.BLACK, x, y, 0, 0, String.valueOf(node.key), null));

/*

		String str1 ="1）每个结点要么是红的，要么是黑的";
		list.add(new Graph(3, Color.BLACK, x, y+20, 0, 0, str1, null));
		String str2 ="2）根结点是黑的";
		list.add(new Graph(3, Color.BLACK, x, y+40, 0, 0, str2, null));
		String str3 ="3）每个叶结点，即空结点（NIL）是黑的";
		list.add(new Graph(3, Color.BLACK, x, y+60, 0, 0, str3, null));
		String str4 ="4）如果一个结点是红的，那么它的俩个儿子都是黑的";
		list.add(new Graph(3, Color.BLACK, x, y+80, 0, 0, str4, null));
		String str5 ="5）对每个结点，从该结点到其子孙结点的所有路径上包含相同数目的黑结点";
		list.add(new Graph(3, Color.BLACK, x, y+100, 0, 0, str5, null));

*/



		collectNode(node.left, x, y, true);
		collectNode(node.right, x, y, false);
	}

	private void collectProcess() {
		String str = process.get(0);
		Color color = null;
		Font font = new Font("宋体", Font.PLAIN, 20);
		int y = 20;
		if (str.startsWith("put")) {
			color = Color.BLACK;
		} else {
			color = Color.BLACK;
		}

if( initCount < 51) {  // 逆向的  并且 数量大于 80 的情况下
	System.out.println("initCount = "+ initCount);
	String str1 ="1）每个结点要么是红的，要么是黑的";
	String str2 ="2）根结点是黑的   NIL结点是黑的  所有的尾叶结点(可见)可以是红色也可以是黑色";
	String str3 ="3）(所有叶子结点指向 虚拟的 不可见 黑色的 NIL结点 空结点)实际上NIL只有一个(不可见)";
	String str4 ="4）如果一个结点是红的(非可见尾叶结点,尾叶结点的子节点是虚拟黑色的NIL结点)，那么它的俩个儿子都是黑的";
	String str5 ="5）对每个结点，从该结点到其子孙结点的所有路径上包含相同数目的黑结点";
	process.add(str1);
	process.add(str2);
	process.add(str3);
	process.add(str4);
	process.add(str5);
}


		for (String s : process) {
			if(s.contains("step")){
				list.add(new Graph(10, color, 10, y, 0, 0, s, font));
			}else{

				list.add(new Graph(10, color, 10, y+270, 0, 0, s, font));
			}

			y = y + 20;
		}


	}

	private void drawNode(Graphics2D graphics2D) {
		list.sort((o1, o2) -> o1.type - o2.type);

		list.forEach((g) -> {
			graphics2D.setColor(g.color);
			graphics2D.setFont(g.font);
			if (g.type == 0) {
				graphics2D.drawLine(g.x1, g.y1, g.x2, g.y2);
			}
			if (g.type == 1) {
				graphics2D.fillArc(g.x1, g.y1, g.x2, g.y2, 0, 360);
			}
			if (g.type == 3) {
				graphics2D.drawString(g.value, g.x1, g.y1);
			}
			if (g.type == 10) {
				graphics2D.drawString(g.value, g.x1, g.y1);
			}
		});
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		Graphics2D graphics2D = (Graphics2D) graphics.create();
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (list.isEmpty()) {
			depthClear(root);
			depthCount(root, root, true);
			collectNode(root, 0, 0, false);
			collectProcess();
		}
		drawNode(graphics2D);
	}

	class Graph {
		int type;// 0 - 直线; 1 - 实心圆； 3 - 写字 ； 10 - 过程描述
		Color color;
		int x1;
		int y1;
		int x2;
		int y2;
		String value;
		Font font;

		public Graph(int type, Color color, int x1, int y1, int x2, int y2) {
			super();
			this.type = type;
			this.color = color;
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		public Graph(int type, Color color, int x1, int y1, int x2, int y2, String value, Font font) {
			super();
			this.type = type;
			this.color = color;
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.value = value;
			this.font = font;
		}
	}
}
