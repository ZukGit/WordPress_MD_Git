import java.util.HashMap;

public class StateMechine {
	public int mStateStackTopIndex ;
	public StateInfo mStateStack[];
	public State mDestState;
	public int mTempStateStackCount;
	public HashMap<State, StateInfo> mStateInfo = new HashMap<State, StateInfo>();
	public StateInfo mTempStateStack[];
	public boolean mTransitionInProgress = false;

	
	
	public StateMechine() {
		// TODO Auto-generated constructor stub
		mTempStateStackCount = 0;
		mStateStackTopIndex = -1;
	}
	
	
	public void handleMessage(int msg) {
		State msgProcessedState = null;
		msgProcessedState = processMsg(msg); // 執行消息的處理  最终会执行到 状态类的ProcessMessage
		performTransitions(msgProcessedState, msg); // 執行狀態的切換
		showState(msg);
	}
	
	
	public void showState(int msg) {
		System.out.println("状态栈中存在"+(mStateStackTopIndex+1)+"个状态分别为");
		System.out.println("msg = "+ msg);
		int i = 0;
//		System.out.println("数组长度为:"+mStateStack.length);
//		if(mStateStack[0] == null){
//			System.out.println("fuck0");
//		}
//		
//		if(mStateStack[1] == null){
//			System.out.println("fuck0");
//		}
		
		while( (i <= mStateStackTopIndex) && (mStateStack[i] != null) ){
			mStateStack[i].getClass().getSimpleName();
			System.out.println(i+ "  "+mStateStack[i].state.getClass().getSimpleName()    + "      " +mStateStack[i].toString());
			i++;
		}

	}
/*总结: 
 * 1. processMsg 是消息处理的函数，在该函数内部，会依次遍历当前状态StateInfo mStateStack[]数组中
 * 的每一个状态State定义的 processMessage方法,依次从树顶State 遍历到 树根State，processMessage函数
 * 返回true，那么就说明 这个消息被这个状态处理了。processMsg 会返回processMessage 处理msg为true的那个树节点 
 * 1.1 消息的处理一般会引起状态State的切换，在ProcessMessage中会把 记录当前树顶状态的切换为需要切换到的
 * 目的状态  mDestState.
 *  processMsg 完成两件事   
 *  一:在当前状态栈mStateStack中通过
 *	函数while (!curStateInfo.state.processMessage(msg)) {curStateInfo = curStateInfo.parentStateInfo;}
 *  找到数组中处理msg 并返回为true的那个 StateInfo    【对应找到  State msgProcessedState】
 *   二: 在 处理msg 并返回为true的那个 StateInfo的ProcessMessage方法中 会触发状态的切换 既通过方法
 * mDestState = (State) destState; 找到对应的  【对应找到  State mDestState】
 * 
 * 
 * 
 * 2.performTransitions  是状态进行切换的操作函数，涉及到Map<State, StateInfo> 能通过这个Map拿到目标状态的栈结构,
 *  正式状态数组StateInfo mStateStack[] 及长度 mStateStackTopIndex
 * StateInfo mTempStateStack[] 临时保存需要入栈的状态的数组 及长度 mTempStateStackCount，
 * State mDestState  目标状态，  StateInfo.active 是否在正式数组 mStateStack内bool标记
 * 来完成切换状态时 从StateInfo mStateStack[]数组 执行退出exit，
 * 把新的StateInfo mTempStateStack[] 加入StateInfo mStateStack[] 执行 enter 方法的过程
 * */
	
	
	

	/*mStateStack 是一个结点的数组，这个数组里面装了当前StateInfo的结点对应关系
	 * 它的大小是通过计算最深层级关系得到的
	 * 
	 * 
	 * mStateStackTopIndex 是记录StateInfo的结点在数组中最大的位置
	 * 
	 * */
	private State processMsg(int msg) { 
		StateInfo curStateInfo = mStateStack[mStateStackTopIndex];
		System.out.println("processMsg: " + curStateInfo.state.getName());
		while (!curStateInfo.state.processMessage(msg)) {
			curStateInfo = curStateInfo.parentStateInfo;
			if (curStateInfo == null) {
				break;
			}
			System.out.println("processMsg: " + curStateInfo.state.getName());

		}
		return (curStateInfo != null) ? curStateInfo.state : null;
	}


