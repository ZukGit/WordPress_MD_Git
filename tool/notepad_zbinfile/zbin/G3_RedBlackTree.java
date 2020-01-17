package zbin;

import java.util.ArrayList;
import java.util.List;

public class G3_RedBlackTree {
	public static final boolean RED = false;
	public static final boolean BLACK = true;
	public Entry root;
	public List<String> process;
	public int stepIndex = 0;

	public int size = 0;
	static boolean isInit = true;

	G3_RedBlackTree(){

	}



	ArrayList<Integer>  initIntList;
	boolean isContainRange = false;
	boolean isDirectRight = true;  // true 正的 往右走     false--负的走 左走

	G3_RedBlackTree(ArrayList<String> inputParams){
		initIntList = new 	ArrayList<Integer>();

		int mBeginIndex = 0;
		int mEndIndex = 0;
		System.out.println("inputParams = "+ inputParams.size());
		for (int i = 0; i < inputParams.size(); i++) {
//			System.out.println("InitA  input ["+i+"] = " + inputParams.get(i));
           String param = inputParams.get(i);
           if(param.contains("_")){
           	String[] range = param.split("_");
           	if(isNumeric(range[0]) && isNumeric(range[1]));
           	   int value0 = Integer.parseInt(range[0]);
			   int value1 = Integer.parseInt(range[1]);
               if(value0 < 0 || value1 < 0){
				   System.out.println("用户输入的范围包含负数  当前范围无效:"+ value0+"_"+value1);
				   continue;
			   }
			   isContainRange = true;
			   if(value0 > value1){    // 倒着 输入
				   mEndIndex = value0;
				   mBeginIndex = value1;
				   isDirectRight = false;
				   for (int j = mEndIndex; j > mBeginIndex -1   ; j--) {
					   if(!initIntList.contains(j)){
						   initIntList.add(j);
					   }
				   }
			   }else{   // 顺着输入
				   mEndIndex = value1;
				   mBeginIndex = value0;
				   isDirectRight = true;
				   for (int j = mBeginIndex; j < mEndIndex + 1 ; j++) {
					   if(!initIntList.contains(j)){
						   initIntList.add(j);
					   }
				   }

			   }


		   }else if(isNumeric(inputParams.get(i))){
		   Integer curInt =  Integer.parseInt(inputParams.get(i));
		   if(!initIntList.contains(curInt)){
			   initIntList.add(curInt);
		   }

	   }
	 }

/*
		for (int i = 0; i < initIntList.size(); i++) {
			System.out.println("InitB  Put ["+i+"] = " + initIntList.get(i));
			put(initIntList.get(i));
		}
*/


 putWithIntList(initIntList);

}


	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0; ) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}


	public boolean remove(int key) {
		Entry p = getEntry(key);
		if (p == null)
			return false;
		process = new ArrayList<>();
//		process.add("remove:" + key);

			stepIndex++;


		process.add("step:"+stepIndex+ " -> remove:" + key);



		deleteEntry(p);
		return true;
	}

	private void deleteEntry(Entry p) {
		size--;
		if (p.left != null && p.right != null) {
			Entry s = successor(p);
			p.key = s.key;
			p = s;
		}

		Entry replacement = (p.left != null ? p.left : p.right);

		if (replacement != null) {
			replacement.parent = p.parent;
			if (p.parent == null)
				root = replacement;
			else if (p == p.parent.left)
				p.parent.left = replacement;
			else
				p.parent.right = replacement;

			p.left = p.right = p.parent = null;

			if (p.color == BLACK)
				fixAfterDeletion(replacement);
		} else if (p.parent == null) {
			root = null;
		} else {
			if (p.color == BLACK)
				fixAfterDeletion(p);

			if (p.parent != null) {
				if (p == p.parent.left)
					p.parent.left = null;
				else if (p == p.parent.right)
					p.parent.right = null;
				p.parent = null;
			}
		}
	}

	static Entry successor(Entry t) {
		if (t == null)
			return null;
		else if (t.right != null) {
			Entry p = t.right;
			while (p.left != null)
				p = p.left;
			return p;
		} else {
			Entry p = t.parent;
			Entry ch = t;
			while (p != null && ch == p.right) {
				ch = p;
				p = p.parent;
			}
			return p;
		}
	}

	final Entry getEntry(int key) {
		Entry p = root;
		while (p != null) {
			int cmp = key - p.key;
			if (cmp < 0)
				p = p.left;
			else if (cmp > 0)
				p = p.right;
			else
				return p;
		}
		return null;
	}

	private void fixAfterDeletion(Entry x) {
		while (x != root && colorOf(x) == BLACK) {
			if (x == leftOf(parentOf(x))) {
				Entry sib = rightOf(parentOf(x));

				if (colorOf(sib) == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateLeft(parentOf(x));
					sib = rightOf(parentOf(x));
				}

				if (colorOf(leftOf(sib)) == BLACK && colorOf(rightOf(sib)) == BLACK) {
					setColor(sib, RED);
					x = parentOf(x);
				} else {
					if (colorOf(rightOf(sib)) == BLACK) {
						setColor(leftOf(sib), BLACK);
						setColor(sib, RED);
						rotateRight(sib);
						sib = rightOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(rightOf(sib), BLACK);
					rotateLeft(parentOf(x));
					x = root;
				}
			} else {
				Entry sib = leftOf(parentOf(x));

				if (colorOf(sib) == RED) {
					setColor(sib, BLACK);
					setColor(parentOf(x), RED);
					rotateRight(parentOf(x));
					sib = leftOf(parentOf(x));
				}

				if (colorOf(rightOf(sib)) == BLACK && colorOf(leftOf(sib)) == BLACK) {
					setColor(sib, RED);
					x = parentOf(x);
				} else {
					if (colorOf(leftOf(sib)) == BLACK) {
						setColor(rightOf(sib), BLACK);
						setColor(sib, RED);
						rotateLeft(sib);
						sib = leftOf(parentOf(x));
					}
					setColor(sib, colorOf(parentOf(x)));
					setColor(parentOf(x), BLACK);
					setColor(leftOf(sib), BLACK);
					rotateRight(parentOf(x));
					x = root;
				}
			}
		}

		setColor(x, BLACK);
	}


	public void putWithIntList( ArrayList<Integer> initIntList ) {
		if(initIntList == null || initIntList.size() == 0){
			//process.add("step:"+stepIndex+ " -> put: [ ]" );
			return;
		}

		for (int i = 0; i < initIntList.size(); i++) {
			int key = initIntList.get(i);

			Entry t = root;
			if (t == null) {
				root = new Entry(key, null);
				size = 1;
				process = new ArrayList<>();
			continue;
			}
			int cmp;
			Entry parent;

			do {
				parent = t;
				cmp = key - t.key;
				if (cmp < 0)
					t = t.left;
				else if (cmp > 0)
					t = t.right;
				else
					break;
			} while (t != null);

			Entry e = new Entry(key, parent);
			if (cmp < 0)
				parent.left = e;
			else
				parent.right = e;
			process = new ArrayList<>();


			fixAfterInsertion(e);
			size++;
			continue;

		}
		process.add("step:"+stepIndex+ " -> put:" + initIntList);


	}


	public boolean put(int key ) {

		Entry t = root;
		if (t == null) {
			root = new Entry(key, null);
			size = 1;
			process = new ArrayList<>();


				stepIndex++;

			process.add("step:"+stepIndex+ " -> put:" + key);
			return true;
		}
		int cmp;
		Entry parent;

		do {
			parent = t;
			cmp = key - t.key;
			if (cmp < 0)
				t = t.left;
			else if (cmp > 0)
				t = t.right;
			else
				return false;
		} while (t != null);

		Entry e = new Entry(key, parent);
		if (cmp < 0)
			parent.left = e;
		else
			parent.right = e;
		process = new ArrayList<>();


			stepIndex++;


		process.add("step:"+stepIndex+ " -> put:" + key);



		fixAfterInsertion(e);
		size++;


		return true;
	}

	private void fixAfterInsertion(Entry x) {
		x.color = RED;

		while (x != null && x != root && x.parent.color == RED) {
			if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
				Entry y = rightOf(parentOf(parentOf(x)));
				if (colorOf(y) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == rightOf(parentOf(x))) {
						x = parentOf(x);
						rotateLeft(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateRight(parentOf(parentOf(x)));
				}
			} else {
				Entry y = leftOf(parentOf(parentOf(x)));
				if (colorOf(y) == RED) {
					setColor(parentOf(x), BLACK);
					setColor(y, BLACK);
					setColor(parentOf(parentOf(x)), RED);
					x = parentOf(parentOf(x));
				} else {
					if (x == leftOf(parentOf(x))) {
						x = parentOf(x);
						rotateRight(x);
					}
					setColor(parentOf(x), BLACK);
					setColor(parentOf(parentOf(x)), RED);
					rotateLeft(parentOf(parentOf(x)));
				}
			}
		}
		root.color = BLACK;
	}

	private static boolean colorOf(Entry p) {
		return (p == null ? BLACK : p.color);
	}

	private static Entry parentOf(Entry p) {
		return (p == null ? null : p.parent);
	}

	private static void setColor(Entry p, boolean c) {
		if (p != null)
			p.color = c;
	}

	private static Entry leftOf(Entry p) {
		return (p == null) ? null : p.left;
	}

	private static Entry rightOf(Entry p) {
		return (p == null) ? null : p.right;
	}

	private void rotateLeft(Entry p) {
		if (p != null) {
			Entry r = p.right;
			p.right = r.left;
			if (r.left != null)
				r.left.parent = p;
			r.parent = p.parent;
			if (p.parent == null)
				root = r;
			else if (p.parent.left == p)
				p.parent.left = r;
			else
				p.parent.right = r;
			r.left = p;
			p.parent = r;
		}
	}

	private void rotateRight(Entry p) {
		if (p != null) {
			Entry l = p.left;
			p.left = l.right;
			if (l.right != null)
				l.right.parent = p;
			l.parent = p.parent;
			if (p.parent == null)
				root = l;
			else if (p.parent.right == p)
				p.parent.right = l;
			else
				p.parent.left = l;
			l.right = p;
			p.parent = l;
		}
	}

	static final class Entry {
		int depthR = 0;
		int depthL = 0;
		int key;
		Entry left;
		Entry right;
		Entry parent;
		boolean color = BLACK;

		Entry(int key, Entry parent) {
			this.key = key;
			this.parent = parent;
		}

		public int getKey() {
			return key;
		}
	}
}
