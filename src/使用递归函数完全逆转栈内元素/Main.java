package 使用递归函数完全逆转栈内元素;

import java.sql.Statement;
import java.util.*;

public class Main {
    public void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int lastOne = pollLastElement(stack);
        reverse(stack);
        stack.push(lastOne);
    }

    public int pollLastElement(Stack<Integer> stack) { // 弹出栈底的元素
//        这是一个普通的递归函数，也就是拿出来之后再塞回去，栈和递归有着一样的原理
        int element = stack.pop();
        int lastOne;
        if (stack.isEmpty()) {
            return element; // 最后一个元素不需要重新塞入栈底
        } else {
            lastOne = pollLastElement(stack);
            stack.push(element);
        }
        return lastOne;
    }

    public static void main(String[] args) {
        Main test = new Main();
//        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] arr = new int[]{114514, 1919};
        Stack<Integer> data = new Stack<>();
        for (int e : arr) {
            data.push(e);
        }
        test.reverse(data);
        while (!data.isEmpty()) {
            int value = data.pop();
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