	/*mStateStack 是一个结点的数组，这个数组里面装了当前StateInfo的结点对应关系
	 * 它的大小是通过计算最深层级关系得到的
	 * 
	 * 
	 * mStateStackTopIndex 是记录StateInfo的结点在数组中最大的位置
	 * 
	 * */
	private void performTransitions(State msgProcessedState, int msg) {
		State orgState = mStateStack[mStateStackTopIndex].state; // 就的状态栈栈顶
		State destState = mDestState; // 新的状态栈 需要的顶
		if (destState != null) {
			while (true) {
				// 執行之前狀態的 exit方法 執行新進入狀態的enter方法
				StateInfo commonStateInfo = setupTempStateStackWithStatesToEnter(destState);
				invokeExitMethods(commonStateInfo);
				int stateStackEnteringIndex = moveTempStateStackToStateStack();
				invokeEnterMethods(stateStackEnteringIndex);

				if (destState != mDestState) {
					destState = mDestState;
				} else {
					break;
				}
			}
		}
		mDestState = null;

	}

	
    public  void transitionTo(State destState) {
 
        	//System.out.println("transitionTo called while transition already in progress to " + mDestState + ", new target state=" + destState);

        mDestState = (State) destState;  // 就是把目标变量mDestState  变为当前的状态，后续执行enter方法
        //System.out.println("transitionTo: destState=" + mDestState.getName());
    }
    
    public  void completeConstruction(State state) {
        int maxDepth = 0;
        for (StateInfo si : mStateInfo.values()) { 
            int depth = 0;
            for (StateInfo i = si; i != null; depth++) {
                i = i.parentStateInfo;
            }
            if (maxDepth < depth) {
                maxDepth = depth;
            }
        }
        // 遍历 HashMap<State, StateInfo>(); 依次回溯父类 得到最多父类的就是最深的长度
        mStateStack = new StateInfo[maxDepth];
        mTempStateStack = new StateInfo[maxDepth];
        mStateStackTopIndex = 0;
        mStateInfo.get(state).active = true;
        mStateStack[mStateStackTopIndex] = mStateInfo.get(state);
    }
    
    
//临时数组  mTempStateStack[] 和正式数组mStateStack[]  之间逻辑处理 使得mStateStack[] 集合重新填充
	private final int moveTempStateStackToStateStack() {  
		// 从已经处理了离开的StateInfo数组 重新构建新的数组
		int startingIndex = mStateStackTopIndex + 1;  
		//临时StateInfo数组的长度   StateInfo StateInfo.parent StateInfo.parent.parent 
		int i = mTempStateStackCount - 1;  
		int j = startingIndex; 
		while (i >= 0) {
			//System.out.println("moveTempStackToStateStack: i=" + i + ",j=" + j);
			
		//把在临时StateInfo数组mTempStateStack中的StateInfo 依次加入到正式的记录StateInfo数组
	   //加入的顺序是 临时数组mTempStateStack的最后一个StateInfo加入到正式StateInfo数组顶部
	  //... 临时数组mTempStateStack的第一个StateInfo最终变为正式StateInfo数组的顶部
			mStateStack[j] = mTempStateStack[i];  
			j += 1;
			i -= 1;
		}

		mStateStackTopIndex = j - 1;  // 记录正式数组的位置

		//System.out.println("moveTempStackToStateStack: X mStateStackTop=" + mStateStackTopIndex + ",startingIndex="+ startingIndex + ",Top=" + mStateStack[mStateStackTopIndex].state.getName());

		return startingIndex;
	}

