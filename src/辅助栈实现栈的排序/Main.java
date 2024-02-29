package 辅助栈实现栈的排序;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    private Stack<Integer> assistStack;

    public Main() {
        this.assistStack = new Stack<>();
    }

    public ArrayList<Integer> sortStackByStack(Stack<Integer> datas) {
        ArrayList res = new ArrayList<>();
        while (!datas.isEmpty()) {
            int topValue = datas.pop();
            while (!assistStack.isEmpty() && topValue > assistStack.peek()) {
                datas.push(assistStack.pop());
            }
            assistStack.push(topValue);
        }
        while (!assistStack.isEmpty()) {
            datas.push(assistStack.pop());
            res.add(datas.peek());
        }
        return res;
    }

    public static void main(String[] args) {
        int[] datas = new int[]{4, 9, 0, -1, 8, 6};
        Stack stacks = new Stack();
        for (int i = 0; i < datas.length; i++) {
            stacks.push(datas[i]);
        }
        Main test = new Main();
        ArrayList res = test.sortStackByStack(stacks);
        System.out.println(res.toString()); // 输出结果  [-1, 0, 4, 6, 8, 9]  是符合预期的
    }
}
