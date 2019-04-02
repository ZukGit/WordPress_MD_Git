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
		msgProcessedState = processMsg(msg); // ������Ϣ��̎��  ���ջ�ִ�е� ״̬���ProcessMessage
		performTransitions(msgProcessedState, msg); // ���Р�B���ГQ
		showState(msg);
	}
	
	
	public void showState(int msg) {
		System.out.println("״̬ջ�д���"+(mStateStackTopIndex+1)+"��״̬�ֱ�Ϊ");
		System.out.println("msg = "+ msg);
		int i = 0;
//		System.out.println("���鳤��Ϊ:"+mStateStack.length);
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
/*�ܽ�: 
 * 1. processMsg ����Ϣ����ĺ������ڸú����ڲ��������α�����ǰ״̬StateInfo mStateStack[]������
 * ��ÿһ��״̬State����� processMessage����,���δ�����State ������ ����State��processMessage����
 * ����true����ô��˵�� �����Ϣ�����״̬�����ˡ�processMsg �᷵��processMessage ����msgΪtrue���Ǹ����ڵ� 
 * 1.1 ��Ϣ�Ĵ���һ�������״̬State���л�����ProcessMessage�л�� ��¼��ǰ����״̬���л�Ϊ��Ҫ�л�����
 * Ŀ��״̬  mDestState.
 *  processMsg ���������   
 *  һ:�ڵ�ǰ״̬ջmStateStack��ͨ��
 *	����while (!curStateInfo.state.processMessage(msg)) {curStateInfo = curStateInfo.parentStateInfo;}
 *  �ҵ������д���msg ������Ϊtrue���Ǹ� StateInfo    ����Ӧ�ҵ�  State msgProcessedState��
 *   ��: �� ����msg ������Ϊtrue���Ǹ� StateInfo��ProcessMessage������ �ᴥ��״̬���л� ��ͨ������
 * mDestState = (State) destState; �ҵ���Ӧ��  ����Ӧ�ҵ�  State mDestState��
 * 
 * 
 * 
 * 2.performTransitions  ��״̬�����л��Ĳ����������漰��Map<State, StateInfo> ��ͨ�����Map�õ�Ŀ��״̬��ջ�ṹ,
 *  ��ʽ״̬����StateInfo mStateStack[] ������ mStateStackTopIndex
 * StateInfo mTempStateStack[] ��ʱ������Ҫ��ջ��״̬������ ������ mTempStateStackCount��
 * State mDestState  Ŀ��״̬��  StateInfo.active �Ƿ�����ʽ���� mStateStack��bool���
 * ������л�״̬ʱ ��StateInfo mStateStack[]���� ִ���˳�exit��
 * ���µ�StateInfo mTempStateStack[] ����StateInfo mStateStack[] ִ�� enter �����Ĺ���
 * */
	
	
	

	/*mStateStack ��һ���������飬�����������װ�˵�ǰStateInfo�Ľ���Ӧ��ϵ
	 * ���Ĵ�С��ͨ����������㼶��ϵ�õ���
	 * 
	 * 
	 * mStateStackTopIndex �Ǽ�¼StateInfo�Ľ��������������λ��
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


	/*mStateStack ��һ���������飬�����������װ�˵�ǰStateInfo�Ľ���Ӧ��ϵ
	 * ���Ĵ�С��ͨ����������㼶��ϵ�õ���
	 * 
	 * 
	 * mStateStackTopIndex �Ǽ�¼StateInfo�Ľ��������������λ��
	 * 
	 * */
	private void performTransitions(State msgProcessedState, int msg) {
		State orgState = mStateStack[mStateStackTopIndex].state; // �͵�״̬ջջ��
		State destState = mDestState; // �µ�״̬ջ ��Ҫ�Ķ�
		if (destState != null) {
			while (true) {
				// ����֮ǰ��B�� exit���� �������M���B��enter����
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

        mDestState = (State) destState;  // ���ǰ�Ŀ�����mDestState  ��Ϊ��ǰ��״̬������ִ��enter����
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
        // ���� HashMap<State, StateInfo>(); ���λ��ݸ��� �õ���ุ��ľ�������ĳ���
        mStateStack = new StateInfo[maxDepth];
        mTempStateStack = new StateInfo[maxDepth];
        mStateStackTopIndex = 0;
        mStateInfo.get(state).active = true;
        mStateStack[mStateStackTopIndex] = mStateInfo.get(state);
    }
    
    
//��ʱ����  mTempStateStack[] ����ʽ����mStateStack[]  ֮���߼����� ʹ��mStateStack[] �����������
	private final int moveTempStateStackToStateStack() {  
		// ���Ѿ��������뿪��StateInfo���� ���¹����µ�����
		int startingIndex = mStateStackTopIndex + 1;  
		//��ʱStateInfo����ĳ���   StateInfo StateInfo.parent StateInfo.parent.parent 
		int i = mTempStateStackCount - 1;  
		int j = startingIndex; 
		while (i >= 0) {
			//System.out.println("moveTempStackToStateStack: i=" + i + ",j=" + j);
			
		//������ʱStateInfo����mTempStateStack�е�StateInfo ���μ��뵽��ʽ�ļ�¼StateInfo����
	   //�����˳���� ��ʱ����mTempStateStack�����һ��StateInfo���뵽��ʽStateInfo���鶥��
	  //... ��ʱ����mTempStateStack�ĵ�һ��StateInfo���ձ�Ϊ��ʽStateInfo����Ķ���
			mStateStack[j] = mTempStateStack[i];  
			j += 1;
			i -= 1;
		}

		mStateStackTopIndex = j - 1;  // ��¼��ʽ�����λ��

		//System.out.println("moveTempStackToStateStack: X mStateStackTop=" + mStateStackTopIndex + ",startingIndex="+ startingIndex + ",Top=" + mStateStack[mStateStackTopIndex].state.getName());

		return startingIndex;
	}

	// ��mStateStack �е�StateInfo���� ���б���  �����ų���ֱ�� ��ǰ״̬�� commonStateInfo
	private final void invokeExitMethods(StateInfo commonStateInfo) {
		while ((mStateStackTopIndex >= 0) && (mStateStack[mStateStackTopIndex] != commonStateInfo)) {
			State curState = mStateStack[mStateStackTopIndex].state;
			System.out.println("invokeExitMethods: " + curState.getName());
			curState.exit();  // ִ�д� mStateStack StateInfo �����뿪��״̬State��exit����
			mStateStack[mStateStackTopIndex].active = false;  // ��¼��ǰStateInfo ����ջ����
			mStateStackTopIndex -= 1;
		}
	}

	private final void invokeEnterMethods(int stateStackEnteringIndex) {
		// ���¹���������  mStateStack ��stateStackEnteringIndex��� mStateStackTopIndexΪ�յ�
		// ִ����Щ״̬�� enter() ����
		for (int i = stateStackEnteringIndex; i <= mStateStackTopIndex; i++) {
			if (stateStackEnteringIndex == mStateStackTopIndex) {
				// Last enter state for transition
				mTransitionInProgress = false;
			}
			mStateStack[i].state.enter(); // ִ��״̬�� enter ����
			mStateStack[i].active = true;   // ��¼��ǰState���� ջ����
		}
		mTransitionInProgress = false;
	}

	private StateInfo setupTempStateStackWithStatesToEnter(State destState) {

		mTempStateStackCount = 0;
		// ��HashMap���Ŀ��״̬destState ��Ӧ�Ľ�� StateInfo
		StateInfo curStateInfo = mStateInfo.get(destState); 
		do {
			// ������destStateΪ��ʼ��  ��������� ��ʱ StateInfo����
			mTempStateStack[mTempStateStackCount++] = curStateInfo; 
			curStateInfo = curStateInfo.parentStateInfo;
			// ѭ�������ڵ�Ϊ��  ���ߵ�ǰ���������Ѿ���ջ����  ����һ�� �˳�ѭwhile��
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
            mStateInfo.put(state, stateInfo);  // ���MAP���������
        }

        // Validate that we aren't adding the same state in two different hierarchies.
        if ((stateInfo.parentStateInfo != null)
                && (stateInfo.parentStateInfo != parentStateInfo)) {
            throw new RuntimeException("state already added");
        }
        stateInfo.state = state;
        stateInfo.parentStateInfo = parentStateInfo;  // ��ɸ��ӹ�ϵ�ķ���
        stateInfo.active = false;
        //System.out.println("addStateInternal: X stateInfo: " + stateInfo);
        return stateInfo;
    }
    

    private void removeState(State state) {   // ��Map�Ƴ�
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
