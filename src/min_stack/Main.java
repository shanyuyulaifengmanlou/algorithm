package min_stack;
import java.sql.Statement;
import java.util.*;
// 最小栈题目
// 题目要求 ： pop push getMin 时间复杂度都是 o(1)
// 空间复杂度 不做要求
// 样例数据： 1 2 1 5 4 3 反过来的顺序压入
public class Main {
    private static Stack<Integer>  data;
    private static Stack<Integer> mins;
    public void NewStack() {
        this.data = new Stack<Integer>();
        this.mins = new Stack<Integer>();
    }
    public int pop () {
        if(data.isEmpty()){
            return -1;
        }
        int value = data.pop();
        if(value== mins.peek()){
            mins.pop();
        }
        return value;
    }
    public int getMin(){
        if(!mins.isEmpty()){
            return mins.peek();
        }
        return -1;
    }
    public void push (int value){
        if(!mins.isEmpty()){
            int minValue = mins.peek();
            if(minValue >=  value){
                mins.push(value);
            }
        }else{
            mins.push(value);
        }
        data.push(value);
    }
    public static void main(String[] args) {
        int []testData = new int[]{3,4,5,1,2,1};
        Main test = new Main();
        test.NewStack();
        for (int i = 0; i < testData.length; i++) {
            test.push(testData[i]);
        }
        for (int i = 0; i < testData.length; i++) {
            System.out.println("当前栈内最小值："+test.getMin());
            System.out.println("弹出元素："+test.pop());
            System.out.println("---------------------");
        }
    }
}
