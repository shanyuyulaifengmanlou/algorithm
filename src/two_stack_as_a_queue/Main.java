package two_stack_as_a_queue;

import java.sql.Statement;
import java.util.Scanner;
import java.util.Stack;

// 两个栈实现一个队列
// 要求实现 add poll peek 方法
// 其中  poll  和  peek  原理是一样的
// 题目样例数据(记得删除数据中第一个空格)
// 6
// add 3
// add 4
// peek
// poll
// add 8
// peek
// 详情：数字N 表示总共有接下来总共有这么多行的操作

//15
//add 3
//add 4
//peek
//poll
//add 8
//peek
//add 1145
//poll
//peek
//poll
//peek
//poll
//peek
//poll
//peek
public class Main {
    private Stack<Integer> push;
    private Stack<Integer> pop;

    public void newQueue() {
        this.push = new Stack<>();
        this.pop = new Stack<>();
    }

    public void add(int value) {
        this.push.push(value);
    }

    public int peek() {
        if (this.pop.isEmpty()) { // 如果已经是空的了，说明弹无可弹了，需要再 push 这个栈的栈底中拿出最下面一个元素出来
            while (!this.push.isEmpty()) {
                this.pop.push(this.push.pop());
            }
            if (!this.pop.isEmpty()) {
                return this.pop.peek();
            } else {
                return -1;// 已经没有元素了1
            }
        }
        return this.pop.peek();
    }

    public int poll() {
        if (this.pop.isEmpty()) {
            while (!this.push.isEmpty()) {
                this.pop.push(this.push.pop());
            }
            if (!this.pop.isEmpty()) {
                return this.pop.pop();
            } else {
                return -1;
            }
        }
        return this.pop.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int opsNums = sc.nextInt();
            String ll = sc.nextLine();
            String line;
            String[] arr;
            Main test = new Main();
            test.newQueue();
            System.out.println("opsNums :" + opsNums);
            for (int i = 0; i < opsNums; i++) {
                line = sc.nextLine();
                System.out.println(line);
                arr = line.split(" ");
//                System.out.println(arr[0]);
                if (arr[0].equals("peek")) {
                    System.out.println("Queue.peek is: " + test.peek());
                } else if (arr[0].equals("poll")) {
                    System.out.println("Queue.poll is: " + test.poll());
                } else if (arr[0].equals("add")) {
                    int value = Integer.parseInt(arr[1]);
                    System.out.println("Queue.add value=" + value);
                    test.add(value);
                }
            }
        }
    }
}