	// 对mStateStack 中的StateInfo数组 进行遍历  依次排除掉直到 当前状态是 commonStateInfo
	private final void invokeExitMethods(StateInfo commonStateInfo) {
		while ((mStateStackTopIndex >= 0) && (mStateStack[mStateStackTopIndex] != commonStateInfo)) {
			State curState = mStateStack[mStateStackTopIndex].state;
			System.out.println("invokeExitMethods: " + curState.getName());
			curState.exit();  // 执行从 mStateStack StateInfo 数组离开的状态State的exit方法
			mStateStack[mStateStackTopIndex].active = false;  // 记录当前StateInfo 不在栈中了
			mStateStackTopIndex -= 1;
		}
	}

	private final void invokeEnterMethods(int stateStackEnteringIndex) {
		// 在新构建的数组  mStateStack 以stateStackEnteringIndex起点 mStateStackTopIndex为终点
		// 执行这些状态的 enter() 方法
		for (int i = stateStackEnteringIndex; i <= mStateStackTopIndex; i++) {
			if (stateStackEnteringIndex == mStateStackTopIndex) {
				// Last enter state for transition
				mTransitionInProgress = false;
			}
			mStateStack[i].state.enter(); // 执行状态的 enter 方法
			mStateStack[i].active = true;   // 记录当前State是在 栈中了
		}
		mTransitionInProgress = false;
	}

	private StateInfo setupTempStateStackWithStatesToEnter(State destState) {

		mTempStateStackCount = 0;
		// 从HashMap获得目标状态destState 对应的结点 StateInfo
		StateInfo curStateInfo = mStateInfo.get(destState); 
		do {
			// 构建以destState为起始的  最父类在最后的 临时 StateInfo数组
			mTempStateStack[mTempStateStackCount++] = curStateInfo; 
			curStateInfo = curStateInfo.parentStateInfo;
			// 循环到父节点为空  或者当前的这个结点已经到栈中了  满足一个 退出循while环
		} while ((curStateInfo != null)  && (!curStateInfo.active));

		return curStateInfo;
	}


	private class StateInfo {
		State state;
		StateInfo parentStateInfo;

        /** True when the state has been entered and on the stack */
        boolean active;
        
		@Override
		public String toString() {
			return "state=" + state.getName() + ",parent="
					+ ((parentStateInfo == null) ? "null" : parentStateInfo.state.getName());
		}
	}

	
    public  StateInfo addState(State state, State parent) {  
        	//System.out.println("addStateInternal: E state=" + state.getName() + ",parent=" + ((parent == null) ? "" : parent.getName()));
     
        StateInfo parentStateInfo = null;
        if (parent != null) {
            parentStateInfo = mStateInfo.get(parent);
            if (parentStateInfo == null) {
                // Recursively add our parent as it's not been added yet.
                parentStateInfo = addState(parent, null);
            }
        }
        StateInfo stateInfo = mStateInfo.get(state);
        if (stateInfo == null) {
            stateInfo = new StateInfo();
            mStateInfo.put(state, stateInfo);  // 完成MAP的数据填充
        }

        // Validate that we aren't adding the same state in two different hierarchies.
        if ((stateInfo.parentStateInfo != null)
                && (stateInfo.parentStateInfo != parentStateInfo)) {
            throw new RuntimeException("state already added");
        }
        stateInfo.state = state;
        stateInfo.parentStateInfo = parentStateInfo;  // 完成父子关系的方法
        stateInfo.active = false;
        //System.out.println("addStateInternal: X stateInfo: " + stateInfo);
        return stateInfo;
    }
    

    private void removeState(State state) {   // 从Map移除
        StateInfo stateInfo = mStateInfo.get(state);
        if (stateInfo == null || stateInfo.active) {
            return;
        }
        boolean isParent = mStateInfo.values().stream()
                .filter(si -> si.parentStateInfo == stateInfo)
                .findAny()
                .isPresent();
        if (isParent) {
            return;
        }
        mStateInfo.remove(state);
    }
    
    private  State getCurrentState() {
        return mStateStack[mStateStackTopIndex].state;
    }
    
    
}
