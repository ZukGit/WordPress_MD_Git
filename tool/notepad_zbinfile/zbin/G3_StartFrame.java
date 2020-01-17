package zbin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class G3_StartFrame {
    private static final String title = "Red_Black_Tree";
    private static final String putButtonDesc = "插入";
    private static final String removeButtonDesc = "删除";

/*
1）每个结点要么是红的，要么是黑的
2）根结点是黑的
3）每个叶结点，即空结点（NIL）是黑的
4）如果一个结点是红的，那么它的俩个儿子都是黑的
5）对每个结点，从该结点到其子孙结点的所有路径上包含相同数目的黑结点
*/

    static G3_RedBlackTree tree;

   static ArrayList<String> inputParams = new  ArrayList<String>();
    static boolean containDigital = false;
    static String curPath ;


    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println("[arg"+i+"] = " + args[i]);
            if(i == 0 && !isNumeric(args[i])){
                curPath = args[i];
            }else{
                String curInputStr = args[i];
                // 把  1_100 这样的数据能够传递到 redtree
                curInputStr = curInputStr.replace("_","");
                if(isNumeric(curInputStr)){
                    inputParams.add(args[i]);
                    containDigital = true;
                }
            }


        }

        EventQueue.invokeLater(() -> new G3_StartFrame().doDraw());

    }




    private void action(JTextField textField, JPanel board, G3_RedBlackTree tree, int action) {
        doAction(textField, board, tree, action);
        textField.requestFocus();
    }

    private void doAction(JTextField textField, JPanel board, G3_RedBlackTree tree, int action) {
        String value = textField.getText();
        if (value == null || value.isEmpty()) {
            return;
        }
        textField.setText("");
        switch (action) {
            case 0:
                if (!tree.put(Integer.valueOf(value))) {
                    return;
                }
                break;
            case 1:
                if (!tree.remove(Integer.valueOf(value)) || tree.size < 1) {
                    return;
                }
                break;
            default:
                break;
        }
        board.add(new G3_TreeJpanel(tree.root, tree.process,tree.initIntList.size(),tree.isDirectRight), 0);
        board.revalidate();
    }

    private void doDraw() {
        JPanel board = createBoard();
        JTextField textField = createTextField();
        JPanel buttonPanel = createButtonPanel(board, textField);
        JScrollPane scrollPane = createScrollPane(board);
        JPanel container = createContainer(scrollPane, buttonPanel);
        JMenuBar menubar = createMenuBar();

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menubar);
        frame.setSize(new Dimension(1100, 600));
        frame.setContentPane(container);
        frame.setVisible(true);

    }

    private JMenuBar createMenuBar() {
        JMenuBar menubar = new JMenuBar();

        JMenu setMenu = new JMenu("设置");
        JMenu helpMenu = new JMenu("帮助");
        JMenuItem aboutItem = new JMenuItem("关于");
        menubar.setFont(new Font("宋体", Font.PLAIN, 12));

        helpMenu.add(aboutItem);
        menubar.add(setMenu);
        menubar.add(helpMenu);
        return menubar;
    }

    private JPanel createContainer(JScrollPane scrollPane, JPanel buttonPanel) {
        JPanel container = new JPanel();
        container.setLayout(new GridBagLayout());
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 0;
        c3.weightx = 0;
        c3.weighty = 1.0;
        c3.fill = GridBagConstraints.HORIZONTAL;
        container.add(buttonPanel, c3);

        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 1;
        c1.gridy = 0;
        c1.weightx = 1.0;
        c1.weighty = 1.0;
        c1.fill = GridBagConstraints.BOTH;
        container.add(scrollPane, c1);
        container.setOpaque(false);
        return container;
    }

    private JScrollPane createScrollPane(JPanel board) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(board);
        scrollPane.getVerticalScrollBar().setUnitIncrement(15);
        return scrollPane;
    }

    private JPanel createButtonPanel(JPanel board, JTextField textField) {
        JButton putButton = new JButton(putButtonDesc);
        JButton removeButton = new JButton(removeButtonDesc);
         tree = new G3_RedBlackTree(inputParams);
if(containDigital){
    board.add(new G3_TreeJpanel(tree.root, tree.process,tree.initIntList.size(),tree.isDirectRight), 0);
    board.revalidate();
}


        putButton.addActionListener((e) -> action(textField, board, tree, 0));
        removeButton.addActionListener((e) -> action(textField, board, tree, 1));



        JPanel textFieldWrap = new JPanel();
        textFieldWrap.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        textFieldWrap.add(textField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(80, 500));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        buttonPanel.add(Box.createVerticalStrut(400));
        buttonPanel.add(textFieldWrap);
        buttonPanel.add(putButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(Box.createVerticalStrut(50));


        return buttonPanel;
    }

    private JPanel createBoard() {
        JPanel board = new JPanel();
        board.setLayout(new BoxLayout(board, BoxLayout.Y_AXIS));
        return board;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setColumns(4);
        textField.setDocument(new PlainDocument() {
            private static final long serialVersionUID = 1L;

            public void insertString(int offset, String str, AttributeSet attrSet) throws BadLocationException {
                if (str == null) {
                    return;
                }
//                if ((getLength() + str.length()) <= 2) {
                    char[] chars = str.toCharArray();
                    int length = 0;
                    for (int i = 0; i < chars.length; i++) {
                        if (chars[i] >= '0' && chars[i] <= '9') {
                            chars[length++] = chars[i];
                        }
                    }

                    super.insertString(offset, new String(chars, 0, length), attrSet);
//                }
            }
        });

        return textField;
    }

}
